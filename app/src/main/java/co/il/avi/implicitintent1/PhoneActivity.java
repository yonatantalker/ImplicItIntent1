package co.il.avi.implicitintent1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PhoneActivity extends AppCompatActivity {

    private EditText phoneNumberEditText;
    private Button dialButton;
    private Button btnPhoneReturn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        // Initialize UI elements
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        dialButton = findViewById(R.id.dialButton);
        btnPhoneReturn = findViewById(R.id.btnPhoneReturn);

        // Set listener for the dial button
        dialButton.setOnClickListener(v -> {
            String phoneNumber = phoneNumberEditText.getText().toString().trim();
            if (phoneNumber.isEmpty()) {
                // Show error if the phone number is empty
                Toast.makeText(this, "Please enter a phone number", Toast.LENGTH_SHORT).show();
            } else if (!isValidPhoneNumber(phoneNumber)) {
                // Show error if the phone number is invalid
                Toast.makeText(this, "Invalid phone number. Please try again.", Toast.LENGTH_SHORT).show();
            } else {
                // Open dialer with the phone number
                openDialer(phoneNumber);
            }
        });

        // Set listener for the return button
        btnPhoneReturn.setOnClickListener(v -> finish());

        // Optional: Add padding for system bars dynamically
        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }
    }

    private void openDialer(String phoneNumber) {
        // Create an implicit intent to open the dialer with the phone number
        Uri uri = Uri.parse("tel:" + phoneNumber);
        Intent dialIntent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(dialIntent);
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Basic validation for phone number (checks only digits and length)
        return phoneNumber.matches("\\d{3,15}");
    }
}

