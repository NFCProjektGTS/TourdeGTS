package gtsoffenbach.tourdegts.implementations;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Noli on 02.09.2014.
 */
public class SaveGame {
    public static final String path = "secretfile.secret";
    public static boolean newGame = true;

    public static Level[] levels = LevelList.getLevels();
    private static int lastlevel = 0;
    private Context caller;
    private FileOutputStream fos;
    private FileInputStream fis;
    private String content;

    SaveGame(Context caller) {
        this.caller = caller;
    }

    public static int getLastlevel() {
        return lastlevel;
    }

    public static void setLastlevel(int lastlevel) {
        SaveGame.lastlevel = lastlevel;
    }

    public static boolean isNewGame() {
        return newGame;
    }

    public static void setNewGame(boolean newGame) {
        SaveGame.newGame = newGame;
    }

    public void loadGame() {
        /*if(newGame){
            save();
        }*/


        try {
            //fos = caller.openFileOutput(path, Context.MODE_PRIVATE);
            fis = caller.openFileInput(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            save();
            newGame = true;
            return;
        }

        newGame = true;
        if (SaveGame.levels[0].isUnlocked()) {
            newGame = false;
        }


        int le = 0;
        for (Level l : SaveGame.levels) {
            le = l.isUnlocked() ? le + 1 : le;
        }
        if (le > 0) newGame = false;


        int n;
        StringBuffer fileContent = new StringBuffer("");
        try {

            byte[] buffer = new byte[1024];

            while ((n = fis.read(buffer)) != -1) {
                fileContent.append(new String(buffer, 0, n));
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        content = fileContent.toString();

        if (content != "" && content != null) {
            //Load from file
            String[] parts = content.split("#");
            try {
                lastlevel = Integer.valueOf(parts[0]);
            } catch (NumberFormatException e) {
                delete();
                loadGame();
                return;
            }
            for (int i = 1; i < parts.length; i++) {
                String[] set = parts[i].split(",");
                if (set[1].equals("1")) {
                    levels[Integer.valueOf(set[0])].setUnlocked(true);
                } else {
                    levels[Integer.valueOf(set[0])].setUnlocked(false);
                }
                //levels[Integer.valueOf(set[0])].setUnlocked(set[1] == "1" ? true : false);
                levels[Integer.valueOf(set[0])].setLevelnumber(Integer.valueOf(set[0]));
            }
        }
    }

    public void save() {

        StringBuilder sb = new StringBuilder();
        sb.append(lastlevel).append("#");
        for (int i = 0; i < levels.length; i++) {
            if (i == levels.length - 1) {
                sb.append(levels[i].getLevelnumber()).append(",").append(levels[i].isUnlocked() ? 1 : 0);
                break;
            } else {
                sb.append(levels[i].getLevelnumber()).append(",").append(levels[i].isUnlocked() ? 1 : 0).append("#");
            }
        }
        try {
            //File f = new File(path);
            //f.delete();
            //f.createNewFile();
            fos = caller.openFileOutput(path, Context.MODE_PRIVATE);
            fos.write(sb.toString().getBytes());
            fos.close();
            //newGame = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        newGame = false;

    }

    public void delete() {  //even more a reset
        for (int i = 0; i < SaveGame.levels.length; i++) {
            SaveGame.levels[i].setUnlocked(false);
        }
        //save();
        caller.deleteFile(path);
        newGame = true;
    }
}
