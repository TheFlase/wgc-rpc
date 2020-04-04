package com.wgc.rpc.codec;

/**
 * @Author wgc
 * @Description 序列化
 * @Date 4/3/2020
 **/
public interface Encoder {
    byte[] encoder(Object obj);
}
