package pma.bankfinder.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pma.bankfinder.R;
import pma.bankfinder.activities.decorators.ToolbarDecorator;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ToolbarDecorator toolbarDecorator = new ToolbarDecorator(this, findViewById(R.id.activity_sign_in_id), R.id.toolbar, "Sign In");
        toolbarDecorator.decorate(true);
    }
}
