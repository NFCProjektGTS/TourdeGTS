package gtsoffenbach.tourdegts.implementations;

/**
 * Created by Noli on 14.07.2014.
 */

import android.media.SoundPool;

import gtsoffenbach.tourdegts.gameinterface.Sound;

public class GameSound implements Sound {
    int soundId;
    SoundPool soundPool;

    public GameSound(SoundPool soundPool, int soundId) {
        this.soundId = soundId;
        this.soundPool = soundPool;
    }

    @Override
    public void play(float volume) {
        soundPool.play(soundId, volume, volume, 0, 0, 1);
    }

    @Override
    public void playcount(float volume, int count) {
        for (int i = 0; i <= count; i++) {
            soundPool.play(soundId, volume, volume, 0, 0, 1);
        }
    }


    @Override
    public void dispose() {
        soundPool.unload(soundId);
    }

}

