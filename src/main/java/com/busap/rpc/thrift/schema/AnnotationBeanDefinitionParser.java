package com.busap.rpc.thrift.schema;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;


/**
 * 
 * @Title: AnnotationBeanDefinitionParser.java
 * @Package com.alice.jecc.schema
 * @author jecc
 * @date 2017-9-13 下午3:51:42
 * @description:
 * @version v1.0
 */
public class AnnotationBeanDefinitionParser implements BeanDefinitionParser {

	private final Class<?> beanClass;

	public AnnotationBeanDefinitionParser(Class<?> beanClass) {
		this.beanClass = beanClass;
	}

	private BeanDefinition parse(Element element, ParserContext parserContext,
			Class<?> beanClass) {
		RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
		rootBeanDefinition.setBeanClass(beanClass);
		rootBeanDefinition.setLazyInit(false);

		String annotationPackage = element.getAttribute("package");
		if (!StringUtils.hasText(annotationPackage)) {
               throw new RuntimeException("alice节点必须有package属性");
         }
		rootBeanDefinition.getPropertyValues().addPropertyValue(
				"annotationPackage", annotationPackage);
		String generatedBeanName = beanClass.getName();
		parserContext.getRegistry().registerBeanDefinition(generatedBeanName,
				rootBeanDefinition);
		return rootBeanDefinition;
	}

	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		return parse(element, parserContext, beanClass);
	}

}