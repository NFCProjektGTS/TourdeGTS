package gtsoffenbach.tourdegts.gameinterface;


import gtsoffenbach.tourdegts.gameinterface.Graphics.ImageFormat;

/**
 * Created by Noli on 14.07.2014.
 */
public interface Image {
    public int getWidth();

    public int getHeight();

    public ImageFormat getFormat();

    public void dispose();
}
