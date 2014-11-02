package gtsoffenbach.tourdegts;

import gtsoffenbach.tourdegts.gameinterface.Image;
import gtsoffenbach.tourdegts.gameinterface.Music;
import gtsoffenbach.tourdegts.gameinterface.Sound;
import gtsoffenbach.tourdegts.implementations.AndroidGame;

/**
 * Created by Noli on 30.07.2014.
 */
public class Assets {

    public static Image menu, splash, background, settingsBackground, helpBackground, progressBackground, infobox,lock;
    public static Image[] chest, chest_half;
    public static Image button, button_pressed, button_small, button_small_pressed;
    public static Sound click, magic; //magic is a sound played when UI element is spawned with cool effect
    public static Music theme;


    public static void load(AndroidGame game) {
        // TODO Auto-generated method stub
        theme = game.getAudio().createMusic("menutheme.ogg");
        theme.setLooping(true);
        theme.setVolume(0.85f);
        //TODO theme.play();

        click = game.getAudio().createSound("buttonsound.mp3");

    }

}