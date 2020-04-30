package com.blackreaper;

public enum Direction {

    UP(0, "up"),

    DOWN(1, "down"),

    RIGHT(2, "right"),

    LEFT(3, "left");

    int code;
    String direction;

    Direction(int code, String direction){
        this.code = code;
        this.direction = direction;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
