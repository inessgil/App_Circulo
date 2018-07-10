package com.example.ines.circulo;

import android.os.Looper;
import android.os.Handler;

import com.example.ines.domain.executor.PostExecutionThread;

public class UIThread implements PostExecutionThread {

    private final Handler handler;

    public UIThread () {
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        handler.post(runnable);
    }
}
