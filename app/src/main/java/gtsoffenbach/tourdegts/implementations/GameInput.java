package gtsoffenbach.tourdegts.implementations;

/**
 * Created by Noli on 14.07.2014.
 */

import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;

import java.util.List;

import gtsoffenbach.tourdegts.gameinterface.Input;

public class GameInput implements Input {
    TouchHandler touchHandler;

    public GameInput(Context context, View view, float scaleX, float scaleY) {
        if (Integer.parseInt(VERSION.SDK) < 5)
            touchHandler = new SingleTouchHandler(view, scaleX, scaleY);
        else
            touchHandler = new MultiTouchHandler(view, scaleX, scaleY);
    }


    @Override
    public boolean isTouchDown(int pointer) {
        return touchHandler.isTouchDown(pointer);
    }

    @Override
    public int getTouchX(int pointer) {
        return touchHandler.getTouchX(pointer);
    }

    @Override
    public int getTouchY(int pointer) {
        return touchHandler.getTouchY(pointer);
    }


    @Override
    public List<TouchEvent> getTouchEvents() {
        return touchHandler.getTouchEvents();
    }

}
