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
    private MultiLineBlinkingText m;
    private Screen lastscreen;

    public HelpScreen(Game game) {
        super(game);
        lastscreen = game.getCurrentScreen();
        container = new ElementContainer(this, true);

        backbutton = new UIButton(container, AndroidGame.width / 2 - Assets.button.getWidth() / 2, AndroidGame.height - 200) {
            @Override
            public void Click() {
                super.Click();
                goToLastScreen();
            }
        };
        backbutton.setGraphics(game.getGraphics());
        backtext = new BlinkingText(backbutton, 0, 0, "Zurück", 70, Color.WHITE, 1, Assets.gRoboto);
        m = new MultiLineBlinkingText(new UIElement(container, 60, AndroidGame.height / 3 + Assets.nfcHand.getHeight() / 2 + 100, 2, 2), 2, 2, "Halten sie ihr Handy an die Tour de GTS Symbole, welche in den Räumen angebracht sind, um Informationen zu erhalten.", 60, Colors.BLACK, 1, Assets.gRoboto, 1440);

    }

    private void goToLastScreen() {
        lastscreen.resume();
        game.setScreen(lastscreen);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            try {
                Input.TouchEvent event = (Input.TouchEvent) touchEvents.get(i);
                container.processClick(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void paint(float deltaTime) {
        backtext.update(deltaTime);
        Graphics g = game.getGraphics();
        g.drawImage(Assets.helpBackground, 0, 0);
        g.drawImage(Assets.nfcHand, AndroidGame.width / 2 - Assets.nfcHand.getWidth() / 2, AndroidGame.height / 3 - Assets.nfcHand.getHeight() / 2);

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
        goToLastScreen();
    }
}
