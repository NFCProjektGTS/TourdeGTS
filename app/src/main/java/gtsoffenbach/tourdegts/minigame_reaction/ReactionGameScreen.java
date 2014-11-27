package gtsoffenbach.tourdegts.minigame_reaction;

import java.util.List;

import gtsoffenbach.tourdegts.Assets;
import gtsoffenbach.tourdegts.Background;
import gtsoffenbach.tourdegts.BlinkingText;
import gtsoffenbach.tourdegts.Colors;
import gtsoffenbach.tourdegts.ElementContainer;
import gtsoffenbach.tourdegts.MultiLineBlinkingText;
import gtsoffenbach.tourdegts.UIButton;
import gtsoffenbach.tourdegts.UIElement;
import gtsoffenbach.tourdegts.gameinterface.Game;
import gtsoffenbach.tourdegts.gameinterface.Graphics;
import gtsoffenbach.tourdegts.gameinterface.Input;
import gtsoffenbach.tourdegts.gameinterface.Screen;
import gtsoffenbach.tourdegts.implementations.AndroidGame;

/**
 * Created by Noli on 26.11.2014.
 */
public class ReactionGameScreen extends Screen {
    private int run = 0;
    private int runs = 18;
    private int total = 126;
    private int[] times;
    private int wrong, corrent;
    private String[][] taps;
    private ElementContainer container;
    private BlinkingText instructions, startTimer, progress;
    private UIButton ready;
    private Background background;
    private int start;
    private int timeleft;
    private Stopwatch sw;
    private Stopwatch timer;


    public ReactionGameScreen(Game game) {
        super(game);
        background = new Background(0, 0);
        startTimer = new BlinkingText(new UIElement(container, AndroidGame.width / 2, AndroidGame.height / 2, 2, 2), 2, 2, "Timer", 350, Colors.RED, 1, Assets.lobster);
        instructions = new MultiLineBlinkingText(new UIElement(container, 30, AndroidGame.height / 3 + Assets.nfcHand.getHeight() / 2 + 100, 2, 2), 2, 2, "Halten sie ihr Handy an die Tour de GTS Symbole, welche in den Räumen angebracht sind, um Informationen zu erhalten.", 60, Colors.BLACK, 1, Assets.gRoboto, 1500);
        ready = new UIButton(container, AndroidGame.width / 2 - Assets.button.getWidth() / 2, AndroidGame.height - 200) {
            @Override
            public void Click() {
                super.Click();
                startGame();
            }
        };
        sw = new Stopwatch();
        timer = new Stopwatch();

    }

    private void startGame() {
        sw.start();
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
        if (sw.elapsed() >= 7000) {
            sw.setEnabled(false);
            run = 1;

        }

    }

    private void printRun() {

    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.blankscreen, background.getBgX(), background.getBgY());
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

    public final class Stopwatch {

        private boolean enabled;
        private long start;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public void start() {
            start = System.currentTimeMillis();
        }

        public int elapsed() {
            if (enabled)
                return (int) (System.currentTimeMillis() - start);
            return 0;
        }
    }


}
