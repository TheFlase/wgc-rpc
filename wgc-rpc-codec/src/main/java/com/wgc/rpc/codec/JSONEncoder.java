package com.wgc.rpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * @Author wgc
 * @Description 基于json的序列化实现
 * @Date 4/3/2020
 **/
public class JSONEncoder implements Encoder{
    @Override
    public byte[] encoder(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}
