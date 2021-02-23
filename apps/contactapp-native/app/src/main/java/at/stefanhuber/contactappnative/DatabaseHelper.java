package at.stefanhuber.contactappnative;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static int DB_VERSION = 1;
    public static String DB_NAME = "contact_database";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTaskTable = "CREATE TABLE person (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "firstname TEXT, " +
                "lastname TEXT, " +
                "phone TEXT " +
                ")";
        db.execSQL(createTaskTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
