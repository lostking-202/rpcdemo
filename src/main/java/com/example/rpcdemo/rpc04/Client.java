package com.example.rpcdemo.rpc04;

import com.example.rpcdemo.commom.IUserService;

public class Client {
    public static void main(String[] args) {
        IUserService service= (IUserService) Stub.getStub(IUserService.class);
        System.out.println(service.findUserById(1));
    }
}
