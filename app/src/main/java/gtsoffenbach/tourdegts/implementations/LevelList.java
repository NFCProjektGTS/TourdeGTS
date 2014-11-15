package gtsoffenbach.tourdegts.implementations;


import gtsoffenbach.tourdegts.gameinterface.Graphics;

/**
 * Created by Kern on 31.10.2014.
 */
public class LevelList {



    private static Level[] levels = {new Level("Start", "", "Foyer", "<lehrer>", 0, false),
            new Level("Infostand/Empfang", "", "Foyer", "<lehrer>", 1, false),
            new Level("DSF Info", "Kreuzer", "A016", "<lehrer>", 2, false),
            new Level("Mechatronik", "Pevny", "A105", "<lehrer>", 3, false),
            new Level("Elektrische Schaltungen", "Hohm", "A109", "<lehrer>", 4, false),
            new Level("JavaScript Taschenrechner", "Ostermann", "A113", "<lehrer>", 5, false),
            new Level("Industriemechaniker", "Schneider", "C201", "<lehrer>", 6, false),
            new Level("Projektpräsentation", "Wendt, Lingnau, Bickel", "A209", "<lehrer>", 7, false)};

    LevelList() {
        //TODO SAVE
    }


    public static void init(Graphics g){
       levels[0].setImage(g.newImage("gts.png", Graphics.ImageFormat.RGB565));
       levels[1].setImage(g.newImage("infostand.png", Graphics.ImageFormat.RGB565));
       levels[2].setImage(g.newImage("fragezeichen.png", Graphics.ImageFormat.RGB565));
       levels[3].setImage(g.newImage("mechatronik.png", Graphics.ImageFormat.RGB565));
       levels[4].setImage(g.newImage("elektrischeSchaltung.png", Graphics.ImageFormat.RGB565));
       levels[5].setImage(g.newImage("rechnerJavaSkript.png", Graphics.ImageFormat.RGB565));
       levels[6].setImage(g.newImage("industriemechaniker.png", Graphics.ImageFormat.RGB565));
       levels[7].setImage(g.newImage("projektpraesentation.png", Graphics.ImageFormat.RGB565));
    }

    public static Level[] getLevels() {
        return levels;
    }
}
