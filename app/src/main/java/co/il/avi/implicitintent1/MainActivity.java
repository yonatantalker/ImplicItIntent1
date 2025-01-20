package co.il.avi.implicitintent1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private ImageButton btnOpenCamera;
    private ImageButton btnSendSMS;
    private ImageButton btnWhatsApp;
    private ImageButton btnGmailApp;
    private ImageButton btnPhoneCall;
    private ImageButton btnGoogleMaps;
    private ImageButton btnOpenBrowser;
    private ImageButton btnOpenYoutube;
    private ImageButton btnOpenWaze;
    private ImageButton btnAlarmClock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initializeViews();
    }

    private void initializeViews() {
        btnOpenCamera   = findViewById(R.id.btnCamera);
        btnSendSMS      = findViewById(R.id.btnSMS);
        btnWhatsApp     = findViewById(R.id.btnWS);
        btnGmailApp     = findViewById(R.id.btnGmail);
        btnPhoneCall    = findViewById(R.id.btnPhone);
        btnGoogleMaps   = findViewById(R.id.btnMaps);
        btnOpenBrowser  = findViewById(R.id.btnWWW);
        btnOpenYoutube  = findViewById(R.id.btnYoutube);
        btnOpenWaze     = findViewById(R.id.btnWaze);
        btnAlarmClock   = findViewById(R.id.btnClock);

        setListeners();
    }

    private void setListeners() {
        btnOpenCamera.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CameraActivity.class);
            startActivity(intent);
        });

        btnWhatsApp.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, WSActivity.class);
            startActivity(intent);
        });

        btnPhoneCall.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, PhoneActivity.class);
            startActivity(intent);
        });

        btnSendSMS.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SMSActivity.class);
            startActivity(intent);
        });

        btnGmailApp.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, GmailActivity.class);
            startActivity(intent);
        });

        btnGoogleMaps.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MapActivity.class);
            startActivity(intent);
        });

        btnOpenBrowser.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, WebActivity.class);
            startActivity(intent);
        });

        btnOpenYoutube.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, YoutubeActivity.class);
            startActivity(intent);
        });

        btnOpenWaze.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, WazeActivity.class);
            startActivity(intent);
        });

        btnAlarmClock.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ClockActivity.class);
            startActivity(intent);
        });
    }
}
