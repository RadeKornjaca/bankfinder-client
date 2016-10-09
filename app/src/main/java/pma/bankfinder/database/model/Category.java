package pma.bankfinder.database.model;

import com.activeandroid.Model;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by rade on 18.9.16..
 */
public class Category extends Model {

    private String name;
    private Category category;

    public Category() {
        super();
    }

    public Category(String name, Category category) {
        super();
        this.name = name;
        this.category = category;
    }

    public List<Place> getPlaces() {
        return getMany(Place.class, "category");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return getName();
    }
}
