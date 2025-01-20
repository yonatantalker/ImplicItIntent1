package co.il.avi.implicitintent1;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CameraActivity extends AppCompatActivity {

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;
    private ImageView capturedImageView;
    private Button captureImageButton;
    private Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_camera1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    capturedImageView = findViewById(R.id.imageView);
    captureImageButton = findViewById(R.id.openCameraButton);
    returnButton = findViewById(R.id.btnCameraReturn);

    // מאזין לחיצה לכפתור פתיחת המצלמה
        captureImageButton.setOnClickListener(v -> {
        Log.d("CameraActivity", "Capture Image button clicked");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            Log.d("CameraActivity", "Camera permission granted, opening camera...");
            launchCamera();
        } else {
            Log.d("CameraActivity", "Camera permission not granted, requesting permission...");
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
        }
    });

    // מאזין לחיצה לכפתור חזרה
        returnButton.setOnClickListener(v -> finish());
}

/**
 * פותח את אפליקציית המצלמה לצילום תמונה.
 */
private void launchCamera() {
    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    if (cameraIntent.resolveActivity(getPackageManager()) != null) {
        startActivityForResult(cameraIntent, CAMERA_PERMISSION_REQUEST_CODE);
    } else {
        Toast.makeText(this, "No camera app found.", Toast.LENGTH_SHORT).show();
    }
}

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == CAMERA_PERMISSION_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
        // שליפת התמונה שצולמה והצגתה
        Bitmap capturedImage = (Bitmap) data.getExtras().get("data");
        if (capturedImage != null) {
            capturedImageView.setImageBitmap(capturedImage);
        } else {
            Log.d("CameraActivity", "No image data received.");
        }
    } else {
        Log.d("CameraActivity", "Failed to capture image.");
    }
}

@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d("CameraActivity", "Camera permission granted after request.");
            launchCamera();
        } else {
            Toast.makeText(this, "Camera permission denied.", Toast.LENGTH_SHORT).show();
        }
    }
}