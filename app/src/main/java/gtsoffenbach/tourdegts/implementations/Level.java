package gtsoffenbach.tourdegts.implementations;


import gtsoffenbach.tourdegts.gameinterface.Image;

/**
 * Created by Noli on 04.09.2014.
 */
public class Level {
    private String name;
    private String anzeigeName;
    private String raum;
    private String info;
    private String lehrer;
    private int levelnumber;
    private boolean unlocked;

    private Image levelPicture;


    Level(String name,String anzeigeName,String info,String raum,String lehrer, int level, boolean unlocked) {
        this.name = name;
        this.anzeigeName= anzeigeName;
        this.info = info;
        this.raum = raum;
        this.lehrer = lehrer;
        this.levelnumber = level;
        this.unlocked = unlocked;


    }

    public Image getImage() {
        return levelPicture;
    }

    public void setImage(Image value){
        levelPicture = value;
    }

    public String getAnzeigeName() {
        return anzeigeName;
    }

    public int getLevelnumber() {
        return levelnumber;
    }

    public void setLevelnumber(int levelnumber) {
        this.levelnumber = levelnumber;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRaum(){
        return raum;
    }
    public String getInfo(){
        return info;
    }
    public String getLehrer(){
        return lehrer;
    }
}
