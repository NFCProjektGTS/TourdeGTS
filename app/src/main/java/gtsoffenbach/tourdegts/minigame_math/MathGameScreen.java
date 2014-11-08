package gtsoffenbach.tourdegts.minigame_math;

import android.graphics.Color;
import android.graphics.drawable.shapes.RectShape;

import java.util.ArrayList;

import gtsoffenbach.tourdegts.Assets;
import gtsoffenbach.tourdegts.Background;
import gtsoffenbach.tourdegts.BlinkingText;
import gtsoffenbach.tourdegts.ElementContainer;
import gtsoffenbach.tourdegts.GameScreen;
import gtsoffenbach.tourdegts.UIElement;
import gtsoffenbach.tourdegts.gameinterface.Game;
import gtsoffenbach.tourdegts.gameinterface.Graphics;
import gtsoffenbach.tourdegts.gameinterface.Screen;
import gtsoffenbach.tourdegts.implementations.AndroidGame;

/**
 * Created by Kern on 07.11.2014.
 */
public class MathGameScreen extends Screen {
    MathGameLogic mgl;
    Background background;
    ElementContainer container;
    BlinkingText startTimer;
    UIButton job, res1,res2,res3,res4;
    private boolean justSwitchedToRunning = true;
    private boolean justSwitchedToEnd = true;

    public MathGameScreen(final Game game) {
        super(game);
        mgl = new MathGameLogic(game, this);
        background = new Background(0, 0);
        container = new ElementContainer(this, true);
        startTimer = new BlinkingText(new UIElement(container, AndroidGame.width / 2, AndroidGame.height / 2, 2, 2), 2, 2, "Timer", 150, Color.RED, 1, Assets.lobster);

        //mgl.getJop();
        //TODO TOP g.drawRect(50,200,700,175, Color.BLUE);


        int[] jobs = mgl.getJop();
        String vorzeichen;
        if(jobs[0]==0){
            vorzeichen = "-";
        }else {
            vorzeichen = "+";
        }
        job = new UIButton(container,50,400,700,350,jobs[1]+vorzeichen+jobs[2]+"="+jobs[3]);
        res1 = new UIButton(container,50,800,325,200,jobs[4]+""){};
        res2 = new UIButton(container,425,800,325,200,jobs[5]+""){};
        res3 = new UIButton(container,50,1050,325,200,jobs[6]+""){};
        res4 = new UIButton(container,425,1050,325,200,jobs[7]+""){};

        job.setGraphics(game.getGraphics());
        res1.setGraphics(game.getGraphics());
        res2.setGraphics(game.getGraphics());
        res3.setGraphics(game.getGraphics());
        res4.setGraphics(game.getGraphics());
/*

        g.drawRect(50,400,700,350, Color.BLUE);

        g.drawRect(50,800,325,200, Color.GRAY);
        g.drawRect(425,800,325,200, Color.GRAY);
        g.drawRect(50,1050,325,200, Color.GRAY);
        g.drawRect(425,1050,325,200, Color.GRAY);*/
    }

    @Override
    public void update(float deltaTime) {
        if (mgl.isStarting) {

            mgl.subStartTimer(deltaTime);
            startTimer.setMsg((int) mgl.getStartTimer() / 100 + 1+"");

        } else if (mgl.isRunning) {
            if(justSwitchedToRunning){

                startTimer.dismiss();
               justSwitchedToRunning=false;
            }



            mgl.subGameTimer(deltaTime);

        } else if (mgl.isEnded) {

        }


        Graphics g = game.getGraphics();
        container.updateAll(deltaTime, g);
    }


    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.background, background.getBgX(), background.getBgY());
        if(mgl.isStarting){
            startTimer.update(deltaTime);
        }
        if(mgl.isRunning){
            g.drawRect(50,200,700,175, Color.BLUE);

            g.drawRect(50,400,700,350, Color.RED);

            g.drawRect(50,800,325,200, Color.GRAY);
            g.drawRect(425,800,325,200, Color.GRAY);
            g.drawRect(50,1050,325,200, Color.GRAY);
            g.drawRect(425,1050,325,200, Color.GRAY);

            job.getBlinkingText().update(deltaTime);
            res1.getBlinkingText().update(deltaTime);
            res2.getBlinkingText().update(deltaTime);
            res3.getBlinkingText().update(deltaTime);
            res4.getBlinkingText().update(deltaTime);

        }
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
        game.setScreen(new GameScreen(game, 0));
    }
}
