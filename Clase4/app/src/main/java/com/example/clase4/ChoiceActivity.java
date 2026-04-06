package com.example.clase4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChoiceActivity extends AppCompatActivity {

    private TextView txtGreeting;
    private TextView txtWelcomeMessage;
    private Button btnOptionA;
    private Button btnOptionB;
    private String userName;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        txtGreeting = findViewById(R.id.txtGreeting);
        txtWelcomeMessage = findViewById(R.id.txtWelcomeMessage);
        btnOptionA = findViewById(R.id.btnOptionA);
        btnOptionB = findViewById(R.id.btnOptionB);

        apiService = ApiClient.getClient().create(ApiService.class);

        userName = getIntent().getStringExtra("USER_NAME");
        String welcomeMessage = getIntent().getStringExtra("WELCOME_MESSAGE");

        txtGreeting.setText("Hello, " + userName);
        txtWelcomeMessage.setText(welcomeMessage);

        btnOptionA.setOnClickListener(v -> sendChoiceToApi("A"));
        btnOptionB.setOnClickListener(v -> sendChoiceToApi("B"));
    }

    private void sendChoiceToApi(String choice) {
        ChoiceRequest request = new ChoiceRequest(userName, choice);

        Call<ChoiceResponse> call = apiService.sendChoice(request);

        call.enqueue(new Callback<ChoiceResponse>() {
            @Override
            public void onResponse(Call<ChoiceResponse> call, Response<ChoiceResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ChoiceResponse choiceResponse = response.body();

                    Intent intent = new Intent(ChoiceActivity.this, ResultActivity.class);
                    intent.putExtra("USER_NAME", userName);
                    intent.putExtra("RESULT_MESSAGE", choiceResponse.getResultMessage());
                    startActivity(intent);
                } else {
                    Toast.makeText(ChoiceActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ChoiceResponse> call, Throwable t) {
                Toast.makeText(ChoiceActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}