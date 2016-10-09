package pma.bankfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import pma.bankfinder.R;
import pma.bankfinder.activities.decorators.ToolbarDecorator;
import pma.bankfinder.adapters.array.CategoryArrayAdapter;
import pma.bankfinder.database.model.Category;
import pma.bankfinder.fragments.AddBankDialogFragment;
import pma.bankfinder.fragments.listeners.DialogNotificationListener;

public class CategoryListActivity extends AppCompatActivity implements DialogNotificationListener{
    private static final String TAG = CategoryListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        ToolbarDecorator toolbarDecorator = new ToolbarDecorator(this, findViewById(R.id.activity_category_list_id),
                                                                R.id.toolbar, "Category List");
        toolbarDecorator.decorate(true);

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

        // List<Category> categories = new Select().from(Category.class).orderBy("ASC").execute();
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Test", null));
        categories.add(new Category("Test2", null));

        ArrayAdapter<Category> categoryArrayAdapter = new ArrayAdapter<Category>(this,
                                                                                R.layout.list_row,
                                                                                R.id.one_element_row,
                                                                                categories);

        ListView listView = (ListView) findViewById(R.id.category_list);

        listView.setAdapter(categoryArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // ListView Clicked item value
                ListView listView = (ListView) findViewById(R.id.category_list);
                Category  itemValue = (Category)listView.getItemAtPosition(i);

                Intent intent = new Intent(getApplicationContext(), CategoryDetailsActivity.class);
                intent.putExtra("categoryId", itemValue.getId());

                startActivity(intent);
            }
        });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option_category_list:
                Intent intent = new Intent(this, CategoryListActivity.class);
                startActivity(intent);

                return true;

            case R.id.app_settings:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
