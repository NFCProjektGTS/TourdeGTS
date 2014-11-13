package gtsoffenbach.tourdegts;


import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.List;

import gtsoffenbach.tourdegts.gameinterface.Game;
import gtsoffenbach.tourdegts.gameinterface.Graphics;
import gtsoffenbach.tourdegts.gameinterface.Image;
import gtsoffenbach.tourdegts.gameinterface.Input.TouchEvent;
import gtsoffenbach.tourdegts.gameinterface.Screen;
import gtsoffenbach.tourdegts.implementations.AndroidGame;
import gtsoffenbach.tourdegts.implementations.SaveGame;
import gtsoffenbach.tourdegts.minigame_math.MathGameScreen;

/**
 * Created by Noli on 05.08.2014.
 */
public class GameScreen extends Screen {
    private static Background bg1;
    GameState state = GameState.Ready;


    private BlinkingText gotoProgresstext;
    private UIProgressbar gameprogressbar;
    private ElementContainer container;
    private UIButton gotoProgressButton;
    private int currentLevel;
    private BlinkingText blink;

    public GameScreen(final Game game, int level) {
        super(game);
        this.currentLevel = level;
        bg1 = new Background(0,0);
        container = new ElementContainer(this, true);
        gotoProgressButton = new UIButton(container, (AndroidGame.width - Assets.button.getWidth()) / 2,350) {
            @Override
            public void Click() {
                super.Click();
                //game.setScreen(new MathGameScreen(game));
                game.setScreen(new ProgressScreen(game, currentLevel, currentLevel));
                //game.setScreen(new ProgressScreen(game, currentLevel, currentLevel));
            }
        };



        gotoProgressButton.setGraphics(game.getGraphics());
        gameprogressbar = new UIProgressbar(container,(AndroidGame.width - Assets.progressbar.getWidth()) / 2,Assets.button.getHeight(),40,new int[0],Colors.ALPHA33);
        gameprogressbar.setGraphics(game.getGraphics());
        blink = new BlinkingText(new UIElement(container, 35,20, 2, 2), 2, 2, "Tag: " + currentLevel, 30, Colors.BLACK, 1, Assets.standard);
        gotoProgresstext = new BlinkingText(gotoProgressButton, 0, 0, "Fortschritt", 50, Color.BLACK, 1,Assets.lobster);
        state = GameState.Running;
    }


    @Override
    public void update(float deltaTime) {
        List touchEvents = game.getInput().getTouchEvents();
        if (state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
    }

    private void updateReady(List touchEvents) {
        if (touchEvents.size() > 0)
            state = GameState.Running;
    }

    private void updateRunning(List touchEvents, float deltaTime) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = (TouchEvent) touchEvents.get(i);
            container.processClick(event);
        }
        bg1.update();
        animate();
    }


    private void updatePaused(List touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = (TouchEvent) touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {    //TODO TOUCH EVENTS
                if (Utils.inBounds(event, new Rect(0, 0, 800, 240))) {
                    if (!Utils.inBounds(event, new Rect(0, 0, 35, 35))) {
                        resume();
                    }
                }
                if (Utils.inBounds(event, new Rect(0, 240, 800, 240))) {
                    goToMenu();
                }
            }
        }
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.background, bg1.getBgX(), bg1.getBgY());
        gotoProgresstext.update(deltaTime);
        if (state == GameState.Running)
            drawRunningUI(deltaTime);
    }

    public void animate() {
        //anim.update(10);
        //hanim.update(50);
        //chest_anim.update(50);
    }



    private void drawRunningUI(float deltaTime) {
        Graphics g = game.getGraphics();
        container.updateAll(deltaTime, g);
    }


    @Override
    public void pause() {
        if (state == GameState.Running)
            state = GameState.Paused;

    }

    public void back() {
        game.setScreen(new MainMenuScreen(game));
    }

    @Override
    public void resume() {
        if (state == GameState.Paused)
            state = GameState.Running;
    }

    @Override
    public void dispose() {
    }

    @Override
    public void backButton() {
        back();
    }

    private void goToMenu() {
        game.setScreen(new MainMenuScreen(game));
    }

    enum GameState {
        Ready, Running, Paused
    }

}