package gtsoffenbach.tourdegts.minigame_math;


import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

import gtsoffenbach.tourdegts.Assets;
import gtsoffenbach.tourdegts.Background;
import gtsoffenbach.tourdegts.BlinkingText;
import gtsoffenbach.tourdegts.Colors;
import gtsoffenbach.tourdegts.ElementContainer;
import gtsoffenbach.tourdegts.GameScreen;
import gtsoffenbach.tourdegts.UIElement;
import gtsoffenbach.tourdegts.gameinterface.Game;
import gtsoffenbach.tourdegts.gameinterface.Graphics;
import gtsoffenbach.tourdegts.gameinterface.Input;
import gtsoffenbach.tourdegts.gameinterface.Screen;
import gtsoffenbach.tourdegts.implementations.AndroidGame;

/**
 * Created by Kern on 07.11.2014.
 */
public class MathGameScreen extends Screen {
    MathGameLogic mgl;
    Background background;
    ElementContainer container;
    BlinkingText startTimer, gameTimer, correctRate, noutofn, job, endtext0, endtext1, endtext2, retrytext, backtext, hint;
    UIButton res1, res2, res3, res4;
    gtsoffenbach.tourdegts.UIButton back, retry;
    private boolean justSwitchedToRunning = true;
    private boolean justSwitchedToEnd = true;
    private int[] acitveJob = null;

    public MathGameScreen(final Game game) {
        super(game);
        mgl = new MathGameLogic(game, this);
        background = new Background(0, 0);
        container = new ElementContainer(this, true);
        startTimer = new BlinkingText(new UIElement(container, AndroidGame.width / 2, AndroidGame.height / 2, 2, 2), 2, 2, "Timer", 350, Colors.BLACK, 1, Assets.lobster);

        noutofn = new BlinkingText(new UIElement(container, 50, 200, 233, 175), 2, 2, "0 / 0", 70, Colors.BLACK, 1, Assets.lobster);
        correctRate = new BlinkingText(new UIElement(container, 50 + 233, 200, 233, 175), 2, 2, "100%", 70, Colors.BLACK, 1, Assets.lobster);
        gameTimer = new BlinkingText(new UIElement(container, 50 + 466, 200, 233, 175), 2, 2, "30s", 70, Colors.BLACK, 1, Assets.lobster);

        hint = new BlinkingText(new UIElement(container, AndroidGame.width / 2, -22, 0, 0), 2, 2, "", 100, Colors.BLACK, 1, Assets.standard);

        endtext0 = new BlinkingText(new UIElement(container, AndroidGame.width / 2, AndroidGame.height / 2 - 300, 2, 2), 2, 2, "a", 150, Colors.BLACK, 1, Assets.lobster);
        endtext1 = new BlinkingText(new UIElement(container, AndroidGame.width / 2, AndroidGame.height / 2 - 150, 2, 2), 2, 2, "b", 150, Colors.GREEN, 1, Assets.lobster);
        endtext2 = new BlinkingText(new UIElement(container, AndroidGame.width / 2, AndroidGame.height / 2, 2, 2), 2, 2, "c", 150, Colors.BLACK, 1, Assets.lobster);

        back = new gtsoffenbach.tourdegts.UIButton(container, (AndroidGame.width - Assets.button.getWidth()) / 2, AndroidGame.height - Assets.button.getHeight() - 50) {
            @Override
            public void Click() {
                super.Click();
                backButton();
            }
        };
        retry = new gtsoffenbach.tourdegts.UIButton(container, (AndroidGame.width - Assets.button.getWidth()) / 2, AndroidGame.height - Assets.button.getHeight() - 225) {
            @Override
            public void Click() {
                super.Click();
                game.setScreen(new MathGameScreen(game));
            }
        };
        back.setEnabled(false);
        retry.setEnabled(false);
        backtext = new BlinkingText(back, 0, 0, "Zurück", 100, Color.BLACK, 1, Assets.lobster);
        retrytext = new BlinkingText(retry, 0, 0, "Nochmal", 100, Color.BLACK, 1, Assets.lobster);
        back.setGraphics(game.getGraphics());
        retry.setGraphics(game.getGraphics());


        job = new BlinkingText(new UIElement(container, 50, 400, 700, 350), 2, 2, "", 200, Colors.BLACK, 1, Assets.lobster);
        res1 = new UIButton(container, 50, 800, 325, 200, "", Colors.BLUE) {
            @Override
            public void Click() {
                super.Click();
                resultClicked(0);
            }
        };
        res2 = new UIButton(container, 425, 800, 325, 200, "", Colors.BLUE) {
            @Override
            public void Click() {
                super.Click();
                resultClicked(1);
            }
        };
        res3 = new UIButton(container, 50, 1050, 325, 200, "", Colors.BLUE) {
            @Override
            public void Click() {
                super.Click();
                resultClicked(2);
            }
        };
        res4 = new UIButton(container, 425, 1050, 325, 200, "", Colors.BLUE) {
            @Override
            public void Click() {
                super.Click();
                resultClicked(3);
            }
        };


        res1.setGraphics(game.getGraphics());
        res2.setGraphics(game.getGraphics());
        res3.setGraphics(game.getGraphics());
        res4.setGraphics(game.getGraphics());
    }

