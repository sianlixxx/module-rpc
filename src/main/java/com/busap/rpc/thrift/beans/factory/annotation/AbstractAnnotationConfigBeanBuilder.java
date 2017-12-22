package com.busap.rpc.thrift.beans.factory.annotation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import com.busap.rpc.thrift.config.AbstractInterfaceConfig;
import com.busap.rpc.thrift.config.ApplicationConfig;
import com.busap.rpc.thrift.config.RegistryConfigBean;
import com.busap.rpc.thrift.utils.BeanFactoryUtils;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Abstract Configurable {@link Annotation} Bean Builder
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 2.5.7
 */
abstract class AbstractAnnotationConfigBeanBuilder<A extends Annotation, B extends AbstractInterfaceConfig> {

    protected final Log logger = LogFactory.getLog(getClass());

    protected final A annotation;

    protected final ApplicationContext applicationContext;

    protected final ClassLoader classLoader;

    protected Object bean;

    protected Class<?> interfaceClass;

    protected AbstractAnnotationConfigBeanBuilder(A annotation, ClassLoader classLoader,
                                                  ApplicationContext applicationContext) {
        Assert.notNull(annotation, "The Annotation must not be null!");
        Assert.notNull(classLoader, "The ClassLoader must not be null!");
        Assert.notNull(applicationContext, "The ApplicationContext must not be null!");
        this.annotation = annotation;
        this.applicationContext = applicationContext;
        this.classLoader = classLoader;

    }

    /**
     * Build {@link B}
     *
     * @return non-null
     * @throws Exception
     */
    public final B build() throws Exception {

        checkDependencies();

        B bean = doBuild();

        configureBean(bean);

        if (logger.isInfoEnabled()) {
            logger.info(bean + " has been built.");
        }

        return bean;

    }

    private void checkDependencies() {

    }

    /**
     * Builds {@link B Bean}
     *
     * @return {@link B Bean}
     */
    protected abstract B doBuild();


    protected void configureBean(B bean) throws Exception {

        preConfigureBean(annotation, bean);

        configureRegistryConfigs(bean);

        postConfigureBean(annotation, bean);

    }

    protected abstract void preConfigureBean(A annotation, B bean) throws Exception;


    private void configureRegistryConfigs(B bean) {

        String[] registryConfigBeanIds = resolveRegistryConfigBeanNames(annotation);

        List<RegistryConfigBean> registryConfigs =BeanFactoryUtils.getBeans(applicationContext, registryConfigBeanIds, RegistryConfigBean.class);

        bean.setRegistries(registryConfigs);

    }


    /**
     * Resolves the bean name of {@link ApplicationConfig}
     *
     * @param annotation {@link A}
     * @return
     */
    protected abstract String resolveApplicationConfigBeanName(A annotation);


    /**
     * Resolves the bean ids of {@link com.alibaba.dubbo.config.RegistryConfig}
     *
     * @param annotation {@link A}
     * @return non-empty array
     */
    protected abstract String[] resolveRegistryConfigBeanNames(A annotation);

    /**
     * Configures Bean
     *
     * @param annotation
     * @param bean
     */
    protected abstract void postConfigureBean(A annotation, B bean) throws Exception;


    public <T extends AbstractAnnotationConfigBeanBuilder<A, B>> T bean(Object bean) {
        this.bean = bean;
        return (T) this;
    }

    public <T extends AbstractAnnotationConfigBeanBuilder<A, B>> T interfaceClass(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
        return (T) this;
    }

}
