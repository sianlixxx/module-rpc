
package com.busap.rpc.thrift.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

import com.busap.rpc.thrift.bean.AnnotationBean;
import com.busap.rpc.thrift.pool.ThriftClientPool;

public class ThriftNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		registerBeanDefinitionParser("annotation",
				new AnnotationBeanDefinitionParser(AnnotationBean.class));
	/*	registerBeanDefinitionParser("poolConfig",
				new ClientPoolsDefinitionParser(ThriftClientPool.class));*/
		registerBeanDefinitionParser("client-factory",
				new ClientConnectionDefinitionParser());
		registerBeanDefinitionParser("thrift-server", new SeverDefinitionParser());
	}
}
