package com.wgc.rpc.server;

import com.wgc.rpc.proto.Request;
import com.wgc.rpc.proto.ServiceDescriptor;
import com.wgc.rpc.proto.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author wgc
 * @Description 管理rpc暴露的服务
 * @Date 4/4/2020
 **/
@Slf4j
public class ServiceManager {
    private Map<ServiceDescriptor,ServiceInstance> services;

    public ServiceManager(){
        this.services = new ConcurrentHashMap<>();
    }

    public <T> void register(Class<T> interfaceClass,T bean){
        Method[] publicMethods = ReflectionUtils.getPublicMethods(interfaceClass);
        for(Method method:publicMethods){
            ServiceInstance serviceInstance = new ServiceInstance(bean, method);
            ServiceDescriptor serviceDescriptor = ServiceDescriptor.from(interfaceClass, method);
            services.put(serviceDescriptor,serviceInstance);
            log.info("register service:{}  {}",serviceDescriptor.getClazz(),serviceDescriptor.getMethod());
        }
    }

    public ServiceInstance lookup(Request request){
        ServiceDescriptor service = request.getService();
        return services.get(service);
    }
}
