package com.bestlinwei;

public class User {

	private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void test() {
        System.err.println("hello,world" + name);
    }
}
