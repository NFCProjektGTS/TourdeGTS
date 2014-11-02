package gtsoffenbach.tourdegts;

import gtsoffenbach.tourdegts.gameinterface.Game;
import gtsoffenbach.tourdegts.gameinterface.Graphics;
import gtsoffenbach.tourdegts.gameinterface.Graphics.ImageFormat;
import gtsoffenbach.tourdegts.gameinterface.Image;
import gtsoffenbach.tourdegts.gameinterface.Screen;
import gtsoffenbach.tourdegts.implementations.AndroidGame;

/**
 * Created by Noli on 05.08.2014.
 */
public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
        game.getSave().loadGame();
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.menu = g.newImage("mainmenu.png", ImageFormat.RGB565);
        Assets.background = g.newImage("gamescreen.png", ImageFormat.RGB565);

        Assets.lock = g.newImage("lock.png", ImageFormat.RGB565);
        Assets.infobox = g.newImage("infobox.png", ImageFormat.RGB565);
        Assets.button = g.newImage("button.png", ImageFormat.RGB565);
        Assets.button_pressed = g.newImage("button_pressed.png", ImageFormat.RGB565);
        Assets.chest = new Image[]{g.newImage("chest/chest001.png", ImageFormat.RGB565), g.newImage("chest/chest002.png", ImageFormat.RGB565), g.newImage("chest/chest003.png", ImageFormat.RGB565), g.newImage("chest/chest004.png", ImageFormat.RGB565), g.newImage("chest/chest005.png", ImageFormat.RGB565), g.newImage("chest/chest006.png", ImageFormat.RGB565), g.newImage("chest/chest007.png", ImageFormat.RGB565), g.newImage("chest/chest008.png", ImageFormat.RGB565), g.newImage("chest/chest009.png", ImageFormat.RGB565), g.newImage("chest/chest010.png", ImageFormat.RGB565), g.newImage("chest/chest011.png", ImageFormat.RGB565), g.newImage("chest/chest012.png", ImageFormat.RGB565)};
        Assets.chest_half = new Image[]{g.newImage("chest/chest001.png", ImageFormat.RGB565), g.newImage("chest/chest002.png", ImageFormat.RGB565), g.newImage("chest/chest003.png", ImageFormat.RGB565), g.newImage("chest/chest004.png", ImageFormat.RGB565), g.newImage("chest/chest005.png", ImageFormat.RGB565), g.newImage("chest/chest006.png", ImageFormat.RGB565), g.newImage("chest/chest007.png", ImageFormat.RGB565), g.newImage("chest/chest008.png", ImageFormat.RGB565), g.newImage("chest/chest009.png", ImageFormat.RGB565)};
        Assets.progressBackground = g.newImage("progressscreen.png", ImageFormat.RGB565);
        Assets.helpBackground = g.newImage("helpscreen.png", ImageFormat.RGB565);
        Assets.settingsBackground = g.newImage("settingsscreen.png", ImageFormat.RGB565);
        Assets.button_small = g.newImage("button_small.png", ImageFormat.RGB565);
        Assets.button_small_pressed = g.newImage("button_small_pressed.png", ImageFormat.RGB565);


        //This is how you would load a sound if you had one.
        //Assets.click = game.getAudio().createSound("explode.ogg");


        game.setScreen(new MainMenuScreen(game));

    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        //g.drawImage(Assets.splash, 0, 0);
        //g.drawImage(Assets.splash, 0, 0, AndroidGame.width, AndroidGame.height, Assets.splash.getWidth(), Assets.splash.getHeight());
        g.drawScaledImage(Assets.splash, 0, 0, AndroidGame.width, AndroidGame.height, 0, 0, Assets.splash.getWidth(), Assets.splash.getHeight());
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