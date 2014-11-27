package gtsoffenbach.tourdegts;

import java.util.List;

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
    private BlinkingText gotext,helptext;
    private ElementContainer container;
    private UIButton goButton,helpButton;
    private MultiLineBlinkingText m1,m2,m3;


    public WelcomeScreen(final Game game) {
        super(game);
        bg1 = new Background(0, 0);

        container = new ElementContainer(this, true);
        m1 = new MultiLineBlinkingText(new UIElement(container, 30,AndroidGame.height/4+90,2,2), 2, 2,"Herzlich Willkommen zur",65,Colors.BLACK,1,Assets.gRoboto,1500);
        m2 = new MultiLineBlinkingText(new UIElement(container, 30,AndroidGame.height/4+90+150,2,2), 2, 2,"   Tour de GTS",120,Colors.RED,1,Assets.lobster,2000);
        m3 = new MultiLineBlinkingText(new UIElement(container, 30, AndroidGame.height / 4 + 90 + 420, 2, 2), 2, 2, "  Wissen Sie wie gespielt wird?", 50, Colors.BLACK, 1, Assets.gRoboto, 1600);

        helpButton = new UIButton(container, (AndroidGame.width - Assets.button.getWidth()) / 2, AndroidGame.height - Assets.button.getHeight() - 220) {
            @Override
            public void Click() {
                super.Click();
                goButton.setEnabled(false);
                //fadeOut(container);
                game.setScreen(new HelpScreen(game));
            }
        };
        goButton = new UIButton(container, (AndroidGame.width - Assets.button.getWidth()) / 2, AndroidGame.height - Assets.button.getHeight() - 20) {
            @Override
            public void Click() {
                super.Click();

                fadeOut(container);
            }
        };
        goButton.setGraphics(game.getGraphics());
        gotext = new BlinkingText(goButton, 0, 0, "weiter", 60, Colors.LIGHT1, 1, Assets.gRoboto);
        helptext = new BlinkingText(helpButton, 0, 0, "zur Hilfe", 60, Colors.LIGHT1, 1, Assets.gRoboto);
        fadeIn(container);


    }


    @Override
    public void update(float deltaTime) {
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
        Graphics g = game.getGraphics();
        g.drawImage(Assets.blankscreen, bg1.getBgX(), bg1.getBgY());

        container.updateAll(deltaTime, g);


    }

    @Override
    public void pause() {

    }
    @Override
    public void resume() {
        goButton.setEnabled(true);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {

    }
}
