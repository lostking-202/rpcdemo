package com.example.rpcdemo.rpc03;

import com.example.rpcdemo.commom.IUserService;
import com.example.rpcdemo.commom.User;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.net.Socket;

//https://www.bilibili.com/video/BV135411e7qS?p=2
public class Stub {
    public static IUserService getStub(){
        InvocationHandler h= (proxy, method, args) -> {
            Socket s=new Socket("127.0.0.1",8888);

            ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());

            String methodName=method.getName();
            Class[] parameterTypes=method.getParameterTypes();
            oos.writeUTF(methodName);
            oos.writeObject(parameterTypes);
            oos.writeObject(args);
            oos.flush();

            ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
            User user=(User) ois.readObject();

            System.out.println(user);

            oos.close();
            s.close();
            return user;
        };
        Object o= Proxy.newProxyInstance(IUserService.class.getClassLoader(),new Class[]{IUserService.class},h);
        return (IUserService) o;
    }
}
