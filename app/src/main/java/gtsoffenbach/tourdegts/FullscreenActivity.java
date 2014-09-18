package gtsoffenbach.tourdegts;

import android.content.Context;
import android.os.Vibrator;

import gtsoffenbach.tourdegts.gameinterface.Screen;
import gtsoffenbach.tourdegts.implementations.AndroidGame;

public class FullscreenActivity extends AndroidGame {


    public static Vibrator vibrator;
    private boolean firstrun = true;

    @Override
    public Screen getInitScreen() {

        if (firstrun) {
            Assets.load(this);
            vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
            firstrun = false;
        }
        return new SplashLoadingScreen(this);

    }

    @Override
    public void onBackPressed() {
        getCurrentScreen().backButton();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
