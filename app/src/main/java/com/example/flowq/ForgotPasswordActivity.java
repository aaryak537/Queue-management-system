package com.example.flowq;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        final EditText email = findViewById(R.id.et_email_address);
        Button send = findViewById(R.id.btnPrimary);
        TextView back = findViewById(R.id.tvSecondary);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = email.getText().toString().trim();
                if (!Patterns.EMAIL_ADDRESS.matcher(e).matches()) {
                    email.setError("Enter a valid email");
                    return;
                }
                Toast.makeText(ForgotPasswordActivity.this, "Recovery request validated.", Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
