package com.wgc.rpc.server;

import com.wgc.rpc.codec.Decoder;
import com.wgc.rpc.codec.Encoder;
import com.wgc.rpc.proto.Request;
import com.wgc.rpc.proto.Response;
import com.wgc.rpc.proto.utils.ReflectionUtils;
import com.wgc.rpc.transport.RequestHandler;
import com.wgc.rpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Author wgc
 * @Description //TODO
 * @Date 4/4/2020
 **/
@Slf4j
public class RpcServer {
    private RpcServerConfig serverConfig;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    public RpcServer(){
        this(new RpcServerConfig());
    }

    public RpcServer(RpcServerConfig serverConfig) {
        this.serverConfig = serverConfig;
        this.net = ReflectionUtils.newInstance(serverConfig.getTransportClass());
        this.net.init(serverConfig.getPort(),this.handler);

        this.encoder = ReflectionUtils.newInstance(serverConfig.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(serverConfig.getDecoderClass());

        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    public <T> void register(Class<T> interfaceClass,T bean){
        serviceManager.register(interfaceClass,bean);
    }

    public void start(){
        this.net.start();
    }

    public void stop(){
        this.net.stop();
    }

    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream receive, OutputStream toResp) {
            Response response = new Response();
            try {
                byte[] inBytes = IOUtils.readFully(receive, receive.available());
                Request request = RpcServer.this.decoder.decoder(inBytes, Request.class);
                log.info("get request:{}",request);
                ServiceInstance serviceInstance = serviceManager.lookup(request);
                Object invokeResult = serviceInvoker.invoke(serviceInstance, request);
                response.setData(invokeResult);
            } catch (Exception e) {
                log.warn(e.getMessage(),e);
                response.setCode(-1);
                response.setMessage("RpcServer get error: "+e.getClass().getName()+":"+e.getMessage());
            }finally {
                try {
                    byte[] outBytes = RpcServer.this.encoder.encoder(response);
                    toResp.write(outBytes);
                    log.info("response client");
                } catch (Exception e) {
                    log.warn(e.getMessage(),e);
                }
            }
        }
    };
}
