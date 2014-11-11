package gtsoffenbach.tourdegts.minigame_math;

import android.graphics.Color;

import gtsoffenbach.tourdegts.Assets;
import gtsoffenbach.tourdegts.BlinkingText;
import gtsoffenbach.tourdegts.Colors;
import gtsoffenbach.tourdegts.ElementContainer;
import gtsoffenbach.tourdegts.UIElement;

/**
 * Created by Kern on 08.11.2014.
 */
class UIButton extends UIElement {

    private String text;
    private int color;
    private BlinkingText msg;
    private int dx,dy,sx,sy;
    UIButton(ElementContainer container, final int dx, final int dy, final int sx, final int sy,String text,int color) {
        super(container, dx, dy, sx, sy);
        this.dx=dx;
        this.dy=dy;
        this.sx=sx;
        this.sy=sy;
        this.text=text;
        this.color=color;
        msg = new BlinkingText(this,0, +30, text, 150, Colors.BLACK, 1,Assets.lobster);
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
            getGraphics().drawRect(dx,dy,sx,sy,color);
        } else {
            getGraphics().drawRect(dx,dy,sx,sy,color+40);
        }
    }

    @Override
    public void Click() {
        Assets.click.play(1f);

    }
}
