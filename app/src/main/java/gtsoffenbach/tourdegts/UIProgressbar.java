package gtsoffenbach.tourdegts;

import android.graphics.Color;
import android.graphics.Paint;

import gtsoffenbach.tourdegts.implementations.AndroidGame;

/**
 * Created by Kern on 04.11.2014.
 */
public class UIProgressbar extends UIElement{
    //private Graphics graphics;
    private Paint paint;
    private ElementContainer container;
    private int fill, dx,dy;
    private int[] markers;
    private BlinkingText text;

    UIProgressbar(final ElementContainer container, final int dx, final int dy,int fill,int[] markers) {
        super(container, dx, dy, Assets.progressbar.getWidth(), Assets.progressbar.getHeight());
        this.container= container;
        this.fill=fill;
        this.markers=markers;
        text = new BlinkingText(new UIElement(container,dx+Assets.progressbar.getWidth()/2,dy+Assets.progressbar.getHeight()/2,0 , 0), 0, 0, " "+fill+ "%", 50, Color.argb(255,70,67,58), 1, Assets.lobster);
    }
    @Override
    public void draw(float delta) {
        getGraphics().drawRect(getRectangle().left+5,getRectangle().top+5,fill*Assets.progressbar.getWidth()/100,Assets.progressbar.getHeight()-10,Color.argb(255,100,182,177));
        getGraphics().drawImage(Assets.progressbar, getRectangle().left, getRectangle().top);
    }
    public void setProgress(int fill){
        this.fill = fill;
        text.setMsg(" "+fill+ "%");
    }
}
