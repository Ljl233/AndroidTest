package com.example.recyclerviewtest;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//如果item style 只有一种样式，泛型直接传自定义的viewHolder，如果多个style 必须是RecyclerView.ViewHolder,然后进行类型的强制转换
public class StyleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //根据不同的ViewHolder填充不同的布局
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    //将ViewHolder与对应位置的数据绑定
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    //因为传参是position，可以得到position对应的数据，根据数据决定使用什么样的viewHolder
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    //创建两个不同的ViewHolder
    public class VH extends RecyclerView.ViewHolder {

        public VH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