    void resultClicked(int res) {
        if (mgl.isRunning) {
            if (res == acitveJob[3]) {
                mgl.addResult(true);
            } else {
                mgl.addResult(false);
            }
            ArrayList<Boolean> results = mgl.getResults();
            int correct = 0;
            for (int i = 0; i < results.size(); i++) {
                if (results.get(i)) {
                    correct += 1;
                }
            }
            noutofn.setMsg(correct + " / " + results.size());

            correctRate.setMsg(((int) (((double) correct) / ((double) results.size()) * 100)) + "%");
            loadNewJob();
        }
    }

    private void loadNewJob() {
        acitveJob = mgl.getJop();
        String vorzeichen;
        if (acitveJob[0] == 0) {
            vorzeichen = "-";
        } else {
            vorzeichen = "+";
        }
        job.setMsg(acitveJob[1] + vorzeichen + acitveJob[2] + "=");
        res1.getBlinkingText().setMsg(acitveJob[4] + "");
        res2.getBlinkingText().setMsg(acitveJob[5] + "");
        res3.getBlinkingText().setMsg(acitveJob[6] + "");
        res4.getBlinkingText().setMsg(acitveJob[7] + "");
        String hintString = "";
        for (int i = 0; i < acitveJob[3]; i++) {
            hintString = hintString + ".";
        }
        hint.setMsg(hintString);
    }

    @Override
    public void update(float deltaTime) {
        List touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            try {
                Input.TouchEvent event = (Input.TouchEvent) touchEvents.get(i);
                container.processClick(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (mgl.isStarting) {

            mgl.subStartTimer(deltaTime);
            startTimer.setMsg((int) mgl.getStartTimer() / 100 + 1 + "");

        } else if (mgl.isRunning) {
            if (justSwitchedToRunning) {
                loadNewJob();
                startTimer.dismiss();
                justSwitchedToRunning = false;
            }

            mgl.subGameTimer(deltaTime);
            gameTimer.setMsg((int) mgl.getGameTimer() / 100 + 1 + "s");

        } else if (mgl.isEnded) {
            if (justSwitchedToEnd) {
                ArrayList<Boolean> results = mgl.getResults();
                int correct = 0;
                for (int i = 0; i < results.size(); i++) {
                    if (results.get(i)) {
                        correct += 1;
                    }
                }
                String noutofn = (correct + " von " + results.size());

                int cRate = ((int) (((double) correct) / ((double) results.size()) * 100));
                if (cRate >= 50) {
                    endtext1.setColor(Colors.GREEN);
                } else {
                    endtext1.setColor(Colors.RED);
                }
                endtext0.setMsg("Du hast");
                endtext1.setMsg(noutofn);
                endtext2.setMsg("richtig!");
                back.setEnabled(true);
                retry.setEnabled(true);
            }
        }


        Graphics g = game.getGraphics();
        container.updateAll(deltaTime, g);
    }


    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.background, background.getBgX(), background.getBgY());
        if (mgl.isStarting) {

            noutofn.update(deltaTime);
            correctRate.update(deltaTime);
            gameTimer.update(deltaTime);
            g.drawRect(50, 350, 700, 400, Colors.RED);
            res1.update(deltaTime);
            res2.update(deltaTime);
            res3.update(deltaTime);
            res4.update(deltaTime);

            startTimer.update(deltaTime);

        }
        if (mgl.isRunning) {
            g.drawRect(50, 350, 700, 400, Colors.RED);
            noutofn.update(deltaTime);
            correctRate.update(deltaTime);
            gameTimer.update(deltaTime);
            job.update(deltaTime);
            hint.update(deltaTime);


            res1.update(deltaTime);
            res2.update(deltaTime);
            res3.update(deltaTime);
            res4.update(deltaTime);


            res1.getBlinkingText().update(deltaTime);
            res2.getBlinkingText().update(deltaTime);
            res3.getBlinkingText().update(deltaTime);
            res4.getBlinkingText().update(deltaTime);
        }
        if (mgl.isEnded) {
            endtext0.update(deltaTime);
            endtext1.update(deltaTime);
            endtext2.update(deltaTime);
            back.update(deltaTime);
            retry.update(deltaTime);
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
