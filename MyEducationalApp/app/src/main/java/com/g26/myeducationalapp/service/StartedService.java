package com.g26.myeducationalapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;

public class StartedService extends Service {
    private static final String TAG = "StartedService";
    private HandlerThread handlerThread;
    private Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        this.handlerThread = new HandlerThread("StartedServiceHandlerThread");
        this.handlerThread.start();
        this.handler = new Handler(this.handlerThread.getLooper());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "StartedService 开始播放音乐");
            }
        });
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.handlerThread != null) {
            this.handlerThread.quit();
            this.handlerThread = null;
            Log.d(TAG, "StartedService 停止播放音乐");
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
