package com.example.superbirds;

import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class CollisionDetection {
    List<Collision> collisionList;

    public CollisionDetection(){
        collisionList = new ArrayList<Collision>();
    }

    public List<Collision> detectCollisions(List<GameObject> movingObjects, GameObject bird){
        if(movingObjects==null || bird == null || movingObjects.size()==0){return null;}

        List<Collision> cl = new ArrayList<Collision>();
        for(GameObject obj : movingObjects) {

            if(Rect.intersects(bird.GetRect(), obj.GetRect())){
                if( obj.getName().contains("pipe")){
                    Log.i("collision","-collision");
                    Log.i("collision",obj.getName());
                    Collision c = new Collision(bird.getName(), obj.getName(),"dead");
                    cl.add(c);
                }
                if(obj.getName().contains("score")){
                    Collision c = new Collision(bird.getName(), obj.getName(),"score");
                    obj.setImage();
                    cl.add(c);
                }
            }
        }

        if(collisionList != null || collisionList.size() > 0 ){//removing inactive objects
            for (Collision c: collisionList) {
                boolean f=false;
                for (Collision col: cl) {
                    if(c.value.equals(col.value) && c.secondObject.equals(col.secondObject)){
                        col.value="canceled";
                        f=true;
                    }
                }
                if(!f){c.value="canceled";}
            }
        }

        ListIterator<Collision> iter = cl.listIterator();

        while(iter.hasNext()){
            Collision c = iter.next();
            if(c.value.equals("canceled")){
                iter.remove();
            }else{
                collisionList.add(c);
            }
        }

        ListIterator<Collision> it = collisionList.listIterator();

        while(it.hasNext()){
            Collision c = it.next();
            if(c.value.equals("canceled")){
                it.remove();
            }
        }
        return cl;
    }
}
