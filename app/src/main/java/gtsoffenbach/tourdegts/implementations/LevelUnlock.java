package gtsoffenbach.tourdegts.implementations;

import gtsoffenbach.tourdegts.ProgressScreen;
import gtsoffenbach.tourdegts.gameinterface.Game;

/**
 * Created by Noli on 04.09.2014.
 */
public class LevelUnlock {


    public static void unlock(Game game, int level) {
        if (!SaveGame.levels[level].isUnlocked()) {
            //GameScreen lastscreen = (GameScreen) game.getCurrentScreen();
            SaveGame.levels[level].setUnlocked(true);
            game.getSave().save();
            ProgressScreen unlockscreen = new ProgressScreen(game, 0, level);
            game.setScreen(unlockscreen);
            unlockscreen.loadChest(level);
        }
        //game.setScreen(lastscreen);
    }



    /*public void lock(){

    }*/
}
