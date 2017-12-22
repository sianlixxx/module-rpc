package com.busap.rpc.thrift.utils;
import static org.springframework.beans.factory.BeanFactoryUtils.beanNamesForTypeIncludingAncestors;
import static org.springframework.beans.factory.BeanFactoryUtils.beanOfTypeIncludingAncestors;
import static org.springframework.beans.factory.BeanFactoryUtils.beansOfTypeIncludingAncestors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.ListableBeanFactory;
public class BeanFactoryUtils {

	 public static <T> T getOptionalBean(ListableBeanFactory beanFactory, String beanName, Class<T> beanType) {

	        String[] allBeanNames = beanNamesForTypeIncludingAncestors(beanFactory, beanType);

	        if (!Constants.isContains(allBeanNames, beanName)) {
	            return null;
	        }

	        Map<String, T> beansOfType = beansOfTypeIncludingAncestors(beanFactory, beanType);

	        return beansOfType.get(beanName);

	    }



	    public static <T> List<T> getBeans(ListableBeanFactory beanFactory, String[] beanNames, Class<T> beanType) {

	        String[] allBeanNames = beanNamesForTypeIncludingAncestors(beanFactory, beanType);

	        List<T> beans = new ArrayList<T>(beanNames.length);

	        for (String beanName : beanNames) {
	            if (Constants.isContains(allBeanNames, beanName)) {
	                beans.add(beanOfTypeIncludingAncestors(beanFactory, beanType));
	            }
	        }

	        return Collections.unmodifiableList(beans);

	    }

}
