package com.clara.quiz2;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {
//Gebaseerd op: https://www.developerfeed.com/simple-quiz-game-andriod/

    int score = 0;
    int questionid = 0;
    int numberOfQuestions = 5;
    Question currentQuestion = new Question("", "", "", "", "");
    TextView txtQuestion;
    TextView scoreText;
    TextView questionText;
    Button button;
    Button button2;
    Button button3;
    Handler mHandler = new Handler();
    String url = "http://10.3.50.220:8080/question/list";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        txtQuestion = (TextView) findViewById(R.id.textView1);
        scoreText = (TextView) findViewById(R.id.textView);
        questionText = (TextView) findViewById(R.id.textView2);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        checkAnswer(button);
        //createQuestionValues();
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
        questionText.setText("Vraag: " + (questionid + 1) + "/" + numberOfQuestions);
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

        if (questionid < numberOfQuestions) {
            getData();
            mHandler.postDelayed(mLaunchTask, 400);


        } else {
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            Bundle b = new Bundle();
            b.putInt("score", score);
            intent.putExtras(b);
            startActivity(intent);
            finish();
        }


    }
    private void getData() {
        JsonArrayRequest jsonRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        // the response is already constructed as a JSONObject!
                        try {

                            JSONObject question = response.getJSONObject(questionid);
                            currentQuestion.setQuestion(question.getString("question"));
                            currentQuestion.setOption1(question.getString("optionA"));
                            currentQuestion.setOption2(question.getString("optionB"));
                            currentQuestion.setOption3(question.getString("optionC"));
                            currentQuestion.setAnswer(question.getString("answer"));
                            System.out.println(currentQuestion.getQuestion());


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        Volley.newRequestQueue(this).add(jsonRequest);

    }

    private Runnable mLaunchTask = new Runnable() {
        public void run() {
            createQuestionValues();
        }
    };
}
