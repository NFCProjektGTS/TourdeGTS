package gtsoffenbach.tourdegts.implementations;

/**
 * Created by Noli on 04.09.2014.
 */
public class Level {
    private String name;
    private int levelnumber;
    private boolean unlocked;

    Level(String name, int level, boolean unlocked) {
        this.name = name;
        this.levelnumber = level;
        this.unlocked = unlocked;
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
}
