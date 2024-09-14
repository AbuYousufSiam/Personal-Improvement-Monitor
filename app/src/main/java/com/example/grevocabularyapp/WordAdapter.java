package com.example.grevocabularyapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    private final List<Word> wordList;

    // Constructor for the adapter
    public WordAdapter(List<Word> wordList) {
        this.wordList = wordList;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_item, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        // Bind the data to the views
        Word currentWord = wordList.get(position);
        holder.wordTextView.setText(currentWord.getWord());
        holder.definitionTextView.setText(currentWord.getDefinition());
    }

    @Override
    public int getItemCount() {
        return wordList.size(); // Return the total number of items in the list
    }

    // ViewHolder class to hold individual list item views
    public static class WordViewHolder extends RecyclerView.ViewHolder {
        TextView wordTextView;
        TextView definitionTextView;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            wordTextView = itemView.findViewById(R.id.wordTextView);
            definitionTextView = itemView.findViewById(R.id.definitionTextView);
        }
    }
}
