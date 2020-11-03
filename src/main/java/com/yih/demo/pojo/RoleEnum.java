package com.yih.demo.pojo;

public enum RoleEnum {
    READ("ROLE_READ"),
    WRITE("ROLE_WRITE");

    private String name;

    RoleEnum(String name) {
        this.name = name;
    }
}