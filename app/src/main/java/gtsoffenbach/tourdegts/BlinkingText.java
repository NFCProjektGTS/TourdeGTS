package gtsoffenbach.tourdegts;

import android.app.Activity;
import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * Created by Noli on 28.08.2014.
 */
public class BlinkingText extends Element {
    //private Graphics graphics;
    public Paint paint;
    private String msg;
    public boolean toggle;
    public double speed;
    public UIElement dad;
    private int size;
    private int color;
    private Typeface font;


    public BlinkingText(UIElement father, int sx, int sy, String msg, int size, int color, double speed, Typeface font) {
        super(father, sx, sy);
        dad = father;
        this.size = size;
        this.speed = speed;
        this.msg = msg;
        this.font = font;
        this.color = color;
        paint = new Paint();
        paint.setTypeface(font);
        paint.setTextSize(size);
        paint.setTextAlign(Paint.Align.CENTER); //no parameter yet
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setAlpha(50);
        toggle = true;

    }

    public void setColor(int color) {
        this.color = color;
        paint.setColor(color);
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public void draw(float delta) {

            /*try {
                Thread.currentThread().sleep(Math.round(time * 1000 / 255));
            } catch (Exception e) {
                e.printStackTrace();
            }*/
        paint.setAlpha(255);
        if (speed != 1) {
            paint.setAlpha(0);
            int alpha = paint.getAlpha();
            if (alpha < 255 && toggle) {
                alpha += (int) (255 * speed);
                if (alpha > 255)
                    alpha = 255;
                paint.setAlpha(alpha);
            }
            if (paint.getAlpha() == 255 && toggle)
                toggle = !toggle;
            if (alpha <= 255 && !toggle) {
                alpha -= (int) (255 * speed);
                if (alpha < 0)
                    alpha = 0;
                paint.setAlpha(alpha);
            }
            if (paint.getAlpha() == 0 && !toggle)
                toggle = !toggle;
        }

        getGraphics().drawString(msg,
                getRectangle().centerX() + dad.getRectangle().centerX(),
                getRectangle().centerY() + dad.getRectangle().centerY() + Math.round(size / 4),
                paint);
    }
}
