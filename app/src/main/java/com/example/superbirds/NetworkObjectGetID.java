package com.example.superbirds;

import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NetworkObjectGetID implements INetworkObject {
    public int id;
    private boolean done;
    private int getID;
    private int restart;

    public NetworkObjectGetID(int getIDt, int restart) {
        if((getIDt != 0 && getIDt != 1) || (restart!=1 && restart!=0)){ return; }
        this.getID=getIDt;
        this.restart=restart;
    }

    @Override
    public boolean getDone() {
        return done;
    }

    @Override
    public void setDone(boolean d) {
        done=d;
    }

    private String domain="heartbleed.de";

    public HttpsURLConnection getConnection() throws IOException {
        if((restart!=0&&restart!=1) || (getID!=0&&getID!=1)){return null;}
        URL addr = new URL("https://"+domain+"/app?getID="+getID+"&restart="+restart);
        System.out.println(addr);
        HttpsURLConnection connection = (HttpsURLConnection) addr.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        connection.setUseCaches(false);
        connection.setDoOutput(true);
        return connection;
    }
}