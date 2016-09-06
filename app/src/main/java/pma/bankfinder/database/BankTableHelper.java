package pma.bankfinder.database;

import android.database.sqlite.SQLiteDatabase;

import com.x5.template.Chunk;

/**
 * Created by rade on 6/30/16.
 */
public class BankTableHelper {

    public static final String TABLE_NAME = "banks";
    private static final String ID = "_id";
    private static final String NAME = "name";
    private static final String OFFICIAL_WEBSITE = "official_website";
    private static final String TIMESTAMP = "timestamp";

    private static final String BANK_TABLE =
            "CREATE TABLE " + TABLE_NAME +
            "(" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            NAME + " TEXT NOT NULL," +
            OFFICIAL_WEBSITE + " TEXT NOT NULL" +
            TIMESTAMP + " DATETIME NOT NULL," +
            ");";

    public static void createTable(SQLiteDatabase database) {
        database.execSQL(BANK_TABLE);
    }

    public static void updateTable(SQLiteDatabase database) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable(database);
    }
}
