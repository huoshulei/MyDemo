package com.example.huo.mydemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.huo.mydemo.App;
import com.example.huo.mydemo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.toast("111111111111111111");
    }
}
