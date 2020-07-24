package com.example.rpcdemo.rpc01;

import com.example.rpcdemo.commom.User;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
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

    }
}
