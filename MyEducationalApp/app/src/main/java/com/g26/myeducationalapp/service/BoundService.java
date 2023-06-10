package com.g26.myeducationalapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;

public class BoundService extends Service {
    private static final String TAG = "BoundService";
    private final IBinder binder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class MyBinder extends Binder {
        public BoundService getService() {
            return BoundService.this;
        }

        public void fetchWeather() {
            Log.d(TAG, "BoundService 获取天气信息");
        }
    }
}
