package com.wgc.rpc.codec;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @Author wgc
 * @Description //TODO
 * @Date 4/3/2020
 **/
@Data
@AllArgsConstructor
@Builder
@ToString
public class TestBean {
    private String name;
    private Integer age;
}
