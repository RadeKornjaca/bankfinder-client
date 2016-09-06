package pma.bankfinder.content_providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import pma.bankfinder.database.BankTableHelper;
import pma.bankfinder.database.BankfinderOpenHelper;

public class BankfinderDatabaseContentProvider extends ContentProvider {

    private static final String SCHEME = "content://";
    private static final String AUTHORITY = "pma.bankfinder";

    private static final String BANK_TABLE = "banks";
    private static final int BANK_TABLE_ID = 1;

    private static final String PLACE_TABLE = "places";
    private static final int PLACE_TABLE_ID = 2;

    private static final String REPORT_TABLE = "reports";
    private static final int REPORT_TABLE_ID = 3;

    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(AUTHORITY, BANK_TABLE, BANK_TABLE_ID);
        uriMatcher.addURI(AUTHORITY, PLACE_TABLE, PLACE_TABLE_ID);
        uriMatcher.addURI(AUTHORITY, REPORT_TABLE, REPORT_TABLE_ID);
    }
    private SQLiteOpenHelper databaseProvider;

    public BankfinderDatabaseContentProvider() {

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase database = databaseProvider.getWritableDatabase();

        int match = uriMatcher.match(uri);
        String tableName;

        switch (match) {
            case BANK_TABLE_ID:
                tableName = BANK_TABLE;
                break;
            case PLACE_TABLE_ID:
                tableName = PLACE_TABLE;
                break;
            case REPORT_TABLE_ID:
                tableName = REPORT_TABLE;
                break;
            default:
                throw new IllegalArgumentException("Unknown URI provided: " + uri);
        }

        long id = database.insert(
                tableName,
                null,
                values
        );

        getContext().getContentResolver().notifyChange(uri, null);

        return generateUri(id, tableName);
    }

    @Override
    public boolean onCreate() {
        databaseProvider = new BankfinderOpenHelper(getContext());

        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private Uri generateUri(long id, String tableName) {
        return Uri.parse(SCHEME + AUTHORITY + "/" + tableName + "/" + String.valueOf(id));
    }
}
