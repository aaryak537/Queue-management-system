package com.example.flowq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminDashboardActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private DatabaseReference usersRef;
    private TextView tvAdminName;
    private TextView tvCustomerCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        auth = FirebaseAuth.getInstance();
        usersRef = FirebaseDatabase.getInstance().getReference("Users");

        tvAdminName = findViewById(R.id.tvAdminName);
        tvCustomerCount = findViewById(R.id.tvCustomerCount);

        Button btnRefresh = findViewById(R.id.btnRefresh);
        Button btnLogout = findViewById(R.id.btnLogout);

        FirebaseUser user = auth.getCurrentUser();
        if (user == null) {
            goToLogin();
            return;
        }

        loadAdminProfile();
        loadCustomerCount();

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAdminProfile();
                loadCustomerCount();
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

    private void loadAdminProfile() {
        FirebaseUser user = auth.getCurrentUser();
        if (user == null) {
            return;
        }

        usersRef.child(user.getUid()).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String name = snapshot.child("name").getValue(String.class);
                        tvAdminName.setText(
                                name == null || name.trim().isEmpty()
                                        ? "Welcome, Admin"
                                        : "Welcome, " + name
                        );
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        tvAdminName.setText("Welcome, Admin");
                    }
                }
        );
    }

    private void loadCustomerCount() {
        usersRef.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int count = 0;

                        for (DataSnapshot child : snapshot.getChildren()) {
                            String role = child.child("role").getValue(String.class);
                            if ("Customer".equalsIgnoreCase(role)
                                    || "User".equalsIgnoreCase(role)) {
                                count++;
                            }
                        }

                        tvCustomerCount.setText("Registered customers: " + count);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        tvCustomerCount.setText("Registered customers: unavailable");
                    }
                }
        );
    }

    private void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
