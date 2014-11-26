package gtsoffenbach.tourdegts;

import android.graphics.Color;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

import gtsoffenbach.tourdegts.gameinterface.Game;
import gtsoffenbach.tourdegts.gameinterface.Graphics;
import gtsoffenbach.tourdegts.gameinterface.Input;
import gtsoffenbach.tourdegts.gameinterface.Screen;
import gtsoffenbach.tourdegts.implementations.AndroidGame;
import gtsoffenbach.tourdegts.implementations.SaveGame;

/**
 * Created by Noli on 03.09.2014.
 */
public class ProgressScreen extends Screen {
    int step;
    BlinkingText levelname,indicator,invIndicator,infotext;
    private int lastLevel;
    private int selectedLevel;
    private ElementContainer container;
    private int speed = 0;
    private UIButton button;
    private Point last, now, veryfirst;
    private ArrayList<UIElement> buttons;
    private boolean inMove, dragged;
    private GameScreen lastscreen;
    private boolean locked;
    private Chest chest;
    private boolean ispop;
    private int mult = 1;
    private int offset = (AndroidGame.width - Assets.infobox.getWidth()) / 2;
    private String indicatorString, inverseindiString,infoString;
    private int wasdragged = 0;

    public ProgressScreen(final Game game, int lastlevel, int seleted) { //NEED SELECTED LEVEL
        super(game);

        for(int i = 0;i<SaveGame.levels.length;i++){
            if(i==selectedLevel){
                indicatorString = indicatorString + " ";
                inverseindiString = inverseindiString + ".";
            }else{
                indicatorString = indicatorString + ".";
                inverseindiString = inverseindiString + " ";
            }

        }
        this.lastLevel = lastlevel;
        this.inMove = false;
        if (seleted <= 0) {
            this.selectedLevel = 0;
        } else {
            this.selectedLevel = seleted;
        }

        this.container = new ElementContainer(this, true);
        invIndicator = new BlinkingText(new UIElement(container, (AndroidGame.width / 2), 940, 0, 0), 0, 0,  inverseindiString, 150, Color.RED, 1,Assets.standard);
        indicator = new BlinkingText(new UIElement(container, (AndroidGame.width / 2), 940, 0, 0), 0, 0,  indicatorString, 150, Color.BLACK, 1,Assets.standard);
        levelname = new BlinkingText(new UIElement(container, (AndroidGame.width / 2), 1050, 0, 0), 0, 0, SaveGame.levels[0].getName(), 60, Color.BLACK, 1,Assets.gRoboto);
        infotext = new BlinkingText(new UIElement(container, (AndroidGame.width / 2), 1120, 0, 0), 0, 0, "info hier", 40, Color.BLACK, 1,Assets.gRoboto);
//TODO alle gleichrücken
//TODO grafik der Buttons(Schlösser) überschreiben
        buttons = new ArrayList<UIElement>();
        for (int i = 0; i < SaveGame.levels.length; i++) {
                buttons.add(new LevelUIButton(SaveGame.levels[i], container, i * AndroidGame.width + (AndroidGame.width - (Assets.infobox.getWidth()) / 2), 600 ));

        }
        updatePosZero(0);
    }

    public int getSelectedLevel() {
        return selectedLevel;
    }

    public void setSelectedLevel(int selectedLevel) {
        this.selectedLevel = selectedLevel;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Chest getChest() {
        return chest;
    }

    public void setChest(Chest chest) {
        this.chest = chest;
    }

    public void loadChest(GameScreen last) {
//this.chest = new Chest(new UIElement(container,AndroidGame.width/2 - Assets.chest[0].getWidth()/2, 50, Assets.chest[0].getWidth(), Assets.chest[0].getHeight()), Assets.chest[0].getWidth(), Assets.chest[0].getHeight(), 700, 5000);
        this.chest = new Chest(new UIElement(container, (AndroidGame.width - Assets.chest[0].getWidth()) / 2, AndroidGame.height - Assets.chest[0].getHeight(), Assets.chest[0].getWidth(), Assets.chest[0].getHeight()), Assets.chest[0].getWidth(), Assets.chest[0].getHeight(), 700, 5000);
        this.chest.setGraphics(game.getGraphics());
        this.step = 65;
        this.lastscreen = last;
        this.locked = true;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.progressBackground, 0, 0);
        container.updateAll(deltaTime, g);
    }

