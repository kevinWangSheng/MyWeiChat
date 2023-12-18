package com.kevin.infrustruct.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wang
 * @create 2023-12-18-21:59
 */
public class BeanUtil {
    private static final Map<String, Object> beanMap = new ConcurrentHashMap<>();

    public static synchronized void putBean(String name,Object bean){
        beanMap.put(name,bean);
    }
    
    public static <T> T getBean(String name,Class<T> t){
        return (T) beanMap.get(name);
    }
}
