package gtsoffenbach.tourdegts;

import android.graphics.Color;

import java.util.List;

import gtsoffenbach.tourdegts.gameinterface.Game;
import gtsoffenbach.tourdegts.gameinterface.Graphics;
import gtsoffenbach.tourdegts.gameinterface.Input;
import gtsoffenbach.tourdegts.gameinterface.Screen;

/**
 * Created by Kern on 04.09.2014.
 */
public class SettingsScreen extends Screen {
    private ElementContainer container;
    private UIElement button_back, button_reset;

    public SettingsScreen(final Game game) {
        super(game);
        container = new ElementContainer(this, true);
        button_back = new UIButton(container, 104, 479) {
            @Override
            public void Click() {
                super.Click();
                goToScreenMenu();
            }
        };
        button_reset = new UIButton(container, 104, 689) {
            @Override
            public void Click() {
                super.Click();
                game.getSave().delete();
                game.setScreen(new LoadingScreen(game));
                //game.getSave().loadGame();
                //game.getSave().setNewGame(true);
            }
        };
        new BlinkingText(button_reset, 0, 0, "Spiel zurücksetzen", 70, Color.WHITE, 1);
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
        Graphics g = game.getGraphics();
        g.drawImage(Assets.settingsBackground, 0, 0);
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
