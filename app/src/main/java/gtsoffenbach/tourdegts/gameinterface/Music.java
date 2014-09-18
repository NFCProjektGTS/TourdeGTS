package gtsoffenbach.tourdegts.gameinterface;

/**
 * Created by Noli on 14.07.2014.
 */
public interface Music {
    public void play();

    public void stop();

    public void pause();

    public void setVolume(float volume);

    public boolean isPlaying();

    public boolean isStopped();

    public boolean isLooping();

    public void setLooping(boolean looping);

    public void dispose();

    void seekBegin();
}
