package pma.bankfinder.database.model;

import java.util.Date;

/**
 * Created by rade on 6/30/16.
 */
public class Bank {
    private int id;
    private String name;
    private String officialWebsite;
    private Date timestamp;

    public Bank(int id, String name, String officialWebsite, Date timestamp) {
        this.id = id;
        this.name = name;
        this.officialWebsite = officialWebsite;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOfficialWebsite() {
        return officialWebsite;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
