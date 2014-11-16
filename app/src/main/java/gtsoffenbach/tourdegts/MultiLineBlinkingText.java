package gtsoffenbach.tourdegts;

import android.graphics.Paint;
import android.graphics.Typeface;

import java.util.ArrayList;

/**
 * Created by Kern on 16.11.2014.
 */
public class MultiLineBlinkingText extends BlinkingText{
    String msg;
    int size;
    int maxlength;
    int sx;

    public MultiLineBlinkingText(UIElement father, int sx, int sy, String msg, int size, int color, double speed, Typeface font,int maxlength) {
        super(father, sx, sy, msg, size, color, speed, font);
        this.msg=msg;
        this.size=size;
        this.maxlength=maxlength;
        this.sx=sx;
        paint = new Paint();
        paint.setTypeface(font);
        paint.setTextSize(size);
        paint.setTextAlign(Paint.Align.LEFT); //no parameter yet
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setAlpha(50);
        toggle = true;
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
        if(msg.length()*size>maxlength){
            msg = msg+"      \n\n\n\n\n\n        ";//workaround

           String[] parts = msg.split(" ");
           String thisline= "";
           String testline= "";
           ArrayList<String> line = new ArrayList<String>();
            for(int i=0;i<parts.length;i++){
                testline=thisline+parts[i];
                if(testline.length()*size<maxlength){
                    thisline=thisline+parts[i]+" ";
                }else {
                    line.add(thisline);
                    thisline=parts[i]+" ";
                }
            }
            for(int a =0;a<line.size();a++){
                getGraphics().drawString(
                        line.get(a),
                        getRectangle().left,
                        dad.getRectangle().top+size*a,
                        paint);
            }


        }else {
            getGraphics().drawString(
                    msg,
                    getRectangle().left,
                    dad.getRectangle().top,
                    paint);
        }



    }
}
