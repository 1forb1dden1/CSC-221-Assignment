package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView totalQuestionsDisplay;
    private TextView questionTextDisplay;
    private Button[] answerButtons;
    private Button submitButton;

    private int score = 0;
    private int totalQuestions;
    private int currentQuestionIndex = 0;
    private String selectedAnswer = "";
    private int solved = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsDisplay = findViewById(R.id.total_question);
        questionTextDisplay = findViewById(R.id.question);

        answerButtons = new Button[]{
                findViewById(R.id.Answer_A),
                findViewById(R.id.Answer_B),
                findViewById(R.id.Answer_C),
                findViewById(R.id.Answer_D)
        };

        submitButton = findViewById(R.id.Submit);

        for (Button button : answerButtons) {
            button.setOnClickListener(this);
        }

        submitButton.setOnClickListener(this);

        totalQuestions = Solutions.question.size();
        totalQuestionsDisplay.setText("Questions Left: " + (totalQuestions - solved));

        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {
        resetButtonColors();
        Button clickedButton = (Button) view;

        if (clickedButton.getId() == R.id.Submit) {
            handleSubmission();
        } else {
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.parseColor("#38b3d9"));
        }
    }

    private void resetButtonColors() {
        for (Button button : answerButtons) {
            button.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }

    private void handleSubmission() {
        if (selectedAnswer.equals(Solutions.correct.get(currentQuestionIndex))) {
            score++;
        }

        showResultDialog("Your Answer was " +
                (selectedAnswer.equals(Solutions.correct.get(currentQuestionIndex)) ? "Correct." : "Incorrect.") +
                "\nThe correct answer was: " + Solutions.correct.get(currentQuestionIndex));

        currentQuestionIndex++;
        updateSolved();
    }

    private void showResultDialog(String message) {
        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        loadNewQuestion();
                    }
                })
                .create();
        dialog.show();
    }

    private void loadNewQuestion() {
        if (currentQuestionIndex == totalQuestions) {
            finishQuiz();
            return;
        }

        questionTextDisplay.setText(Solutions.question.get(currentQuestionIndex));

        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i].setText(Solutions.options.get(currentQuestionIndex).get(i));
        }
    }

    private void updateSolved() {
        solved++;
        totalQuestionsDisplay.setText("Questions Left: " + (totalQuestions - solved));
    }

    private void finishQuiz() {
        new AlertDialog.Builder(this)
                .setMessage("Your Score is: " + score + "/" + totalQuestions)
                .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        restartQuiz();
                    }
                })
                .setCancelable(false)
                .show();
    }

    private void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        selectedAnswer = "";
        solved = 0;
        totalQuestionsDisplay.setText("Questions left: " + totalQuestions);
        loadNewQuestion();
    }
}
