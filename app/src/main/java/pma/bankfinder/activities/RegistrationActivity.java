package pma.bankfinder.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pma.bankfinder.R;
import pma.bankfinder.activities.decorators.ToolbarDecorator;
import pma.bankfinder.rest.Parameter;
import pma.bankfinder.rest.PostMethod;
import pma.bankfinder.rest.Request;
import pma.bankfinder.tasks.RegistrationAsyncTask;

public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG = RegistrationActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ToolbarDecorator toolbarDecorator = new ToolbarDecorator(this, findViewById(R.id.activity_registration_id), R.id.toolbar, "Registration");
        toolbarDecorator.decorate();
    }

    public void sendRegistrationRequest(View view) {

        TextView emailTextView = (TextView) findViewById(R.id.registration_email_id);
        TextView passwordTextView = (TextView) findViewById(R.id.registration_password_id);
        TextView confirmPasswordTextView = (TextView) findViewById(R.id.registration_confirm_password_id);

        List<Parameter> parameters = new ArrayList<Parameter>();
        parameters.add(new Parameter("email", emailTextView.getText().toString()));
        parameters.add(new Parameter("password", passwordTextView.getText().toString()));
        parameters.add(new Parameter("password_confirmation", confirmPasswordTextView.getText().toString()));

        Request request = new Request(getString(R.string.freefinder_api_url), "users", parameters);
        PostMethod postMethod = new PostMethod(this, request, "user");

        new RegistrationAsyncTask(this).execute(postMethod);
    }
}
