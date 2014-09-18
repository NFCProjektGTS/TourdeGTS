package gtsoffenbach.tourdegts.gameinterface;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Parcelable;

/**
 * Created by Noli on 14.07.2014.
 */
public abstract class NFC {
    protected NfcAdapter mNfcAdapter;
    protected Activity caller;
    protected Tag wTag;
    protected boolean enabled = false;
    protected IntentFilter[] mTagFilters;
    protected NdefMessage[] mCurrentNdef;
    protected int NFCState;

    public NfcAdapter getmNfcAdapter() {
        return mNfcAdapter;
    }

    public void setmNfcAdapter(NfcAdapter mNfcAdapter) {
        this.mNfcAdapter = mNfcAdapter;
    }

    public Tag getwTag() {
        return wTag;
    }

    public void setwTag(Tag wTag) {
        this.wTag = wTag;
    }

    public IntentFilter[] getmTagFilters() {
        return mTagFilters;
    }

    public void setmTagFilters(IntentFilter[] mTagFilters) {
        this.mTagFilters = mTagFilters;
    }

    public NdefMessage[] getmCurrentNdef() {
        return mCurrentNdef;
    }

    public void setmCurrentNdef(NdefMessage[] mCurrentNdef) {
        this.mCurrentNdef = mCurrentNdef;
    }

    public int getNFCState() {
        return NFCState;
    }

    public void setNFCState(int NFCState) {
        this.NFCState = NFCState;
    }

    public abstract boolean isEnabled();

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public abstract void installService();

    public abstract void uninstallService();

    public abstract boolean checkNFC();

    public abstract void resolveIntent(Intent intent);

    public abstract byte[] rawTagData(Parcelable parc);

    public abstract NdefMessage[] RawNDEFContent(Intent intent);

    public abstract void printTag(NdefMessage[] msgs);

    public abstract void operate(NdefMessage[] msgs);
}
