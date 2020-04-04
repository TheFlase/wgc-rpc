package com.wgc.rpc.codec;

/**
 * @Author wgc
 * @Description 反序列化
 * @Date 4/3/2020
 **/
public interface Decoder {
    <T> T decoder(byte[] bytes,Class<T> clazz);
}
