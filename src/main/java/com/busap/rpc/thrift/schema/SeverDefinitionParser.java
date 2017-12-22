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
import org.w3c.dom.Element;

import com.busap.rpc.thrift.bean.ThriftServerBean;
import com.busap.rpc.thrift.exception.ThriftException;

public class SeverDefinitionParser extends AbstractSingleBeanDefinitionParser  {

	@Override
	protected void doParse(Element element, ParserContext parserContext,
			BeanDefinitionBuilder builder) {
         String port = element.getAttribute("port");
         if (!StringUtils.hasText(port)) {
             throw new ThriftException("service节点必须有port属性");
         }
         builder.addPropertyValue("port", port);
         // 这些属性一定会有值（有默认值）
         builder.addPropertyValue("minWorkThreads", element.getAttribute("minWorkThreads"));
         builder.addPropertyValue("maxWorkThreads", element.getAttribute("maxWorkThreads"));
         builder.addPropertyValue("clientTimeout", element.getAttribute("clientTimeout"));
         builder.addPropertyValue("requestTimeout", element.getAttribute("requestTimeout"));
  
	}

	@Override
	protected Class<?> getBeanClass(Element element) {
		return ThriftServerBean.class;
	}
}
