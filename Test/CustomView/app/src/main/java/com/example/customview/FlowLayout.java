package com.example.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class FlowLayout extends ViewGroup {

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //度量子view大小
        int childCount=getChildCount();


        //度量viewGroup的大小

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
