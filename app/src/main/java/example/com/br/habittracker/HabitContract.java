package example.com.br.habittracker;

import android.provider.BaseColumns;

/**
 * Created by ptramontin on 8/29/17.
 */

public final class HabitContract {

    private HabitContract() {
    }

    public static class HabitEntry implements BaseColumns {

        public static final String TABLE_NAME = "habit";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_FREQUENCY = "frequency";
    }

}
