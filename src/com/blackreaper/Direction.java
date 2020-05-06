package com.blackreaper;

public enum Direction {

    UP(0, "up"),

    DOWN(1, "down"),

    RIGHT(2, "right"),

    LEFT(3, "left");

    int code;
    String direction;

    Direction(int code, String direction) {
        this.code = code;
        this.direction = direction;
    }

}
