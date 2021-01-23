package com.example.numgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int score = 0;
    Button goButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    TextView scoreTextView;
    TextView resultTextView;
    TextView timerTextView;
    TextView questionTextView;
    int locationOfCorrectAnswer;
    int totalNumberOfQuestion = 0;
    ConstraintLayout gameLayout;
    ArrayList<Integer> answer = new ArrayList<Integer>();

    public void playAgain(View view){
        score = 0;
        totalNumberOfQuestion = 0;
        timerTextView.setText("30s");
        resultTextView.setText("Choose Correct One!");
        scoreTextView.setText(Integer.toString(score)+" /"+Integer.toString(totalNumberOfQuestion));
        newQuestion();
        Enable();
        playAgainButton.setVisibility(View.INVISIBLE);
        new CountDownTimer(30000, 1000){

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Time Up!");
                playAgainButton.setVisibility(View.VISIBLE);
                Disable();
            }
        }.start();

    }

    public void Disable(){
        questionTextView.setVisibility(View.INVISIBLE);
        button0.setVisibility(View.INVISIBLE);
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
    }
    public void Enable(){
        questionTextView.setVisibility(View.VISIBLE);
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
    }

    public void newQuestion(){
        Random rand = new Random();
        int a = rand.nextInt(50);
        int b = rand.nextInt(50);
        questionTextView.setText(Integer.toString(a) + getString(R.string.concatenate) +Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answer.clear();

        for(int i=0; i<4; i++){
            if(i == locationOfCorrectAnswer){
                answer.add(a+b);
            }else{
                int wrongAnswer = rand.nextInt(50);
                while(wrongAnswer == a+b){
                    wrongAnswer = rand.nextInt(50);
                }
                answer.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgainButton = findViewById(R.id.playAgainButton);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        gameLayout = findViewById(R.id.gameLayout);
        goButton = findViewById(R.id.goButton);
        questionTextView = findViewById(R.id.questionTextView);
        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);
    }

    public void onClickStart(View view) {
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));
    }

    public void chooseAnswer(View view) {
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            resultTextView.setText(R.string.correctAnswer);
            score++;
        }else{
            resultTextView.setText(R.string.wrongAnswer);
        }
        totalNumberOfQuestion++;
        scoreTextView.setText(Integer.toString(score)+" /"+Integer.toString(totalNumberOfQuestion));
        newQuestion();
    }
}