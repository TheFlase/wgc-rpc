package com.wgc.rpc.example;

import com.wgc.rpc.server.RpcServer;

/**
 * @Author wgc
 * @Description //TODO
 * @Date 4/4/2020
 **/
public class Server {
    public static void main(String[] args) {
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(CalcService.class,new CalcServiceImpl());
        rpcServer.start();

    }
}
