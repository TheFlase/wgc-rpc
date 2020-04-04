package com.wgc.rpc.server;

import com.wgc.rpc.codec.Decoder;
import com.wgc.rpc.codec.Encoder;
import com.wgc.rpc.codec.JSONDecoder;
import com.wgc.rpc.codec.JSONEncoder;
import com.wgc.rpc.transport.HTTPTransportServer;
import com.wgc.rpc.transport.TransportServer;
import lombok.Data;

/**
 * @Author wgc
 * @Description server配置
 * @Date 4/4/2020
 **/
@Data
public class RpcServerConfig {
    private Class<? extends TransportServer> transportClass = HTTPTransportServer.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private int port = 3000;
}
