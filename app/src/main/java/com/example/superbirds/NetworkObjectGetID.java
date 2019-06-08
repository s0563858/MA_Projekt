package com.example.superbirds;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NetworkObjectGetID implements INetworkObject {
    public int id;
    public boolean done;
    private int getID;
    private int restart;

    public NetworkObjectGetID(int getIDt, int restartt){
        this.getID=getIDt;
        this.restart=restartt;
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