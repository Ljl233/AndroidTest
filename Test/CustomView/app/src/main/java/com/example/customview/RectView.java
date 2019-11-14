package com.example.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class RectView extends View {
    public RectView(Context context) {
        super(context);
        initDraw();
    }

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mColor = Color.RED;

    private void initDraw() {
        mPaint.setColor(mColor);
        mPaint.setStrokeWidth((float) 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        canvas.drawRect();
    }
}
