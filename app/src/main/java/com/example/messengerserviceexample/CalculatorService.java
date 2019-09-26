package com.example.messengerserviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class CalculatorService extends Service {

    class LocalBinder extends Binder {
        public CalculatorService getService() {
            // gives(returns) the Service Object to Activity
            CalculatorService calculatorService = new CalculatorService();
            return calculatorService;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        LocalBinder binder = new LocalBinder();
        return binder;
    }

    public int addNumbers(int a, int b) {
        int sum = a + b;
        return sum;
    }
}
