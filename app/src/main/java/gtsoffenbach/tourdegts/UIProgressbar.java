package gtsoffenbach.tourdegts;

import android.graphics.Color;
import android.graphics.Paint;

import gtsoffenbach.tourdegts.implementations.AndroidGame;

/**
 * Created by Kern on 04.11.2014.
 */
public class UIProgressbar extends UIButton{
    //private Graphics graphics;
    private Paint paint;
    private ElementContainer container;
    private int fill, dx,dy;
    private int[] markers;
    private BlinkingText text;
    private int color;

    UIProgressbar(final ElementContainer container, final int dx, final int dy,int fill,int[] markers,int color) {
        super(container, dx, dy, Assets.progressbar.getWidth(), Assets.progressbar.getHeight());
        this.color=color;
        this.container= container;
        this.fill=fill;
        this.markers=markers;
        text = new BlinkingText(new UIElement(container,dx+Assets.progressbar.getWidth()/2,dy+Assets.progressbar.getHeight()/2+10,0 , 0), 0, 0, " "+fill+ "%", 70, Colors.BLACK, 1, Assets.gRoboto);
    }
    @Override
    public void draw(float delta) {
        getGraphics().drawRect(getRectangle().left,getRectangle().top,fill*Assets.progressbar.getWidth()/100,Assets.progressbar.getHeight(),color);
        getGraphics().drawImage(Assets.progressbar, getRectangle().left, getRectangle().top);
    }
    public void setProgress(int fill){
        this.fill = fill;
        text.setMsg(" "+fill+ "%");
    }
}
