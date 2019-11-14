package com.example.recyclerviewtest;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder> {
    private static List<Integer> arra = new ArrayList<>();
    private int lastOpenItem = 0;
    private static int mHiddenViewMeasuredHeight;
    private View mLastOpenItemView;

    //自定义ViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        TextView textView, textView2;
        ImageView imageView;

        public MyViewHolder(@NonNull View view) {
            super(view);
            itemView = view;
            textView = view.findViewById(R.id.item_tv);
            textView2 = view.findViewById(R.id.text);
            imageView = view.findViewById(R.id.item_iv);


        }
    }

    private List<Data> datas;

    //构造器
    public TestAdapter(List<Data> datas) {
        this.datas = datas;

    }

    //将item封装为一个ViewHolder
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mHiddenViewMeasuredHeight = (int) parent.getContext().getResources().getDimension(R.dimen.dp_40);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rc_item, parent, false);
        return new MyViewHolder(view);
    }

    //适配渲染数据到View中
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        arra.add(position);
        holder.imageView.setImageResource(R.drawable.ic_launcher_background);
        Log.e("TestAdapter---->", String.valueOf(position));
        holder.textView.setText(String.valueOf(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "onclick!!!" + position, Toast.LENGTH_SHORT).show();
                    if (arra.get(position) >= 0) {
                        if (mLastOpenItemView!=null){
                            animClose(mLastOpenItemView);
                        }
                        animOpen(holder.textView2);
                        // holder.textView2.setVisibility(View.VISIBLE);
                        //  notifyItemChanged(position);
                        mLastOpenItemView = holder.textView2;
                        arra.set(position, -arra.get(position));
                    } else {
                        animClose(holder.textView2);
                        //holder.textView2.setVisibility(View.GONE);
                        //  notifyItemChanged(position);
                        arra.set(position, -arra.get(position));
                    }
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    private void animOpen(View view) {
        view.setVisibility(View.VISIBLE);
        view.setAlpha(0);
        createDropAnim(view, 0, mHiddenViewMeasuredHeight).start();
    }

    private void animClose(final View view) {
        int origHeight = view.getHeight();
        view.setAlpha(1);
        ValueAnimator valueAnimator;
        valueAnimator = createDropAnim(view, origHeight, 0);
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });
        valueAnimator.start();
    }


    //动画
    private ValueAnimator createDropAnim(final View view, int start, int end) {
        final ValueAnimator va = ValueAnimator.ofInt(start, end);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();//根据时间因子的变化系数进行设置高度
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;

                float alpha = ((float) value) / mHiddenViewMeasuredHeight;
                view.setAlpha(alpha);

                view.setLayoutParams(layoutParams);//设置高度
            }
        });
        return va;
    }

}
