package pma.bankfinder.fragments;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pma.bankfinder.R;
import pma.bankfinder.activities.MapOverviewActivity;
import pma.bankfinder.activities.RegistrationActivity;
import pma.bankfinder.activities.SettingsActivity;

public class FirstTimeDialogFragment extends DialogFragment {

    private static final int DELAY_TIME = 2000;
    private Activity activity;
    private Handler mapOverviewHandler;
    private Runnable handlerAction;

    public FirstTimeDialogFragment() {

    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate the layout for this fragment
        builder.setView(inflater.inflate(R.layout.fragment_first_time_dialog, null))
            .setPositiveButton("Open Settings", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(getActivity(), SettingsActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }).setNegativeButton("Registration", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(getActivity(), RegistrationActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
        });

        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

//        mapOverviewHandler = new Handler();
//        handlerAction = new HandlerActionRunnable(getActivity());
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

//        mapOverviewHandler.postDelayed(handlerAction, DELAY_TIME);
    }

    private class HandlerActionRunnable implements Runnable {

        private Activity activity;

        public HandlerActionRunnable(Activity activity) {
            this.activity = activity;
        }

        @Override
        public void run() {
            Intent startMainScreen = new Intent(activity, MapOverviewActivity.class);
            startActivity(startMainScreen);
            activity.finish();
        }
    }
}
