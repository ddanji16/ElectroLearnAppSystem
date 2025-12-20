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

        btnNext.setOnClickListener(v -> {
            if (selectedAnswer.isEmpty()) {
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
                return;
            }
            checkAnswer();
            currentQuestionIndex++;
            if (currentQuestionIndex < questionsList.size()) {
                loadQuestion();
            } else {
                finishQuiz();
            }
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
        timer = new CountDownTimer(25000, 1000) {
            public void onTick(long millisUntilFinished) {
                tvTimer.setText(millisUntilFinished / 1000 + "s");
            }

            public void onFinish() {
                // Time up logic: Auto-move or mark wrong
                Toast.makeText(QuizActivity.this, "Time's up!", Toast.LENGTH_SHORT).show();
                // Optionally move to next question automatically here
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
}