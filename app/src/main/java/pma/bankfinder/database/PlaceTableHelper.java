package pma.bankfinder.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by rade on 6/30/16.
 */
public class PlaceTableHelper {

    private static final String TABLE_NAME = "places";
    private static final String ID = "_id";
    private static final String ADDRESS = "address";
    private static final String CITY = "city";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String BANK_ID = "bank_id";
    private static final String BANK_TABLE_NAME = "banks";
    private static final String TIMESTAMP = "timestamp";

    private static final String BANK_TABLE =
                    "CREATE TABLE " + TABLE_NAME +
                    "(" +
                            ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                            ADDRESS + " TEXT NOT NULL," +
                            CITY + " TEXT NOT NULL" +
                            TIMESTAMP + " DATETIME NOT NULL," +
                            LATITUDE + " REAL," +
                            LONGITUDE + " REAL," +
                            "FOREIGN KEY(" + BANK_ID + ") REFERENCES " + BANK_TABLE_NAME + "(_ID)" +
                    ");";

    public static void createTable(SQLiteDatabase database) {
        database.execSQL(BANK_TABLE);
    }

    public static void updateTable(SQLiteDatabase database) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable(database);
    }
}
