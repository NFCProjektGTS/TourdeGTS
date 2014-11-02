package gtsoffenbach.tourdegts.implementations;

import java.util.ArrayList;

/**
 * Created by Kern on 31.10.2014.
 */
public class LevelList {
    public static Level[] levels = new Level[8];

    LevelList(){
 //TODO SAVE
    }

    public static Level[] getLevels() {
        levels[0]=new Level("Start","","Foyer","<lehrer>",0,false);
        levels[1]=new Level("Infostand/Empfang","","Foyer","<lehrer>",1,false);
        levels[2]=new Level("DSF Info","Kreuzer","A016","<lehrer>",2,false);
        levels[3]=new Level("Mechatronik","Pevny","A105","<lehrer>",3,false);
        levels[4]=new Level("Elektrische Schaltungen","Hohm","A109","<lehrer>",4,false);
        levels[5]=new Level("JavaScript Taschenrechner","Ostermann","A113","<lehrer>",5,false);
        levels[6]=new Level("Industriemechaniker","Schneider","C201","<lehrer>",6,false);
        levels[7]=new Level("Projektpräsentation","Wendt, Lingnau, Bickel","A209","<lehrer>",7,false);
        return levels;
    }
}
