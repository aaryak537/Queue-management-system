package com.example.flowq;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResetPasswordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        final EditText newPassword = findViewById(R.id.et_new_password);
        final EditText confirm = findViewById(R.id.et_confirm_password);
        Button reset = findViewById(R.id.btnPrimary);
        TextView back = findViewById(R.id.tvSecondary);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p1 = newPassword.getText().toString();
                String p2 = confirm.getText().toString();

                if (p1.length() < 6) {
                    newPassword.setError("Use at least 6 characters");
                    return;
                }
                if (!p1.equals(p2)) {
                    confirm.setError("Passwords do not match");
                    return;
                }
                Toast.makeText(ResetPasswordActivity.this, "Password validated. Connect Firebase reset flow next.", Toast.LENGTH_SHORT).show();
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
