package gtsoffenbach.tourdegts;

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


        getGraphics().drawImage(Assets.infobox, getRectangle().left, getRectangle().top - Assets.infobox.getHeight() / 2);
        if (!level.isUnlocked()) {
            getGraphics().drawImage(Assets.lock, getRectangle().left + (Assets.infobox.getWidth() - Assets.lock.getWidth()) / 2, getRectangle().top - Assets.lock.getHeight() / 2);
        } else {
            getGraphics().drawImage(level.getImage(), getRectangle().left + (Assets.infobox.getWidth() - Assets.lock.getWidth()) / 2, getRectangle().top - level.getImage().getHeight() / 2);
        }


    }
}
