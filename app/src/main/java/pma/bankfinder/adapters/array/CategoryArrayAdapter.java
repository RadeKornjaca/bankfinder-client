package pma.bankfinder.adapters.array;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pma.bankfinder.R;
import pma.bankfinder.database.model.Category;

/**
 * Created by rade on 18.9.16..
 */
public class CategoryArrayAdapter extends ArrayAdapter<Category> {

    public CategoryArrayAdapter(Context context, int layoutId, List<Category> objects) {
        super(context, layoutId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Category category = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_category_list, parent, false);
        }
        // Lookup view for data population
        // TextView tvName = (TextView) convertView.findViewById(R.id.category_list);
        // Populate the data into the template view using the data object
        // tvName.setText(category.getName());

        // Return the completed view to render on screen
        return convertView;
    }
}
