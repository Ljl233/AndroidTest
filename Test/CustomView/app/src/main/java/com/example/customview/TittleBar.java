package com.example.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TittleBar extends RelativeLayout {

    private TextView mTvTitle;
    private ImageView mIvLeft, mIvRight;
    private RelativeLayout mTitleLayout;
    private int mColor, mTextColor;
    private String mTitleName;


    public TittleBar(Context context) {
        super(context);
        Log.e("constructor--->", "1");
        initView(context);
    }


    public TittleBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        //解析自定义属性
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        mColor = mTypedArray.getColor(R.styleable.TitleBar_title_bg, Color.RED);
        mTextColor = mTypedArray.getColor(R.styleable.TitleBar_title_text_color, Color.BLACK);
        mTitleName = mTypedArray.getString(R.styleable.TitleBar_title_text);
        //回收哦
        mTypedArray.recycle();


        Log.e("constructor--->", "2");
        initView(context);
    }

    public TittleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e("constructor--->", "3");
        initView(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    private void initView(Context context) {
        //加载布局
        LayoutInflater.from(context).inflate(R.layout.titlebar_layout, this, true);

        mIvLeft = findViewById(R.id.title_left);
        mIvRight = findViewById(R.id.title_right);
        mTvTitle = findViewById(R.id.title_title);
        mTitleLayout = findViewById(R.id.title_layout);

        //设置背景颜色
        mTitleLayout.setBackgroundColor(mColor);
        mTvTitle.setText(mTitleName);
        //设置字体颜色
        mTvTitle.setTextColor(mTextColor);
    }

    //三个方法供外部调用 设置标题，设置左右的监听器
    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            mTvTitle.setText(title);
        }
    }

    public void setLeftListener(OnClickListener onClickListener) {
        mIvLeft.setOnClickListener(onClickListener);
    }

    public void setRightListener(OnClickListener onClickListener) {
        mIvRight.setOnClickListener(onClickListener);
    }
}
