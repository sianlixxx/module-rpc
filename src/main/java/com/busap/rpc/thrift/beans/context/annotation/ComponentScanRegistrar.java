package com.busap.rpc.thrift.beans.context.annotation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.*;

import com.busap.rpc.thrift.bean.ThriftServiceBean;
import com.busap.rpc.thrift.beans.factory.annotation.ReferenceAnnotationBeanPostProcessor;
import com.busap.rpc.thrift.protobuf.annotation.RemoteService;

import java.util.*;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.rootBeanDefinition;
import static org.springframework.beans.factory.support.BeanDefinitionReaderUtils.registerWithGeneratedName;
import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;
import static org.springframework.util.ClassUtils.resolveClassName;
public class ComponentScanRegistrar implements ImportBeanDefinitionRegistrar , ResourceLoaderAware,
EnvironmentAware, BeanClassLoaderAware {  

    private ResourceLoader resourceLoader;

    private Environment environment;

    private ClassLoader classLoader;
    protected String BEAN_NAME = "componentScanRegistrar";  
  
    public void dynamicConfiguration() throws Exception {  
    }  
      
    @Override  
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {  
//        if (!registry.containsBeanDefinition(BEAN_NAME)) {  
//            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();  
//            beanDefinition.setBeanClass(Myservice.class);  
//            beanDefinition.setSynthetic(true);   
//            registry.registerBeanDefinition(BEAN_NAME, beanDefinition);  
//        } 
    	
        Set<String> packagesToScan = getPackagesToScan(importingClassMetadata);

        registerServiceBeans(packagesToScan, registry);

        registerReferenceAnnotationBeanPostProcessor(registry);
    }





    /**
     * Registers Beans whose classes was annotated {@link Service}
     *
     * @param packagesToScan The base packages to scan
     * @param registry       {@link BeanDefinitionRegistry}
     */
    private void registerServiceBeans(Set<String> packagesToScan, BeanDefinitionRegistry registry) {

    	CustomClassPathBeanDefinitionScanner customClassPathBeanDefinitionScanner =
                new CustomClassPathBeanDefinitionScanner(registry, environment, resourceLoader);

    	customClassPathBeanDefinitionScanner.addIncludeFilter(new AnnotationTypeFilter(RemoteService.class));

        for (String packageToScan : packagesToScan) {

            Set<BeanDefinitionHolder> beanDefinitionHolders = customClassPathBeanDefinitionScanner.doScan(packageToScan);

            for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
                registerServiceBean(beanDefinitionHolder, registry);
            }

       
        }

    }

    private Class<?> resolveClass(BeanDefinitionHolder beanDefinitionHolder) {

        BeanDefinition beanDefinition = beanDefinitionHolder.getBeanDefinition();

        return resolveClass(beanDefinition);

    }

    private Class<?> resolveClass(BeanDefinition beanDefinition) {

        String beanClassName = beanDefinition.getBeanClassName();

        return resolveClassName(beanClassName, classLoader);

    }

    /**
     * Registers {@link ServiceBean} from new annotated {@link Service} {@link BeanDefinition}
     *
     * @param beanDefinitionHolder
     * @param registry
     * @see ServiceBean
     * @see BeanDefinition
     */
    private void registerServiceBean(BeanDefinitionHolder beanDefinitionHolder, BeanDefinitionRegistry registry) {

        Class<?> beanClass = resolveClass(beanDefinitionHolder);

        RemoteService service = findAnnotation(beanClass, RemoteService.class);

        Class<?> interfaceClass = resolveServiceInterfaceClass(beanClass, service);

        String beanName = beanDefinitionHolder.getBeanName();

        AbstractBeanDefinition serviceBeanDefinition = buildServiceBeanDefinition(service, interfaceClass, beanName);

        registerWithGeneratedName(serviceBeanDefinition, registry);

    }

    private ManagedList<RuntimeBeanReference> toRuntimeBeanReferences(String... beanNames) {

        ManagedList<RuntimeBeanReference> runtimeBeanReferences = new ManagedList<RuntimeBeanReference>();

        if (!ObjectUtils.isEmpty(beanNames)) {

            for (String beanName : beanNames) {
                runtimeBeanReferences.add(new RuntimeBeanReference(beanName));
            }

        }

        return runtimeBeanReferences;

    }

    private AbstractBeanDefinition buildServiceBeanDefinition(RemoteService service, Class<?> interfaceClass,
                                                              String annotatedServiceBeanName) {

        BeanDefinitionBuilder builder = rootBeanDefinition(ThriftServiceBean.class)
                .addConstructorArgValue(service)
                // References "ref" property to annotated-@Service Bean
                .addPropertyReference("ref", annotatedServiceBeanName)
                .addPropertyValue("interfaceClass", interfaceClass);

        return builder.getBeanDefinition();

    }

    private Class<?> resolveServiceInterfaceClass(Class<?> annotatedServiceBeanClass, RemoteService service) {

        Class<?> interfaceClass = service.interfaceClass();

        if (void.class.equals(interfaceClass)) {

            interfaceClass = null;

            String interfaceClassName = service.interfaceName();

            if (StringUtils.hasText(interfaceClassName)) {
                if (ClassUtils.isPresent(interfaceClassName, classLoader)) {
                    interfaceClass = resolveClassName(interfaceClassName, classLoader);
                }
            }

        }

        if (interfaceClass == null) {

            Class<?>[] allInterfaces = annotatedServiceBeanClass.getInterfaces();

            if (allInterfaces.length > 0) {
                interfaceClass = allInterfaces[0];
            }

        }

        Assert.notNull(interfaceClass,
                "@Service interfaceClass() or interfaceName() or interface class must be present!");

        Assert.isTrue(interfaceClass.isInterface(),
                "The type that was annotated @Service is not an interface!");

        return interfaceClass;
    }

    /**
     * Registers {@link ReferenceAnnotationBeanPostProcessor} into {@link BeanFactory}
     *
     * @param registry {@link BeanDefinitionRegistry}
     */
    private void registerReferenceAnnotationBeanPostProcessor(BeanDefinitionRegistry registry) {

        // Register @Reference Annotation Bean Processor
        BeanRegistrar.registerInfrastructureBean(registry,
                ReferenceAnnotationBeanPostProcessor.BEAN_NAME, ReferenceAnnotationBeanPostProcessor.class);
//
    }

    private Set<String> getPackagesToScan(AnnotationMetadata metadata) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(
                metadata.getAnnotationAttributes(JeccComponentScan.class.getName()));
        String[] basePackages = attributes.getStringArray("basePackages");
        Class<?>[] basePackageClasses = attributes.getClassArray("basePackageClasses");
        Set<String> packagesToScan = new LinkedHashSet<String>();
        packagesToScan.addAll(Arrays.asList(basePackages));
        for (Class<?> basePackageClass : basePackageClasses) {
            packagesToScan.add(ClassUtils.getPackageName(basePackageClass));
        }
        if (packagesToScan.isEmpty()) {
            return Collections.singleton(ClassUtils.getPackageName(metadata.getClassName()));
        }
        return packagesToScan;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    } 
}  