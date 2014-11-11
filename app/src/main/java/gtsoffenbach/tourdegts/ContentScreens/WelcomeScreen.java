package gtsoffenbach.tourdegts.ContentScreens;

import android.graphics.Color;

import gtsoffenbach.tourdegts.Assets;
import gtsoffenbach.tourdegts.Background;
import gtsoffenbach.tourdegts.BlinkingText;
import gtsoffenbach.tourdegts.ElementContainer;
import gtsoffenbach.tourdegts.GameScreen;
import gtsoffenbach.tourdegts.UIButton;
import gtsoffenbach.tourdegts.UIElement;
import gtsoffenbach.tourdegts.gameinterface.Game;
import gtsoffenbach.tourdegts.gameinterface.Graphics;
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
    private UIElement element;

    public WelcomeScreen(final Game game) {
        super(game);
        bg1 = new Background(0, 0);
        bg2 = new Background(AndroidGame.width, 0);
        container = new ElementContainer(this, true);
        element = new UIElement(container, 50, 50, 750, 100);
        goButton = new UIButton(container, (AndroidGame.width - Assets.button.getWidth()) / 2, AndroidGame.height - Assets.button.getHeight() - 100) {
            @Override
            public void Click() {
                super.Click();
                for (int i = 255; i > 0; i--) {
                    game.getGraphics().drawARGB(i, 0, 0, 0);
                    game.setScreen(new GameScreen(game, 0)); //init first Level
                }
            }
        };

        welcometext = new BlinkingText(element, 750, 1000, "Herzlich Wilkommen zur 'Tour de GTS'", 22, Color.RED, 1);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        container.updateAll(deltaTime, g);
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawImage(Assets.background, bg1.getBgX(), bg1.getBgY());
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
