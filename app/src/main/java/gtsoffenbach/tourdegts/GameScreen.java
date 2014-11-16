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
    private BlinkingText blink,textname,textraum,textlehrer;
    private MultiLineBlinkingText textinfo;

    public GameScreen(final Game game, int level) {
        super(game);
        this.currentLevel = level;
        bg1 = new Background(0,0);
        container = new ElementContainer(this, true);
        /*gotoProgressButton = new UIButton(container, (AndroidGame.width - Assets.button.getWidth()) / 2,200) {
            @Override
            public void Click() {
                super.Click();

                //game.setScreen(new MathGameScreen(game));
                game.setScreen(new ProgressScreen(game, currentLevel, currentLevel));
                //game.setScreen(new ProgressScreen(game, currentLevel, currentLevel));
            }
        };*/
        //gotoProgressButton.setGraphics(game.getGraphics());
        //gotoProgresstext = new BlinkingText(gotoProgressButton, 0, 0, "Fortschritt", 70, Colors.LIGHT1, 1,Assets.gRoboto);
        gameprogressbar = new UIProgressbar(container,(AndroidGame.width - Assets.progressbar.getWidth()) / 2,177,40,new int[0],Colors.ALPHA33){
            @Override
            public void Click() {
                super.Click();
                game.setScreen(new ProgressScreen(game, currentLevel, currentLevel));
                for(int i=0; i<SaveGame.levels.length;i++){
                   SaveGame.levels[i].setUnlocked(true);
                }
            }
        };
        gameprogressbar.setGraphics(game.getGraphics());
        blink = new BlinkingText(new UIElement(container, 35,20, 2, 2), 2, 2, "Tag: " + currentLevel, 30, Colors.RED, 1, Assets.standard);
        textname= new MultiLineBlinkingText(new UIElement(container, 100,480, 2, 2), 2, 2, SaveGame.levels[currentLevel].getAnzeigeName(), 80, Colors.BLACK, 1, Assets.gRoboto,1300);
        textraum= new MultiLineBlinkingText(new UIElement(container, 100,630, 2, 2), 2, 2, SaveGame.levels[currentLevel].getRaum(), 50, Colors.BLACK, 1, Assets.gRoboto,1300);
        textlehrer= new MultiLineBlinkingText(new UIElement(container, 100,690, 2, 2), 2, 2, SaveGame.levels[currentLevel].getLehrer(), 50, Colors.BLACK, 1, Assets.gRoboto,1300);
        textinfo= new MultiLineBlinkingText(new UIElement(container, 100,760, 2, 2), 2, 2, "Hi, mein name ist peter, ich wohne in petersbach und esse gerne alfreds, machmal treffe ich einen Donaudampfschiffahrtsgesellschafts Kapitän",50, Colors.BLACK, 1, Assets.gRoboto,1300);
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
        g.drawImage(Assets.background, 0,0);
//        gotoProgresstext.update(deltaTime);
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