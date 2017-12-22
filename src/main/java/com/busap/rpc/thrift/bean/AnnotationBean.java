package com.busap.rpc.thrift.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.busap.rpc.thrift.config.ApplicationConfig;
import com.busap.rpc.thrift.protobuf.annotation.Reference;
import com.busap.rpc.thrift.protobuf.annotation.RemoteService;
import com.busap.rpc.thrift.utils.Constants;
import com.busap.rpc.thrift.utils.ReflectUtils;

/**
 * 
 * @Title: AnnotationBean.java
 * @Package com.alice.jecc.beans
 * @author jecc
 * @date 2017-9-13 下午3:47:03
 * @description:
 * @version v1.0
 */
public class AnnotationBean implements BeanDefinitionRegistryPostProcessor,
		BeanPostProcessor, ApplicationContextAware, BeanNameAware {
	private static final Logger logger = LoggerFactory.getLogger(Logger.class);
	  private final ConcurrentMap<String, ReferenceBean<?>> referenceConfigs = new ConcurrentHashMap<String, ReferenceBean<?>>();
	private String annotationPackage;
	private String[] annotationPackages;
	private String beanName;
	private ApplicationContext applicationContext;

	public String getAnnotationPackage() {
		return annotationPackage;
	}

	public void setAnnotationPackage(String annotationPackage) {
		this.annotationPackage = annotationPackage;
		this.annotationPackages = (annotationPackage == null || annotationPackage
				.length() == 0) ? null : Constants.COMMA_SPLIT_PATTERN
				.split(annotationPackage);
	}

	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {
		if (annotationPackage == null || annotationPackage.length() == 0) {
			return;
		}
		if (beanFactory instanceof BeanDefinitionRegistry) {
			try {
				// init scanner
				Class<?> scannerClass = ReflectUtils
						.forName("org.springframework.context.annotation.ClassPathBeanDefinitionScanner");
				Object scanner = scannerClass.getConstructor(
						new Class<?>[] { BeanDefinitionRegistry.class,
								boolean.class }).newInstance(
						new Object[] { (BeanDefinitionRegistry) beanFactory,
								true });
				// add filter
				Class<?> filterClass = ReflectUtils
						.forName("org.springframework.core.type.filter.AnnotationTypeFilter");
				Object filter = filterClass.getConstructor(Class.class)
						.newInstance(RemoteService.class);
				Method addIncludeFilter = scannerClass
						.getMethod(
								"addIncludeFilter",
								ReflectUtils
										.forName("org.springframework.core.type.filter.TypeFilter"));
				addIncludeFilter.invoke(scanner, filter);
				// scan packages
				String[] packages = Constants.COMMA_SPLIT_PATTERN
						.split(annotationPackage);
				Method scan = scannerClass.getMethod("scan",
						new Class<?>[] { String[].class });
				scan.invoke(scanner, new Object[] { packages });
			} catch (Throwable e) {
				// spring 2.0
			}
		}
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		if (!isMatchPackage(bean)) {
			return bean;
		}
		Method[] methods = bean.getClass().getMethods();
		for (Method method : methods) {
			String name = method.getName();
			if (name.length() > 3 && name.startsWith("set")
					&& method.getParameterTypes().length == 1
					&& Modifier.isPublic(method.getModifiers())
					&& !Modifier.isStatic(method.getModifiers())) {
				try {
					Reference reference = method.getAnnotation(Reference.class);
					if (reference != null) {
						Object value = refer(reference,
								method.getParameterTypes()[0]);
						if (value != null) {
							method.invoke(bean, new Object[] { value });
						}
					}
				} catch (Throwable e) {
					logger.error(
							"Failed to init remote service reference at method "
									+ name + " in class "
									+ bean.getClass().getName() + ", cause: "
									+ e.getMessage(), e);
				}
			}
		}
		Field[] fields = bean.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				if (!field.isAccessible()) {
					field.setAccessible(true);
				}
				Reference reference = field.getAnnotation(Reference.class);
				if (reference != null) {
					Object value = refer(reference, field.getType());
					if (value != null) {
						field.set(bean, value);
					}
				}
			} catch (Throwable e) {
				logger.error(
						"Failed to init remote service reference at filed "
								+ field.getName() + " in class "
								+ bean.getClass().getName() + ", cause: "
								+ e.getMessage(), e);
			}
		}
		return bean;
	}

	private Object refer(Reference reference, Class<?> referenceClass) {
		   String interfaceName;
	        if (!"".equals(reference.interfaceName())) {
	            interfaceName = reference.interfaceName();
	        } else if (!void.class.equals(reference.interfaceClass())) {
	            interfaceName = reference.interfaceClass().getName();
	        } else if (referenceClass.isInterface()) {
	            interfaceName = referenceClass.getName();
	        } else {
	            throw new IllegalStateException("The @Reference undefined interfaceClass or interfaceName, and the property type " + referenceClass.getName() + " is not a interface.");
	        }
	        String key =interfaceName + ":" + reference.version();
	        ReferenceBean<?> referenceConfig = referenceConfigs.get(key);
	        if (referenceConfig == null) {
	            referenceConfig = new ReferenceBean<Object>(reference);
	            if (void.class.equals(reference.interfaceClass())
	                    && "".equals(reference.interfaceName())
	                    && referenceClass.isInterface()) {
	                referenceConfig.setInterfaceClass(referenceClass);
	            }
	            if (applicationContext != null) {
	                referenceConfig.setApplicationContext(applicationContext);
	                try {
	              //      referenceConfig.afterPropertiesSet();
	                } catch (RuntimeException e) {
	                    throw (RuntimeException) e;
	                } catch (Exception e) {
	                    throw new IllegalStateException(e.getMessage(), e);
	                }
	            }
	            referenceConfigs.putIfAbsent(key, referenceConfig);
	            referenceConfig = referenceConfigs.get(key);
	        }
	        return referenceConfig.get();
	}

	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		if (!isMatchPackage(bean)) {
			return bean;
		}
		RemoteService service = bean.getClass().getAnnotation(
				RemoteService.class);
		if (service != null) {
			ThriftServiceBean<Object> serviceBean = new ThriftServiceBean<Object>(
					service);
			if (void.class.equals(service.interfaceClass())
					&& "".equals(service.interfaceName())) {
				if (bean.getClass().getInterfaces().length > 0) {
					serviceBean
							.setInterface(bean.getClass().getInterfaces()[0]);
				} else {
					throw new IllegalStateException(
							"Failed to export remote service class "
									+ bean.getClass().getName()
									+ ", cause: The @Service undefined interfaceClass or interfaceName, and the service class unimplemented any interfaces.");
				}
			}

			if (applicationContext != null) {
				serviceBean.setApplicationContext(applicationContext);
				try {
					serviceBean.afterPropertiesSet();
				} catch (RuntimeException e) {
					throw (RuntimeException) e;
				} catch (Exception e) {
					throw new IllegalStateException(e.getMessage(), e);
				}
			}
			serviceBean.setRef(bean);
		}
		// else
		// {
		// throw new
		// ThriftException(String.format("Missing annotation(RemoteService) in '%s'.",
		// o.getClass().getName()));
		// }
		return bean;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	@Override
	public void postProcessBeanDefinitionRegistry(
			BeanDefinitionRegistry registry) throws BeansException {
		if (annotationPackage == null || annotationPackage.length() == 0) {
			return;
		}
		if (registry instanceof BeanDefinitionRegistry) {
			try {
				// init scanner
				Class<?> scannerClass = ReflectUtils
						.forName("org.springframework.context.annotation.ClassPathBeanDefinitionScanner");
				Object scanner = scannerClass
						.getConstructor(
								new Class<?>[] { BeanDefinitionRegistry.class,
										boolean.class })
						.newInstance(
								new Object[] {
										(BeanDefinitionRegistry) registry, true });
				// add filter
				Class<?> filterClass = ReflectUtils
						.forName("org.springframework.core.type.filter.AnnotationTypeFilter");
				Object filter = filterClass.getConstructor(Class.class)
						.newInstance(RemoteService.class);
				Method addIncludeFilter = scannerClass
						.getMethod(
								"addIncludeFilter",
								ReflectUtils
										.forName("org.springframework.core.type.filter.TypeFilter"));
				addIncludeFilter.invoke(scanner, filter);
				// scan packages
				String[] packages = Constants.COMMA_SPLIT_PATTERN
						.split(annotationPackage);
				Method scan = scannerClass.getMethod("scan",
						new Class<?>[] { String[].class });
				Object object = scan.invoke(scanner, new Object[] { packages });
				System.out.println("scaner class"
						+ object.getClass().getSimpleName());
			} catch (Throwable e) {
				e.getStackTrace();
			}
		}
	}

	private boolean isMatchPackage(Object bean) {
		if (annotationPackages == null || annotationPackages.length == 0) {
			return true;
		}
		String beanClassName = bean.getClass().getName();
		for (String pkg : annotationPackages) {
			if (beanClassName.startsWith(pkg)) {
				return true;
			}
		}
		return false;
	}
}
