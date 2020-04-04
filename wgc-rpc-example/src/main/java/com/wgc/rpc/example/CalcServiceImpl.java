package com.wgc.rpc.example;

/**
 * @Author wgc
 * @Description //TODO
 * @Date 4/4/2020
 **/
public class CalcServiceImpl implements CalcService{
    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int minus(int a, int b) {
        return a-b;
    }
}
