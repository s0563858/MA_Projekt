package com.example.superbirds;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void instantiate() {
        Game g = new Game(null,null,null,null);
        assertNotNull(g);
    }

    @Test
    public void run() {
    }

    @Test
    public void incrementScore() {
        Game g = new Game(null,null,null,null);
        g.incrementScore();
        assertTrue(g.score==1);
    }

    @Test
    public void flyButtonClicked() {
        GameObject go = new GameObject(0,0,"bird",null );
        Game g = new Game(go,null,null,null);
        g.gameActivated=true;
        g.FlyButtonClicked();
        assertTrue(go.getY()==-50);
    }
}