package com.busap.rpc.thrift.config;

import java.util.List;

public abstract class AbstractInterfaceConfig<T>  extends ApplicationConfig<T>{

	 // 注册中心
    protected List<RegistryConfigBean> registries;

	public List<RegistryConfigBean> getRegistries() {
		return registries;
	}

	public void setRegistries(List<RegistryConfigBean> registries) {
		this.registries = registries;
	}

}
