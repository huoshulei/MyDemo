package com.example.huo.mydemo.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.huo.mydemo.R;
import com.example.huo.mydemo.utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huo on 21/06/16.
 */

public class SisterAnimator extends DefaultItemAnimator {
    private static final String                 TAG                      = "SisterAnimator";
    private static final DecelerateInterpolator DECCELERATE_INTERPOLATOR = new
            DecelerateInterpolator();
    private static final AccelerateInterpolator ACCELERATE_INTERPOLATOR  = new
            AccelerateInterpolator();
    private static final OvershootInterpolator  OVERSHOOT_INTERPOLATOR   = new OvershootInterpolator
            (4);

    Map<RecyclerView.ViewHolder, AnimatorSet> likeAnimationsMap  = new HashMap<>();
    Map<RecyclerView.ViewHolder, AnimatorSet> heartAnimationsMap = new HashMap<>();

    private int lastAddAnimatedItem = -2;

    @Override
    public boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder viewHolder) {
        return true;
    }

    @NonNull
    @Override
    public ItemHolderInfo recordPreLayoutInformation(@NonNull RecyclerView.State state,
                                                     @NonNull RecyclerView.ViewHolder viewHolder,
                                                     int changeFlags, @NonNull List<Object>
                                                             payloads) {
        if (changeFlags == FLAG_CHANGED) {
            for (Object payload : payloads) {
                if (payload instanceof String) {
                    return new FeedItemHolderInfo((String) payload);
                }
            }
        }

        return super.recordPreLayoutInformation(state, viewHolder, changeFlags, payloads);
    }

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder viewHolder) {
//        if (viewHolder.getItemViewType() == FeedAdapter.VIEW_TYPE_DEFAULT) {
        if (viewHolder.getLayoutPosition() > lastAddAnimatedItem) {
            lastAddAnimatedItem++;
            runEnterAnimation((BaseViewHolder) viewHolder);
            return false;
//            }
        }

        dispatchAddFinished(viewHolder);
        return false;
    }

    private void runEnterAnimation(final BaseViewHolder holder) {
        final int screenHeight = (int) Utils.getScreenHeight(holder.itemView.getContext());
        holder.itemView.setTranslationY(screenHeight);
        holder.itemView.animate()
                .translationY(0)
                .setInterpolator(new DecelerateInterpolator(3.f))
                .setDuration(700)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        dispatchAddFinished(holder);
                    }
                })
                .start();
    }

    @Override
    public boolean animateChange(@NonNull RecyclerView.ViewHolder oldHolder,
                                 @NonNull RecyclerView.ViewHolder newHolder,
                                 @NonNull ItemHolderInfo preInfo,
                                 @NonNull ItemHolderInfo postInfo) {
        cancelCurrentAnimationIfExists(newHolder);

        if (preInfo instanceof FeedItemHolderInfo) {
            FeedItemHolderInfo feedItemHolderInfo = (FeedItemHolderInfo) preInfo;
            BaseViewHolder     holder             = (BaseViewHolder) newHolder;

//            if (SisterAdapter.ACTION_LIKE_IMAGE_CLICKED.equals(feedItemHolderInfo.updateAction)) {
//                animatePhotoLike(holder);
//            }
        }

        return false;
    }

    private void cancelCurrentAnimationIfExists(RecyclerView.ViewHolder item) {
        if (likeAnimationsMap.containsKey(item)) {
            likeAnimationsMap.get(item).cancel();
        }
        if (heartAnimationsMap.containsKey(item)) {
            heartAnimationsMap.get(item).cancel();
        }
    }


    private void animatePhotoLike(final BaseViewHolder holder) {
        View      vBgLike = holder.getView(R.id.v_bg_like);
        ImageView ivLike  = holder.getView(R.id.iv_like);
        vBgLike.setVisibility(View.VISIBLE);
        ivLike.setVisibility(View.VISIBLE);
//        holder.setVisible(R.id.v_bg_like,true);
//        holder.setVisible(R.id.iv_like,true);
        vBgLike.setScaleY(0.01f);//初始缩放系数
        vBgLike.setScaleX(0.01f);
        vBgLike.setAlpha(1f);//设置透明度
        ivLike.setScaleY(0.01f);
        ivLike.setScaleX(0.01f);

        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator bgScaleYAnim = ObjectAnimator.ofFloat(vBgLike, "scaleY", 0.1f, 1f);
        bgScaleYAnim.setDuration(500);
        bgScaleYAnim.setInterpolator(DECCELERATE_INTERPOLATOR);
        ObjectAnimator bgScaleXAnim = ObjectAnimator.ofFloat(vBgLike, "scaleX", 0.1f, 1f);
        bgScaleXAnim.setDuration(500);
        bgScaleXAnim.setInterpolator(DECCELERATE_INTERPOLATOR);
        ObjectAnimator bgAlphaAnim = ObjectAnimator.ofFloat(vBgLike, "alpha", 1f, 0f);
        bgAlphaAnim.setDuration(500);
        bgAlphaAnim.setStartDelay(200);
        bgAlphaAnim.setInterpolator(DECCELERATE_INTERPOLATOR);

        ObjectAnimator imgScaleUpYAnim = ObjectAnimator.ofFloat(ivLike, "scaleY", 0.1f, 1f);
        imgScaleUpYAnim.setDuration(600);
        imgScaleUpYAnim.setInterpolator(DECCELERATE_INTERPOLATOR);
        ObjectAnimator imgScaleUpXAnim = ObjectAnimator.ofFloat(ivLike, "scaleX", 0.1f, 1f);
        imgScaleUpXAnim.setDuration(600);
        imgScaleUpXAnim.setInterpolator(DECCELERATE_INTERPOLATOR);

        ObjectAnimator imgScaleDownYAnim = ObjectAnimator.ofFloat(ivLike, "scaleY", 1f, 0f);
        imgScaleDownYAnim.setDuration(600);
        imgScaleDownYAnim.setInterpolator(ACCELERATE_INTERPOLATOR);
        ObjectAnimator imgScaleDownXAnim = ObjectAnimator.ofFloat(ivLike, "scaleX", 1f, 0f);
        imgScaleDownXAnim.setDuration(600);
        imgScaleDownXAnim.setInterpolator(ACCELERATE_INTERPOLATOR);
        animatorSet.playTogether(bgScaleYAnim, bgScaleXAnim, bgAlphaAnim, imgScaleUpYAnim,
                imgScaleUpXAnim);
        animatorSet.play(imgScaleDownYAnim).with(imgScaleDownXAnim).after(imgScaleUpYAnim);

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d(TAG, "onAnimationEnd: 3");
                likeAnimationsMap.remove(holder);
                resetLikeAnimationState(holder);
                dispatchChangeFinishedIfAllAnimationsEnded(holder);
            }
        });
        animatorSet.start();
        Log.d(TAG, "animatePhotoLike: 1");
        likeAnimationsMap.put(holder, animatorSet);
        Log.d(TAG, "animatePhotoLike: 2");
    }

    private void dispatchChangeFinishedIfAllAnimationsEnded(BaseViewHolder holder) {
        if (likeAnimationsMap.containsKey(holder) || heartAnimationsMap.containsKey(holder)) {
            return;
        }

        dispatchAnimationFinished(holder);
    }

    private void resetLikeAnimationState(BaseViewHolder holder) {
        holder.setVisible(R.id.v_bg_like, false);
        holder.setVisible(R.id.iv_like, false);
        mStartActivity.startActivity(holder.getAdapterPosition());
    }

    @Override
    public void endAnimation(RecyclerView.ViewHolder item) {
        super.endAnimation(item);
        cancelCurrentAnimationIfExists(item);
    }

    @Override
    public void endAnimations() {
        super.endAnimations();
        for (AnimatorSet animatorSet : likeAnimationsMap.values()) {
            animatorSet.cancel();
        }
    }

    private StartActivity mStartActivity;

    public void setStartActivity(StartActivity startActivity) {
        mStartActivity = startActivity;
    }

    private static class FeedItemHolderInfo extends ItemHolderInfo {
        String updateAction;

        FeedItemHolderInfo(String updateAction) {
            this.updateAction = updateAction;
        }
    }

    public interface StartActivity {
        void startActivity(int position);
    }
}
