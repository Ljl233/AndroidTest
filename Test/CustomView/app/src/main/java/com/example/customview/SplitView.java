package com.example.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class SplitView extends View {
    TextPaint mTPDate = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    TextPaint mTPTime = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    Paint mPCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mPLine = new Paint(Paint.ANTI_ALIAS_FLAG);

    float mDateSize, mTimeSize, mCircleRadius;
    String mDateColor, mTimeColor;
    String mTextDate = "", mTextTime = "";

    public SplitView(Context context) {
        super(context);
        init();
    }

    public SplitView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.SplitView);

        mTextDate = mTypedArray.getString(R.styleable.SplitView_date_text);
        mTextTime = mTypedArray.getString(R.styleable.SplitView_time_text);
        mDateColor = mTypedArray.getString(R.styleable.SplitView_data_color);
        mTimeColor = mTypedArray.getString(R.styleable.SplitView_time_color);
        mDateSize = mTypedArray.getDimensionPixelSize(R.styleable.SplitView_date_size, 40);
        mTimeSize = mTypedArray.getDimensionPixelSize(R.styleable.SplitView_time_size, 40);
        mCircleRadius = mTypedArray.getFloat(R.styleable.SplitView_circle_radius, 100);

        mTypedArray.recycle();
        init();

    }

    public SplitView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {
        mPCircle.setColor(Color.BLUE);
        mPCircle.setStrokeWidth(20);
        mPCircle.setStyle(Paint.Style.STROKE);

        mPLine.setColor(Color.GRAY);

        mTPDate.setColor(Color.BLUE);
        mTPDate.setTextSize(mDateSize);

        mTPTime.setColor(Color.GRAY);
        mTPTime.setTextSize(mTimeSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(300, 300);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(300, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, 300);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int height = getHeight();
        int width = getWidth();
        int radius = (int) mCircleRadius;

        //圆心
        int cx = width / 24 + radius;
        int cy = height / 2;

        int mLineLength = (int) (width * 0.7);


        //文字的宽度
        int mDTWidth = (int) mTPDate.measureText(mTextDate);
        int mTimeWidth = (int) mTPDate.measureText(mTextTime);

        //文字的y轴
        Paint.FontMetrics fontMetrics = mTPDate.getFontMetrics();
        int y = (int) (height / 2 + (Math.abs(fontMetrics.ascent) - fontMetrics.descent) / 2);


        canvas.drawCircle(cx, cy, radius, mPCircle);
        canvas.drawLine(cx + radius, cy, mLineLength + cx, cy, mPLine);

        canvas.drawText(mTextDate, cx - mDTWidth / 2, y, mTPDate);
        canvas.drawText(mTextTime, width - mTimeWidth, y, mTPTime);


    }


    public float getDateSize() {
        return mDateSize;
    }

    public float getTimeSize() {
        return mTimeSize;
    }

    public float getCircleRadius() {
        return mCircleRadius;
    }

    public String getDateColor() {
        return mDateColor;
    }

    public String getTimeColor() {
        return mTimeColor;
    }

    public String getTextDate() {
        return mTextDate;
    }

    public String getTextTime() {
        return mTextTime;
    }

    public void setDateSize(float mDateSize) {
        this.mDateSize = mDateSize;
    }

    public void setTimeSize(float mTimeSize) {
        this.mTimeSize = mTimeSize;
    }

    public void setCircleRadius(float mCircleRadius) {
        this.mCircleRadius = mCircleRadius;
    }

    public void setDateColor(String mDateColor) {
        this.mDateColor = mDateColor;
    }

    public void setTimeColor(String mTimeColor) {
        this.mTimeColor = mTimeColor;
    }

    public void setTextDate(String mTextDate) {
        this.mTextDate = mTextDate;
    }

    public void setTextTime(String mTextTime) {
        this.mTextTime = mTextTime;
    }


}
