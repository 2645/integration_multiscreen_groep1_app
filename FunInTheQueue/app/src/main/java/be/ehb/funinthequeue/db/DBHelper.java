package be.ehb.funinthequeue.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import be.ehb.funinthequeue.model.Question;

/**
 * Created by Clara on 16/12/2015.
 */

public class DBHelper extends SQLiteOpenHelper {
    private SQLiteDatabase dbase;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "triviaQuiz";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS" + DBContract.QuizEntry.TABLE_NAME + "("
                    + DBContract.QuizEntry._ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT," + COMMA_SEP
                    + DBContract.QuizEntry.COLUMN_QUESTION + TEXT_TYPE + COMMA_SEP
                    + DBContract.QuizEntry.COLUMN_ANSWER + TEXT_TYPE + COMMA_SEP
                    + DBContract.QuizEntry.COLUMN_OPTION1 + TEXT_TYPE + COMMA_SEP
                    + DBContract.QuizEntry.COLUMN_OPTION2 + TEXT_TYPE + COMMA_SEP
                    + DBContract.QuizEntry.COLUMN_OPTION3 + TEXT_TYPE
                    + ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DBContract.QuizEntry.TABLE_NAME;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;
        db.execSQL(SQL_CREATE_ENTRIES);
        addQuestions();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    private void addQuestions() {
        this.addQuestion(new Question("Wie is de uitvinder van Coca-Cola?","John Pemberton", "Bill Preston", "Theodore Logan", "John Pemberton"));
        this.addQuestion(new Question("Wie bedacht de naam Coca-Cola?", "John Pemberton", "Jane Darrs", "Frank Robinson", "Frank Robinson"));
        this.addQuestion(new Question("In welk jaar werd Coca-Cola uitgevonden?","1874", "1886","1890","1886"));
        this.addQuestion(new Question("Welke kleur had Coca-Cola oorspronkelijk?", "groen", "rood", "bruin", "groen"));
        this.addQuestion(new Question("Waar werd de eerste colafabriek geopend?", "Mississippi", "Atlanta", "Oklahoma", "Mississippi"));
    }

    public void addQuestion(Question quest) {
        ContentValues values = new ContentValues();
        values.put(DBContract.QuizEntry.COLUMN_QUESTION, quest.getQuestion());
        values.put(DBContract.QuizEntry.COLUMN_ANSWER, quest.getAnswer());
        values.put(DBContract.QuizEntry.COLUMN_OPTION1, quest.getOptionA());
        values.put(DBContract.QuizEntry.COLUMN_OPTION2, quest.getOptionB());
        values.put(DBContract.QuizEntry.COLUMN_OPTION3, quest.getOptionC());
        dbase.insert(DBContract.QuizEntry.TABLE_NAME, null, values);
    }

    public List<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + DBContract.QuizEntry.TABLE_NAME;
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                questionList.add(new Question(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)));

            } while (cursor.moveToNext());
        }

        return questionList;
    }
}
