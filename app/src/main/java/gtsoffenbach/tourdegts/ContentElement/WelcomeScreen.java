package gtsoffenbach.tourdegts.ContentElement;

import android.graphics.Color;

import java.util.List;

import gtsoffenbach.tourdegts.Assets;
import gtsoffenbach.tourdegts.Background;
import gtsoffenbach.tourdegts.BlinkingText;
import gtsoffenbach.tourdegts.ElementContainer;
import gtsoffenbach.tourdegts.UIButton;
import gtsoffenbach.tourdegts.gameinterface.Game;
import gtsoffenbach.tourdegts.gameinterface.Graphics;
import gtsoffenbach.tourdegts.gameinterface.Input;
import gtsoffenbach.tourdegts.gameinterface.Screen;
import gtsoffenbach.tourdegts.implementations.AndroidGame;

/**
 * Created by Noli on 05.10.2014.
 */


//EVENT NO1
public class WelcomeScreen extends Screen {

    private static Background bg1, bg2;
    private BlinkingText welcometext;
    private ElementContainer container;
    private UIButton goButton;

    private Thread thread;


    //private int transparency = 0;
    //private UIElement element;

    public WelcomeScreen(final Game game) {
        super(game);
        bg1 = new Background(0, 0);

        container = new ElementContainer(this, true);


        //element = new UIElement(container, 0, 0, 0, 0);

        goButton = new UIButton(container, (AndroidGame.width - Assets.button.getWidth()) / 2, AndroidGame.height - Assets.button.getHeight() - 300) {
            @Override
            public void Click() {
                super.Click();
                fadeOut(container);


               /* while(true){
                    fadeaway();
                    if(!totalelement.isFade()){
                        game.getSave().save();
                        game.setScreen(new GameScreen(game, 0)); //init first Level
                    }
                }*/
                /*while(true){
                    if(!totalelement.isFade()){
                        game.getSave().save();
                        game.setScreen(new GameScreen(game, 0)); //init first Level
                    }
                }    */

            }
        };
        goButton.setGraphics(game.getGraphics());
        welcometext = new BlinkingText(goButton, 0, 0, "Herzlich Wilkommen zur 'Tour de GTS'", 44, Color.parseColor("#324C95"), 1, Assets.lobster);
        fadeIn(container);


    }


    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            container.processClick(event);
        }
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.background, bg1.getBgX(), bg1.getBgY());


        container.updateAll(deltaTime, g);

    }

    @Override
    public void pause() {

    }


   /* public void fadeIn() {
        for (int i = 255; i > 0; i--) {
            game.getGraphics().drawARGB(i, 0, 0, 0);
            Thread.sleep(100);
        }
    }

    public void fadeOut() {
        for (int i = 0; i > 255; i++) {
            game.getGraphics().drawARGB(i, 0, 0, 0);
            Thread.sleep(100);
        }
    }*/

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
