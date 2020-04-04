package com.wgc.rpc.proto;

import lombok.Data;

/**
 * @Author wgc
 * @Description 表示rpc的一个请求
 * @Date 4/3/2020
 **/
@Data
public class Request {
    private ServiceDescriptor service;
    private Object[] patameters;

}
