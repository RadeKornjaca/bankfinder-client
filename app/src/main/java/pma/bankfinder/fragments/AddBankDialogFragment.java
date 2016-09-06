package pma.bankfinder.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;

import pma.bankfinder.R;
import pma.bankfinder.fragments.listeners.DialogNotificationListener;

/**
 * Created by rade on 7/2/16.
 */
public class AddBankDialogFragment extends DialogFragment {

    private EditText editTextBankName;
    private EditText editTextOfficialWebsite;

    // Use this instance of the interface to deliver action events
    private DialogNotificationListener mListener;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        editTextBankName = (EditText) inflater.inflate(R.layout.add_bank_dialog, null).findViewById(R.id.bank_name);
        editTextOfficialWebsite = (EditText) inflater.inflate(R.layout.add_bank_dialog, null).findViewById(R.id.official_website);

        builder.setView(inflater.inflate(R.layout.add_bank_dialog, null))
                .setPositiveButton(R.string.add_bank_btn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogPositiveClick(AddBankDialogFragment.this);
                    }
                })
                .setNegativeButton(R.string.cancel_btn, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddBankDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (DialogNotificationListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement DialogNotificationListener");
        }
    }

    public EditText getEditTextBankName() {
        return editTextBankName;
    }

    public EditText getEditTextOfficialWebsite() {
        return editTextOfficialWebsite;
    }
}
