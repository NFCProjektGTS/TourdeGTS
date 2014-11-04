package gtsoffenbach.tourdegts;

import android.graphics.Paint;

/**
 * Created by Kern on 04.11.2014.
 */
public class UIProgressbar extends UIElement{
    //private Graphics graphics;
    private Paint paint;
    private ElementContainer container;
    private int fill;
    private int[] markers;

    UIProgressbar(final ElementContainer container, final int dx, final int dy,int fill,int[] markers) {
        super(container, dx, dy, Assets.progressbar.getWidth(), Assets.progressbar.getHeight());
        this.container= container;
        this.fill=fill;
        this.markers=markers;
    }
    @Override
    public void draw(float delta) {

         getGraphics().drawImage(Assets.button_small,getRectangle().left,getRectangle().top);
        getGraphics().drawImage(Assets.progressbar, getRectangle().left, getRectangle().top);
    }
}
