package com.example.superbirds;

import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CollisionDetection {

    List<Collision> collisionList;


    public CollisionDetection(){
        collisionList = new ArrayList<Collision>();
    }


    public List<Collision> detectCollisions(List<GameObject> movingObjects, GameObject bird){

        if(movingObjects==null || bird == null){return null;}

        if(collisionList != null || collisionList.size() > 0 ){
            for (Collision c:
                    collisionList) {
                c.state="inactive";
            }
        }



        for(GameObject obj : movingObjects) {
            if(Rect.intersects(bird.GetRect(), obj.GetRect())){
                String state = checkState(bird.getName(),obj.getName());
                if( obj.getName().contains("pipe")){
                    Log.i("collision","-collision");
                    Log.i("collision",obj.getName());
                    Collision c = new Collision(bird.getName(), obj.getName(),"dead",state);
                    collisionList.add(c);
                }
                if(obj.getName().contains("score")){
                    Log.i("score","-score");
                    Collision c = new Collision(bird.getName(), obj.getName(),"score",state);
                    collisionList.add(c);
                }
            }

        }

        List<Integer> toRemoveIds = new ArrayList<>();
        if(collisionList != null || collisionList.size() > 0 ){//removing inactive objects
            for (Collision c:
                    collisionList) {
                if(c.state=="inactive"){ toRemoveIds.add(collisionList.indexOf(c)); }
            }
            for (int i:
                    toRemoveIds) {
                 collisionList.remove(i);
            }
        }

        return collisionList;
    }



    private String checkState(String obj1Name, String obj2Name){
        if(collisionList != null || collisionList.size() > 0 ){//removing inactive objects
            for (Collision c:
                    collisionList) {
                if(c.state=="inactive" && c.firstObject == obj1Name && c.secondObject == obj2Name){collisionList.remove(c); return "in";}
            }
        }
        return "entered";
    }





}
