package com.busap.rpc.thrift.utils;

import java.util.regex.Pattern;

/**
 * 
 * @Title: Constants.java
 * @Package com.alice.jecc.utils
 * @author jecc
 * @date 2017-9-13 下午3:50:17
 * @description:
 * @version v1.0
 */
public class Constants {
	public static final Pattern COMMA_SPLIT_PATTERN = Pattern
			.compile("\\s*[,]+\\s*");
	
    /**
     * @param values
     * @param value
     * @return contains
     */
    public static boolean isContains(String[] values, String value) {
        if (value != null && value.length() > 0 && values != null && values.length > 0) {
            for (String v : values) {
                if (value.equals(v)) {
                    return true;
                }
            }
        }
        return false;
    }

}
