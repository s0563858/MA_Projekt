package com.example.superbirds;

import android.widget.ImageView;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameObjectTest {

    @Test
    public void instantiate() {
        //bird = new GameObject(0,0,"bird", (ImageView) findViewById( R.id.imageView));
        GameObject g = new GameObject(0,0,"bird",null);
        assertNotNull(g);
    }

    @Test
    public void getName() {
        GameObject g = new GameObject(0,0,"bird",null);
        assertEquals(g.getName(),"bird");
    }

    @Test
    public void getRectNull() {
        GameObject g = new GameObject(0,0,"bird",null);
        assertEquals(g.GetRect(), null);
    }

    @Test
    public void getX() {
        GameObject g = new GameObject(1,0,"bird",null);
        assertEquals(g.getX(),1,0.0f);
    }

    @Test
    public void getY() {
        GameObject g = new GameObject(1,0,"bird",null);
        assertEquals(g.getY(),0,0.0f);
    }

    @Test
    public void getImageNull() {
        GameObject g = new GameObject(1,0,"bird",null);
        assertEquals(g.getImage(),null);
    }

    @Test
    public void setNewPosition() {
        GameObject g = new GameObject(1,0,"bird",null);
        g.setNewPosition(2,3);
        assertEquals(g.getX(),2,0.0f);
        assertEquals(g.getY(),3,0.0f);
    }
}