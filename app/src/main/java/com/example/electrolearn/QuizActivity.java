package com.example.electrolearn;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.electrolearn.R;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<QuestionModel> questionsList;
    private int currentQuestionIndex = 0;
    private String selectedAnswer = "";
    private int score = 0;

    // UI Components
    private TextView tvQuestion, tvProgress, tvTimer;
    private TextView opt1, opt2, opt3, opt4;
    private ProgressBar progressBar;
    private Button btnNext;

    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Initialize Views
        tvQuestion = findViewById(R.id.tv_question);
        tvProgress = findViewById(R.id.tv_progress_text);
        tvTimer = findViewById(R.id.tv_timer);
        progressBar = findViewById(R.id.progressBar);

        opt1 = findViewById(R.id.tv_option1);
        opt2 = findViewById(R.id.tv_option2);
        opt3 = findViewById(R.id.tv_option3);
        opt4 = findViewById(R.id.tv_option4);
        btnNext = findViewById(R.id.btn_next);

        // Load Questions
        questionsList = com.example.electrolearn.Constants.getQuestions();

        // Set Listeners
        opt1.setOnClickListener(this);
        opt2.setOnClickListener(this);
        opt3.setOnClickListener(this);
        opt4.setOnClickListener(this);

        // inside onCreate...
        btnNext.setOnClickListener(v -> {
            if (selectedAnswer.isEmpty()) {
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
                return;
            }
            checkAnswer();

            // Use the helper method instead of writing the logic twice
            goToNextQuestion();
        });

        loadQuestion();
    }

    private void loadQuestion() {
        // Reset selections
        selectedAnswer = "";
        resetOptionsStyle();

        // Update UI data
        QuestionModel q = questionsList.get(currentQuestionIndex);
        tvQuestion.setText(q.getQuestion());
        opt1.setText(q.getOption1());
        opt2.setText(q.getOption2());
        opt3.setText(q.getOption3());
        opt4.setText(q.getOption4());

        // Update Progress
        int progress = (int) (((float) (currentQuestionIndex + 1) / questionsList.size()) * 100);
        tvProgress.setText((currentQuestionIndex + 1) + "/" + questionsList.size());
        progressBar.setProgress(progress);

        startTimer();
    }

    private void startTimer() {
        if (timer != null) timer.cancel();

        // 25 seconds per question
        timer = new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                tvTimer.setText(millisUntilFinished / 1000 + "s");
            }

            public void onFinish() {
                tvTimer.setText("0s");
                Toast.makeText(QuizActivity.this, "Time's up!", Toast.LENGTH_SHORT).show();

                // 1. Lock buttons so user can't click anymore
                setOptionsClickable(false);

                // 2. Show the correct answer visually
                showCorrectAnswer();

                // 3. Wait 1.5 seconds, then go to next question automatically
                new android.os.Handler().postDelayed(() -> {
                    goToNextQuestion();
                }, 1500);
            }
        }.start();
    }

    @Override
    public void onClick(View v) {
        resetOptionsStyle();
        TextView selected = (TextView) v;
        selectedAnswer = selected.getText().toString();

        // Highlight selected option
        selected.setBackgroundResource(R.drawable.bg_option_selected);
    }

    private void resetOptionsStyle() {
        opt1.setBackgroundResource(R.drawable.bg_option_default);
        opt2.setBackgroundResource(R.drawable.bg_option_default);
        opt3.setBackgroundResource(R.drawable.bg_option_default);
        opt4.setBackgroundResource(R.drawable.bg_option_default);
    }

    private void checkAnswer() {
        QuestionModel q = questionsList.get(currentQuestionIndex);
        if (selectedAnswer.equals(q.getAnswer())) {
            score++;
        }
    }

    private void finishQuiz() {
        if (timer != null) timer.cancel();
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("TOTAL", questionsList.size());
        startActivity(intent);
        finish();
    }

    public void backtbtn(android.view.View view) {
        startActivity(new Intent(QuizActivity.this, MainActivity.class));
    }
    // --- NEW METHODS TO ADD ---

    private void showCorrectAnswer() {
        // Get the correct answer text
        QuestionModel q = questionsList.get(currentQuestionIndex);
        String answer = q.getAnswer();

        // Check which option matches the answer and turn it Green
        // Note: If you don't have a drawable for correct, we use Color.GREEN
        if (opt1.getText().toString().equals(answer)) {
            opt1.setBackgroundColor(Color.GREEN);
        } else if (opt2.getText().toString().equals(answer)) {
            opt2.setBackgroundColor(Color.GREEN);
        } else if (opt3.getText().toString().equals(answer)) {
            opt3.setBackgroundColor(Color.GREEN);
        } else if (opt4.getText().toString().equals(answer)) {
            opt4.setBackgroundColor(Color.GREEN);
        }
    }

    private void setOptionsClickable(boolean clickable) {
        // Helper to lock/unlock buttons
        opt1.setClickable(clickable);
        opt2.setClickable(clickable);
        opt3.setClickable(clickable);
        opt4.setClickable(clickable);
        btnNext.setClickable(clickable);
    }

    private void goToNextQuestion() {
        currentQuestionIndex++;
        if (currentQuestionIndex < questionsList.size()) {
            loadQuestion();
        } else {
            finishQuiz();
        }
        // Re-enable clicks for the new question
        setOptionsClickable(true);
    }
}