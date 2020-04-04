package com.wgc.rpc.transport;

/**
 * step:
 * 1.启动,监听
 * 2.接受客户端连接请求
 * 3.关闭监听
 *
 * @Author wgc
 * @Description
 * @Date 4/3/2020
 **/
public interface TransportServer {
    void init(int port,RequestHandler handler);

    void start();

    void stop();
}
