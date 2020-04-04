package com.wgc.rpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * @Author wgc
 * @Description 基于json的反序列化
 * @Date 4/3/2020
 **/
public class JSONDecoder implements Decoder{
    @Override
    public <T> T decoder(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes,clazz);
    }
}
