package com.wgc.rpc.codec;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author wgc
 * @Description //TODO
 * @Date 4/3/2020
 **/
public class JSONEncoderTest {

    @Test
    public void encoder() {
        JSONEncoder jsonEncoder = new JSONEncoder();
        TestBean wgcTestBean = new TestBean("wgc", 20);
        byte[] bytes = jsonEncoder.encoder(wgcTestBean);
        JSONDecoder jsonDecoder = new JSONDecoder();
        TestBean testBean = jsonDecoder.decoder(bytes, TestBean.class);
        Assert.assertEquals(wgcTestBean,testBean);
        System.out.println(testBean.toString());
    }
}
