package gtsoffenbach.tourdegts;

import android.graphics.Color;
import android.widget.Toast;

import java.util.List;

import gtsoffenbach.tourdegts.ContentScreens.WelcomeScreen;
import gtsoffenbach.tourdegts.gameinterface.Game;
import gtsoffenbach.tourdegts.gameinterface.Graphics;
import gtsoffenbach.tourdegts.gameinterface.Input;
import gtsoffenbach.tourdegts.gameinterface.Screen;
import gtsoffenbach.tourdegts.implementations.AndroidGame;
import gtsoffenbach.tourdegts.implementations.SaveGame;

/**
 * Created by Noli on 30.07.2014.
 */
public class MainMenuScreen extends Screen {
    UIButton button_start, button_settings, button_help;
    BlinkingText start, help, settings;
    boolean backonce;
    private ElementContainer container;
    private int levelselected = 0;

    public MainMenuScreen(Game game) {
        super(game);
        container = new ElementContainer(this, true);
        button_start = new UIButton(container, AndroidGame.width/2-Assets.button.getWidth()/2, 500) {
            @Override
            public void Click() {
                super.Click();

                goToScreenGame();
            }
        };
        button_help = new UIButton(container,AndroidGame.width/2-Assets.button.getWidth()/2,715) {
            @Override
            public void Click() {
                super.Click();
                goToScreenHelp();
            }
        };

        button_settings = new UIButton(container, AndroidGame.width/2-Assets.button.getWidth()/2, 930) {
            @Override
            public void Click() {
                super.Click();
                goToScreenSettings();
            }
        };
        button_start.setGraphics(game.getGraphics());
        button_settings.setGraphics(game.getGraphics());
        button_help.setGraphics(game.getGraphics());


        String start_str;
        if (SaveGame.newGame) {
            start_str = "Neues Spiel";
        } else {
            start_str = "Spiel fortsetzen";
        }

        start = new BlinkingText(button_start, 0, 0, start_str, 75, Color.BLACK, 1,Assets.lobster);
        help = new BlinkingText(button_help, 0, 0, "Hilfe", 75, Color.BLACK, 1,Assets.lobster);
        settings = new BlinkingText(button_settings, 0,0, "Einstellungen", 75, Color.BLACK, 1,Assets.lobster);

    }

    private void goToScreenSettings() {
        game.setScreen(new SettingsScreen(game));
    }

    private void goToScreenHelp() {
        game.setScreen(new HelpScreen(game));
    }

    private void goToScreenGame() {

        if (SaveGame.isNewGame()) {       //ADDED WELCOMESCREEN ON FIRST STARTUP
            game.setScreen(new WelcomeScreen(game));
        } else {
            game.setScreen(new GameScreen(game, levelselected));
        }
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

    private boolean inBounds(Input.TouchEvent event, int x, int y, int width,
                             int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.menu, 0, 0);
        container.updateAll(deltaTime, g);
        start.update(deltaTime);

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
        if (backonce) {
            android.os.Process.killProcess(android.os.Process.myPid());
        } else {
            Toast.makeText(game.getThiscontext(), "Erneut drücken zum Beenden!", Toast.LENGTH_SHORT);
            backonce = true;
        }
    }
}
