package com.example.flowq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustomerDashboardActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private DatabaseReference userRef;
    private TextView tvWelcome;
    private TextView tvQueueStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dashboard);

        auth = FirebaseAuth.getInstance();
        tvWelcome = findViewById(R.id.tvWelcome);
        tvQueueStatus = findViewById(R.id.tvQueueStatus);

        Button btnJoinQueue = findViewById(R.id.btnJoinQueue);
        Button btnRefresh = findViewById(R.id.btnRefresh);
        Button btnLogout = findViewById(R.id.btnLogout);

        FirebaseUser user = auth.getCurrentUser();

        if (user == null) {
            goToLogin();
            return;
        }

        userRef = FirebaseDatabase.getInstance()
                .getReference("Users")
                .child(user.getUid());

        loadProfile();

        btnJoinQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        CustomerDashboardActivity.this,
                        "Queue joining module is ready for the next FlowQ feature setup.",
                        Toast.LENGTH_LONG
                ).show();
            }
        });

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadProfile();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                goToLogin();
            }
        });
    }

    private void loadProfile() {
        if (userRef == null) {
            return;
        }

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("name").getValue(String.class);

                if (name == null || name.trim().isEmpty()) {
                    FirebaseUser user = auth.getCurrentUser();
                    name = user != null && user.getDisplayName() != null
                            ? user.getDisplayName()
                            : "Customer";
                }

                tvWelcome.setText("Welcome, " + name);
                tvQueueStatus.setText("You are not currently in a queue.");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                tvWelcome.setText("Welcome to FlowQ");
                tvQueueStatus.setText("Could not load your profile.");
            }
        });
    }

    private void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
