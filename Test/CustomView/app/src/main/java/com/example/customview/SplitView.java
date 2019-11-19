package com.example.customview;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class SplitView extends View {
    Paint mPaint;

    public SplitView(Context context) {
        super(context);
    }

    public SplitView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SplitView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



}
