package com.codewiz.looper;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.codewiz.looper.auto.Looper;
import com.codewiz.looper.auto.LooperRunnable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Looper dd = new Looper("dd");
        getLifecycle().addObserver(dd);
        dd.post(new LooperRunnable() {
            @Override
            public void run() {
                System.out.println("Looping");
            }
        });
    }
}