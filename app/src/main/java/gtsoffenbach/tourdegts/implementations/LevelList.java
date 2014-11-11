package gtsoffenbach.tourdegts.implementations;

/**
 * Created by Kern on 31.10.2014.
 */
public class LevelList {
    public static Level[] levels = {new Level("Start", "", "Foyer", "<lehrer>", 0, false),
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


    public static Level[] getLevels() {
        return levels;
    }
}
