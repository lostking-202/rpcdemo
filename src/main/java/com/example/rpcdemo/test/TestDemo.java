package com.example.rpcdemo.test;

import com.example.rpcdemo.commom.IUserService;
import com.example.rpcdemo.commom.User;
import com.example.rpcdemo.commom.UserServiceImpl;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TestDemo {
    public static void main(String[] args) {
        IUserService service=(IUserService) getSub(IUserService.class);
        System.out.println(service.findUserById(1));
    }
    private static Object getSub(Class clazz){
        InvocationHandler h= (proxy, method, args) -> {

            String className=clazz.getName();
            String methodName=method.getName();
            Class[] parameterTypes=method.getParameterTypes();
            Class service=UserServiceImpl.class;
            Arrays.stream(service.getMethods()).forEach(a->System.out.println(a.getName()));
            Method method1=service.getMethod(methodName,parameterTypes);

            User user=(User)method1.invoke(new UserServiceImpl(),args);
            return user;
        };
        Object o= Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},h);
        return o;
    }
}
