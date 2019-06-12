package com.example.superbirds;

import android.os.Message;
import android.util.Log;

import java.util.List;
import java.util.Random;

public class Game extends Thread {
    GameObject bird;
    List<GameObject> movingObjects;
    int networkID;
    private String domain="heartbleed.de";
    INetworkObject netObj;
    GameObject otherPlayer;


    public boolean gameActivated;//public for testing purpouses
    MainActivity main;
    public int score;
    public boolean firstPacketFetched;

    long collisionLastTime;

    CollisionDetection cd;

    ClientGame c;
    long lastTime;

    public Game(GameObject b,GameObject o , List<GameObject> mo, MainActivity m) {
        score=-1;
        bird=b;
        movingObjects=mo;
        main=m;
        cd = new CollisionDetection();
        incrementScore();
        otherPlayer=o;
        firstPacketFetched=false;
        gameActivated = true;
        lastTime = System.nanoTime();

        networkID=-2;
        netObj = new NetworkObjectGetID(1,0);
        c = new ClientGame();
        c.execute(netObj);
    }

    public void movingObjects() {
        long time = System.nanoTime();
        float delta_time = (float) ((time - lastTime) / 1000000);
        lastTime = time;
        bird.setNewPosition(bird.getX(),bird.getY() + 0.02f*delta_time);
        drawGameobject(bird);
        if(netObj!=null && netObj.getDone()){ //|| (networkID==2 && netObj!=null && !netObj.done && firstPacketFetched) ){
            Log.i("network","- y: "+((NetworkObjectPosition)netObj).otherPlayersPosY);
            otherPlayer.setNewPosition(bird.getX(),((NetworkObjectPosition)netObj).otherPlayersPosY);
            drawGameobject(otherPlayer);
        }

        if(networkID==-1 || networkID==1 || (networkID==2 && netObj!=null && !netObj.getDone() && firstPacketFetched)){//wenn spieler der server ist oder es keinen freien platz gibt
            for(GameObject obj : movingObjects) {
                obj.setNewPosition(obj.getX() - 0.02f*delta_time, obj.getY() );
                if(obj.getX() < -50){
                    if(obj.getName().contains("score")) {
                        Random rnd = new Random();;
                        float objY = rnd.nextFloat() * (obj.getY() - obj.getY()-60) + obj.getY()-60;
                        obj.setNewPosition(400, objY );
                    } else {
                        obj.setNewPosition(400, obj.getY() );
                    }
                }
                drawGameobject(obj);
            }

        }

        if(networkID==2 && netObj!=null && netObj.getDone() && ((NetworkObjectPosition)netObj).pipe1PosX!=((NetworkObjectPosition)netObj).pipe2PosX){//wenn der spiler die client-rolle hat
            firstPacketFetched=true;
            for(GameObject obj : movingObjects) {
                if(obj.getName().equals("pipe1")){
                    obj.setNewPosition(((NetworkObjectPosition)netObj).pipe1PosX, obj.getY() );
                }
                if(obj.getName().equals("pipe2")){
                    obj.setNewPosition(((NetworkObjectPosition)netObj).pipe2PosX, obj.getY() );
                }
                if(obj.getName().equals("score1")){obj.setNewPosition(((NetworkObjectPosition)netObj).pipe1PosX, obj.getY() );}
                if(obj.getName().equals("score2")){obj.setNewPosition(((NetworkObjectPosition)netObj).pipe2PosX, obj.getY() );}
                drawGameobject(obj);
            }
            Log.i("network","- y: "+((NetworkObjectPosition)netObj).otherPlayersPosY);
            otherPlayer.setNewPosition(bird.getX(),((NetworkObjectPosition)netObj).otherPlayersPosY);
            drawGameobject(otherPlayer);
        }
    }

    private void network() {
        if(c==null || netObj == null || netObj.getDone()){
            Log.i("network","- New async network task");
            netObj = new NetworkObjectPosition();
            netObj.setDone(false);
            ((NetworkObjectPosition)netObj).currentPlayersID=networkID;
            ((NetworkObjectPosition)netObj).currentPlayersPosY=bird.getY();
            ((NetworkObjectPosition)netObj).currentPlayersPipe1Pos = getGameobjectPositionX( "pipe1");
            ((NetworkObjectPosition)netObj).currentPlayersPipe2Pos = getGameobjectPositionX( "pipe2");
            c=new ClientGame();
            c.execute(netObj);
        }
    }

    public void run() {
        //c.run();
        if(bird == null || movingObjects == null  || main == null || cd == null){
            gameActivated=false;
            Log.i("Error: ","- game deactivated");
            return;
        }

        while(gameActivated) {
            if(netObj.getClass()==NetworkObjectGetID.class){//getting the id asynchronously
                if(netObj.getDone()){networkID=((NetworkObjectGetID)netObj).id; netObj=null; }
                else {continue;}
            }
            movingObjects();
            detectCollisions();
            network();
        }
        saveScore();
    }

    public void detectCollisions() {
        if((networkID==1 || (networkID==2 && firstPacketFetched)) && collisionLastTime + 1000000000l < System.nanoTime()){
            collisionLastTime = System.nanoTime();
            List<Collision> collisions = cd.detectCollisions(movingObjects,bird);

            for (Collision c : collisions) {
                if(c.value=="dead" ){
                    Log.i("collision","-gameover");
                    gameActivated=false;
                    bird.setNewPosition(0,-1000);
                }
                if(c.value=="score" ){
                    Log.i("score","-score incremented");
                    incrementScore();
                }
            }
        }

    }

    public float getGameobjectPositionX(String gameObjectName) {
        float pos = -1000;

        for (GameObject g:
             movingObjects) {
            if(g.getName().equals(gameObjectName)){
                pos=g.getX();
            }

        }
        return pos;
    }

    public void saveScore() {
        if(main==null){return;}
        Message msg = main.handler.obtainMessage(1,score);
        main.handler.sendMessage(msg);
    }

    public void incrementScore() {
        score++;
        if(main==null){return;}
        Message msg = main.handler.obtainMessage(0,score);
        main.handler.sendMessage(msg);
    }

    public void FlyButtonClicked() {
        if(bird==null){return;}
        // Log.i("ImageButton","clicked");
        System.out.println(gameActivated);
        if(gameActivated){
            bird.setNewPosition(bird.getX(),bird.getY() - 20);
            drawGameobject(bird);
        }
    }

    private void drawGameobject(GameObject g) {
        final GameObject a = g;

        if(g== null || g.getImage() == null){ return;}
        if(main==null){return;}

        Message msg = main.handler.obtainMessage(2,g);
        main.handler.sendMessage(msg);
    }
}
