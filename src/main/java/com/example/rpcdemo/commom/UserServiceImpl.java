package com.example.rpcdemo.commom;

import com.example.rpcdemo.commom.IUserService;
import com.example.rpcdemo.commom.User;

public class UserServiceImpl implements IUserService {
    @Override
    public User findUserById(int id) {
        return new User(id,"a");
    }
}
