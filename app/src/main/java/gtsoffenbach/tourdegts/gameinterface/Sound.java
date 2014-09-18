package gtsoffenbach.tourdegts.gameinterface;

/**
 * Created by Noli on 14.07.2014.
 */
public interface Sound {
    public void play(float volume);

    public void playcount(float volume, int count);

    public void dispose();
}
