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

/**
 * Created by Noli on 05.08.2014.
 */
public class GameScreen extends Screen {
    private static Background bg1, bg2;
    GameState state = GameState.Ready;

    // Variable Setup
    int livesLeft = 1;
    Paint paint, paint2, paint3;
    private Image currentSprite;
    //private Animation chest_anim;
    //private ArrayList tilearray = new ArrayList();
    private BlinkingText unlocktext,gotoProgresstext;
    private UIProgressbar gameprogressbar;
    private ElementContainer container;
    private UIButton unlockbutton,gotoProgressButton;
    private int currentLevel;
    private BlinkingText blink;

    public GameScreen(final Game game, int level) {
        super(game);
        this.currentLevel = level;

        // Initialize game objects here

        bg1 = new Background(0, 0);
        bg2 = new Background(AndroidGame.width, 0);

        // Defining a paint object
        paint = new Paint();
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);


        paint2 = new Paint();
        paint2.setTextSize(100);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setAntiAlias(true);
        paint2.setColor(Color.WHITE);

        paint3 = new Paint();
        paint3.setTextSize(100);
        paint3.setTextAlign(Paint.Align.CENTER);
        paint3.setAntiAlias(true);
        paint3.setColor(Color.BLACK);
        paint3.setAlpha(50);


        //currentSprite = chest_anim.getImage();

        container = new ElementContainer(this, true);
        unlockbutton = new UIButton(container, (AndroidGame.width - Assets.button.getWidth()) / 2, AndroidGame.height - Assets.button.getHeight()-300) {
            @Override
            public void Click() {
                super.Click();
                for (int i = 0; i < SaveGame.levels.length; i++) {
                    SaveGame.levels[i].setUnlocked(true);
                }
            }
        };
        unlockbutton.setGraphics(game.getGraphics());
        gotoProgressButton = new UIButton(container, (AndroidGame.width - Assets.button.getWidth()) / 2, AndroidGame.height - Assets.button.getHeight()-100) {
            @Override
            public void Click() {
                super.Click();
                game.setScreen(new ProgressScreen(game, currentLevel, currentLevel));
            }
        };
        gotoProgressButton.setGraphics(game.getGraphics());
        gameprogressbar = new UIProgressbar(container,(AndroidGame.width - Assets.button.getWidth()) / 2,AndroidGame.height - Assets.button.getHeight()-500,40,new int[0]);
        gameprogressbar.setGraphics(game.getGraphics());

        blink = new BlinkingText(new UIElement(container, AndroidGame.width / 2, AndroidGame.height -50, 2, 2), 2, 2, "Level: " + currentLevel, 80, Color.MAGENTA, 1, Assets.standard);



        //container.addElement(firstbutton);

        //game.getGraphics().drawString("Tap to Start.", 400, 240, paint);
        unlocktext = new BlinkingText(unlockbutton, 0, 0, "Alles Freischalten", 50, Color.BLACK, 1,Assets.lobster);
        gotoProgresstext = new BlinkingText(gotoProgressButton, 0, 0, "Fortschritt", 50, Color.BLACK, 1,Assets.lobster);
        //UIElement chest_container = new UIElement(container, AndroidGame.width / 2 - Assets.chest[0].getWidth() / 2, 10, Assets.chest[0].getWidth(), Assets.chest[0].getHeight());
        //Chest chest = new Chest(chest_container, chest_container.getRectangle().right, chest_container.getRectangle().bottom, 150, 5000);
        state = GameState.Running;
    }


    @Override
    public void update(float deltaTime) {
        List touchEvents = game.getInput().getTouchEvents();

        // We have four separate update methods in this example.
        // Depending on the state of the game, we call different update methods.
        // Refer to Unit 3's code. We did a similar thing without separating the
        // update methods.


        if (state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        /*
        if (state == GameState.Ready)
            updateReady(touchEvents);

        if (state == GameState.Paused)
            updatePaused(touchEvents);*/
    }

    private void updateReady(List touchEvents) {

        // This example starts with a "Ready" screen.
        // When the user touches the screen, the game begins.
        // state now becomes GameState.Running.
        // Now the updateRunning() method will be called!

        if (touchEvents.size() > 0)
            state = GameState.Running;
    }

    private void updateRunning(List touchEvents, float deltaTime) {

        // This is identical to the update() method from our Unit 2/3 game.

        // 1. All touch input is handled here:
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = (TouchEvent) touchEvents.get(i);
            container.processClick(event);
        }


        // 3. Call individual update() methods here.
        // This is where all the game updates happen.
        // For example, robot.update();
        //text.update(deltaTime);

        bg1.update();
        bg2.update();
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
                    nullify();
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
        unlocktext.update(deltaTime);

        if (state == GameState.Running)
            drawRunningUI(deltaTime);

    }

    public void animate() {
//        anim.update(10);
        //       hanim.update(50);
        //chest_anim.update(50);
    }

    private void nullify() {

        // Set all variables to null. You will be recreating them in the
        // constructor.
        paint = null;
        bg1 = null;
        bg2 = null;
        currentSprite = null;
        //chest_anim = null;

        // Call garbage collector to clean up memory.
        System.gc();

    }


    private void drawRunningUI(float deltaTime) {
        Graphics g = game.getGraphics();
        container.updateAll(deltaTime, g);

        //g.drawImage(currentSprite, 400, 800);
        //currentSprite = chest_anim.getImage();

        /*g.drawImage(Assets.button, 0, 285, 0, 0, 60, 60);
        g.drawImage(Assets.button, 0, 350, 0, 65, 60, 60);
        g.drawImage(Assets.button, 0, 415, 0, 130, 60, 60);
        g.drawImage(Assets.button, 0, 0, 0, 195, 60, 60);*/


    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        // Darken the entire screen so you can display the Paused screen.
        g.drawARGB(155, 0, 0, 0);
        g.drawString("Resume", 400, 165, paint2);
        g.drawString("Menu", 400, 360, paint2);

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
        // TODO Auto-generated method stub
        game.setScreen(new MainMenuScreen(game));

    }

    enum GameState {
        Ready, Running, Paused
    }

}