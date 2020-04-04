package com.wgc.rpc.client;

import com.wgc.rpc.codec.Decoder;
import com.wgc.rpc.codec.Encoder;
import com.wgc.rpc.codec.JSONDecoder;
import com.wgc.rpc.codec.JSONEncoder;
import com.wgc.rpc.proto.Peer;
import com.wgc.rpc.transport.HTTPTransportClient;
import com.wgc.rpc.transport.TransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @Author wgc
 * @Description
 * @Date 4/4/2020
 **/
@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClientClass = HTTPTransportClient.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;

    private int connectCount = 1;
    private List<Peer> servers = Arrays.asList(new Peer("127.0.0.1",3000));

}
