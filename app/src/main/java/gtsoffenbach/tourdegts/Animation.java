package gtsoffenbach.tourdegts;

import java.util.ArrayList;

import gtsoffenbach.tourdegts.gameinterface.Image;

/**
 * Created by Noli on 05.08.2014.
 */
public class Animation {

    private ArrayList frames;
    private int currentFrame;
    private long animTime;
    private long totalDuration;
    private boolean endless;
    private boolean end = false;

    public Animation(boolean endless) {
        frames = new ArrayList();
        totalDuration = 0;

        synchronized (this) {
            animTime = 0;
            currentFrame = 0;
        }
        this.end = false;
        this.endless = endless;
    }

    public boolean isEndless() {
        return endless;
    }

    public void setEndless(boolean endless) {
        this.endless = endless;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public synchronized void addFrame(Image image, long duration) {
        totalDuration += duration;
        frames.add(new AnimFrame(image, totalDuration));
    }

    public synchronized void update(long elapsedTime) {
        //if(endless){
        if (frames.size() > 1) {
            this.end = false;
            animTime += elapsedTime;
            if (animTime >= totalDuration) {
                animTime = animTime % totalDuration;
                if (!endless) {
                    currentFrame = frames.size() - 1;
                } else {
                    currentFrame = 0;
                }

                this.end = true;
            }
            while (animTime > getFrame(currentFrame).endTime) {
                if (!end) {
                    currentFrame++;
                }
            }
        }

    }

    public synchronized Image getImage() {
        if (frames.size() == 0) {
            return null;
        } else {
            return getFrame(currentFrame).image;
        }
    }

    private AnimFrame getFrame(int i) {
        return (AnimFrame) frames.get(i);
    }

    private class AnimFrame {

        Image image;
        long endTime;

        public AnimFrame(Image image, long endTime) {
            this.image = image;
            this.endTime = endTime;
        }
    }
}
