package com.example.huo.mydemo.base;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.huo.mydemo.GanKApplication;
import com.example.huo.mydemo.api.GanKApiService;
import com.example.huo.mydemo.component.AppComponent;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity {
    protected abstract
    @LayoutRes
    int getLayoutResId();

    @Inject
    GanKApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        setupActivityComponent(((GanKApplication) getApplication()).getAppComponent());
        configView();
        initData();
    }

    protected abstract void setupActivityComponent(AppComponent appComponent);

    protected abstract void configView();

    protected abstract void initData();
}
