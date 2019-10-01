//package com.example.recyclerviewtest;
//
//import android.animation.Animator;
//import android.animation.AnimatorListenerAdapter;
//import android.animation.ValueAnimator;
//import android.view.View;
//import android.view.ViewGroup;
//
//public class ViewHolder {
//    ViewGroup mExpandableView;
//    ValueAnimator mCloseValueAnimator,mOpenValueAnimator;
//if(mExpandableView.getVisibility()==View.VISIBLE)
//
//    {
//        animClose(mExpandableView);
//    } else{
//        animOpen(mExpandableView);
//    }
//
//    private void animOpen(final View view) {
//        view.setVisibility(View.VISIBLE);
//        view.setAlpha(0);
//        if (mOpenValueAnimator == null) {
//            mOpenValueAnimator = createDropAnim(view, 0, mHiddenViewMeasuredHeight);
//        }
//        mOpenValueAnimator.start();
//    }
//
//    private void animClose(final View view) {
//        int origHeight = view.getHeight();
//        view.setAlpha(1);
//        if (mCloseValueAnimator == null) {
//            mCloseValueAnimator = createDropAnim(view, origHeight, 0);
//            mCloseValueAnimator.addListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    view.setVisibility(View.GONE);
//                }
//            });
//        }
//        mCloseValueAnimator.start();
//    }
//
//    private ValueAnimator createDropAnim(final View view, int start, int end) {
//        final ValueAnimator va = ValueAnimator.ofInt(start, end);
//        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                int value = (int) animation.getAnimatedValue();//根据时间因子的变化系数进行设置高度
//                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
//                layoutParams.height = value;
//
//                float alpha = ((float) value) / mHiddenViewMeasuredHeight;
//                view.setAlpha(alpha);
//
//                view.setLayoutParams(layoutParams);//设置高度
//            }
//        });
//        return va;
//    }
//
//}
