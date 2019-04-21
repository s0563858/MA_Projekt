package mobanw.birds.game.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class PipeController {

    private Texture topPipe;
    private Texture bottomPipe;

    public static final int gapBetween = 100;
    public static final int fluctuation = 120;
    public static final int lowest_opening = 120;
    public static final int pipeWidth= 40;

    private Random randomObject;
    private Vector2 positionTopPipe;
    private Vector2 positionBottomPipe;

    private Rectangle colliderTopPipe, colliderBottomPipe;


    public PipeController(float x){
        topPipe = new Texture("newpipe2.png");
        bottomPipe = new Texture("newpipe1.png");
        randomObject = new Random();

        positionTopPipe = new Vector2(x,randomObject.nextInt(fluctuation)+gapBetween+lowest_opening);
        positionBottomPipe = new Vector2(x, positionTopPipe.y - gapBetween -240);

        colliderBottomPipe = new Rectangle(positionBottomPipe.x, positionBottomPipe.y,20,200);
        colliderTopPipe = new Rectangle(positionTopPipe.x, positionTopPipe.y,20,200);
    }

    public void movePipes(float x){
        positionTopPipe.set(x,randomObject.nextInt(fluctuation)+gapBetween+lowest_opening);
        positionBottomPipe.set(x, positionTopPipe.y - gapBetween -240);

        colliderTopPipe.setPosition(positionTopPipe.x,positionTopPipe.y);
        colliderBottomPipe.setPosition(positionBottomPipe.x,positionBottomPipe.y);
    }

    public boolean detectCollision(Rectangle playerCollider){
        return playerCollider.overlaps(colliderTopPipe) || playerCollider.overlaps(colliderBottomPipe);
    }


    public Texture getTopPipe() {
        return topPipe;
    }

    public Texture getBottomPipe() {
        return bottomPipe;
    }

    public Vector2 getPositionBottomPipe() {
        return positionBottomPipe;
    }

    public Vector2 getPositionTopPipe() {
        return positionTopPipe;
    }
}
