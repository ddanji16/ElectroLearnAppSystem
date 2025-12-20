package com.example.electrolearn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.electrolearn.R;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView tvScore = findViewById(R.id.tv_score_fraction);
        Button btnRestart = findViewById(R.id.btn_restart);

        // Get data from intent
        int score = getIntent().getIntExtra("SCORE", 0);
        int total = getIntent().getIntExtra("TOTAL", 0);

        tvScore.setText(score + "/" + total);

        btnRestart.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, com.example.electrolearn.QuizActivity.class);
            startActivity(intent);
            finish();
        });
    }
    public void backtbtn1(android.view.View view) {
        startActivity(new Intent(ResultActivity.this, MainActivity.class));
    }
}