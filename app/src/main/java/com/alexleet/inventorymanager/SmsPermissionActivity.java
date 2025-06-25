package com.alexleet.inventorymanager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.alexleet.inventorymanager.databinding.ActivitySmsPermissionBinding;

/**
 * SmsPermissionActivity manages SMS permission requests and allows
 * the user to send a test SMS if permission is granted.
 */
public class SmsPermissionActivity extends AppCompatActivity {

    private ActivitySmsPermissionBinding binding;

    // Launcher to handle runtime SMS permission request
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    binding.textPermissionStatus.setText(R.string.sms_permission_granted);
                } else {
                    binding.textPermissionStatus.setText(R.string.sms_permission_denied);
                }
            });

    /**
     * Called when the activity is first created.
     * Sets up view binding and button listeners for requesting permission and sending SMS.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySmsPermissionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        updatePermissionStatus();

        /*
         * Handle request permission button click:
         * If permission not granted, launch request.
         * Otherwise, show a message that it's already granted.
         */
        binding.btnRequestPermission.setOnClickListener(view -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(Manifest.permission.SEND_SMS);
            } else {
                Toast.makeText(this, getString(R.string.sms_permission_already_granted), Toast.LENGTH_LONG).show();
            }
        });

        /*
         * Handle send test SMS button click:
         * Only sends SMS if permission has already been granted.
         */
        binding.btnSendTestSms.setOnClickListener(view -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                    == PackageManager.PERMISSION_GRANTED) {

                try {
                    String phoneNumber = "5551234567"; // demo/test number
                    String message = "Inventory Alert: A test SMS was successfully sent from the app.";

                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNumber, null, message, null, null);

                    Toast.makeText(this, "Test SMS sent", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(this, "Failed to send SMS: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            } else {
                // Inform user that SMS permission is required
                Toast.makeText(this, "SMS permission not granted", Toast.LENGTH_LONG).show();
            }
        });

        binding.btnBackToDashboard.setOnClickListener(view -> {
            finish(); // Close this activity and return to DashboardActivity
        });
    }

    /**
     * Updates the UI text based on whether SMS permission is currently granted.
     */
    private void updatePermissionStatus() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED) {
            binding.textPermissionStatus.setText(R.string.sms_permission_granted);
        } else {
            binding.textPermissionStatus.setText(R.string.sms_permission_not_granted);
        }
    }
}
