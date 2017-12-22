package com.busap.rpc.thrift.beans.context.annotation;

import static org.springframework.context.annotation.AnnotationConfigUtils.registerAnnotationConfigProcessors;

import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
public class CustomClassPathBeanDefinitionScanner extends
		ClassPathBeanDefinitionScanner {

	public CustomClassPathBeanDefinitionScanner(
			BeanDefinitionRegistry registry, boolean useDefaultFilters,
			Environment environment, ResourceLoader resourceLoader) {

		super(registry, useDefaultFilters, environment);
		registerAnnotationConfigProcessors(registry);

	}

	public CustomClassPathBeanDefinitionScanner(
			BeanDefinitionRegistry registry, Environment environment,
			ResourceLoader resourceLoader) {

		this(registry, false, environment, resourceLoader);

	}

	public Set<BeanDefinitionHolder> doScan(String... basePackages) {
		return super.doScan(basePackages);
	}

}