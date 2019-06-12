package com.example.superbirds;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {
    @Test
    public void instantiate() {
        MainActivity m = new MainActivity();
        assertNotNull(m);
    }
}