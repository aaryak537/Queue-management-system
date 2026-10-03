package com.example.flowq;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference usersRef;

    private RadioGroup rgRole;

    private RadioButton rbUser;
    private RadioButton rbAdmin;

    private EditText etFullName;
    private EditText etEmailAddress;
    private EditText etMobileNumber;
    private EditText etPassword;
    private EditText etConfirmPassword;

    private Button btnPrimary;
    private TextView tvSecondary;

    private String selectedRole = "Customer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        /*
         * Firebase Authentication
         */
        mAuth = FirebaseAuth.getInstance();

        /*
         * Firebase Realtime Database
         */
        usersRef = FirebaseDatabase.getInstance()
                .getReference("Users");

        initializeViews();
        setupListeners();
    }

    private void initializeViews() {

        rgRole = findViewById(R.id.rgRole);

        rbUser = findViewById(R.id.rbUser);
        rbAdmin = findViewById(R.id.rbAdmin);

        etFullName = findViewById(R.id.et_full_name);
        etEmailAddress = findViewById(R.id.et_email_address);
        etMobileNumber = findViewById(R.id.et_mobile_number);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);

        btnPrimary = findViewById(R.id.btnPrimary);
        tvSecondary = findViewById(R.id.tvSecondary);
    }

    private void setupListeners() {

        /*
         * Role Selection
         */
        rgRole.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(
                            RadioGroup group,
                            int checkedId) {

                        if (checkedId == R.id.rbAdmin) {

                            selectedRole = "Admin";

                            btnPrimary.setText(
                                    "Create Admin Account  →"
                            );

                            updateRoleAppearance(true);

                        } else {

                            selectedRole = "Customer";

                            btnPrimary.setText(
                                    "Create Account  →"
                            );

                            updateRoleAppearance(false);
                        }
                    }
                }
        );

        /*
         * Register Button
         */
        btnPrimary.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        registerUser();
                    }
                }
        );

        /*
         * Login
         */
        tvSecondary.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(
                                RegisterActivity.this,
                                LoginActivity.class
                        );

                        startActivity(intent);
                        finish();
                    }
                }
        );
    }

    private void updateRoleAppearance(
            boolean adminSelected) {

        if (adminSelected) {

            rbAdmin.setTextColor(
                    getResources().getColor(
                            R.color.white
                    )
            );

            rbAdmin.setBackgroundResource(
                    R.drawable.bg_role_selected
            );

            rbUser.setTextColor(
                    getResources().getColor(
                            R.color.grey_text
                    )
            );

            rbUser.setBackgroundResource(
                    R.drawable.bg_role_unselected
            );

        } else {

            rbUser.setTextColor(
                    getResources().getColor(
                            R.color.white
                    )
            );

            rbUser.setBackgroundResource(
                    R.drawable.bg_role_selected
            );

            rbAdmin.setTextColor(
                    getResources().getColor(
                            R.color.gray_text
                    )
            );

            rbAdmin.setBackgroundResource(
                    R.drawable.bg_role_unselected
            );
        }
    }

    private void registerUser() {

        String fullName =
                etFullName.getText()
                        .toString()
                        .trim();

        String email =
                etEmailAddress.getText()
                        .toString()
                        .trim();

        String mobile =
                etMobileNumber.getText()
                        .toString()
                        .trim();

        String password =
                etPassword.getText()
                        .toString();

        String confirmPassword =
                etConfirmPassword.getText()
                        .toString();

        /*
         * Full Name
         */
        if (TextUtils.isEmpty(fullName)) {

            etFullName.setError(
                    "Enter your full name"
            );

            etFullName.requestFocus();
            return;
        }

        /*
         * Email
         */
        if (TextUtils.isEmpty(email)) {

            etEmailAddress.setError(
                    "Enter email address"
            );

            etEmailAddress.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS
                .matcher(email)
                .matches()) {

            etEmailAddress.setError(
                    "Enter a valid email address"
            );

            etEmailAddress.requestFocus();
            return;
        }

        /*
         * Mobile
         */
        if (TextUtils.isEmpty(mobile)) {

            etMobileNumber.setError(
                    "Enter mobile number"
            );

            etMobileNumber.requestFocus();
            return;
        }

        mobile = mobile.replace(
                " ",
                ""
        );

        if (!mobile.startsWith("+")) {

            if (mobile.startsWith("0")) {

                mobile = "+91"
                        + mobile.substring(1);

            } else {

                mobile = "+91"
                        + mobile;
            }
        }

        if (mobile.length() < 12) {

            etMobileNumber.setError(
                    "Enter a valid mobile number"
            );

            etMobileNumber.requestFocus();
            return;
        }

        /*
         * Password
         */
        if (TextUtils.isEmpty(password)) {

            etPassword.setError(
                    "Enter password"
            );

            etPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {

            etPassword.setError(
                    "Password must contain at least 6 characters"
            );

            etPassword.requestFocus();
            return;
        }

        /*
         * Confirm Password
         */
        if (TextUtils.isEmpty(confirmPassword)) {

            etConfirmPassword.setError(
                    "Confirm your password"
            );

            etConfirmPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {

            etConfirmPassword.setError(
                    "Passwords do not match"
            );

            etConfirmPassword.requestFocus();
            return;
        }

        setRegisterLoading(true);

        final String finalMobile = mobile;
        final String finalFullName = fullName;
        final String finalEmail = email;
        final String finalRole = selectedRole;

        /*
         * Firebase Authentication
         */
        mAuth.createUserWithEmailAndPassword(
                finalEmail,
                password
        ).addOnCompleteListener(
                this,
                new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(
                            @NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            FirebaseUser firebaseUser =
                                    mAuth.getCurrentUser();

                            if (firebaseUser != null) {

                                String uid =
                                        firebaseUser.getUid();

                                saveUserProfile(
                                        uid,
                                        finalFullName,
                                        finalEmail,
                                        finalMobile,
                                        finalRole
                                );

                            } else {

                                showError(
                                        "Unable to create account."
                                );
                            }

                        } else {

                            String message =
                                    "Registration failed.";

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

    /*
     * Save user profile to Realtime Database
     */
    private void saveUserProfile(
            String uid,
            String name,
            String email,
            String phone,
            String role) {

        Map<String, Object> userData =
                new HashMap<>();

        userData.put("name", name);
        userData.put("email", email);
        userData.put("phone", phone);
        userData.put("role", role);

        usersRef.child(uid)
                .setValue(userData)
                .addOnCompleteListener(
                        this,
                        new OnCompleteListener<Void>() {

                            @Override
                            public void onComplete(
                                    @NonNull Task<Void> task) {

                                if (task.isSuccessful()) {

                                    Toast.makeText(
                                            RegisterActivity.this,
                                            "Account created successfully!",
                                            Toast.LENGTH_SHORT
                                    ).show();

                                    openDashboard(role);

                                } else {

                                    showError(
                                            "Account created, but profile could not be saved."
                                    );
                                }
                            }
                        }
                );
    }

    private void openDashboard(String role) {

        Intent intent;

        if (role.equalsIgnoreCase("Admin")) {

            intent = new Intent(
                    RegisterActivity.this,
                    AdminDashboardActivity.class
            );

        } else {

            intent = new Intent(
                    RegisterActivity.this,
                    CustomerDashboardActivity.class
            );
        }

        startActivity(intent);
        finish();
    }

    private void setRegisterLoading(
            boolean loading) {

        btnPrimary.setEnabled(!loading);

        if (loading) {

            btnPrimary.setText(
                    "Creating Account..."
            );

        } else {

            if (selectedRole.equals("Admin")) {

                btnPrimary.setText(
                        "Create Admin Account  →"
                );

            } else {

                btnPrimary.setText(
                        "Create Account  →"
                );
            }
        }
    }

    private void showError(String message) {

        setRegisterLoading(false);

        Toast.makeText(
                RegisterActivity.this,
                message,
                Toast.LENGTH_LONG
        ).show();
    }
}