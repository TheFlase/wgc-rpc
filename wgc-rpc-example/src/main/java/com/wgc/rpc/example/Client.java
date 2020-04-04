package com.wgc.rpc.example;

import com.wgc.rpc.client.RpcClient;

/**
 * @Author wgc
 * @Description
 * @Date 4/4/2020
 **/
public class Client {
    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalcService calcServiceProxy = client.getProxy(CalcService.class);
        int addResult = calcServiceProxy.add(1, 2);
        int minusResult = calcServiceProxy.minus(2, 1);
        System.out.println("add result is :"+addResult+", minus result is: "+minusResult);

    }
}
