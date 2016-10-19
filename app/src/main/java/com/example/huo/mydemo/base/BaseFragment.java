package com.example.huo.mydemo.base;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huo.mydemo.GanKApplication;
import com.example.huo.mydemo.component.AppComponent;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {


    protected abstract
    @LayoutRes
    int getLayoutResId();


    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResId(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setupActivityComponent(((GanKApplication) getActivity().getApplication()).getAppComponent());
    }

    protected abstract void setupActivityComponent(AppComponent appComponent);
}
