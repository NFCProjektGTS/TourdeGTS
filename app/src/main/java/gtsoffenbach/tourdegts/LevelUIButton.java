package gtsoffenbach.tourdegts;

import gtsoffenbach.tourdegts.implementations.AndroidGame;
import gtsoffenbach.tourdegts.implementations.Level;

/**
 * Created by Marlon on 15.11.2014.
 */
public class LevelUIButton extends UIButton {

    Level level;

    public LevelUIButton(Level level, final ElementContainer container, final int dx, final int dy) {
        super(container, dx, dy, Assets.button.getWidth(), Assets.button.getHeight());
        this.level = level;
    }

    @Override
    public void Click() {
        //game.setScreen(new GameScreen(game, selectedLevel));
        System.out.println("Click");
    }

    @Override
    public void draw(float delta) {



        getGraphics().drawImage(Assets.infobox,  getRectangle().left -35 + AndroidGame.width/2 - Assets.infobox.getWidth()/2, getRectangle().top -50 - Assets.infobox.getHeight() / 2);
        if(! level.isUnlocked()){
            getGraphics().drawImage(Assets.lock,  getRectangle().left -35 +AndroidGame.width/2 - Assets.lock.getWidth()/2 , getRectangle().top - 50 - Assets.lock.getHeight() / 2);
        }else {
            getGraphics().drawImage(level.getImage(),  getRectangle().left -35 + AndroidGame.width/2 - level.getImage().getWidth()/2, getRectangle().top -50 - level.getImage().getHeight() / 2);
        }


    }
}
