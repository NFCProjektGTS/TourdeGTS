package gtsoffenbach.tourdegts.implementations;

/**
 * Created by Noli on 05.08.2014.
 */

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.PowerManager;

import gtsoffenbach.tourdegts.SplashLoadingScreen;
import gtsoffenbach.tourdegts.gameinterface.Audio;
import gtsoffenbach.tourdegts.gameinterface.FileIO;
import gtsoffenbach.tourdegts.gameinterface.Game;
import gtsoffenbach.tourdegts.gameinterface.Graphics;
import gtsoffenbach.tourdegts.gameinterface.Input;
import gtsoffenbach.tourdegts.gameinterface.NFC;
import gtsoffenbach.tourdegts.gameinterface.Screen;

public class AndroidGame extends Activity implements Game {


    public static final int width = 800;
    public static final int height = 1280;
    public static final String TAG = "DroidGame";
    GameFastRenderView renderView;
    Graphics graphics;
    Audio audio;
    Input input;
    FileIO fileIO;
    Screen screen;
    Context thiscontext;
    SaveGame save;
    PowerManager.WakeLock wakeLock;
    NFC nfc;
    private NFCDialog dialog;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (action.equals(NfcAdapter.ACTION_ADAPTER_STATE_CHANGED)) {
                final int state = intent.getIntExtra(NfcAdapter.EXTRA_ADAPTER_STATE,
                        NfcAdapter.STATE_OFF);
                switch (state) {
                    case NfcAdapter.STATE_OFF:
                        nfc.setNFCState(NfcAdapter.STATE_OFF);
                        if (dialog != null)
                            dialog.dismiss();
                        dialog = new NFCDialog(context, 0);                      //only used
                        break;
                    case NfcAdapter.STATE_TURNING_OFF:
                        nfc.setNFCState(NfcAdapter.STATE_TURNING_OFF);  //placeholder
                        break;
                    case NfcAdapter.STATE_ON:
                        nfc.setNFCState(NfcAdapter.STATE_ON);           //placeholder
                        dialog.dismiss();
                        break;
                    case NfcAdapter.STATE_TURNING_ON:
                        nfc.setNFCState(NfcAdapter.STATE_TURNING_ON);   //placeholder
                        break;
                }
            }
        }
    };

    public Context getThiscontext() {
        return thiscontext;
    }

    public SaveGame getSave() {
        return save;
    }

    public void setSave(SaveGame save) {
        this.save = save;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (nfc != null && nfc.isEnabled())
            nfc.resolveIntent(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/


        //setContentView(R.layout.activity_fullscreen);

        boolean isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
        int frameBufferWidth = isPortrait ? width : height;
        int frameBufferHeight = isPortrait ? height : width;
        //int frameBufferHeight = isPortrait ? 15360 : 8640;
        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
                frameBufferHeight, Bitmap.Config.RGB_565);

        float scaleX = (float) frameBufferWidth
                / getWindowManager().getDefaultDisplay().getWidth();
        float scaleY = (float) frameBufferHeight
                / getWindowManager().getDefaultDisplay().getHeight();

        renderView = new GameFastRenderView(this, frameBuffer);
        graphics = new GameGraphics(getAssets(), frameBuffer);
        fileIO = new GameFileIO(this);
        audio = new GameAudio(this);
        input = new GameInput(this, renderView, scaleX, scaleY);
        screen = getInitScreen();
        nfc = new GameNFC(this, this);
        save = new SaveGame(this);
        thiscontext = this;
        if (android.os.Build.VERSION.SDK_INT >= 18) {
            IntentFilter filter = new IntentFilter(NfcAdapter.ACTION_ADAPTER_STATE_CHANGED);
            this.registerReceiver(mReceiver, filter);
        }
        setContentView(renderView);


        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "MyGame");

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.


        // delayedHide(100);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */

    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public FileIO getFileIO() {
        return fileIO;
    }

    @Override
    public Graphics getGraphics() {
        return graphics;
    }

    @Override
    public Audio getAudio() {
        return audio;
    }

    @Override
    public NFC getNFC() {
        return nfc;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void setScreen(Screen screen) {
        if (screen == null)
            throw new IllegalArgumentException("Screen must not be null");

        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen = screen;
    }

    @Override
    public Screen getCurrentScreen() {

        return screen;
    }

    @Override
    protected void onResume() {
        super.onResume();
        wakeLock.acquire();
        screen.resume();
        renderView.resume();

        if (nfc.getmNfcAdapter() != null && !nfc.getmNfcAdapter().isEnabled()) {
            dialog = new NFCDialog(this, 0);
        }

        if (nfc != null) {
            nfc.checkNFC();
            nfc.installService();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        wakeLock.release();
        renderView.pause();
        screen.pause();
        if (nfc != null) {
            nfc.checkNFC();
            nfc.uninstallService();
        }
        if (isFinishing()) {
            screen.dispose();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (android.os.Build.VERSION.SDK_INT >= 18) {
            this.unregisterReceiver(mReceiver);
        }

    }

    @Override
    public Screen getInitScreen() { //TODO ???


        return new SplashLoadingScreen(this); // default, weil kein InitScreen festgelegt, evtl ladebildschirm?
    }

    @Override
    public void onBackPressed() {
        getCurrentScreen().backButton();
    }
}