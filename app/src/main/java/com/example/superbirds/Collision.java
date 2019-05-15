package com.example.superbirds;

public class Collision {

    String firstObject;
    String secondObject;

    String value;

    String state;//entered oder in oder inactive


    public Collision(String f, String s, String v, String st){
        firstObject=f;
        secondObject=s;
        value=v;
        state =st;
    }
}
