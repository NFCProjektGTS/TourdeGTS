package gtsoffenbach.tourdegts.implementations;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import gtsoffenbach.tourdegts.R;

/**
 * Created by Noli on 31.08.2014.
 */
public class NFCDialog {
    private Context caller;
    private boolean cancel;
    private AlertDialog dialog;

    NFCDialog(final Context mContext, int id) {
        this.caller = mContext;
        switch (id) {
            case 0:
                dialog = new AlertDialog.Builder(mContext)
                        .setTitle(R.string.dialog_nfc_title)
                        .setMessage(R.string.dialog_nfc)
                        .setPositiveButton(R.string.dialog_nfc_on, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if (android.os.Build.VERSION.SDK_INT >= 16) {
                                    caller.startActivity(new Intent(android.provider.Settings.ACTION_NFC_SETTINGS));
                                } else {
                                    caller.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                                }
                            }

                        })
                        .setNegativeButton(R.string.dialog_nfc_off, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Log.w(AndroidGame.TAG, "NFC wurde nicht Aktiviert");
                                Toast.makeText(caller.getApplicationContext(), "NFC ist für diese Anwendung erforderlich!", Toast.LENGTH_LONG).show();
                                android.os.Process.killProcess(android.os.Process.myPid());
                            }
                        })
                        .setIcon(android.R.drawable.ic_notification_clear_all)
                        .setCancelable(false)
                        .show();
                break;
            case 1:
                break;
        }
    }

    public void dismiss() {
        dialog.dismiss();
    }
}
