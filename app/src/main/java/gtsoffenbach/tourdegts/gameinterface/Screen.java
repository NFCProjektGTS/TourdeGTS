package gtsoffenbach.tourdegts.gameinterface;

import gtsoffenbach.tourdegts.ElementContainer;
import gtsoffenbach.tourdegts.GameScreen;
import gtsoffenbach.tourdegts.UIElement;
import gtsoffenbach.tourdegts.implementations.AndroidGame;

/**
 * Created by Noli on 14.07.2014.
 */
public abstract class Screen {
    protected final Game game;
    protected UIElement totalelement;
    protected Thread thread;

    public Screen(Game game) {
        this.game = game;
    }

    public abstract void update(float deltaTime);

    public abstract void paint(float deltaTime);

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();

    public abstract void backButton();

    public void fadeIn(ElementContainer container) {         ///BUGG FIX TIMEGAP OMG THAT BUG CRAZY SHIT!

        totalelement = new UIElement(container, 0, 0, AndroidGame.width, AndroidGame.height);
        totalelement.fadeIn(true);
        totalelement.setBringtoforeground(false);

        thread = new Thread(new Updater());
        thread.start();
    }

    public void fadeOut(ElementContainer container) {
        totalelement = new UIElement(container, 0, 0, AndroidGame.width, AndroidGame.height);
        totalelement.fadeIn(false);
        totalelement.setBringtoforeground(false);

        thread = new Thread(new Fader());
        thread.start();
    }

    private class Fader implements Runnable {

        @Override
        public void run() {
            while (true) {
                if (totalelement != null) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!totalelement.isFade()) {
                        //  totalelement.getContainer().remove(totalelement);

                        // totalelement = null;
                        Thread.currentThread().interrupt();
                        game.getSave().save();
                        game.setScreen(new GameScreen(game, 0));
                        return;
                    }

                }


            }
        }
    }

    private class Updater implements Runnable {

        @Override
        public void run() {
            while (true) {
                if (totalelement != null) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!totalelement.isFade()) {
                        totalelement.getContainer().remove(totalelement);

                        totalelement = null;
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

            }
        }
    }


}
