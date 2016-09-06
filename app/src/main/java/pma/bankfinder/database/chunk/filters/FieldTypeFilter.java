package pma.bankfinder.database.chunk.filters;

import com.x5.template.Chunk;
import com.x5.template.filters.FilterArgs;
import com.x5.template.filters.ObjectFilter;


/**
 * Created by rade on 6/30/16.
 */
public class FieldTypeFilter extends ObjectFilter{
    @Override
    public String getFilterName() {
        return "field_type";
    }

    @Override
    public Object transformObject(Chunk chunk, Object object, FilterArgs args) {
        String type = (String)object;

        if(type.equals("String")) {
            return "TEXT";
        } else if(type.equals("double")) {
            return "REAL";
        } else if(type.equals("int")) {
            return "INTEGER";
        }

        return null;
    }
}
