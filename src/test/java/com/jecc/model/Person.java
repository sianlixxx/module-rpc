/*
 * 文件名：Person.java
 * 版权：Copyright 2007-2016 成长GPS Tech. Co. Ltd. All Rights Reserved. 
 * 描述： Person.java
 * 修改人：xuanyuan
 * 修改时间：2016年10月18日
 * 修改内容：新增
 */
package com.jecc.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.busap.rpc.thrift.common.FieldType;
import com.busap.rpc.thrift.protobuf.annotation.RemoteSMember;
import com.busap.rpc.thrift.protobuf.annotation.RemoteSModel;

/**
 * TODO 添加类的一句话简单描述.
 * <p>
 * TODO 详细描述
 * <p>
 * TODO 示例代码
 * 
 * <pre>
 * </pre>
 * 
 * @author xuanyuan
 */
@RemoteSModel
public class Person {
    @RemoteSMember(order=1,fieldType=FieldType.INT32)
    private int age;

    @RemoteSMember(order=2,fieldType=FieldType.STRING)
    private String name;

    @RemoteSMember(order=3,fieldType=FieldType.DOUBLE)
    private BigDecimal money;

    @RemoteSMember(order=4,fieldType=FieldType.STRUCT)
    private Person son;

    @RemoteSMember(order=3,fieldType=FieldType.LIST)
    private List<Person> sonlist;

    @RemoteSMember(order=3,fieldType=FieldType.MAP)
    private Map<String, Person> map;

    @RemoteSMember(order=3,fieldType=FieldType.SET)
    private Set<Person> set;

    @RemoteSMember(order=3,fieldType=FieldType.DOUBLE)
    private Double dou;

    @RemoteSMember(order=3,fieldType=FieldType.DOUBLE)
    private Date date;

    /**
     * 设置age.
     * 
     * @return 返回age
     */
    public int getAge() {
        return age;
    }

    /**
     * 获取age.
     * 
     * @param age
     *            要设置的age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 设置name.
     * 
     * @return 返回name
     */
    public String getName() {
        return name;
    }

    /**
     * 获取name.
     * 
     * @param name
     *            要设置的name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 设置son.
     * 
     * @return 返回son
     */
    public Person getSon() {
        return son;
    }

    /**
     * 获取son.
     * 
     * @param son
     *            要设置的son
     */
    public void setSon(Person son) {
        this.son = son;
    }

    /**
     * 设置sonlist.
     * 
     * @return 返回sonlist
     */
    public List<Person> getSonlist() {
        return sonlist;
    }

    /**
     * 获取sonlist.
     * 
     * @param sonlist
     *            要设置的sonlist
     */
    public void setSonlist(List<Person> sonlist) {
        this.sonlist = sonlist;
    }

    /**
     * 设置map.
     * 
     * @return 返回map
     */
    public Map<String, Person> getMap() {
        return map;
    }

    /**
     * 获取map.
     * 
     * @param map
     *            要设置的map
     */
    public void setMap(Map<String, Person> map) {
        this.map = map;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Person [age=%s", this.age));
        if (name != null) {
            sb.append(String.format("，name=%s", this.name));
        }
        if (money != null) {
            sb.append(String.format("，money=%s", this.money));
        }
        if (dou != null) {
            sb.append(String.format("，dou=%s", this.dou));
        }
        if (date != null) {
            sb.append(String.format("，date=%s", this.date));
        }
        if (son != null) {
            sb.append(String.format("，son=%s", this.son));
        }
        if (sonlist != null) {
            sb.append("，sonlist=[");
            for (Person person : this.sonlist) {
                sb.append(person.toString());
            }
            sb.append("]");
        }
        if (map != null) {
            sb.append("，map=[");
            for (Map.Entry<String, Person> entry : map.entrySet()) {
                sb.append(String.format("key=%s-value=%s  ", entry.getKey(), entry.getValue()));
            }
            sb.append("]");
        }
        if (set != null) {
            sb.append("，set=[");
            for (Person person : this.set) {
                sb.append(person.toString());
            }
            sb.append("]");
        }

        sb.append("]");
        return sb.toString();
    }

    /**
     * 设置set.
     * 
     * @return 返回set
     */
    public Set<Person> getSet() {
        return set;
    }

    /**
     * 获取set.
     * 
     * @param set
     *            要设置的set
     */
    public void setSet(Set<Person> set) {
        this.set = set;
    }

    /**
     * 设置money.
     * 
     * @return 返回money
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * 获取money.
     * 
     * @param money
     *            要设置的money
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * 设置dou.
     * 
     * @return 返回dou
     */
    public Double getDou() {
        return dou;
    }

    /**
     * 获取dou.
     * 
     * @param dou
     *            要设置的dou
     */
    public void setDou(Double dou) {
        this.dou = dou;
    }

    /**
     * 设置date.
     * 
     * @return 返回date
     */
    public Date getDate() {
        return date;
    }

    /**
     * 获取date.
     * 
     * @param date
     *            要设置的date
     */
    public void setDate(Date date) {
        this.date = date;
    }
}