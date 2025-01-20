package co.il.avi.implicitintent1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class YoutubeActivity extends AppCompatActivity {
    private EditText editVideoTitle;
    private Button btnSearchYoutube;
    private Button btnReturnToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        initializeViews();
        setListeners();
    }

    private void initializeViews() {
        editVideoTitle = findViewById(R.id.editVideoTitle);
        btnSearchYoutube = findViewById(R.id.btnSearchYoutube);
        btnReturnToMain = findViewById(R.id.btnReturnToMain);
    }

    private void setListeners() {
        // Search YouTube for the entered video title
        btnSearchYoutube.setOnClickListener(v -> {
            String videoTitle = editVideoTitle.getText().toString().trim();

            if (videoTitle.isEmpty()) {
                showToast("Please enter a video title");
                return;
            }

            // Create a YouTube search intent
            Uri youtubeSearchUri = Uri.parse("https://www.youtube.com/results?search_query=" + Uri.encode(videoTitle));
            Intent youtubeIntent = new Intent(Intent.ACTION_VIEW, youtubeSearchUri);
            youtubeIntent.setPackage("com.google.android.youtube"); // Launch YouTube app if installed

            try {
                startActivity(youtubeIntent);
            } catch (Exception e) {
                showToast("YouTube app is not installed. Opening in browser.");
                // Launch in browser if YouTube app is unavailable
                youtubeIntent.setPackage(null);
                startActivity(youtubeIntent);
            }
        });

        // Return to Main Activity
        btnReturnToMain.setOnClickListener(v -> finish());
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

