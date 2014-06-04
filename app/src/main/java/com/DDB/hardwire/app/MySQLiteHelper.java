package com.DDB.hardwire.app;

/**
 * Created by MarK on 22-May-14.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "products.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_PRODUCTS = "PRODUCTS";
    public static final String COLUMN_LISTID = "listid";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PRICE = "price";
    public static final String TABLE_BUILDNAME = "BUILDNAME";
    public static final String COLUMN_BUILDNAME = "name";

    private static final String DATABASE_CREATE = "create table "
            + TABLE_PRODUCTS + "(" + COLUMN_LISTID + " text not null, " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_NAME
            + " text not null, " + COLUMN_DESCRIPTION + " text not null, " + COLUMN_PRICE + " double(4,2) not null);";

    private static final String DATABASE_NAME_CREATE = "create table "
            + TABLE_BUILDNAME + "(" + COLUMN_BUILDNAME + " text);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
        database.execSQL(DATABASE_NAME_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUILDNAME);
        onCreate(db);
    }
}
