package com.example.superbirds;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Game extends Thread {


    GameObject bird;
    List<GameObject> movingObjects;


    public boolean gameActivated;//public for testing purpouses
    MainActivity main;
    public int score;

    CollisionDetection cd;

    public Game(GameObject b, List<GameObject> mo, MainActivity m){
        score=-1;
        bird=b;
        movingObjects=mo;
        main=m;
        cd = new CollisionDetection();
        incrementScore();
    }


    public void run(){
        if(bird == null || movingObjects == null  || main == null || cd == null){
            gameActivated=false;
            return;
        }
        long lastTime = System.nanoTime();
        gameActivated = true;

        while(gameActivated) {

            //-------collision detection---------
            List<Collision> collisions = cd.detectCollisions(movingObjects,bird);

            for (Collision c : collisions) {
                if(c.value=="dead" && c.state=="entered"){
                    Log.i("collision","-gameover");
                    gameActivated=false;
                }
                if(c.value=="score" && c.state=="entered"){
                    Log.i("score","-score incremented");
                    incrementScore();
                }
            }
            //-------collision detection---------

            //-------moving the objects----------
            long time = System.nanoTime();
            float delta_time = (float) ((time - lastTime) / 1000000);
            lastTime = time;
            bird.setNewPosition(bird.getX(),bird.getY() + 0.1f*delta_time);
            drawGameobject(bird);
            for(GameObject obj : movingObjects) {
                obj.setNewPosition(obj.getX() - 0.1f*delta_time, obj.getY() );
                if(obj.getX() < -50){
                    obj.setNewPosition(400, obj.getY() );
                }
                drawGameobject(obj);
            }
            //-------moving the objects----------

        }

    }


    public  void incrementScore(){
        score++;
        if(main!=null){}

        main.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                main.saveNewScore(score);
                //scoreText.setText("Score: "+String.valueOf(score));
            }
        });

    }





    public void FlyButtonClicked(){
        if(bird==null){return;}
        Log.i("ImageButton","clicked");
        System.out.println(gameActivated);
        if(gameActivated){
            bird.setNewPosition(bird.getX(),bird.getY() - 50);
            drawGameobject(bird);
        }
    }



    private void drawGameobject(GameObject g){
        if(g== null || g.getImage() == null){ return;}
        ImageView image  = g.getImage();
        image.setY(g.getY());
        image.setX(g.getX());
    }
}
