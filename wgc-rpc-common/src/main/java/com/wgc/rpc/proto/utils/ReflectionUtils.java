package com.wgc.rpc.proto.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author wgc
 * @Description 反射工具类
 * @Date 4/3/2020
 **/
public class ReflectionUtils {
    /**
     * 根据claszz创建对象
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T newInstance(Class<T> clazz){
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 获取某个类的公有方法
     * @param clazz
     * @return
     */
    public static Method[] getPublicMethods(Class clazz){
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> pMethosList = new ArrayList<Method>();
        for(Method item:methods){
            if(Modifier.isPublic(item.getModifiers())){
                pMethosList.add(item);
            }
        }
        return pMethosList.toArray(new Method[0]);
    }

    /**
     * 调用指定对象的指定方法
     * @param obj
     * @param method
     * @param args
     * @return
     */
    public static Object invoke(Object obj,Method method,Object...args){
        try {
            return method.invoke(obj,args);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        Integer[] integers = list.toArray(new Integer[1]);
        System.out.println(Arrays.toString(integers));
    }
}
