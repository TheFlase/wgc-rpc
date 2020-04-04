package com.wgc.rpc.client;

import com.wgc.rpc.proto.Peer;
import com.wgc.rpc.proto.utils.ReflectionUtils;
import com.wgc.rpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author wgc
 * @Description //TODO
 * @Date 4/4/2020
 **/
@Slf4j
public class RandomTransportSelector implements TransportSelector {

    /**
     * 已经连接好的client
     */
    private List<TransportClient> clients;

    public RandomTransportSelector() {
        this.clients = new ArrayList<>();
    }

    @Override
    public synchronized void init(List<Peer> peerList, int count, Class<? extends TransportClient> clazz) {
        count = Math.max(count,1);
        for(Peer peerItem:peerList){
            for (int i = 0; i < count; i++) {
                TransportClient transportClient = ReflectionUtils.newInstance(clazz);
                transportClient.connect(peerItem);
                clients.add(transportClient);
            }
            log.info("connect server:{}",peerItem);
        }
    }

    @Override
    public synchronized TransportClient select() {
        int i= new Random().nextInt(clients.size());
        return clients.remove(i);
    }

    @Override
    public synchronized void release(TransportClient client) {
        clients.add(client);
    }

    @Override
    public synchronized void close() {
        for(TransportClient clientItem:clients){
            clientItem.close();
        }
        clients.clear();
    }
}
