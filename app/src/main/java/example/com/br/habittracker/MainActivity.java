package example.com.br.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract.CommonDataKinds.Contactables;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import example.com.br.habittracker.HabitContract.HabitEntry;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new HabitDbHelper(this);

        testReadWrite();
    }

    private void testReadWrite() {
        Boolean inserted = insertHabit("Wake up", "Times I wake up in a week", 7);

        Log.i(TAG, String.format("inserted? %B", inserted));

        Cursor cursor = readHabits();

        try {
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int titleColumIndex = cursor.getColumnIndex(HabitEntry.COLUMN_NAME_TITLE);
            int descColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_NAME_DESCRIPTION);
            int freqColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_NAME_FREQUENCY);

            Log.i(TAG, String.format("id | title | description | frequency"));

            while (cursor.moveToNext()) {
                int id = cursor.getInt(idColumnIndex);
                String title = cursor.getString(titleColumIndex);
                String desc = cursor.getString(descColumnIndex);
                int freq = cursor.getInt(freqColumnIndex);

                Log.i(TAG, String.format("%d | %s | %s | %d", id, title, desc, freq));
            }
        } finally {
            cursor.close();
        }
    }

    /**
     * Inserts a new habit into the database
     *
     * @param title habit title
     * @param description description of the title
     * @param frequency frequency that the habit occurs
     * @return <code>true</code> when it is inserted correctly or <code>false</code> otherwise
     */
    private boolean insertHabit(String title, String description, Integer frequency) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_NAME_TITLE, title);
        values.put(HabitEntry.COLUMN_NAME_DESCRIPTION, description);
        values.put(HabitEntry.COLUMN_NAME_FREQUENCY, frequency);

        return -1 != db.insert(HabitEntry.TABLE_NAME, null, values);
    }

    /**
     * Queries the database and returns a Cursor
     *
     * @return a Cursor to the database
     */
    private Cursor readHabits() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
            HabitEntry._ID,
            HabitEntry.COLUMN_NAME_TITLE,
            HabitEntry.COLUMN_NAME_DESCRIPTION,
            HabitEntry.COLUMN_NAME_FREQUENCY,
        };

        return db.query(HabitEntry.TABLE_NAME, projection, null, null, null, null, null);
    }
}
