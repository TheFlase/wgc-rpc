package com.wgc.rpc.server;

import com.wgc.rpc.proto.Request;
import com.wgc.rpc.proto.utils.ReflectionUtils;

/**
 * @Author wgc
 * @Description 调用具体服务
 * @Date 4/4/2020
 **/
public class ServiceInvoker {
    public Object invoke(ServiceInstance serviceInstance, Request request){
        return ReflectionUtils.invoke(serviceInstance.getTarget(),
                serviceInstance.getMethod(),
                request.getPatameters());
    }
}
