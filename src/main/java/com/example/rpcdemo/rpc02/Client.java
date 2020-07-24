package com.example.rpcdemo.rpc02;

import com.example.rpcdemo.commom.IUserService;

public class Client {
    public static void main(String[] args) {
        IUserService service=Stub.getStub();
        System.out.println(service.findUserById(1));
    }
}
