package gtsoffenbach.tourdegts.implementations;


import gtsoffenbach.tourdegts.gameinterface.Graphics;

/**
 * Created by Kern on 31.10.2014.
 */
public class LevelList {



    private static Level[] levels = {
            new Level("Start","Start der Tour", "Wähle die Prozentanzeige oben aus, um Informationen über ein nächstes Ziel der Tour zu bekommen.", "Foyer", "", 0, false),
            new Level("Infostand / Empfang","Infostand und Empfang", "", "Foyer", "Frau Bickel", 1, false),
            new Level("DSF Info", "DSF Informationen", "Kreuzer", "Raum A016", "Kreuzer", 2, false),
            new Level("Mechatronik","Mechatronik an der GTS", "Kompetenzcheck zur Arbeit eines Mechatronikers mit Beispielen aus der Messtechnik und der Steuerungstechnik sollen die Schüler Einblicke in mechatronische Systeme bekommen.", "Raum A105", "Herr Pevny", 3, false),
            new Level("Elektrische Schaltungen","Elektrische Schaltungen", "<beschreibung>", "Raum A109", "Herr Hohm", 4, false),
            new Level("JavaScript Taschenrechner","JavaScript Taschenrechner", "Programmierung eines Taschenrechners in JavaScript", "Raum A113", "Herr Ostermann", 5, false),
            new Level("Industriemechaniker","Industrie- mechaniker", "<beschreibung>", "Raum C201", "Herr Schneider", 6, false),
            new Level("Projektpräsentationen","Projekt- präsentationen", "Prasentationen des Informatik Leistungskurses der Q3", "Raum A209", "Hr. Wendt, Fr. Lingau", 7, false)};

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
