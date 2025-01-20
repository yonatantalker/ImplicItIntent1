package co.il.avi.implicitintent1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MapActivity extends AppCompatActivity {
    private EditText editLatitude;
    private EditText editLongitude;
    private EditText editAddress;
    private Button btnOpenMap;
    private Button btnPinAddress;
    private Button btnReturnToMain;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Initialize UI elements
        editLatitude = findViewById(R.id.editLatitude);
        editLongitude = findViewById(R.id.editLongitude);
        editAddress = findViewById(R.id.editAddress);
        btnOpenMap = findViewById(R.id.btnOpenMap);
        btnPinAddress = findViewById(R.id.btnPinAddress);
        btnReturnToMain = findViewById(R.id.btnReturnToMain);

        // Open map with coordinates
        btnOpenMap.setOnClickListener(v -> openMapWithCoordinates());

        // Open map with address
        btnPinAddress.setOnClickListener(v -> openMapWithAddress());

        // Return to main activity
        btnReturnToMain.setOnClickListener(v -> finish());
    }

    private void openMapWithCoordinates() {
        String latitude = editLatitude.getText().toString().trim();
        String longitude = editLongitude.getText().toString().trim();

        if (latitude.isEmpty() || longitude.isEmpty()) {
            showToast("Please enter both latitude and longitude");
            return;
        }

        try {
            double lat = Double.parseDouble(latitude);
            double lon = Double.parseDouble(longitude);

            // Validate latitude and longitude ranges
            if (lat < -90 || lat > 90 || lon < -180 || lon > 180) {
                showToast("Coordinates out of range. Latitude must be between -90 and 90, and longitude between -180 and 180.");
                return;
            }

            Uri gmmIntentUri = Uri.parse("geo:" + lat + "," + lon + "?q=" + lat + "," + lon);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        } catch (NumberFormatException e) {
            showToast("Invalid coordinates. Please enter valid numeric values.");
        }
    }

    private void openMapWithAddress() {
        String address = editAddress.getText().toString().trim();

        if (address.isEmpty()) {
            showToast("Please enter an address");
            return;
        }

        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(address));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        try {
            startActivity(mapIntent);
        } catch (Exception e) {
            showToast("Could not open the map. Please ensure Google Maps is installed.");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
