package com.example.flowq;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class PhoneAuthActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference usersRef;

    private EditText etOtp;
    private Button btnVerify;
    private TextView tvPhone;
    private TextView tvResend;

    private String verificationId;
    private String phoneNumber;
    private String selectedRole;

    private PhoneAuthProvider.ForceResendingToken resendToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_phone_auth);

        mAuth = FirebaseAuth.getInstance();

        usersRef = FirebaseDatabase
                .getInstance()
                .getReference("Users");

        phoneNumber = getIntent()
                .getStringExtra("phone_number");

        selectedRole = getIntent()
                .getStringExtra("selected_role");

        initializeViews();

        tvPhone.setText(
                "OTP sent to " + phoneNumber
        );

        sendOTP(phoneNumber);
    }

    private void initializeViews() {

        etOtp = findViewById(R.id.etOtp);
        btnVerify = findViewById(R.id.btnVerifyOtp);
        tvPhone = findViewById(R.id.tvPhone);
        tvResend = findViewById(R.id.tvResend);

        btnVerify.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        verifyOTP();
                    }
                }
        );

        tvResend.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        sendOTP(phoneNumber);
                    }
                }
        );
    }

    private void sendOTP(String phone) {

        btnVerify.setEnabled(false);

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phone)
                        .setTimeout(
                                60L,
                                TimeUnit.SECONDS
                        )
                        .setActivity(this)
                        .setCallbacks(
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                                    @Override
                                    public void onVerificationCompleted(
                                            @NonNull PhoneAuthCredential credential) {

                                        signInWithPhoneCredential(
                                                credential
                                        );
                                    }

                                    @Override
                                    public void onVerificationFailed(
                                            @NonNull FirebaseException e) {

                                        btnVerify.setEnabled(true);

                                        Toast.makeText(
                                                PhoneAuthActivity.this,
                                                "OTP failed: "
                                                        + e.getMessage(),
                                                Toast.LENGTH_LONG
                                        ).show();
                                    }

                                    @Override
                                    public void onCodeSent(
                                            @NonNull String id,
                                            @NonNull PhoneAuthProvider.ForceResendingToken token) {

                                        verificationId = id;
                                        resendToken = token;

                                        btnVerify.setEnabled(true);

                                        Toast.makeText(
                                                PhoneAuthActivity.this,
                                                "OTP sent successfully",
                                                Toast.LENGTH_SHORT
                                        ).show();
                                    }
                                }
                        )
                        .build();

        PhoneAuthProvider.verifyPhoneNumber(
                options
        );
    }

    private void verifyOTP() {

        String otp = etOtp
                .getText()
                .toString()
                .trim();

        if (TextUtils.isEmpty(otp)) {

            etOtp.setError(
                    "Enter OTP"
            );

            etOtp.requestFocus();

            return;
        }

        if (otp.length() != 6) {

            etOtp.setError(
                    "Enter 6 digit OTP"
            );

            etOtp.requestFocus();

            return;
        }

        if (verificationId == null) {

            Toast.makeText(
                    this,
                    "Please request OTP again.",
                    Toast.LENGTH_LONG
            ).show();

            return;
        }

        PhoneAuthCredential credential =
                PhoneAuthProvider.getCredential(
                        verificationId,
                        otp
                );

        signInWithPhoneCredential(
                credential
        );
    }

    private void signInWithPhoneCredential(
            PhoneAuthCredential credential) {

        btnVerify.setEnabled(false);
        btnVerify.setText("Verifying...");

        mAuth.signInWithCredential(
                credential
        ).addOnCompleteListener(
                this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            FirebaseUser user =
                                    mAuth.getCurrentUser();

                            if (user != null) {

                                checkUserRole(
                                        user.getUid()
                                );

                            } else {

                                showError(
                                        "User information not found."
                                );
                            }

                        } else {

                            String message =
                                    "Invalid OTP.";

                            if (task.getException() != null) {

                                message =
                                        task.getException()
                                                .getMessage();
                            }

                            showError(message);
                        }
                    }
                }
        );
    }

    private void checkUserRole(String uid) {

        usersRef.child(uid)
                .addListenerForSingleValueEvent(
                        new ValueEventListener() {

                            @Override
                            public void onDataChange(
                                    @NonNull DataSnapshot snapshot) {

                                if (!snapshot.exists()) {

                                    mAuth.signOut();

                                    showError(
                                            "No FlowQ account found for this number."
                                    );

                                    return;
                                }

                                String databaseRole =
                                        snapshot.child("role")
                                                .getValue(String.class);

                                if (databaseRole == null) {

                                    mAuth.signOut();

                                    showError(
                                            "User role not found."
                                    );

                                    return;
                                }

                                if (databaseRole.equalsIgnoreCase(
                                        selectedRole)) {

                                    openDashboard(
                                            databaseRole
                                    );

                                } else {

                                    mAuth.signOut();

                                    showError(
                                            "This phone number is not registered as "
                                                    + selectedRole
                                    );
                                }
                            }

                            @Override
                            public void onCancelled(
                                    @NonNull DatabaseError error) {

                                mAuth.signOut();

                                showError(
                                        "Database error: "
                                                + error.getMessage()
                                );
                            }
                        }
                );
    }

    private void openDashboard(String role) {

        Toast.makeText(
                this,
                "Login successful",
                Toast.LENGTH_SHORT
        ).show();

        Intent intent;

        if (role.equalsIgnoreCase("Admin")) {

            intent = new Intent(
                    PhoneAuthActivity.this,
                    AdminDashboardActivity.class
            );

        } else {

            intent = new Intent(
                    PhoneAuthActivity.this,
                    CustomerDashboardActivity.class
            );
        }

        startActivity(intent);

        finish();
    }

    private void showError(String message) {

        btnVerify.setEnabled(true);
        btnVerify.setText("Verify OTP");

        Toast.makeText(
                PhoneAuthActivity.this,
                message,
                Toast.LENGTH_LONG
        ).show();
    }
}