package com.example.customview;

import android.content.Context;
import android.icu.util.Measure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Scroller;

import androidx.viewpager.widget.ViewPager;

public class HorizontalView extends ViewGroup {
    int lastX, lastY, interceptX = 0, interceptY = 0;
    int currentIndex = 0;
    int mChildWidth = 0;
    Scroller mScroller;
    private VelocityTracker mTracker;

    public HorizontalView(Context context) {
        super(context);
        init();
    }

    public HorizontalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public HorizontalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    void init() {
        mScroller = new Scroller(getContext());
        mTracker = VelocityTracker.obtain();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);//default spec
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        mChildWidth = widthSize;
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        if (getChildCount() == 0) {

            setMeasuredDimension(0, 0);

        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {

            View childOne = getChildAt(0);
            int childWidth = childOne.getMeasuredWidth();//getMeasureWidth() getWidth()
            int childHeight = childOne.getMeasuredHeight();

            setMeasuredDimension(childWidth * getChildCount(), childHeight);//???

        } else if (widthMode == MeasureSpec.AT_MOST) {

            int childWidth = getChildAt(0).getMeasuredWidth();
            setMeasuredDimension(childWidth * getChildCount(), heightSize);

        } else if (heightMode == MeasureSpec.AT_MOST) {

            int childHeight = getChildAt(0).getMeasuredHeight();
            setMeasuredDimension(widthSize, childHeight * getChildCount());

        }
        //todo: margin and padding
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int left = 0;
        View child;
        for (int i = 0; i < childCount; i++) {
            child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                int childWidth = child.getMeasuredWidth();
                child.layout(left, 0, left + childWidth, child.getMeasuredHeight());
                left += childWidth;
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        Log.e("TAG", "intercept x:" + x + "");

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }

                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - interceptX;
                int deltaY = y - interceptY;
                Log.e("TAG:", "OnIntercept");

                if (Math.abs(deltaX) >= Math.abs(deltaY)) {
                    intercept = true;
                    Log.e("TAG:", "OnIntercept");
                } else {
                    intercept = false;
                    Log.e("TAG", "intercept=false");
                }

                break;
            case MotionEvent.ACTION_UP:
                Log.e("TAG", "intercept up");
                intercept = false;
                break;
        }

        lastX = x;
        lastY = y;
        interceptX = x;
        interceptY = y;

        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mTracker.addMovement(event);
        int x = (int) event.getX();
        int y = (int) event.getY();
        Log.e("TAG", "touch x:" + x + "");

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                Log.e("TAG", "down");
                break;
            case MotionEvent.ACTION_MOVE:

                int deltaX = x - lastX;
                Log.e("TAG", deltaX + "");
                scrollBy(-deltaX, 0);

                break;
            case MotionEvent.ACTION_UP:
                Log.e("TAG", "touch up");

                int distance = getScrollX() - mChildWidth * currentIndex;
                if (Math.abs(distance) > mChildWidth / 2) {
                    if (distance > 0) {
                        currentIndex++;
                    } else {
                        currentIndex--;
                    }
                    Log.e("TAG", "up->slide");
                } else {
                    mTracker.computeCurrentVelocity(1000);
                    float xV = mTracker.getXVelocity();
                    if (Math.abs(xV) > 50) {
                        if (xV > 0) {
                            currentIndex--;
                        } else {
                            currentIndex++;
                        }
                    }
                }
                currentIndex = currentIndex < 0 ? 0 :
                        currentIndex > getChildCount() - 1 ? getChildCount() - 1 : currentIndex;

                Log.e("TAG", (currentIndex + 1) * mChildWidth + "(currentIndex + 1) * childWidth");
                smoothScrollTo((currentIndex) * mChildWidth, 0);
                mTracker.clear();

                break;
            default:
                Log.e("TAG", "default");
                break;
        }
        lastY = y;
        lastX = x;
        return true;//???
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    private void smoothScrollTo(int destX, int destY) {
        mScroller.startScroll(getScrollX(), getScrollY(),
                destX - getScrollX(), destY - getScrollY(), 1000);
        Log.e("TAG", "Smooth");
        invalidate();
    }


}