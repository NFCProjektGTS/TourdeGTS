package gtsoffenbach.tourdegts.ContentElement;

import gtsoffenbach.tourdegts.gameinterface.Game;
import gtsoffenbach.tourdegts.gameinterface.Screen;
import gtsoffenbach.tourdegts.implementations.Level;
import gtsoffenbach.tourdegts.implementations.LevelList;

/**
 * Created by Noli on 11.11.2014.
 */
public class Mechatronik extends Screen {
    Level level;

    public Mechatronik(Game game) {
        super(game);
        level = LevelList.getLevels()[3];
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void paint(float deltaTime) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {

    }
}