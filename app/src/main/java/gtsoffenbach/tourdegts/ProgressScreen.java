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
    BlinkingText levelname,indicator,invIndicator;
    private int lastLevel;
    private int selectedLevel;
    private ElementContainer container;
    private int speed = 0;
    private UIButton button;
    private Point last, now, veryfirst;
    private ArrayList<UIElement> buttons;
    private boolean inMove;
    private GameScreen lastscreen;
    private boolean locked;
    private Chest chest;
    private boolean ispop;
    private int mult = 1;
    private int offset = 110;
    private String indicatorString, inverseindiString;
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
        levelname = new BlinkingText(new UIElement(container, (AndroidGame.width / 2), 1050, 0, 0), 0, 0, SaveGame.levels[0].getName(), 60, Color.BLACK, 1,Assets.lobster);
//TODO alle gleichrücken
//TODO grafik der Buttons(Schlösser) überschreiben
        buttons = new ArrayList<UIElement>();
        for (int i = 0; i < SaveGame.levels.length; i++) {
            int localoffset = offset;
            if(i==0){
                localoffset = offset/2;
            }
            if (SaveGame.levels[i].isUnlocked()) {

                final int finalLocaloffset = localoffset;
                buttons.add(new UIButton(container, finalLocaloffset + i * AndroidGame.width + (AndroidGame.width - Assets.infobox.getWidth()) / 2, 600 ){
                    @Override
                    public void Click() {
                        //game.setScreen(new GameScreen(game, selectedLevel));
                        System.out.println("Click");
                    }

                    @Override
                    public void draw(float delta) {
                        getGraphics().drawImage(Assets.infobox,  getRectangle().left -35 +AndroidGame.width/2 - Assets.infobox.getWidth()/2, getRectangle().top -50 - Assets.infobox.getHeight() / 2);

                    }
                });
            } else {
                final int finalLocaloffset1 = localoffset;
                buttons.add(new UIButton(container, finalLocaloffset1 + i * AndroidGame.width + (AndroidGame.width - Assets.infobox.getWidth()) / 2, 600) {
                    @Override
                    public void Click() {
                        //game.setScreen(new GameScreen(game, selectedLevel));
                    }

                    @Override
                    public void draw(float delta) {
                        getGraphics().drawImage(Assets.infobox,  getRectangle().left -35 +AndroidGame.width/2 - Assets.infobox.getWidth()/2, getRectangle().top -50 - Assets.infobox.getHeight() / 2);
                        getGraphics().drawImage(Assets.lock,  getRectangle().left -35 +AndroidGame.width/2 - Assets.lock.getWidth()/2 , getRectangle().top - 50 - Assets.lock.getHeight() / 2);
                    }
                });
            }
        }
