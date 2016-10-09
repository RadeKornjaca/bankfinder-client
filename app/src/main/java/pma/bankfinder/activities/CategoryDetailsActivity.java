package pma.bankfinder.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import pma.bankfinder.R;
import pma.bankfinder.activities.decorators.ToolbarDecorator;
import pma.bankfinder.database.model.Category;
import pma.bankfinder.listeners.ParentCategoryOnClickListener;

public class CategoryDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);

        ToolbarDecorator toolbarDecorator = new ToolbarDecorator(this, findViewById(R.id.activity_category_details_id), R.id.toolbar, "Category Details");
        toolbarDecorator.decorate(true);

        Intent intent = getIntent();
        long categoryId = intent.getLongExtra("categoryId", -1);

        // Category category = Category.load(Category.class, categoryId);
        Category parentCategory = new Category("Parent", null);
        Category category = new Category("Test", parentCategory);

        TextView categoryNameTextView = (TextView) findViewById(R.id.category_name_text_view);
        categoryNameTextView.setText(categoryNameTextView.getText() + category.getName());

        TextView parentCategoryTextView = (TextView) findViewById(R.id.parent_category_text_view);
        parentCategoryTextView.setText(parentCategoryTextView.getText() + category.getCategory().getName());

        parentCategoryTextView.setOnClickListener(new ParentCategoryOnClickListener(parentCategory, this, CategoryDetailsActivity.class));
    }
}
