package gtsoffenbach.tourdegts.minigame_math;

import android.graphics.Color;

import gtsoffenbach.tourdegts.Assets;
import gtsoffenbach.tourdegts.BlinkingText;
import gtsoffenbach.tourdegts.ElementContainer;
import gtsoffenbach.tourdegts.UIElement;

/**
 * Created by Kern on 08.11.2014.
 */
class UIButton extends UIElement {

    private String text;
    private BlinkingText msg;
    UIButton(final ElementContainer container, final int dx, final int dy, final int sx, final int sy,String text) {
        super(container, dx, dy, sx, sy);
        this.text=text;
        msg = new BlinkingText(this, 0, 0, text, 100, Color.BLACK, 1,Assets.lobster);
    }
    void setText(String textmsg){
        msg.setMsg(textmsg);
    }
    public BlinkingText getBlinkingText(){
        return msg;
    }

    @Override
    public void draw(float delta) {
        if (isPressed()) {
            //getGraphics().drawImage(Assets.button,getRectangle().left,getRectangle().top, 0, 0,getRectangle().width(),getRectangle().height());
            //getGraphics().drawImage(Assets.button,getRectangle().left,getRectangle().top, getRectangle().width(), getRectangle().height() );
            getGraphics().drawImage(Assets.button, getRectangle().left, getRectangle().top); //TODO change image
        } else {
            //getGraphics().drawImage(Assets.button_pressed,getRectangle().left,getRectangle().top, 0, 0,getRectangle().width(),getRectangle().height());
            getGraphics().drawImage(Assets.button_pressed, getRectangle().left, getRectangle().top);
        }
    }

    @Override
    public void Click() {
        Assets.click.play(1f);
        //getContainer().remove(1); Button click to remove other button :D

    }
}
