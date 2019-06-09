package com.example.superbirds;

import java.io.IOException;

import javax.net.ssl.HttpsURLConnection;

public interface INetworkObject {
    HttpsURLConnection getConnection() throws IOException;
    boolean getDone();
    void setDone(boolean d);
}
