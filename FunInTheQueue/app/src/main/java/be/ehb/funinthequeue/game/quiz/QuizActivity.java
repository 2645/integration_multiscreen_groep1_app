package be.ehb.funinthequeue.game.quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import be.ehb.funinthequeue.R;
import be.ehb.funinthequeue.model.Question;
import be.ehb.funinthequeue.rest.RestAPI;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class QuizActivity extends AppCompatActivity {
    //Gebaseerd op: https://www.developerfeed.com/simple-quiz-game-andriod/
    RestAPI api = new RestAPI();

    private int score = 0;
    private int questionid = 0;
    private ArrayList<Question> questions;
    private Question currentQuestion = new Question("", "", "", "", "");
    private TextView txtQuestion;
    private TextView scoreText;
    private TextView questionText;
    private Button button;
    private Button button2;
    private Button button3;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/GOTHAM-BOOK.OTF")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );

        txtQuestion = (TextView) findViewById(R.id.textView1);
        scoreText = (TextView) findViewById(R.id.textView);
        questionText = (TextView) findViewById(R.id.textView2);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        new Thread(new Runnable() {
            @Override
            public void run() {
                questions = api.questions_list(0);
                currentQuestion = questions.get(0);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        createQuestionValues();
                    }
                });
            }
        }).start();

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
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void createQuestionValues() {
        txtQuestion.setText(currentQuestion.getQuestion());
        button.setText(currentQuestion.getOptionA());
        button.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundedbutton));
        button2.setText(currentQuestion.getOptionB());
        button2.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundedbutton));
        button3.setText(currentQuestion.getOptionC());
        button3.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundedbutton));
        scoreText.setText("Score: " + score);
        questionText.setText("Vraag: " + (questionid + 1) + "/" + questions.size());
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

        if (questionid < questions.size() - 1) {
            questionid++;
            getData();
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

    private void getData() {
        if (questionid < questions.size()) {
            currentQuestion = questions.get(questionid);
        }
    }

    private Runnable mLaunchTask = new Runnable() {
        public void run() {
            createQuestionValues();
        }
    };
}

