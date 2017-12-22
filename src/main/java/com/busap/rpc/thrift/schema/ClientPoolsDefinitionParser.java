/*
 * 文件名：ServiceConfigBeanDefinitionParser.java
 * 版权：Copyright 2007-2016 成长GPS Tech. Co. Ltd. All Rights Reserved. 
 * 描述： ServiceConfigBeanDefinitionParser.java
 * 修改人：xuanyuan
 * 修改时间：2016年8月19日
 * 修改内容：新增
 */
package com.busap.rpc.thrift.schema;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

import com.busap.rpc.thrift.exception.ThriftException;
import com.busap.rpc.thrift.utils.PackagesToScanUtil;

public class ClientPoolsDefinitionParser extends AbstractBeanDefinitionParser {

    private Class<?> beanClass;

    public ClientPoolsDefinitionParser(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(this.beanClass);
        beanDefinition.setLazyInit(false);
            // 将配置元素转为Bean
            String host = element.getAttribute("host");
            if (!StringUtils.hasText(host)) {
					throw new ThriftException("client节点必须有host属性");
            }
            beanDefinition.getPropertyValues().addPropertyValue("host", host);
            String port = element.getAttribute("port");
            if (!StringUtils.hasText(port)) {
                throw new ThriftException("client节点必须有port属性");
            }
            beanDefinition.getPropertyValues().addPropertyValue("port", port);
//            String protocol = element.getAttribute("protocol");
//         	String attributeValue = element.getAttribute("protocol");
//        	Conventions.attributeNameToPropertyName("protocol");
//            if (!StringUtils.hasText(protocol)) {
//                throw new ThriftException("client节点必须有protocol属性");
//            }
//            beanDefinition.getPropertyValues().addPropertyValue("protocol", new TypedStringValue(attributeValue));
            String generatedBeanName =PackagesToScanUtil.getFirstSmall(beanClass.getSimpleName());
            beanDefinition.getPropertyValues().addPropertyValue("maxIdle", element.getAttribute("maxIdle"));
            beanDefinition.getPropertyValues().addPropertyValue("maxTotal", element.getAttribute("maxTotal"));
            beanDefinition.getPropertyValues().addPropertyValue("socketTimeout", element.getAttribute("socketTimeout"));
            beanDefinition.getPropertyValues().addPropertyValue("maxWaitMillis", element.getAttribute("maxWaitMillis"));
            beanDefinition.getPropertyValues().addPropertyValue("minEvictableIdleTimeMillis", element.getAttribute("minEvictableIdleTimeMillis"));
            beanDefinition.getPropertyValues().addPropertyValue("testOnReturn", element.getAttribute("testOnReturn"));
            beanDefinition.getPropertyValues().addPropertyValue("testOnBorrow", element.getAttribute("testOnBorrow"));
            beanDefinition.getPropertyValues().addPropertyValue("testWhileIdle", element.getAttribute("testWhileIdle"));
            beanDefinition.getPropertyValues().addPropertyValue("timeBetweenEvictionRunsMillis", element.getAttribute("timeBetweenEvictionRunsMillis"));
            if (!parserContext.getRegistry().containsBeanDefinition(generatedBeanName)) {
                parserContext.getRegistry().registerBeanDefinition(generatedBeanName, beanDefinition);
            }
        return beanDefinition;
    }
}
