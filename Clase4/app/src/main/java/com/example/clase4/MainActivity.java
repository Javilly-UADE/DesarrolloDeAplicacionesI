package com.example.clase4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText edtName;
    private Button btnContinue;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edtName);
        btnContinue = findViewById(R.id.btnContinue);

        apiService = ApiClient.getClient().create(ApiService.class);

        btnContinue.setOnClickListener(v -> {
            String userName = edtName.getText().toString().trim();

            if (userName.isEmpty()) {
                edtName.setError("Name is required");
                return;
            }

            StartRequest request = new StartRequest(userName);

            Call<StartResponse> call = apiService.startSession(request);

            call.enqueue(new Callback<StartResponse>() {
                @Override
                public void onResponse(Call<StartResponse> call, Response<StartResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        StartResponse startResponse = response.body();

                        Intent intent = new Intent(MainActivity.this, ChoiceActivity.class);
                        intent.putExtra("USER_NAME", startResponse.getUserName());
                        intent.putExtra("WELCOME_MESSAGE", startResponse.getMessage());
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<StartResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}