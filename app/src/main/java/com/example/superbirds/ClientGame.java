package com.example.superbirds;

import android.os.AsyncTask;
import android.util.Log;

import java.io.*;
import java.net.*;

public class ClientGame extends AsyncTask<NetworkObject, Integer, NetworkObject> {
    private static int playerYPos = 0;

    NetworkObject netObj;




    @Override
    protected void onPreExecute() {

    }
    @Override
    protected NetworkObject doInBackground(NetworkObject... params) {
        // Runs in background thread
        if(params==null){return null;}
        netObj = params[0];
        try {
            communicateWithServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return netObj;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(NetworkObject resp) {
        netObj.done=true;
    }


    public  void communicateWithServer() throws IOException {
        if(netObj==null){return;}
        HttpURLConnection connection = netObj.getConnection();
        PrintWriter out;
        BufferedReader in;
        String line;
        String player = String.valueOf(playerYPos);

        //out = new PrintWriter(connection.getOutputStream());
        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        line = in.readLine();
        in.close();


       // out.println(player + "," + netObj.currentPlayersID);
        //out.flush();
        //out.close();
        System.out.println(line);




        if(line==null || line=="" ){netObj.otherPlayersPosY=-1000;}
        else{
            String[] numbers = line.split(",");
            if(numbers.length!=3){netObj.otherPlayersPosY=-1000;}
            else {
                netObj.otherPlayersPosY=Float.parseFloat(numbers[0]);
                netObj.pipe1PosX=Float.parseFloat(numbers[1]);
                netObj.pipe2PosX=Float.parseFloat(numbers[2]);
            }
        }
        Log.i("network","[Client] anderer user: " + netObj.otherPlayersPosY+" : "+line);

    }



}
