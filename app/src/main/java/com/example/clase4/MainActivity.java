package com.example.clase4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edtName;
    private Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edtName);
        btnContinue = findViewById(R.id.btnContinue);

        btnContinue.setOnClickListener(v -> {
            String userName = edtName.getText().toString().trim();

            if (userName.isEmpty()) {
                edtName.setError("Name is required");
            } else {
                Intent intent = new Intent(MainActivity.this, ChoiceActivity.class);
                intent.putExtra("USER_NAME", userName);
                startActivity(intent);
            }
        });
    }
}