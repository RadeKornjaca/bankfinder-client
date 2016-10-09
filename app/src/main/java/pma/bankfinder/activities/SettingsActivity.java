package pma.bankfinder.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pma.bankfinder.R;
import pma.bankfinder.activities.decorators.ToolbarDecorator;
import pma.bankfinder.fragments.SettingsFragment;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        ToolbarDecorator toolbarDecorator = new ToolbarDecorator(this, findViewById(R.id.activity_settings_id), R.id.toolbar, "Settings");
        toolbarDecorator.decorate(true);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(R.id.settings_layout_id, new SettingsFragment())
                .commit();
    }
}
