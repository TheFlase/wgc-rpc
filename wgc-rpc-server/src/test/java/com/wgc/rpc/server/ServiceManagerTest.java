package com.wgc.rpc.server;

import com.wgc.rpc.proto.Request;
import com.wgc.rpc.proto.ServiceDescriptor;
import com.wgc.rpc.proto.utils.ReflectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;


/**
 * @Author wgc
 * @Description //TODO
 * @Date 4/4/2020
 **/
public class ServiceManagerTest {
    ServiceManager serviceManager;

    @Before
    public void before(){
        serviceManager = new ServiceManager();
        TestInterface4ServiceManager testInterface4ServiceManager = new TestInterface4ServiceManagerImpl();
        serviceManager.register(TestInterface4ServiceManager.class,testInterface4ServiceManager);
    }

    @Test
    public void register() {
        TestInterface4ServiceManager testInterface4ServiceManager = new TestInterface4ServiceManagerImpl();
        serviceManager.register(TestInterface4ServiceManager.class,testInterface4ServiceManager);
    }

    @Test
    public void lookup() {
        Method publicMethod = ReflectionUtils.getPublicMethods(TestInterface4ServiceManager.class)[0];
        ServiceDescriptor serviceDescriptor = ServiceDescriptor.from(TestInterface4ServiceManager.class, publicMethod);

        Request request = new Request();
        request.setService(serviceDescriptor);

        ServiceInstance serviceInstance = serviceManager.lookup(request);
        Assert.assertNotNull(serviceInstance);
    }
}