package com.example.rpcdemo.rpc04;

import com.example.rpcdemo.commom.IUserService;
import com.example.rpcdemo.commom.User;
import com.example.rpcdemo.commom.UserServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static boolean running=true;

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ServerSocket ss=new ServerSocket(8080);
        while(running){
            Socket s=ss.accept();
            process(s);
            s.close();
        }
    }

    private static void process(Socket s) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        InputStream in=s.getInputStream();
        OutputStream out=s.getOutputStream();
        ObjectInputStream ois=new ObjectInputStream(in);
        ObjectOutputStream oos=new ObjectOutputStream(out);

        String className=ois.readUTF();
        String methodName=ois.readUTF();
        Class[] parameterTypes=(Class[]) ois.readObject();
        Object[] args=(Object[])ois.readObject();

        IUserService service=new UserServiceImpl();
        Method method=service.getClass().getMethod(methodName,parameterTypes);
        User user=(User)method.invoke(service,args);

        oos.writeObject(user);
        oos.flush();

    }
}
