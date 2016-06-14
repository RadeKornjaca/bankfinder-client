package pma.bankfinder.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import pma.bankfinder.R;

/**
 * Created by rade on 6/13/16.
 */
public class LocationUnavailableDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_location_unavailable)
                .setPositiveButton(R.string.close_application, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getActivity().finish();
                        System.exit(0);
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
