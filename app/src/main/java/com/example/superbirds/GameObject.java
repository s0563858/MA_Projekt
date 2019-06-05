package com.example.superbirds;

import android.graphics.Rect;
import android.widget.ImageView;


public class GameObject {


    private float x;
    private float y;



    private String name;


    private ImageView image;


    public GameObject(float startX, float startY, String n, ImageView iv ){
        x = startX;
        y = startY;
        name = n;
        image=iv;

    }

    public String getName() {
        return name;
    }

    public Rect GetRect(){
        if(image == null){ return null;}
        Rect r = new Rect();
        image.getHitRect(r);

        return r;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public ImageView getImage() {
        return image;
    }

    public void setNewPosition(float newx, float newy){
        x=newx;
        y=newy;
    }
}
