package com.example.superbirds;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CollisionDetectionTest {
    @Test
    public void insantiate() {
        CollisionDetection cd = new CollisionDetection();
        assertNotNull(cd);
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