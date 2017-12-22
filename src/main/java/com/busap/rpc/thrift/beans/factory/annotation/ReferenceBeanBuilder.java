package com.busap.rpc.thrift.beans.factory.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import com.busap.rpc.thrift.bean.ReferenceBean;
import com.busap.rpc.thrift.protobuf.annotation.Reference;
public class  ReferenceBeanBuilder extends AbstractAnnotationConfigBeanBuilder<Reference, ReferenceBean> {


    private ReferenceBeanBuilder(Reference annotation, ClassLoader classLoader, ApplicationContext applicationContext) {
        super(annotation, classLoader, applicationContext);
    }

    private void configureInterface(Reference reference, ReferenceBean referenceBean) {

        Class<?> interfaceClass = reference.interfaceClass();

        if (void.class.equals(interfaceClass)) {

            interfaceClass = null;

            String interfaceClassName = reference.interfaceName();

            if (StringUtils.hasText(interfaceClassName)) {
                if (ClassUtils.isPresent(interfaceClassName, classLoader)) {
                    interfaceClass = ClassUtils.resolveClassName(interfaceClassName, classLoader);
                }
            }

        }

        if (interfaceClass == null) {
            interfaceClass = this.interfaceClass;
        }

        Assert.isTrue(interfaceClass.isInterface(),
                "The class of field or method that was annotated @Reference is not an interface!");

        referenceBean.setInterfaceClass(interfaceClass);

    }



    @Override
    protected ReferenceBean doBuild() {
        return new ReferenceBean<Object>(annotation);
    }

    @Override
    protected void preConfigureBean(Reference annotation, ReferenceBean bean) {
        Assert.notNull(interfaceClass, "The interface class must set first!");
    }



    @Override
    protected String resolveApplicationConfigBeanName(Reference annotation) {
        return annotation.application();
    }

    @Override
    protected String[] resolveRegistryConfigBeanNames(Reference annotation) {
        return annotation.registry();
    }

  

    @Override
    protected void postConfigureBean(Reference annotation, ReferenceBean bean) throws Exception {

    	bean.setApplicationContext(applicationContext);

        configureInterface(annotation, bean);

  //      bean.afterPropertiesSet();

    }

    public static ReferenceBeanBuilder create(Reference annotation, ClassLoader classLoader,
                                              ApplicationContext applicationContext) {
        return new ReferenceBeanBuilder(annotation, classLoader, applicationContext);
    }


}