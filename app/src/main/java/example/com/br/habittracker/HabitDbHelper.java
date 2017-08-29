package example.com.br.habittracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import example.com.br.habittracker.HabitContract.HabitEntry;

/**
 * Created by ptramontin on 8/29/17.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "HabitTracker.db";

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_HABIT_TRACKER_TABLE =
            "CREATE TABLE " + HabitEntry.TABLE_NAME + " (" +
                HabitEntry._ID + " INTEGER PRIMARY KEY," +
                HabitEntry.COLUMN_NAME_TITLE + " TEXT," +
                HabitEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
                HabitEntry.COLUMN_NAME_FREQUENCY + " INTEGER)";

        db.execSQL(SQL_CREATE_HABIT_TRACKER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_HABIT_TRACKER_TABLE =
            "DROP TABLE IF EXISTS " + HabitEntry.TABLE_NAME;

        db.execSQL(SQL_DELETE_HABIT_TRACKER_TABLE);
        onCreate(db);
    }
}
