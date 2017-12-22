/*
 * 文件名：Test.java
 * 版权：Copyright 2007-2016 成长GPS Tech. Co. Ltd. All Rights Reserved. 
 * 描述： Test.java
 * 修改人：xuanyuan
 * 修改时间：2016年8月21日
 * 修改内容：新增
 */
package com.jecc.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.busap.rpc.thrift.protobuf.annotation.RemoteService;
import com.busap.rpc.thrift.protobuf.annotation.RemoteMethod;


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
@RemoteService(interfaceName = "test",interfaceClass=PersonService.class)
public interface PersonService {

    @RemoteMethod
    String testParam(Person person, List<Person> list, Map<String, Person> map, Set<Person> set);

    @RemoteMethod
    String test(int age, String name, Person person);

    @RemoteMethod
    String test2(Person person1, Person person2);

    @RemoteMethod
    List<Person> testList(List<Person> list);

    @RemoteMethod
    Map<String, Person> testMap(Map<String, Person> map);

    @RemoteMethod
    Set<Person> testSet(Set<Person> set);

    @RemoteMethod
    String testParam2(BigDecimal big, Date date, UUID uuid, Double dob, Boolean bool);

    /**
     * 测试非注解类调用Spring的注解类(普通类调用Mapper)
     * 
     * @param dob
     * @param bool
     * @return
     */
    @RemoteMethod
    String testSpring(String test);
}
