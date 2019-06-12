package com.example.superbirds;

import org.junit.Test;

import static org.junit.Assert.*;

public class CollisionTest {
    @Test
    public void instantiating() {
        Collision c =  new Collision("name1", "name2","dead");
        assertNotNull(c);
    }

    @Test
    public void values() {
        Collision c =  new Collision("name1", "name2","dead");
        assertEquals(c.firstObject, "name1");
        assertEquals(c.secondObject, "name2");
        assertEquals(c.value, "dead");
    }
}