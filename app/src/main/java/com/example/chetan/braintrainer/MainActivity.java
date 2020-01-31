package com.example.chetan.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView;
    TextView expressionTextView;
    TextView percentTextView;
    androidx.gridlayout.widget.GridLayout gridLayout;
    Button startButton;
    CountDownTimer myCountDownTimer;
    Button playAgain;
    Boolean isRunning;
    Button topLeft;
    Button topRight;
    Button bottomLeft;
    Button bottomRight;
    TextView feedback;
    int numOfQuestions, correctAnswers;
    int correctAnswer;

    public void changeVisibility(){
        timerTextView = findViewById(R.id.timerTextView);
        expressionTextView = findViewById(R.id.expressionTextView);
        percentTextView = findViewById(R.id.percentTextView);
        gridLayout = findViewById(R.id.gridLayout);
        startButton = findViewById(R.id.startGameButton);
        playAgain = findViewById(R.id.playAgainButton);

        playAgain.setVisibility(Button.INVISIBLE);
        timerTextView.setVisibility(TextView.VISIBLE);
        expressionTextView.setVisibility(TextView.VISIBLE);
        percentTextView.setVisibility(TextView.VISIBLE);
        gridLayout.setVisibility(androidx.gridlayout.widget.GridLayout.VISIBLE);
        startButton.setVisibility(Button.INVISIBLE);

    }

    public void restartGame(){
        timerTextView = findViewById(R.id.timerTextView);
        expressionTextView = findViewById(R.id.expressionTextView);
        percentTextView = findViewById(R.id.percentTextView);
        gridLayout = findViewById(R.id.gridLayout);
        startButton = findViewById(R.id.startGameButton);

        timerTextView.setVisibility(TextView.INVISIBLE);
        expressionTextView.setVisibility(TextView.INVISIBLE);
        percentTextView.setVisibility(TextView.INVISIBLE);
        gridLayout.setVisibility(androidx.gridlayout.widget.GridLayout.INVISIBLE);
        startButton.setVisibility(Button.VISIBLE);
    }

    public void startGame(View view){
        //Log.i("Button Pressed", "You pressed the start button");
        changeVisibility();
        generateQuestions();
        isRunning = true;
        numOfQuestions = 0;
        correctAnswers = 0;
        percentTextView.setText(correctAnswers + "/" + numOfQuestions);
        myCountDownTimer = new CountDownTimer(30000, 1000){
            public void onTick(long millisecondsUntilDone){
                //update timer text
                updateTimer((int) millisecondsUntilDone/1000);
            }

            public void onFinish(){
                timerTextView.setText(":00");
                playAgain = findViewById(R.id.playAgainButton);
                playAgain.setVisibility(Button.VISIBLE);
                feedback.setText("Done!");
                isRunning = false;

            }
        }.start();

    }

    //test
    public void generateQuestions(){
        String expression;
        Random r = new Random();
        int x1,x2,solution;
        topLeft = findViewById(R.id.topLeftButton);
        topRight = findViewById(R.id.topRightButton);
        bottomLeft = findViewById(R.id.bottomLeftButton);
        bottomRight = findViewById(R.id.bottomRightButton);
        //duration of timer
        x1 = r.nextInt(21);
        x2 = r.nextInt(21);
        solution = x1 + x2;
        expression = x1 + "+" + x2;
        expressionTextView.setText(expression);
        //which tag will have the right answer
        correctAnswer = r.nextInt(5-1) + 1;
        switch(correctAnswer){
            case 1:
                //topLeft.setTag("Correct");
                topLeft.setText(Integer.toString(solution));
                topRight.setText(Integer.toString(r.nextInt(21)));
                bottomLeft.setText(Integer.toString(r.nextInt(21)));
                bottomRight.setText(Integer.toString(r.nextInt(21)));
                break;
            case 2:
                //topRight.setTag("Correct");
                topRight.setText(Integer.toString(solution));
                topLeft.setText(Integer.toString(r.nextInt(21)));
                bottomLeft.setText(Integer.toString(r.nextInt(21)));
                bottomRight.setText(Integer.toString(r.nextInt(21)));
                break;
            case 3:
                //bottomLeft.setTag("Correct");
                bottomLeft.setText(Integer.toString(solution));
                topRight.setText(Integer.toString(r.nextInt(21)));
                topLeft.setText(Integer.toString(r.nextInt(21)));
                bottomRight.setText(Integer.toString(r.nextInt(21)));
                break;
            case 4:
                //bottomRight.setTag("Correct");
                bottomRight.setText(Integer.toString(solution));
                topRight.setText(Integer.toString(r.nextInt(21)));
                bottomLeft.setText(Integer.toString(r.nextInt(21)));
                topLeft.setText(Integer.toString(r.nextInt(21)));
                break;
            }

    }


    public void pickTheAnswer(View view){
        if(isRunning) {
            percentTextView = findViewById(R.id.percentTextView);
            feedback = findViewById(R.id.rightWrongText);
            if (view.getTag().toString().equals(Integer.toString(correctAnswer))) {
                //update score, Correct
                feedback.setText("Correct!");
                feedback.setVisibility(TextView.VISIBLE);
                numOfQuestions++;
                correctAnswers++;
                percentTextView.setText(correctAnswers + "/" + numOfQuestions);
                generateQuestions();
            } else {
                //update score, Wrong
                feedback.setText("Wrong :(");
                feedback.setVisibility(TextView.VISIBLE);
                numOfQuestions++;
                percentTextView.setText(correctAnswers + "/" + numOfQuestions);
                generateQuestions();
            }
        }
    }

    public void updateTimer(int secondsLeft){
        String secondString = Integer.toString(secondsLeft);
        if(secondsLeft <= 9){
            secondString = "0" + secondString;
        }
        timerTextView.setText(":" + secondString);
    }

    public void playAgain(View view){
        //Log.i("Play Again", "Resetting game...");
        playAgain.setVisibility(Button.INVISIBLE);
        feedback.setVisibility(TextView.INVISIBLE);
        restartGame();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}