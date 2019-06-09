package com.example.superbirds;

import org.junit.Test;

import java.io.IOException;

import javax.net.ssl.HttpsURLConnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class NetworkObjectGetIDTest {

    @Test
    public void instantiate() {
        NetworkObjectGetID no = new NetworkObjectGetID(1,1);
        assertNotNull(no);
    }



    @Test
    public void getConnectionTest() throws IOException {
        NetworkObjectGetID no = new NetworkObjectGetID(0,1);
        no.id = 1;
        no.setDone(false);


        HttpsURLConnection h = no.getConnection();
        assertTrue(h.getURL().toString().equals ("https://heartbleed.de/app?getID=0&restart=1"));
    }

    @Test
    public void getConnectionWithoutValuesTest() throws IOException {
        NetworkObjectGetID no = new NetworkObjectGetID(1,1);

        HttpsURLConnection h = no.getConnection();
        System.out.println(h.getURL());
        assertTrue(h.getURL().toString().equals ("https://heartbleed.de/app?getID=1&restart=1"));
    }

    @Test
    public void valuesTest() {
        NetworkObjectGetID no = new NetworkObjectGetID(1,1);
        no.id=1;
        no.setDone(false);
        assertEquals(no.getDone() ,false  );
        assertEquals(no.id,1);
    }

}