package com.example.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;


public class CustomView extends View {
    private int lastX, lastY;
    private Scroller mScroller;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            //getCurrX():   The new X offset as an absolute distance from the origin.
            ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            //使无效。。。 将旧的view从主UI线程队列中pop掉，刷新view
            invalidate();
        }
    }

    public void smoothScrollTo(int destX, int destY) {
        int scrollX = getScrollX();
        Log.e("tag", scrollX + "");
        int delta = destX - scrollX;
        mScroller.startScroll(scrollX, 0, delta, 0, 2000);
        invalidate();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //get the coordinates of the current point you touch
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            //a pressed gesture has started
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                Log.e("TAG-down", String.valueOf(lastX) + "::::" + String.valueOf(lastY));

                break;
            case MotionEvent.ACTION_MOVE:
                //计算移动的距离
                int offsetX = x - lastX;
                int offsetY = y - lastY;

                //用layout方法重新进行布局
                //layout方法是直接改变view的坐标
//                layout(getLeft() + offsetX,
//                        getTop() + offsetY,
//                        getRight() + offsetX,
//                        getBottom() + offsetY);

                //进行偏移
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);
//使用layoutParams进行布局
                //constraintLayout 不能使用layoutparams进行配置应该使用constraintset
//                ViewGroup.MarginLayoutParams layoutParams =
//                        (ViewGroup.MarginLayoutParams) getLayoutParams();
//
//                layoutParams.leftMargin = getLeft() + offsetX;
//                layoutParams.topMargin = getTop() + offsetY;


                ((View) getParent()).scrollBy(-offsetX, -offsetY);
                Log.e("TAG", "当前X-->" + String.valueOf(x));
                Log.e("TAG", "lastX-->" + String.valueOf(lastX));
                Log.e("TAG", "offsetX-->" + String.valueOf(offsetX));
                Log.e("TAG", "getLeft()-->" + String.valueOf(getLeft()));

                Log.e("TAG", "layoutParams.leftMargin-->" + String.valueOf(getLeft() + offsetX));
//                setLayoutParams(layoutParams);
                break;

        }
        return true;

    }
}
