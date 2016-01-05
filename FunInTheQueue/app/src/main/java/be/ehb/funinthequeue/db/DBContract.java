package be.ehb.funinthequeue.db;

import android.provider.BaseColumns;

/**
 * Created by Dieter on 4/01/2016.
 */
public class DBContract {
    public static abstract class QuizEntry implements BaseColumns {
        public static final String TABLE_NAME = "name";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_ANSWER = "answer";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
    }
}
