package com.codewiz.looper;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.codewiz.looper.auto.AutoLooper;
import com.codewiz.looper.auto.AutoLooperRunnable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AutoLooper dd = new AutoLooper("dd");
        getLifecycle().addObserver(dd);
        dd.post(new AutoLooperRunnable() {
            @Override
            public void run() {
                System.out.println("Looping ");
            }
        });

    }
}