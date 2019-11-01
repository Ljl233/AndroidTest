package com.example.roomdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdemo.data.Word;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LayoutInflater mInflater;
    private List<Word> words;

    WordListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        holder.textView.setText(words.get(position).getWord());
    }

    void setWords(List<Word> words) {
        this.words = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (words != null) {
            return words.size();
        }
        return 0;
    }


    class WordViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }

    }
}
