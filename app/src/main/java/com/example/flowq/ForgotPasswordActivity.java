package com.example.flowq;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mAuth = FirebaseAuth.getInstance();

        final EditText email = findViewById(R.id.et_email_address);
        sendButton = findViewById(R.id.btnPrimary);
        TextView back = findViewById(R.id.tvSecondary);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailAddress = email.getText().toString().trim();

                if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
                    email.setError("Enter a valid email");
                    email.requestFocus();
                    return;
                }

                sendButton.setEnabled(false);
                sendButton.setText("Sending...");

                mAuth.sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener(
                                ForgotPasswordActivity.this,
                                new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        sendButton.setEnabled(true);
                                        sendButton.setText("Send Reset Link");

                                        if (task.isSuccessful()) {
                                            Toast.makeText(
                                                    ForgotPasswordActivity.this,
                                                    "Password reset link sent. Check your email.",
                                                    Toast.LENGTH_LONG
                                            ).show();
                                        } else {
                                            String message = "Could not send reset email.";
                                            if (task.getException() != null
                                                    && task.getException().getMessage() != null) {
                                                message = task.getException().getMessage();
                                            }

                                            Toast.makeText(
                                                    ForgotPasswordActivity.this,
                                                    message,
                                                    Toast.LENGTH_LONG
                                            ).show();
                                        }
                                    }
                                }
                        );
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
