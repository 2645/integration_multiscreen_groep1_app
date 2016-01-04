package be.ehb.funinthequeue.game.quiz;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import be.ehb.funinthequeue.MainActivity;
import be.ehb.funinthequeue.R;
import be.ehb.funinthequeue.db.DBHelper;

public class QuizActivity extends MainActivity {

    List<Question> questionList;
    int score = 0;
    int questionid = 0;
    Question currentQuestion;
    TextView txtQuestion;
    TextView scoreText;
    TextView questionText;
    Button button;
    Button button2;
    Button button3;
    Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quiz);
        DBHelper db = new DBHelper(this);
        questionList = db.getAllQuestions();
        currentQuestion = questionList.get(questionid);
        txtQuestion = (TextView) findViewById(R.id.textView1);
        scoreText = (TextView) findViewById(R.id.textView);
        questionText = (TextView) findViewById(R.id.textView2);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        createQuestionValues();
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                checkAnswer(button);


            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(button2);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(button3);
            }
        });
    }

    private void createQuestionValues() {
        txtQuestion.setText(currentQuestion.getQuestion());
        button.setText(currentQuestion.getOption1());
        button.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundedbutton));
        button2.setText(currentQuestion.getOption2());
        button2.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundedbutton));
        button3.setText(currentQuestion.getOption3());
        button3.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundedbutton));
        scoreText.setText("Score: " + score);
        questionText.setText("Vraag: " + (questionid + 1));
        questionid++;
    }

    private void checkAnswer(Button btn) {

        if (currentQuestion.getAnswer().equals(btn.getText())) {
            score++;
            btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundedbutton2));
        } else if (currentQuestion.getAnswer().equals(button.getText())) {
            button.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundedbutton2));
        } else if (currentQuestion.getAnswer().equals(button2.getText())) {
            button2.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundedbutton2));
        } else if (currentQuestion.getAnswer().equals(button3.getText())) {
            button3.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundedbutton2));
        }

        if (questionid < 5) {
            currentQuestion = questionList.get(questionid);
            mHandler.postDelayed(mLaunchTask, 400);


        } else {
            Intent intent = new Intent(QuizActivity.this, QuizResultActivity.class);
            Bundle b = new Bundle();
            b.putInt("score", score);
            intent.putExtras(b);
            startActivity(intent);
            finish();
        }

    }


    private Runnable mLaunchTask = new Runnable() {
        public void run() {
            createQuestionValues();
        }
    };
}
