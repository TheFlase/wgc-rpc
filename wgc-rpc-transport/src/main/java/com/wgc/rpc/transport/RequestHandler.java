package com.wgc.rpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Author wgc
 * @Description 处理网络请求
 * @Date 4/3/2020
 **/
public interface RequestHandler {
    void onRequest(InputStream receive, OutputStream toResp);
}