    @Override
    public void update(float deltaTime) {
        levelname.setMsg(SaveGame.levels[selectedLevel].getName());
        inverseindiString="";
        indicatorString="";
        for(int o = 0;o<SaveGame.levels.length;o++) {
            if (o == selectedLevel) {
                indicatorString = indicatorString + " ";
                inverseindiString = inverseindiString + ".";
            } else {
                indicatorString = indicatorString + ".";
                inverseindiString = inverseindiString + " ";
            }

        }
        if(SaveGame.levels[selectedLevel].isUnlocked()){
            infoString="Bild berühren für mehr Infos";
        }else{
            infoString=SaveGame.levels[selectedLevel].getRaum()+" freischaltbar";
        }

        infotext.setMsg(infoString);
        invIndicator.setMsg(inverseindiString);
        indicator.setMsg(indicatorString);
            if (chest != null) {
            if (chest.getChest_anim().isEnd()) {
                chest.dismiss();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                game.setScreen(lastscreen);
            }
        }
        Graphics g = game.getGraphics();
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        if (!inMove && !locked) {
            int len = touchEvents.size();

            for (int i = 0; i < len; i++) {
                Input.TouchEvent event;
                try {
                    event = touchEvents.get(i);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                container.processClick(event);
                if (event.type == Input.TouchEvent.TOUCH_DOWN) {
                    veryfirst = last = new Point(event.x, event.y);

                }
                if (event.type == Input.TouchEvent.TOUCH_DRAGGED) {
                    //wasdragged++;
                    //System.out.println(wasdragged);
                    now = new Point(event.x, event.y);
                    if (Math.sqrt(Math.pow(now.x - last.x, 2) + Math.pow(now.y - last.y, 2)) > 10)
                        dragged = true;
                    speed = now.x - last.x;
                    for (int i2 = 0; i2 < buttons.size(); i2++) {
                        buttons.get(i2).getRectangle().offsetTo(buttons.get(i2).getRectangle().left + speed, buttons.get(i2).getRectangle().top);
                    }

                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                if (event.type == Input.TouchEvent.TOUCH_UP) {

                    //if (wasdragged < 6) {

                    //wasdragged = 0;
                    dragged = false;
                    inMove = true;
                    int dist = (veryfirst.x - event.x);
                    int trigger = (AndroidGame.width - Assets.infobox.getWidth());
                    if (selectedLevel == 0) {
                        if (dist < -trigger) {
//BLOCK
                            System.out.println("BLOCK" + selectedLevel);
                        }
//if ((veryfirst.x - event.x) > (AndroidGame.width - buttons[0].getRectangle().right)) {
                        if (dist > trigger) {
                            System.out.println("NEXT" + selectedLevel);
//right
                            selectedLevel++;
                            return;
                        }
                    }
                    if (selectedLevel > 0 && selectedLevel < buttons.size() - 1) {
                        if (dist < -trigger) {
                            System.out.println("Left" + selectedLevel);
//left
                            selectedLevel--;
                            return;
                        }
                        if (dist > trigger) {
                            System.out.println("Right" + selectedLevel);
//right
                            selectedLevel++;
                            return;
                        }
                    }
                    if (selectedLevel == buttons.size() - 1) {
                        if (dist < -trigger) {
                            System.out.println("Last" + selectedLevel);
//left
                            selectedLevel--;
                            return;
                        }
                        if (dist > trigger) {
                            System.out.println("Block" + selectedLevel);
//BLOCK
                        }
                    }

                    if (dragged) {
                        if (event.x > 150 && event.x < 650 && event.y < 900 && event.y > 300) { //TODO NOTLÖSUNG?

                            if (SaveGame.levels[selectedLevel].isUnlocked()) {
                                SaveGame.setLastlevel(selectedLevel);
                                game.setScreen(new GameScreen(game, selectedLevel));
                            }
                        }
                    }
                }
                last = new Point(event.x, event.y);
            }
        } //else {
            updatePosZero(deltaTime);
        //}
    }

    private void updatePosZero(float deltaTime) {
//int step = (int) Math.abs(buttons[selectedLevel].getRectangle().left)/1/deltaTime;
        int glitch = Math.abs(buttons.get(selectedLevel).getRectangle().left) == 0 ? 1 : Math.abs(buttons.get(selectedLevel).getRectangle().left);
        step = 50;//(int) Math.ceil(glitch / 1 / deltaTime);
        if (buttons.get(selectedLevel).getRectangle().left > (AndroidGame.width - Assets.infobox.getWidth()) / 2) {
//buttons[selectedLevel].getRectangle().offsetTo(buttons[selectedLevel].getRectangle().left - step, buttons[selectedLevel].getRectangle().top);
            if (buttons.get(selectedLevel).getRectangle().left - step < (AndroidGame.width - Assets.infobox.getWidth()) / 2) {
                step = buttons.get(selectedLevel).getRectangle().left - (AndroidGame.width - Assets.infobox.getWidth()) / 2;
            }
            for (int i2 = 0; i2 < buttons.size(); i2++) {
                buttons.get(i2).getRectangle().offsetTo(buttons.get(i2).getRectangle().left - step, buttons.get(i2).getRectangle().top);
            }
        } else {
//buttons[selectedLevel].getRectangle().offsetTo(buttons[selectedLevel].getRectangle().left + step, buttons[selectedLevel].getRectangle().top);
/*if(buttons[selectedLevel].getRectangle().left<0){
step = -step;
}*/
            if (buttons.get(selectedLevel).getRectangle().left + step > (AndroidGame.width - Assets.infobox.getWidth()) / 2) {
                step = ((AndroidGame.width - Assets.infobox.getWidth()) / 2) - buttons.get(selectedLevel).getRectangle().left;
            }
            for (int i2 = 0; i2 < buttons.size(); i2++) {
                buttons.get(i2).getRectangle().offsetTo(buttons.get(i2).getRectangle().left + step, buttons.get(i2).getRectangle().top);
            }
        }
        if (buttons.get(selectedLevel).getRectangle().left == (AndroidGame.width - Assets.infobox.getWidth()) / 2) {
            inMove = false;
        }
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
        if (!locked)
            game.setScreen(new GameScreen(game, lastLevel));
    }
}