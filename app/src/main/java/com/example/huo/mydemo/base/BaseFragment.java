package com.example.huo.mydemo.base;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huo.mydemo.GanKApplication;
import com.example.huo.mydemo.component.AppComponent;
import com.example.huo.mydemo.view.loadding.CustomDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {


    private CustomDialog dialog;

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

    public void hideDialog() {
        if (dialog != null) dialog.hide();
    }

    public void showDialog() {
        initDialog().show();
    }

    private void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    private CustomDialog initDialog() {
        if (dialog == null) {
            dialog = CustomDialog.instance(getActivity());
            dialog.setCancelable(false);
        }
        return dialog;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dismissDialog();
    }
}
