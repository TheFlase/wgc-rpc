package com.wgc.rpc.client;

import com.wgc.rpc.codec.Decoder;
import com.wgc.rpc.codec.Encoder;
import com.wgc.rpc.proto.Request;
import com.wgc.rpc.proto.Response;
import com.wgc.rpc.proto.ServiceDescriptor;
import com.wgc.rpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author wgc
 * @Description 调用远程服务的代理类
 * @Date 4/4/2020
 **/
@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RemoteInvoker(Class clazz, Encoder encoder, Decoder decoder, TransportSelector selector){
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.selector = selector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setService(ServiceDescriptor.from(clazz,method));
        request.setPatameters(args);
        Response response = invokeRemote(request);
        if(response != null && response.getCode() != 0){
            throw new IllegalStateException("fail to invoke remote: "+response);
        }
        return response.getData();
    }

    private Response invokeRemote(Request request) {
        TransportClient transportClient = null;
        Response response = null;
        try {
            transportClient = selector.select();
            byte[] outBytes = this.encoder.encoder(request);
            InputStream receiveInputStream = transportClient.write(new ByteArrayInputStream(outBytes));
            byte[] inBytes = IOUtils.readFully(receiveInputStream, receiveInputStream.available());
            response = this.decoder.decoder(inBytes, Response.class);
        }catch (Exception e) {
            log.warn(e.getMessage(),e);
            response.setCode(-1);
            response.setMessage("RpcClient get error: "+e.getClass()+", "+e.getMessage());

        } finally {
            if(transportClient != null){
                selector.release(transportClient);
            }
        }
        return response;
    }
}
