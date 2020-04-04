package com.wgc.rpc.server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * @Author wgc
 * @Description 表示一个具体服务
 * @Date 4/4/2020
 **/
@Data
@AllArgsConstructor
public class ServiceInstance {
    /**
     * 这个服务由target对象提供
     */
    private Object target;
    /**
     * 暴露的方法
     */
    private Method method;
}
