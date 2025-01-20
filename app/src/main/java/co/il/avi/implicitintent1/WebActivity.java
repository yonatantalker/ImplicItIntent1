package co.il.avi.implicitintent1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WebActivity extends AppCompatActivity {
    private EditText etUrl;
    private Button btnSearch;
    private Button btnWebReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_web);

        // Applying window insets for edge-to-edge layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views and set listeners
        initializeViews();
    }

    private void initializeViews() {
        etUrl = findViewById(R.id.etUrl);
        btnSearch = findViewById(R.id.btnSearch);
        btnWebReturn = findViewById(R.id.btnWebReturn);

        setListeners();
    }

    private void setListeners() {
        // Listener for Search button (launches the URL in a browser)
        btnSearch.setOnClickListener(v -> {
            String url = etUrl.getText().toString().trim();
            if (!url.isEmpty()) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        // Listener for Return button (closes the activity)
        btnWebReturn.setOnClickListener(v -> finish());
    }
}
