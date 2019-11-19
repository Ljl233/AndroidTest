package com.example.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class RectView extends View {


    public RectView(Context context) {
        super(context);
        initDraw();
    }

    public RectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //设置画笔抗锯齿
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mColor = Color.RED;


    private void initDraw() {
        mPaint.setColor(mColor);
        mPaint.setStrokeWidth((float) 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingButton = getPaddingBottom();
        int paddingTop = getPaddingTop();
        Log.e("left--->", String.valueOf(paddingLeft));
        Log.e("right--->", String.valueOf(paddingRight));
        Log.e("button--->", String.valueOf(paddingButton));
        Log.e("top--->", String.valueOf(paddingTop));


        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingButton;

        canvas.drawRect(paddingLeft, paddingTop, width + paddingLeft,
                height + paddingTop, mPaint);
    }
}
