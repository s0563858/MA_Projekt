package mobanw.birds.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import mobanw.birds.game.SuperBirdsMain;

public class MenuState extends State {

    private Texture bg;
    private Texture playButton;

    public MenuState(GameStateManager g) {
        super(g);
        bg = new Texture("background.png");
        playButton = new Texture("playButton1.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg,0,0, SuperBirdsMain.width,SuperBirdsMain.height);
        sb.draw(playButton,(SuperBirdsMain.width/2) - (playButton.getWidth()/8),(SuperBirdsMain.height/2) - (playButton.getHeight()/8),playButton.getWidth()/4,SuperBirdsMain.height/4);
        sb.end();
    }

    @Override
    protected void dispose() {
        bg.dispose();
        playButton.dispose();
    }
}
