package com.example.avvmarket.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.avvmarket.data.DatabaseContract.StocksEntry;


public class StocksProvider extends ContentProvider {

    private static final String LOG_TAG = StocksProvider.class.getSimpleName();
    public DBHelper dbHelper;
    private static final int STOCKS = 100; //URI matcher code for all the rows in stocks table.
    private static final int STOCKS_ID = 101; //URI matcher code for particular row in stocks table.
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // This line is executed first if anything from this class is called.
    static {
        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY, DatabaseContract.PATH_DB, STOCKS);
        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,DatabaseContract.PATH_DB+"/#",STOCKS_ID);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        int match = sUriMatcher.match(uri);
        switch(match){
            case STOCKS:
                cursor = sqLiteDatabase.query(StocksEntry.TABLE_NAME, projection, selection,
                        selectionArgs, null,null,sortOrder);
                break;
            case STOCKS_ID:
                selection = StocksEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri))};
                cursor = sqLiteDatabase.query(StocksEntry.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("StocksProvider: Cannot query unknown URI " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        //TODO: Insert function not started as it is not required yet.
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsupdated = 0;

        int match = sUriMatcher.match(uri);
        switch (match){
            case STOCKS:
                rowsupdated = db.update(StocksEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case STOCKS_ID:
                selection = StocksEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri))};
                rowsupdated = db.update(StocksEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("update not working properly!");
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return rowsupdated;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }
}
