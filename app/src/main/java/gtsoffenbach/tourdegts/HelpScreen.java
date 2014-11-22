package gtsoffenbach.tourdegts;

import android.graphics.Color;

import java.util.List;

import gtsoffenbach.tourdegts.gameinterface.Game;
import gtsoffenbach.tourdegts.gameinterface.Graphics;
import gtsoffenbach.tourdegts.gameinterface.Input;
import gtsoffenbach.tourdegts.gameinterface.Screen;
import gtsoffenbach.tourdegts.implementations.AndroidGame;

/**
 * Created by Kern on 04.09.2014.
 */
public class HelpScreen extends Screen {
    private ElementContainer container;
    private UIElement backbutton;
    private BlinkingText backtext;

    public HelpScreen(Game game) {
        super(game);

        container = new ElementContainer(this, true);

        backbutton = new UIButton(container, AndroidGame.width/2-Assets.button.getWidth()/2, AndroidGame.height-200) {
            @Override
            public void Click() {
                super.Click();
                goToScreenMenu();
            }
        };
        backbutton.setGraphics(game.getGraphics());
        backtext = new BlinkingText(backbutton, 0, 0, "Zurück", 70, Color.WHITE, 1,Assets.gRoboto);
    }

    private void goToScreenMenu() {
        game.setScreen(new MainMenuScreen(game));
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            container.processClick(event);
        }
    }


    @Override
    public void paint(float deltaTime) {
        backtext.update(deltaTime);
        Graphics g = game.getGraphics();
        g.drawImage(Assets.helpBackground, 0, 0);
        container.updateAll(deltaTime, g);

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
