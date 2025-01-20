package co.il.avi.implicitintent1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ClockActivity extends AppCompatActivity {
    private EditText hourInput;
    private EditText minuteInput;
    private Button setAlarmButton;
    private Button returnToMainButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);


            hourInput = findViewById(R.id.editHour);
            minuteInput = findViewById(R.id.editMinute);
            setAlarmButton = findViewById(R.id.btnSetAlarm);
            returnToMainButton = findViewById(R.id.btnReturnToMain);

        // Set Alarm Button Listener
        setAlarmButton.setOnClickListener(v -> {
            String hourText = hourInput.getText().toString().trim();
            String minuteText = minuteInput.getText().toString().trim();

            if (hourText.isEmpty() || minuteText.isEmpty()) {
                Toast.makeText(this, "Please enter both hour and minute", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int hour = Integer.parseInt(hourText);
                int minute = Integer.parseInt(minuteText);

                if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
                    Toast.makeText(this, "Please enter a valid time", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                        .putExtra(AlarmClock.EXTRA_HOUR, hour)
                        .putExtra(AlarmClock.EXTRA_MINUTES, minute)
                        .putExtra(AlarmClock.EXTRA_MESSAGE, "New Alarm");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "No Clock app available to set the alarm", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid input. Please enter numbers only.", Toast.LENGTH_SHORT).show();
            }
        });

        // Return to Main Activity Button Listener
        returnToMainButton.setOnClickListener(v -> finish());
    }
}
