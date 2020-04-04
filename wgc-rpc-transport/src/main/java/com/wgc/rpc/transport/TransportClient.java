package com.wgc.rpc.transport;

import com.wgc.rpc.proto.Peer;

import java.io.InputStream;

/**
 * step:
 * 1.创建连接
 * 2.发送数据,并且等待连接
 * 3.关闭连接
 *
 * @Author wgc
 * @Description
 * @Date 4/3/2020
 **/
public interface TransportClient {
    void connect(Peer peer);

    InputStream write(InputStream inputStream);

    void close();

}