//this.flip = new Point((AndroidGame.width-buttons[0].getRectangle().right)/2,(AndroidGame.height/2)); // Left Point of our main Element, + rect.right; for the right Point of our main Element
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
                Input.TouchEvent event = touchEvents.get(i);

                container.processClick(event);
                if (event.type == Input.TouchEvent.TOUCH_DOWN) {
                    veryfirst = last = new Point(event.x, event.y);

                }
                if (event.type == Input.TouchEvent.TOUCH_DRAGGED) {
                    wasdragged++;
                    System.out.println(wasdragged);
                    now = new Point(event.x, event.y);
                    this.speed = (int) ((now.x - last.x));
                    for (int i2 = 0; i2 < buttons.size(); i2++) {
                        buttons.get(i2).getRectangle().offsetTo(buttons.get(i2).getRectangle().left + speed, buttons.get(i2).getRectangle().top);
                    }
//button.getRectangle().offsetTo(button.getRectangle().left + speed, button.getRectangle().top);
/*if (selectedLevel == 0) {
if (Math.abs(buttons[0].getRectangle().left) > (AndroidGame.width - buttons[0].getRectangle().right) / 2) { //out of bounds!!
}
if (Math.abs(buttons[0].getRectangle().left) + (AndroidGame.width - buttons[0].getRectangle().right) < (AndroidGame.width - buttons[0].getRectangle().right) / 2) {
//load next
}
}
if (selectedLevel > 0) {
if (Math.abs(buttons[selectedLevel].getRectangle().left) > (AndroidGame.width - buttons[selectedLevel].getRectangle().right) / 2) { //left
}
if (Math.abs(buttons[0].getRectangle().left) + (AndroidGame.width - buttons[selectedLevel].getRectangle().right) < (AndroidGame.width - buttons[selectedLevel].getRectangle().right) / 2) {
//load next
}
}
if (selectedLevel < buttons.length) {
if (Math.abs(buttons[0].getRectangle().left) < (AndroidGame.width - buttons[0].getRectangle().right) / 2) { //out of bounds!!
}
if (Math.abs(buttons[0].getRectangle().left) + (AndroidGame.width - buttons[0].getRectangle().right) > (AndroidGame.width - buttons[0].getRectangle().right) / 2) {
//load next
}
}*/
                    last = new Point(event.x, event.y);
                }
                if (event.type == Input.TouchEvent.TOUCH_UP) {

                    if (wasdragged < 6) {
                    if (event.x > 150 && event.x < 650 && event.y < 900 && event.y > 300) { //TODO NOTLÖSUNG?

                        if (SaveGame.levels[selectedLevel].isUnlocked()) {
                            game.setScreen(new GameScreen(game, selectedLevel));
                        }
                    }
                    }
                    wasdragged = 0;

                    inMove = true;
                    int dist = (veryfirst.x - event.x);
                    int trigger = (AndroidGame.width - Assets.button.getWidth());
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
                }
            }
        } //else {
            updatePosZero(deltaTime);
        //}
    }

    private void updatePosZero(float deltaTime) {
//int step = (int) Math.abs(buttons[selectedLevel].getRectangle().left)/1/deltaTime;
        int glitch = Math.abs(buttons.get(selectedLevel).getRectangle().left) == 0 ? 1 : Math.abs(buttons.get(selectedLevel).getRectangle().left);
        step = 50;//(int) Math.ceil(glitch / 1 / deltaTime);
        if (buttons.get(selectedLevel).getRectangle().left > (AndroidGame.width - Assets.button.getWidth()) / 2) {
//buttons[selectedLevel].getRectangle().offsetTo(buttons[selectedLevel].getRectangle().left - step, buttons[selectedLevel].getRectangle().top);
            if (buttons.get(selectedLevel).getRectangle().left - step < (AndroidGame.width - Assets.button.getWidth()) / 2) {
                step = buttons.get(selectedLevel).getRectangle().left - (AndroidGame.width - Assets.button.getWidth()) / 2;
            }
            for (int i2 = 0; i2 < buttons.size(); i2++) {
                buttons.get(i2).getRectangle().offsetTo(buttons.get(i2).getRectangle().left - step, buttons.get(i2).getRectangle().top);
            }
        } else {
//buttons[selectedLevel].getRectangle().offsetTo(buttons[selectedLevel].getRectangle().left + step, buttons[selectedLevel].getRectangle().top);
/*if(buttons[selectedLevel].getRectangle().left<0){
step = -step;
}*/
            if (buttons.get(selectedLevel).getRectangle().left + step > (AndroidGame.width - Assets.button.getWidth()) / 2) {
                step = ((AndroidGame.width - Assets.button.getWidth()) / 2) - buttons.get(selectedLevel).getRectangle().left;
            }
            for (int i2 = 0; i2 < buttons.size(); i2++) {
                buttons.get(i2).getRectangle().offsetTo(buttons.get(i2).getRectangle().left + step, buttons.get(i2).getRectangle().top);
            }
        }
        if (buttons.get(selectedLevel).getRectangle().left == (AndroidGame.width - Assets.button.getWidth()) / 2) {
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