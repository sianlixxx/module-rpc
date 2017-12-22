/*
 * 文件名：ServiceConfigBeanDefinitionParser.java
 * 版权：Copyright 2007-2016 成长GPS Tech. Co. Ltd. All Rights Reserved. 
 * 描述： ServiceConfigBeanDefinitionParser.java
 * 修改人：xuanyuan
 * 修改时间：2016年8月19日
 * 修改内容：新增
 */
package com.busap.rpc.thrift.schema;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

import com.busap.rpc.thrift.exception.ThriftException;
import com.busap.rpc.thrift.pool.ConnectionProvider;

public class ClientConnectionDefinitionParser extends AbstractSingleBeanDefinitionParser {
	private static final String HOST = "host";
	private static final String PORT = "port";
	private static final String AUTO_STARTUP_ATTRIBUTE = "auto-startup";

 
	@Override
	protected void doParse(Element element, ParserContext parserContext,
			BeanDefinitionBuilder builder) {
		String attributeValue;
		attributeValue = element.getAttribute(AUTO_STARTUP_ATTRIBUTE);
		if (StringUtils.hasText(attributeValue)) {
			builder.addPropertyValue("autoStartup", attributeValue);
		}

        Element poolConfigElem = DomUtils.getChildElementByTagName(element, "poolConfig");
        if(poolConfigElem!=null){
        // 将配置元素转为Bean
        String host = poolConfigElem.getAttribute(HOST);
        if (!StringUtils.hasText(host)) {
				throw new ThriftException("client节点必须有host属性");
        }
        builder.addPropertyValue(HOST, host);
        String port = poolConfigElem.getAttribute(PORT);
        if (!StringUtils.hasText(port)) {
            throw new ThriftException("client节点必须有port属性");
        }
        builder.addPropertyValue(PORT, port);
        builder.addPropertyValue("maxIdle", poolConfigElem.getAttribute("maxIdle"));
        builder.addPropertyValue("maxTotal", poolConfigElem.getAttribute("maxTotal"));
        builder.addPropertyValue("socketTimeout", poolConfigElem.getAttribute("socketTimeout"));
        builder.addPropertyValue("maxWaitMillis", poolConfigElem.getAttribute("maxWaitMillis"));
        builder.addPropertyValue("minEvictableIdleTimeMillis", poolConfigElem.getAttribute("minEvictableIdleTimeMillis"));
        builder.addPropertyValue("testOnReturn", poolConfigElem.getAttribute("testOnReturn"));
        builder.addPropertyValue("testOnBorrow", poolConfigElem.getAttribute("testOnBorrow"));
        builder.addPropertyValue("testWhileIdle", poolConfigElem.getAttribute("testWhileIdle"));
        builder.addPropertyValue("timeBetweenEvictionRunsMillis", poolConfigElem.getAttribute("timeBetweenEvictionRunsMillis"));
        }
	}


	@Override
	protected Class<?> getBeanClass(Element element) {
		 return ConnectionProvider.class;
	}

 
}
