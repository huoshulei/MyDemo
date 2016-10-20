package com.example.huo.mydemo.ui;


import com.example.huo.mydemo.App;
import com.example.huo.mydemo.R;
import com.example.huo.mydemo.base.BaseActivity;
import com.example.huo.mydemo.component.AppComponent;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected void configView() {

    }

    @Override
    protected void initData() {
        App.toast("111111111111111111111");
        showDialog();
    }
}
