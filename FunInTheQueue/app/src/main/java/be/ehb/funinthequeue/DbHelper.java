package be.ehb.funinthequeue;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clara on 16/12/2015.
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "triviaQuiz";
        private static final String TABLE_NAME = "name";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_QUESTION = "question";
    private static final String COLUMN_ANSWER = "answer";
    private static final String COLUMN_OPTION1 = "option1";
    private static final String COLUMN_OPTION2 = "option2";
    private static final String COLUMN_OPTION3 = "option3";
    private SQLiteDatabase dbase;
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_QUESTION
                + " TEXT, " + COLUMN_ANSWER+ " TEXT, "+COLUMN_OPTION1 +" TEXT, "
                +COLUMN_OPTION2 +" TEXT, "+COLUMN_OPTION3+" TEXT)";
        db.execSQL(sql);
        addQuestions();
    }
    private void addQuestions()
    {
        Question q1=new Question("Wie is de uitvinder van Coca-Cola?","John Pemberton", "Bill Preston", "Theodore Logan", "John Pemberton");
        this.addQuestion(q1);
        Question q2=new Question("Wie bedacht de naam Coca-Cola?", "John Pemberton", "Jane Darrs", "Frank Robinson", "Frank Robinson");
        this.addQuestion(q2);
        Question q3=new Question("In welk jaar werd Coca-Cola uitgevonden?","1874", "1886","1890","1886");
        this.addQuestion(q3);
        Question q4=new Question("Welke kleur had Coca-Cola oorspronkelijk?", "groen", "rood", "bruin","groen");
        this.addQuestion(q4);
        Question q5=new Question("Waar werd de eerste colafabriek geopend?","Mississippi","Atlanta","Oklahoma","Mississippi");
        this.addQuestion(q5);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addQuestion(Question quest) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION, quest.getQuestion());
        values.put(COLUMN_ANSWER, quest.getAnswer());
        values.put(COLUMN_OPTION1, quest.getOption1());
        values.put(COLUMN_OPTION2, quest.getOption2());
        values.put(COLUMN_OPTION3, quest.getOption3());

        dbase.insert(TABLE_NAME, null, values);
    }
    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<Question>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setID(cursor.getInt(0));
                question.setQuestion(cursor.getString(1));
                question.setAnswer(cursor.getString(2));
                question.setOption1(cursor.getString(3));
                question.setOption2(cursor.getString(4));
                question.setOption3(cursor.getString(5));
                questionList.add(question);
            } while (cursor.moveToNext());
        }
        return questionList;
    }

}
