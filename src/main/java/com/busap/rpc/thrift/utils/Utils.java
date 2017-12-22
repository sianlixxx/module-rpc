package com.busap.rpc.thrift.utils;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.lang3.StringUtils;

import com.busap.rpc.thrift.common.SerializerMode;
import com.busap.rpc.thrift.protobuf.annotation.RemoteService;


public final class Utils {
    private static String version;

    static {
        version = "0.6";
    }

    public static String getVersion() {
        return version;
    }

    public static String getServiceName(Class<? extends Object> contractType) {
        String serviceName = StringUtils.EMPTY;

        RemoteService contractAnnotation = contractType.getAnnotation(RemoteService.class);
        if (contractAnnotation != null) {
            serviceName = contractAnnotation.interfaceName();
        }

        if (serviceName == null || serviceName.isEmpty()) {
            serviceName = contractType.getName();
        }

        return serviceName;
    }

    public static SerializerMode getSerializerMode(Class<? extends Object> targetType) {
        return SerializerMode.Thrift;

    }

    public static Integer getPid() {
        //获取进程的PID
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        String name = runtime.getName(); // format: "pid@hostname"
        try {
            return Integer.parseInt(name.substring(0, name.indexOf('@')));
        } catch (Exception e) {
            return -1;
        }
    }
    
    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return StringUtils.EMPTY;
        }
    }
}
