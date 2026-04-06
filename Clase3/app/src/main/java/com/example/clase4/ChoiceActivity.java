package com.example.clase4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChoiceActivity extends AppCompatActivity {

    private TextView txtGreeting;
    private Button btnOptionA;
    private Button btnOptionB;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        txtGreeting = findViewById(R.id.txtGreeting);
        btnOptionA = findViewById(R.id.btnOptionA);
        btnOptionB = findViewById(R.id.btnOptionB);

        userName = getIntent().getStringExtra("USER_NAME");
        txtGreeting.setText("Hello, " + userName);

        btnOptionA.setOnClickListener(v -> {
            Intent intent = new Intent(ChoiceActivity.this, ResultActivity.class);
            intent.putExtra("USER_NAME", userName);
            intent.putExtra("RESULT_MESSAGE", "You selected option A");
            startActivity(intent);
        });

        btnOptionB.setOnClickListener(v -> {
            Intent intent = new Intent(ChoiceActivity.this, ResultActivity.class);
            intent.putExtra("USER_NAME", userName);
            intent.putExtra("RESULT_MESSAGE", "You selected option B");
            startActivity(intent);
        });
    }
}