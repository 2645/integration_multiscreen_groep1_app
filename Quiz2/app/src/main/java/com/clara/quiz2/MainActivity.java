package com.clara.quiz2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        DbHelper db = new DbHelper(this);
        questionList = db.getAllQuestions();
        currentQuestion = questionList.get(questionid);
        txtQuestion=(TextView)findViewById(R.id.textView1);
        scoreText = (TextView)findViewById(R.id.textView);
        questionText = (TextView)findViewById(R.id.textView2);
        button=(Button)findViewById(R.id.button);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
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

    private void createQuestionValues()
    {
        txtQuestion.setText(currentQuestion.getQuestion());
        button.setText(currentQuestion.getOption1());
        button2.setText(currentQuestion.getOption2());
        button3.setText(currentQuestion.getOption3());
        scoreText.setText("Score: " + score);
        questionText.setText("Vraag: " + (questionid + 1));
        questionid++;
    }

    private void checkAnswer(Button btn){

        if(currentQuestion.getAnswer().equals(btn.getText()))
        {
            score++;
        }
        if(questionid < 5){
            currentQuestion = questionList.get(questionid);
            createQuestionValues();

        }else{
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            Bundle b = new Bundle();
            b.putInt("score", score);
            intent.putExtras(b);
            startActivity(intent);
            finish();
        }

    }
}
