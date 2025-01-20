package co.il.avi.implicitintent1;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SMSActivity extends AppCompatActivity {

    private EditText messageEditText;
    private Button sendSmsButton;
    private Button btnSMSReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsactivity);

        // Initialize UI elements
        messageEditText = findViewById(R.id.messageEditText);
        sendSmsButton = findViewById(R.id.sendSmsButton);
        btnSMSReturn = findViewById(R.id.btnSMSReturn);

        // Set listener for the send SMS button
        sendSmsButton.setOnClickListener(v -> {
            String message = messageEditText.getText().toString().trim();
            if (message.isEmpty()) {
                Toast.makeText(this, "Please enter a message before sending", Toast.LENGTH_SHORT).show();
                return;
            }
            openSmsApp(message);
        });

        // Listener for return button
        btnSMSReturn.setOnClickListener(v -> finish());
    }

    private void openSmsApp(String message) {
        // Create an implicit intent to open the SMS app with the message pre-filled
        Uri uri = Uri.parse("smsto:"); // No recipient pre-filled
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO, uri);
        smsIntent.putExtra("sms_body", message); // Pre-fill the SMS body
        try {
            startActivity(smsIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No SMS app found on this device", Toast.LENGTH_SHORT).show();
        }
    }
}

