package pma.bankfinder.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rade on 6/29/16.
 */
public class BankfinderOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bankfinder.db";
    private static final int DATABASE_VERSION = 1;

    public BankfinderOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        BankTableHelper.createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
