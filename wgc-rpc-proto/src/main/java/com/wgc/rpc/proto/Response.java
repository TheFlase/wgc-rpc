package com.wgc.rpc.proto;

import lombok.Data;

/**
 * @Author wgc
 * @Description 表示rpc的返回
 * @Date 4/3/2020
 **/
@Data
public class Response {
    /**
     * 服务返回编码 0-成功；非0-失败
     */
    private int code;
    /**
     * 具体的返回信息
     */
    private String message = "ok";
    /**
     * 返回的数据
     */
    private Object data;
}
