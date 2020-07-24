package com.example.rpcdemo.test;

import com.example.rpcdemo.test.People;

public class Teacher implements People {
    @Override
    public String work() {
        System.out.println("老师教书育人...");
        return "教书";
    }
}
