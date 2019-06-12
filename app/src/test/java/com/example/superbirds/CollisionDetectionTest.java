package com.example.superbirds;






import android.graphics.Rect;
import android.graphics.RectF;

import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CollisionDetectionTest {

    @Test
    public void insantiate() {
        CollisionDetection cd = new CollisionDetection();
        assertNotNull(cd);
    }


    @Test
    public void detectCollisions() {

        CollisionDetection cd = new CollisionDetection();
        List<GameObject> pipes = new ArrayList<GameObject>();
        GameObject p = mock(GameObject.class);
        GameObject bird = mock(GameObject.class);

        Rect r = new Rect(1,2,3,4);

        when(bird.GetRect()).thenReturn(r);
        when(p.GetRect()).thenReturn(r);

        pipes.add(p);
        List<Collision> collisions = cd.detectCollisions(pipes,bird);
        assertNotNull(collisions);
    }

    @Test
    public void detectCollisionsNull() {
        CollisionDetection cd = new CollisionDetection();
        List<GameObject> pipes = new ArrayList<GameObject>();
        GameObject bird;
        List<Collision> collisions = cd.detectCollisions(null,null);
        assertNull(collisions);
    }
}