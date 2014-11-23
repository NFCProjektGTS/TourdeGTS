package gtsoffenbach.tourdegts.implementations;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.os.Parcelable;
import android.util.Log;

import java.math.BigInteger;

import gtsoffenbach.tourdegts.Utils;
import gtsoffenbach.tourdegts.gameinterface.NFC;

/**
 * Created by Noli on 14.07.2014.
 */
public class GameNFC extends NFC {
    private AndroidGame game;

    GameNFC(Activity caller, AndroidGame game) {
        this.game = game;
        this.caller = caller;
        this.mNfcAdapter = NfcAdapter.getDefaultAdapter(caller);
        checkNFC();

        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        this.mTagFilters = new IntentFilter[]{tagDetected};
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }


    @Override
    public void installService() {
        if (mNfcAdapter != null) {
            if (mNfcAdapter.isEnabled() || mNfcAdapter != null) {
                Intent activityIntent = new Intent(caller, caller.getClass());
                activityIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                PendingIntent intent = PendingIntent.getActivity(caller, 0,
                        activityIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                mNfcAdapter.enableForegroundDispatch(caller, intent, mTagFilters, null);
            }
        }
    }

    @Override
    public void uninstallService() {
        if (mNfcAdapter != null) {
            if (mNfcAdapter.isEnabled()) {
                mNfcAdapter.disableForegroundDispatch(caller);
            }
        }
    }

    @Override
    public boolean checkNFC() {
        if (mNfcAdapter != null) {
            if (!mNfcAdapter.isEnabled()) {
                Log.e(AndroidGame.TAG, "NFC is disabled");
                Log.e(AndroidGame.TAG, "Please enable NFC in the settings");
            }
            if (mNfcAdapter.isEnabled()) {
                this.enabled = true;
                return true;
            }
        } else {
            Log.e(AndroidGame.TAG, "NFC Hardware nicht gefunden!");
        }
        this.enabled = false;
        return false;
    }

    @Override
    public void resolveIntent(Intent intent) {
        String action = intent.getAction();
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            Log.i(AndroidGame.TAG, "Tag discovered");
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage[] msgs;
            Log.i(AndroidGame.TAG, "Reading");
            if (rawMsgs != null) {
                msgs = new NdefMessage[rawMsgs.length];
                for (int i = 0; i < rawMsgs.length; i++) {
                    msgs[i] = (NdefMessage) rawMsgs[i];
                }
            } else {
                msgs = NoNDEFContent(intent);
            }
            mCurrentNdef = msgs;
            operate(mCurrentNdef);
            printTag(mCurrentNdef);

        }

    }

    @Override
    public byte[] rawTagData(Parcelable parc) {
        StringBuilder s = new StringBuilder();
        Tag tag = (Tag) parc;
        byte[] id = tag.getId();
        s.append("UID In Hex: ").append(Utils.convertByteArrayToHexString(id)).append("\n");
        s.append("UID In Dec: ").append(Utils.convertByteArrayToDecimal(id)).append("\n\n");
        String prefix = "android.nfc.tech.";
        s.append("Technologies: ");
        for (String tech : tag.getTechList()) {
            s.append(tech.substring(prefix.length()));
            s.append(", ");
        }
        s.delete(s.length() - 2, s.length());
        for (String tech : tag.getTechList()) {
            if (tech.equals(MifareClassic.class.getName())) {
                s.append('\n');
                MifareClassic mifareTag = MifareClassic.get(tag);
                String type = "Unknown";
                switch (mifareTag.getType()) {
                    case MifareClassic.TYPE_CLASSIC:
                        type = "Classic";
                        break;
                    case MifareClassic.TYPE_PLUS:
                        type = "Plus";
                        break;
                    case MifareClassic.TYPE_PRO:
                        type = "Pro";
                        break;
                }
                s.append("Mifare Classic type: ").append(type).append('\n');
                s.append("Mifare size: ").append(mifareTag.getSize() + " bytes").append('\n');
                s.append("Mifare sectors: ").append(mifareTag.getSectorCount()).append('\n');
                s.append("Mifare blocks: ").append(mifareTag.getBlockCount());
            }
            if (tech.equals(MifareUltralight.class.getName())) {
                s.append('\n');
                MifareUltralight mifareUlTag = MifareUltralight.get(tag);
                String type = "Unknown";
                switch (mifareUlTag.getType()) {
                    case MifareUltralight.TYPE_ULTRALIGHT:
                        type = "Ultralight";
                        break;
                    case MifareUltralight.TYPE_ULTRALIGHT_C:
                        type = "Ultralight C";
                        break;
                }
                s.append("Mifare Ultralight type: ").append(type);
            }
        }
        return s.toString().getBytes();
    }

    @Override
    public NdefMessage[] NoNDEFContent(Intent intent) {
        byte[] empty = new byte[0];
        byte[] id = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
        Parcelable tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        byte[] payload = rawTagData(tag);
        NdefRecord record = new NdefRecord(NdefRecord.TNF_UNKNOWN, empty, id, payload);
        NdefMessage msg = new NdefMessage(new NdefRecord[]{record});
        return new NdefMessage[]{msg};

    }

    @Override
    public void printTag(NdefMessage[] msgs) {
        for (NdefMessage msg : msgs) {
            for (NdefRecord rec : msg.getRecords()) {
                byte[] payload = rec.getPayload();
                String content = new String(payload);
                Log.i(AndroidGame.TAG, "Message: " + msg.toString());
                Log.i(AndroidGame.TAG, "Record: " + rec.toString());
                Log.i(AndroidGame.TAG, "Content: " + content);
            }
        }
    }


    @Override
    public void operate(NdefMessage[] msgs) {
        //TODO unlock game content on operation
        for (NdefMessage ms : msgs) {
            for (NdefRecord rec : ms.getRecords()) {
                switch (new BigInteger(rec.getType()).intValue()) {
                    //case OpCode.NFC_INITIAL_TAG:
                    //not in prototype
                    //    break;
                    case OpCode.NFC_UNLOCK_LEVEL_1:
                        LevelUnlock.unlock(game, OpCode.NFC_UNLOCK_LEVEL_1);
                        break;
                    case OpCode.NFC_UNLOCK_LEVEL_2:
                        LevelUnlock.unlock(game, OpCode.NFC_UNLOCK_LEVEL_2);
                        break;
                    case OpCode.NFC_UNLOCK_LEVEL_3:
                        LevelUnlock.unlock(game, OpCode.NFC_UNLOCK_LEVEL_3);
                        break;
                    case OpCode.NFC_UNLOCK_LEVEL_4:
                        //LevelUnlock.unlock(game, OpCode.NFC_UNLOCK_LEVEL_4);
                        break;
                    default:
                        System.out.print("Unrecognized format");
                        break;
                }
            }
        }

    }


    private abstract interface OpCode {
        //public static final int NFC_INITIAL_TAG = 0;
        public static final int NFC_UNLOCK_LEVEL_1 = 0;
        public static final int NFC_UNLOCK_LEVEL_2 = 1;
        public static final int NFC_UNLOCK_LEVEL_3 = 2;
        public static final int NFC_UNLOCK_LEVEL_4 = 3;

    }

    ;
}
