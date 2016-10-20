package com.example.huo.mydemo.ui.activity;

import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.huo.mydemo.R;
import com.example.huo.mydemo.base.BaseActivity;
import com.example.huo.mydemo.component.AppComponent;


public class WebActivity extends BaseActivity {
    private static final String TAG = "WebActivity";
    String url;
//    @BindView(R.id.wv_show)
    WebView mWvShow;

//    @Override
    public void initView() {
        url = getIntent().getData().toString();
        setContentView(R.layout.activity_web);
        Log.d(TAG, "initView: " + url);
//        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutResId() {
        return 0;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected void configView() {

    }

    @Override
    public void initData() {
        WebSettings settings = mWvShow.getSettings();
        if (url != null) mWvShow.loadUrl(url);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
    }

}
