package co.il.avi.implicitintent1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;

public class GmailActivity extends AppCompatActivity {

    private EditText messageEditText;
    private Button sendGmailButton, returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmail);

        // Initialize UI elements
        messageEditText = findViewById(R.id.messageEditText);
        sendGmailButton = findViewById(R.id.sendGmailButton);
        returnButton = findViewById(R.id.returnButton);

        // Set listener for the "Send Gmail" button
        sendGmailButton.setOnClickListener(v -> {
            String message = messageEditText.getText().toString().trim();
            if (!message.isEmpty()) {
                sendEmail(message);
            } else {
                Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show();
            }
        });

        // Set listener for the "Return to Main Activity" button
        returnButton.setOnClickListener(v -> finish()); // Closes this activity and returns to the previous one

        // Handle window insets (system bar padding)
        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsets.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }
    }

    private void sendEmail(String message) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822"); // MIME type for email
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"recipient@example.com"}); // Replace with default recipient if needed
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your Subject Here");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        // Start email client
        try {
            startActivity(Intent.createChooser(emailIntent, "Send email using..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "No email client installed", Toast.LENGTH_SHORT).show();
        }
    }
}
