package pma.bankfinder.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import pma.bankfinder.database.model.Category;

/**
 * Created by rade on 23.9.16..
 */
public class ParentCategoryOnClickListener implements View.OnClickListener {

    private Category parentCategory;
    private Context context;
    private Class aClass;

    public ParentCategoryOnClickListener(Category parentCategory, Context context, Class aClass) {
        this.parentCategory = parentCategory;
        this.context = context;
        this.aClass = aClass;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, aClass);
        intent.putExtra("categoryId", parentCategory.getId());

        context.startActivity(intent);
    }
}
