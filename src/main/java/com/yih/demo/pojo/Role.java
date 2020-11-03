package com.yih.demo.pojo;

public enum Role {
    READ("ROLE_READ"),
    WRITE("ROLE_WRITE");

    private String name;

    Role(String name) {
        this.name = name;
    }
}