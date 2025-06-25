package com.alexleet.inventorymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alexleet.inventorymanager.databinding.ActivityLoginBinding;

/**
 * LoginActivity handles user authentication.
 * Users can either log in or create a new account.
 */
public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    // Database helper for user authentication
    private UserDatabaseHelper userDbHelper;

    /**
     * Called when the activity is first created.
     * Sets up view binding, initializes the database helper,
     * and sets click listeners for the login and create account buttons.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // View binding setup
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize UserDatabaseHelper
        userDbHelper = new UserDatabaseHelper(this);

        /*
         * When the Login button is clicked:
         * - Retrieve username and password from input fields
         * - Validate credentials against the database
         * - Show a success or failure message
         * - If successful, navigate to the Dashboard
         */
        binding.btnLogin.setOnClickListener(view -> {
            String username = String.valueOf(binding.editTextUsername.getText()).trim();
            String password = String.valueOf(binding.editTextPassword.getText()).trim();

            // Check if fields are empty
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_LONG).show();
            } else {
                // Validate user credentials
                if (userDbHelper.validateUser(username, password)) {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_LONG).show();

                    // Navigate to Dashboard and finish login activity
                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Invalid username or password", Toast.LENGTH_LONG).show();
                }
            }
        });

        /*
         * When the Create Account button is clicked:
         * - Retrieve username and password from input fields
         * - Attempt to add user to database
         * - Show success or failure message
         */
        binding.btnCreateAccount.setOnClickListener(view -> {
            String username = String.valueOf(binding.editTextUsername.getText()).trim();
            String password = String.valueOf(binding.editTextPassword.getText()).trim();

            // Check if fields are empty
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both username and password to create account", Toast.LENGTH_LONG).show();
            } else {
                // Try adding new user to the database
                if (userDbHelper.addUser(username, password)) {
                    Toast.makeText(this, "Account created for " + username, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Username already exists", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}