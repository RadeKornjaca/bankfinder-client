package pma.bankfinder.database.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by rade on 6/30/16.
 */
@Table(name = "place")
public class Place extends Model {

    private String name;
    private double lat;
    private double lng;
    // private String imagePath; // Is this necessary to be done this way?
    private int likes;
    private int dislikes;
    private int inappropriate;
    private Category category;

    public Place() {
        super();
    }

    public Place(String name, double lat, double lng, int likes, int dislikes, int inappropriate, Category category) {
        super();
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.likes = likes;
        this.dislikes = dislikes;
        this.inappropriate = inappropriate;
        this.category = category;
    }
}