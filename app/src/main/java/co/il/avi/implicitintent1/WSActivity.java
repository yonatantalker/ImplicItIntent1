package co.il.avi.implicitintent1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WSActivity extends AppCompatActivity {
    private EditText editPhoneNumber;
    private Button btnSendWithNumber;
    private Button btnSendWithoutNumber;
    private Button btnCheckWhatsApp;
    private Button btnReturnToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // הפעלת התאמה לגבולות המסך
        setContentView(R.layout.activity_wsactivity);

        // אתחול רכיבי הממשק
        editPhoneNumber = findViewById(R.id.editPhoneNumber);
        btnSendWithNumber = findViewById(R.id.btnSendWithNumber);
        btnSendWithoutNumber = findViewById(R.id.btnSendWithoutNumber);
        btnCheckWhatsApp = findViewById(R.id.btnCheckWhatsApp);
        btnReturnToMain = findViewById(R.id.btnReturnToMain);

        // שליחת הודעה עם מספר טלפון
        btnSendWithNumber.setOnClickListener(v -> sendWhatsAppMessageWithNumber());

        // שליחת הודעה ללא מספר טלפון
        btnSendWithoutNumber.setOnClickListener(v -> openWhatsApp());

        // בדיקת נוכחות WhatsApp
        btnCheckWhatsApp.setOnClickListener(v -> redirectToGooglePlay());

        // חזרה למסך הראשי
        btnReturnToMain.setOnClickListener(v -> finish());

        // התאמת חלון (פדים של מערכת)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void sendWhatsAppMessageWithNumber() {
        String phoneNumber = editPhoneNumber.getText().toString().trim();

        if (phoneNumber.isEmpty()) {
            Toast.makeText(this, "Please enter a phone number", Toast.LENGTH_SHORT).show();
            return;
        }

        Uri uri = Uri.parse("https://wa.me/" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.whatsapp");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "WhatsApp is not installed on your device", Toast.LENGTH_SHORT).show();
        }
    }

    private void openWhatsApp() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setPackage("com.whatsapp");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "WhatsApp is not installed on your device", Toast.LENGTH_SHORT).show();
        }
    }

    private void redirectToGooglePlay() {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.whatsapp"));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Unable to open Google Play", Toast.LENGTH_SHORT).show();
        }
    }
}

