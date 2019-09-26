package com.example.messengerserviceexample;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText firstValue, secondValue;
    TextView tvResult;
    Intent bindIntent;
    CalculatorService calculatorService;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            CalculatorService.LocalBinder localBinder = (CalculatorService.LocalBinder) iBinder;
            calculatorService = localBinder.getService(); // Binder giving Service object refernce
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstValue = findViewById(R.id.aValue);
        secondValue = findViewById(R.id.bValue);
        tvResult = findViewById(R.id.cValue);

        bindIntent = new Intent(this, CalculatorService.class);
        bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE);
    }

    public void Sumvalue(View view) {
        int a = Integer.parseInt(firstValue.getText().toString());
        int b = Integer.parseInt(secondValue.getText().toString());
        int c = calculatorService.addNumbers(a, b); // Step 4
        tvResult.setText(String.valueOf(c));

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
