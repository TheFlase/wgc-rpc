package com.wgc.rpc.client;

import com.wgc.rpc.proto.Peer;
import com.wgc.rpc.transport.TransportClient;

import java.util.List;

/**
 * @Author wgc
 * @Description 表示选择哪个Server去连接
 * @Date 4/4/2020
 **/
public interface TransportSelector {

    /**
     * 初始化selector
     * @param peerList 可以连接的server端点信息
     * @param count client与server建立多少个连接
     * @param clazz client的实现类
     */
    void init(List<Peer> peerList,int count,Class<? extends TransportClient> clazz);

    /**
     * 选择一个transport与server做交互
     * @return 网络client
     */
    TransportClient select();

    /**
     * 释放用完的client
     * @param client
     */
    void release(TransportClient client);

    void close();
}
