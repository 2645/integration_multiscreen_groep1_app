package be.ehb.funinthequeue.game.quiz;

import android.os.Bundle;
import android.widget.TextView;

import be.ehb.funinthequeue.MainActivity;
import be.ehb.funinthequeue.R;

public class QuizResultActivity extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_quiz_result);

        TextView t=(TextView)findViewById(R.id.textResult);
        Bundle b = getIntent().getExtras();
        int score= b.getInt("score");
    t.setText("Je score is: " + score);
    }

}
