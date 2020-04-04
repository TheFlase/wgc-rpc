package com.wgc.rpc.proto.utils;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @Author wgc
 * @Description //TODO
 * @Date 4/3/2020
 **/
public class ReflectionUtilsTest {

    @Test
    public void newInstance() {
        TestReflectionUtils testReflectionUtils = ReflectionUtils.newInstance(TestReflectionUtils.class);
        Assert.assertNotNull(testReflectionUtils);
    }

    @Test
    public void getPublicMethods() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestReflectionUtils.class);
        Assert.assertEquals(1,methods.length);
        String publicMethodName = methods[0].getName();
        Assert.assertEquals("c",publicMethodName);
    }

    @Test
    public void invoke() {
        Method[] publicMethods = ReflectionUtils.getPublicMethods(TestReflectionUtils.class);
        TestReflectionUtils testClazz = new TestReflectionUtils();
        Object invokeResult = ReflectionUtils.invoke(testClazz, publicMethods[0]);
        Assert.assertEquals("c",invokeResult);
    }
}
