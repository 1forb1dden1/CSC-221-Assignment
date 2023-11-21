package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsDisplay;
    TextView questionTextDisplay;
    Button answerAButton, answerBButton, answerCButton, answerDButton;
    Button submitButton;

    int score = 0;
    int totalQuestions = Solutions.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";
    int solved = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsDisplay = findViewById(R.id.total_question);
        questionTextDisplay = findViewById(R.id.question);
        answerAButton = findViewById(R.id.Answer_A);
        answerBButton = findViewById(R.id.Answer_B);
        answerCButton = findViewById(R.id.Answer_C);
        answerDButton = findViewById(R.id.Answer_D);
        submitButton = findViewById(R.id.Submit);

        answerAButton.setOnClickListener(this);
        answerBButton.setOnClickListener(this);
        answerCButton.setOnClickListener(this);
        answerDButton.setOnClickListener(this);
        submitButton.setOnClickListener(this);

        totalQuestionsDisplay.setText("Questions Left: " + (Integer.parseInt(String.valueOf(totalQuestions)) - solved));

        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {
        answerAButton.setBackgroundColor(Color.parseColor("#ffffff"));
        answerBButton.setBackgroundColor(Color.parseColor("#ffffff"));
        answerCButton.setBackgroundColor(Color.parseColor("#ffffff"));
        answerDButton.setBackgroundColor(Color.parseColor("#ffffff"));

        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.Submit) {
            if (selectedAnswer.equals(Solutions.correct[currentQuestionIndex])) {
                score++;
                final AlertDialog dialog = new AlertDialog.Builder(this)
                        .setMessage("Your Answer was Correct.\nThe correct answer was: " + Solutions.correct[currentQuestionIndex])
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss(); // Dismiss the dialog when the user presses OK
                                currentQuestionIndex++;
                                loadNewQuestion();
                                updateSolved();
                            }
                        })
                        .create();
                dialog.show();
            } else {
                final AlertDialog dialog = new AlertDialog.Builder(this)
                        .setMessage("Your Answer was Incorrect.\nThe correct answer was: " + Solutions.correct[currentQuestionIndex])
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss(); // Dismiss the dialog when the user presses OK
                                currentQuestionIndex++;
                                loadNewQuestion();
                                updateSolved();
                            }
                        })
                        .create();
                dialog.show();
            }
        } else {
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.parseColor("#38b3d9"));
        }
    }

    void loadNewQuestion() {
        if (currentQuestionIndex == totalQuestions) {
            finishQuiz();
            return;
        }
        questionTextDisplay.setText(Solutions.question[currentQuestionIndex]);
        answerAButton.setText(Solutions.options[currentQuestionIndex][0]);
        answerBButton.setText(Solutions.options[currentQuestionIndex][1]);
        answerCButton.setText(Solutions.options[currentQuestionIndex][2]);
        answerDButton.setText(Solutions.options[currentQuestionIndex][3]);
    }
    void updateSolved(){
        solved++;
        totalQuestionsDisplay.setText("Questions Left: " + (Integer.parseInt(String.valueOf(totalQuestions)) - solved));
    }
    void finishQuiz() {
        new AlertDialog.Builder(this)
                .setMessage("Your Score is: " + score + "\n" + "Out of: " + totalQuestions + " questions")
                .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        restartQuiz();
                    }
                })
                .setCancelable(false)
                .show();
    }

    void restartQuiz() {
        //Initialize everything to default
        score = 0;
        currentQuestionIndex = 0;
        selectedAnswer = "";
        totalQuestionsDisplay.setText("Questions left: " + (totalQuestions));
        loadNewQuestion();
    }
}
