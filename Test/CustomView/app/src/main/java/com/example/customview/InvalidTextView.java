package com.example.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;


public class InvalidTextView extends AppCompatTextView {


    public InvalidTextView(Context context) {
        super(context);
        initDraw();
    }

    public InvalidTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDraw();
    }

    public InvalidTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDraw();
    }

    //anti_aliasing 保真
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    void initDraw() {
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth((float) 1.5);//stroke-> 笔画
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        canvas.drawLine(0,height/2,width,0,mPaint);

    }

}
