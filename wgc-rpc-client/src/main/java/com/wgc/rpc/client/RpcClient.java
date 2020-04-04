package com.wgc.rpc.client;

import com.wgc.rpc.codec.Decoder;
import com.wgc.rpc.codec.Encoder;
import com.wgc.rpc.proto.utils.ReflectionUtils;

import java.lang.reflect.Proxy;

/**
 * @Author wgc
 * @Description
 * @Date 4/4/2020
 **/
public class RpcClient {
    private RpcClientConfig clientConfig;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RpcClient() {
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig clientConfig) {
        this.clientConfig = clientConfig;
        this.encoder = ReflectionUtils.newInstance(clientConfig.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(clientConfig.getDecoderClass());
        this.selector = ReflectionUtils.newInstance(clientConfig.getSelectorClass());

        this.selector.init(this.clientConfig.getServers(),
                this.clientConfig.getConnectCount(),
                this.clientConfig.getTransportClientClass());
    }

    public <T> T getProxy(Class<T> clazz){
        return (T) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[]{clazz},
                new RemoteInvoker(clazz,encoder,decoder,selector)
        );
    }
}
