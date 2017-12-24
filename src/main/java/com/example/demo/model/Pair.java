package com.example.demo.model;

public class Pair {

    String name;
    String hobby;

    //automated stuff

    public Pair() {
    }

    public Pair(String name, String hobby) {
        this.name = name;
        this.hobby = hobby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
