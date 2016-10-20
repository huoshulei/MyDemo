package com.example.huo.mydemo.ui.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.huo.mydemo.R;
import com.example.huo.mydemo.animator.SisterAnimator;
import com.example.huo.mydemo.base.BaseFragment;
import com.example.huo.mydemo.bean.ResultBean;
import com.example.huo.mydemo.component.AppComponent;

import java.util.List;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class SisterFragment extends BaseFragment {
    private static final String TAG = "SisterFragment";
//    @BindView(R.id.rv_sister)
    RecyclerView mRvSister;
//    private SisterAdapter mAdapter;
    //    String URL = "http://gank.io/api/data/福利/20/";
    int i = 1;
    private boolean isLoadNext = false;
//    private HttpRetrofitDemo mRetrofit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getData(i + "");
    }


//    private void getData(String page) {
//        if (mRetrofit == null)
//            mRetrofit = new HttpRetrofitDemo();
//        mRetrofit.getData("data", "福利", "20", page);
//
//
//        mRetrofit.setRxjavaListener(new RxjavaListener() {
//
//            @Override
//            public void onNext(List t) {
//                if (t.size() <= 0) {
//                    Toast.makeText(getActivity(), "已无更多可以加载", Toast.LENGTH_SHORT).show();
//                    mAdapter.openLoadMore(false);
//                    return;
//                }
//                mAdapter.addData(t);
//                mAdapter.setPageSize(mAdapter.getPageSize() + t.size());
//                mAdapter.notifyDataChangedAfterLoadMore(true);
//                isLoadNext = true;
//            }
//
//            @Override
//            public void onError() {
//                mMainActivity.showToast("请检查网络！");
//            }
//        });
//    }

    @Override
    protected int getLayoutResId() {
        return 0;
    }

    public SisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sister, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mRvSister.setHasFixedSize(true);
        mRvSister.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager
                .VERTICAL));
//        mAdapter = new SisterAdapter(getActivity(), R.layout.layout_photo_item, new
//                ImageLoader(mQueue, new BitmpCacheUtil()));
//        mAdapter.openLoadAnimation();
//        mRvSister.setAdapter(mAdapter);
//        SisterAnimator animator = new SisterAnimator();
//        mRvSister.setItemAnimator(animator);
//        animator.setStartActivity(getStartActivity());
//        mAdapter.setOnRecyclerViewItemClickListener(getOnRecyclerViewItemClickListener());
//        mAdapter.setOnRecyclerViewItemChildClickListener(getRecyclerViewItem());
//        mAdapter.addFooterView(getView());
//        mAdapter.openLoadMore(true);
//        mAdapter.setLoadingView();
//        mAdapter.setOnLoadMoreListener(getRequestLoadMoreListener());
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @NonNull
    private BaseQuickAdapter.RequestLoadMoreListener getRequestLoadMoreListener() {
        return new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (isLoadNext) {
                    isLoadNext = false;
                    i++;
//                    getData("" + i);
                }
            }
        };
    }

//    @NonNull
//    private BaseQuickAdapter.OnRecyclerViewItemChildClickListener getRecyclerViewItem() {
//        return new BaseQuickAdapter.OnRecyclerViewItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
//                mAdapter.notifyItemChanged(i, SisterAdapter.ACTION_LIKE_IMAGE_CLICKED);
//                Toast.makeText(getActivity(), "点了吗" + i, Toast.LENGTH_SHORT).show();
//            }
//        };
//    }

    @NonNull
    private SisterAnimator.StartActivity getStartActivity() {
        return new SisterAnimator.StartActivity() {
            @Override
            public void startActivity(int position) {
//                Uri uri = Uri.parse(((ResultBean) mAdapter.getData().get(position)).getUrl());
//                ((MainActivity) getActivity()).startActivity(SisterActivity.class, uri);
            }

        };
    }

}

