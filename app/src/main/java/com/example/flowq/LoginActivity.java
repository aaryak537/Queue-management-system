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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference usersRef;

    private RadioGroup rgRole;
    private RadioGroup rgLoginMethod;

    private RadioButton rbUser;
    private RadioButton rbAdmin;
    private RadioButton rbEmail;
    private RadioButton rbPhone;

    private EditText etIdentifier;
    private EditText etPassword;

    private TextView tvIdentifierLabel;
    private TextView tvForgotPassword;
    private TextView tvRegister;

    private View passwordSection;

    private Button btnLogin;

    private String selectedRole = "Customer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        usersRef = FirebaseDatabase.getInstance()
                .getReference("Users");

        initializeViews();
        setupListeners();

        updateRoleAppearance(false);
        showEmailLogin();
    }

    private void initializeViews() {

        rgRole = findViewById(R.id.rgRole);
        rgLoginMethod = findViewById(R.id.rgLoginMethod);

        rbUser = findViewById(R.id.rbUser);
        rbAdmin = findViewById(R.id.rbAdmin);

        rbEmail = findViewById(R.id.rbEmail);
        rbPhone = findViewById(R.id.rbPhone);

        etIdentifier = findViewById(R.id.etIdentifier);
        etPassword = findViewById(R.id.etPassword);

        tvIdentifierLabel =
                findViewById(R.id.tvIdentifierLabel);

        tvForgotPassword =
                findViewById(R.id.tvForgotPassword);

        tvRegister =
                findViewById(R.id.tvRegister);

        passwordSection =
                findViewById(R.id.passwordSection);

        btnLogin =
                findViewById(R.id.btnLogin);
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

                            updateRoleAppearance(true);

                        } else {

                            selectedRole = "Customer";

                            updateRoleAppearance(false);
                        }

                        if (rbPhone.isChecked()) {

                            showPhoneLogin();

                        } else {

                            showEmailLogin();
                        }
                    }
                }
        );

        /*
         * Login Method
         */
        rgLoginMethod.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(
                            RadioGroup group,
                            int checkedId) {

                        if (checkedId == R.id.rbPhone) {

                            showPhoneLogin();

                        } else {

                            showEmailLogin();
                        }
                    }
                }
        );

        /*
         * Login Button
         */
        btnLogin.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        if (rbPhone.isChecked()) {

                            startPhoneLogin();

                        } else {

                            loginWithEmail();
                        }
                    }
                }
        );

        /*
         * Forgot Password
         */
        tvForgotPassword.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Intent intent =
                                new Intent(
                                        LoginActivity.this,
                                        ForgotPasswordActivity.class
                                );

                        startActivity(intent);
                    }
                }
        );

        /*
         * Register
         */
        tvRegister.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Intent intent =
                                new Intent(
                                        LoginActivity.this,
                                        RegisterActivity.class
                                );

                        startActivity(intent);
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
                            R.color.grey_text
                    )
            );

            rbAdmin.setBackgroundResource(
                    R.drawable.bg_role_unselected
            );
        }
    }

    private void showEmailLogin() {

        passwordSection.setVisibility(View.VISIBLE);

        tvForgotPassword.setVisibility(View.VISIBLE);

        if (selectedRole.equals("Admin")) {

            tvIdentifierLabel.setText(
                    "Admin Email"
            );

            etIdentifier.setHint(
                    "Enter admin email"
            );

        } else {

            tvIdentifierLabel.setText(
                    "Email"
            );

            etIdentifier.setHint(
                    "Enter your email"
            );
        }

        etIdentifier.setInputType(
                android.text.InputType.TYPE_CLASS_TEXT
                        |
                        android.text.InputType
                                .TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        );

        if (selectedRole.equals("Admin")) {

            btnLogin.setText(
                    "Login as Admin"
            );

        } else {

            btnLogin.setText(
                    "Login as User"
            );
        }
    }

    private void showPhoneLogin() {

        passwordSection.setVisibility(
                View.GONE
        );

        tvForgotPassword.setVisibility(
                View.GONE
        );

        if (selectedRole.equals("Admin")) {

            tvIdentifierLabel.setText(
                    "Admin Phone Number"
            );

            etIdentifier.setHint(
                    "+91 9876543210"
            );

        } else {

            tvIdentifierLabel.setText(
                    "Phone Number"
            );

            etIdentifier.setHint(
                    "+91 9876543210"
            );
        }

        etIdentifier.setInputType(
                android.text.InputType.TYPE_CLASS_PHONE
        );

        btnLogin.setText(
                "Send OTP"
        );
    }

    private void loginWithEmail() {

        String email =
                etIdentifier.getText()
                        .toString()
                        .trim();

        String password =
                etPassword.getText()
                        .toString();

        if (TextUtils.isEmpty(email)) {

            etIdentifier.setError(
                    "Enter email"
            );

            etIdentifier.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS
                .matcher(email)
                .matches()) {

            etIdentifier.setError(
                    "Enter a valid email"
            );

            etIdentifier.requestFocus();
            return;
        }

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

        setLoginLoading(true);

        mAuth.signInWithEmailAndPassword(
                email,
                password
        ).addOnCompleteListener(
                this,
                new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(
                            @NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            FirebaseUser user =
                                    mAuth.getCurrentUser();

                            if (user != null) {

                                checkUserRole(
                                        user.getUid(),
                                        selectedRole
                                );

                            } else {

                                showError(
                                        "Unable to get user information."
                                );
                            }

                        } else {

                            String message =
                                    "Login failed.";

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

    private void startPhoneLogin() {

        String phone =
                etIdentifier.getText()
                        .toString()
                        .trim();

        if (TextUtils.isEmpty(phone)) {

            etIdentifier.setError(
                    "Enter phone number"
            );

            etIdentifier.requestFocus();
            return;
        }

        phone = phone.replace(
                " ",
                ""
        );

        if (!phone.startsWith("+")) {

            if (phone.startsWith("0")) {

                phone = "+91"
                        + phone.substring(1);

            } else {

                phone = "+91"
                        + phone;
            }
        }

        if (phone.length() < 12) {

            etIdentifier.setError(
                    "Enter a valid phone number"
            );

            etIdentifier.requestFocus();
            return;
        }

        Intent intent =
                new Intent(
                        LoginActivity.this,
                        PhoneAuthActivity.class
                );

        intent.putExtra(
                "phone_number",
                phone
        );

        intent.putExtra(
                "selected_role",
                selectedRole
        );

        startActivity(intent);
    }

    private void checkUserRole(
            String uid,
            String expectedRole) {

        usersRef.child(uid)
                .addListenerForSingleValueEvent(
                        new ValueEventListener() {

                            @Override
                            public void onDataChange(
                                    @NonNull DataSnapshot snapshot) {

                                if (!snapshot.exists()) {

                                    mAuth.signOut();

                                    showError(
                                            "User profile not found."
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
                                        expectedRole
                                )) {

                                    openDashboard(
                                            databaseRole
                                    );

                                } else {

                                    mAuth.signOut();

                                    showError(
                                            "Incorrect role selected."
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

    public void openDashboard(String role) {

        Toast.makeText(
                LoginActivity.this,
                "Login successful",
                Toast.LENGTH_SHORT
        ).show();

        Intent intent;

        if (role.equalsIgnoreCase("Admin")) {

            intent =
                    new Intent(
                            LoginActivity.this,
                            AdminDashboardActivity.class
                    );

        } else {

            intent =
                    new Intent(
                            LoginActivity.this,
                            CustomerDashboardActivity.class
                    );
        }

        startActivity(intent);
        finish();
    }

    private void setLoginLoading(
            boolean loading) {

        btnLogin.setEnabled(!loading);

        if (loading) {

            btnLogin.setText(
                    "Please wait..."
            );

        } else {

            if (rbPhone.isChecked()) {

                btnLogin.setText(
                        "Send OTP"
                );

            } else if (selectedRole.equals("Admin")) {

                btnLogin.setText(
                        "Login as Admin"
                );

            } else {

                btnLogin.setText(
                        "Login as User"
                );
            }
        }
    }

    private void showError(String message) {

        setLoginLoading(false);

        Toast.makeText(
                LoginActivity.this,
                message,
                Toast.LENGTH_LONG
        ).show();
    }
}