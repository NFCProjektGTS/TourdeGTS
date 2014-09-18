package gtsoffenbach.tourdegts;

import java.util.List;

import gtsoffenbach.tourdegts.gameinterface.Game;
import gtsoffenbach.tourdegts.gameinterface.Graphics;
import gtsoffenbach.tourdegts.gameinterface.Input;
import gtsoffenbach.tourdegts.gameinterface.Screen;

/**
 * Created by Kern on 04.09.2014.
 */
public class HelpScreen extends Screen {
    private ElementContainer container;
    private UIElement button_back;

    public HelpScreen(Game game) {
        super(game);
        container = new ElementContainer(this, true);
        button_back = new UIButton(container, 104, 479) {
            @Override
            public void Click() {
                super.Click();
                goToScreenMenu();
            }
        };
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
