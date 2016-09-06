package pma.bankfinder.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import pma.bankfinder.R;
import pma.bankfinder.fragments.AddBankDialogFragment;
import pma.bankfinder.fragments.listeners.DialogNotificationListener;

public class BankListActivity extends AppCompatActivity implements DialogNotificationListener{
    private static final String TAG = BankListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogFragment addBankDialogFragment = new AddBankDialogFragment();
                    addBankDialogFragment.show(getSupportFragmentManager(), "new_bank");
                }
            });
        }
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        EditText editTextBankName = ((AddBankDialogFragment)dialog).getEditTextBankName();
        EditText editTextOfficialWebsite = ((AddBankDialogFragment)dialog).getEditTextOfficialWebsite();

        Log.d(TAG, editTextBankName.getText().toString());
        Log.d(TAG, editTextOfficialWebsite.getText().toString());

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
