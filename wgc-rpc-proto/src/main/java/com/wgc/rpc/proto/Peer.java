package com.wgc.rpc.proto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author wgc
 * @Description 表示网络传输的一个端点
 * @Date 4/3/2020
 **/
@Data
@AllArgsConstructor
public class Peer {
    private String host;
    private int port;
}
