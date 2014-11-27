package gtsoffenbach.tourdegts.implementations;


import gtsoffenbach.tourdegts.gameinterface.Graphics;

/**
 * Created by Kern on 31.10.2014.
 */
public class LevelList {



    private static Level[] levels = {
            new Level("Start","Start der Tour", "Wähle die Prozentanzeige oben aus, um Informationen über ein nächstes Ziel der Tour zu bekommen.", "im Foyer", "", 0, false),
            new Level("Infostand / Empfang","Infostand und Empfang", "Empfang am Hauteingang mit Informationen über die Schule.", "im Foyer", "Frau Bickel", 1, false),
            new Level("DSF Info", "DSF Informationen", "Hier gibt es Informationen zur DSF", "in Raum A016", "Kreuzer", 2, false),
            new Level("Mechatronik","Mechatronik an der GTS", "Kompetenzcheck zur Arbeit eines Mechatronikers mit Beispielen aus der Messtechnik und der Steuerungstechnik sollen die Schüler Einblicke in mechatronische Systeme bekommen.", "in Raum A105", "Herr Pevny", 3, false),
            new Level("Elektrische Schaltungen","Elektrische Schaltungen", "Elektrische Schaltungen werden Präsentiert von Herr Höhm.", "in Raum A109", "Herr Hohm", 4, false),
            new Level("Mathe im BG","Mathe im Beruflichen Gym.", "Vorstellung der Themen die in der Oberstufe unterrichtet werden. Mit unteranderem einem kleinen Kenntnis Test im Bereich Mathe und der Vorstellung von Geogebra.", "Raum A109", "Herr Hohm",5, false),
            new Level("JavaScript Taschenrechner","JavaScript Taschenrechner", "Programmierung eines Taschenrechners in JavaScript der z.B. Berechnungen, wie Addieren oder Nullstellenfindung, im Browser ausführt.", "in Raum A113", "Herr Ostermann",6, false),
            new Level("Industriemechaniker","Industrie- mechaniker", "Industriemechanik an der GTS wird vorgestellt.", "in Raum C201", "Herr Schneider", 7, false),
            new Level("Sport","Sportangebot an der GTS", "Es ist eine Slackline Aufgebaut und Schüler Präsentationen über den Ski- und Snowboardkurs.", "in der Sporthalle", "Frau Nubert", 8, false),
            new Level("Projektpräsentationen","Projekt- präsentationen", "Präsentationen des Informatik Leistungskurses der Q3.", "in Raum A209", "Hr. Wendt, Fr. Lingau", 9, false),
            new Level("Mathe Mini Game","Mathe Mini Game","Mathe Mini Game"," bei 60%","",10,false)
};

    LevelList() {
        //TODO SAVE
    }


    public static void init(Graphics g){
       levels[0].setImage(g.newImage("gts.png", Graphics.ImageFormat.RGB565));
       levels[1].setImage(g.newImage("infostand.png", Graphics.ImageFormat.RGB565));
       levels[2].setImage(g.newImage("fragezeichen.png", Graphics.ImageFormat.RGB565));
       levels[3].setImage(g.newImage("mechatronik.png", Graphics.ImageFormat.RGB565));
       levels[4].setImage(g.newImage("elektrischeSchaltung.png", Graphics.ImageFormat.RGB565));
       levels[5].setImage(g.newImage("grundkursmathe.png", Graphics.ImageFormat.RGB565));
       levels[6].setImage(g.newImage("projektpraesentation.png", Graphics.ImageFormat.RGB565));
       levels[7].setImage(g.newImage("rechnerJavaSkript.png", Graphics.ImageFormat.RGB565));
       levels[8].setImage(g.newImage("industriemechaniker.png", Graphics.ImageFormat.RGB565));
       levels[9].setImage(g.newImage("sport.png", Graphics.ImageFormat.RGB565));
       levels[10].setImage(g.newImage("matheapp.png", Graphics.ImageFormat.RGB565));
    }

    public static Level[] getLevels() {
        return levels;
    }
}
