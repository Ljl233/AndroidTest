package com.example.recyclerviewtest;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder> {
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        TextView textView;
        ImageView imageView;

        public MyViewHolder(@NonNull View view) {
            super(view);
            itemView = view;
            textView = view.findViewById(R.id.item_tv);
            imageView = view.findViewById(R.id.item_iv);

        }
    }
}
