package com.busap.rpc.thrift.config;

/**
 * 应用程序配置
 * 
 * @author dell
 * 
 */
public abstract class ApplicationConfig<T> {

	protected String id;
	protected String name; //应用名称
	protected String desc; //应用描述
	protected int weight; //权重 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

}
