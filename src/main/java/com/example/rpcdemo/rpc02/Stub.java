package com.example.rpcdemo.rpc02;

import com.example.rpcdemo.commom.IUserService;
import com.example.rpcdemo.commom.User;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.net.Socket;

//https://www.bilibili.com/video/BV135411e7qS?p=2
public class Stub {
    public static IUserService getStub(){
        InvocationHandler h= (proxy, method, args) -> {
            Socket s=new Socket("127.0.0.1",8888);
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            DataOutputStream dos=new DataOutputStream(baos);
            dos.writeInt(123);

            s.getOutputStream().write(baos.toByteArray());
            s.getOutputStream().flush();

            DataInputStream dis=new DataInputStream(s.getInputStream());
            int id=dis.readInt();
            String name=dis.readUTF();
            User user=new User(id,name);

            System.out.println(user);

            dos.close();
            s.close();
            return user;
        };
        Object o= Proxy.newProxyInstance(IUserService.class.getClassLoader(),new Class[]{IUserService.class},h);
        return (IUserService) o;
    }
}
