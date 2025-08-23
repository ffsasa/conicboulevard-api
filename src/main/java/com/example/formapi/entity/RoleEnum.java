package com.example.formapi.entity;

import lombok.Getter;

@Getter
public enum RoleEnum {
    ADMIN(1);

    private final int value;

    RoleEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
