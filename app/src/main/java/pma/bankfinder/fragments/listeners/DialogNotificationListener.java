package pma.bankfinder.fragments.listeners;

import android.support.v4.app.DialogFragment;

/**
 * Created by rade on 7/3/16.
 */
public interface DialogNotificationListener {

    public abstract void onDialogPositiveClick(DialogFragment dialog);
    public abstract void onDialogNegativeClick(DialogFragment dialog);
}
