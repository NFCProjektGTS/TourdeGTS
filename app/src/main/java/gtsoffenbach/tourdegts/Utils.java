package gtsoffenbach.tourdegts;

import android.graphics.Rect;

import java.nio.ByteBuffer;

import gtsoffenbach.tourdegts.gameinterface.Input;

/**
 * Created by Noli on 31.08.2014.
 */
public class Utils {
    public static String convertByteArrayToHexString(byte[] array) {
        StringBuilder hex = new StringBuilder(array.length * 2);
        for (byte b : array) {
            hex.append(String.format("%02X ", b));
        }
        return hex.toString();
    }

    public static long convertByteArrayToDecimal(byte[] array) {
        ByteBuffer bb = ByteBuffer.wrap(array);
        return bb.getLong();
    }

    public static boolean inBounds(Input.TouchEvent event, Rect rect) { //TODO ganz ganz ganz ganz ganz komisch

        boolean one = event.x > rect.left;
        boolean two = event.x < rect.left + rect.right - 1;

        boolean three = event.y > rect.top;
        boolean four = event.y < rect.top + rect.bottom - 1;
        if (one) {
            if (two) {
                if (three) {
                    if (four) {
                        return true;
                    }
                }
            }
        }
        return false;

        //return rect.contains(event.x,event.y);
    }
}
