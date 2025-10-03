package com.wallet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.improvementmonitor.R;
import com.wallet.model.SavingEntry;

import java.util.List;

public class SavingAdapter extends RecyclerView.Adapter<SavingAdapter.SavingViewHolder> {
    private Context context;
    private List<SavingEntry> savingList;

    public SavingAdapter(Context context, List<SavingEntry> savingList) {
        this.context = context;
        this.savingList = savingList;
    }

    @NonNull
    @Override
    public SavingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_saving, parent, false);
        return new SavingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SavingViewHolder holder, int position) {
        SavingEntry entry = savingList.get(position);

        holder.amount.setText("৳ " + entry.getAmount());
        holder.note.setText(entry.getNote() != null ? entry.getNote() : "");
        holder.date.setText(entry.getDate());
    }

    @Override
    public int getItemCount() {
        return savingList.size();
    }

    public static class SavingViewHolder extends RecyclerView.ViewHolder {
        TextView amount, note, date;

        public SavingViewHolder(@NonNull View itemView) {
            super(itemView);
            amount = itemView.findViewById(R.id.savingAmount);
            note = itemView.findViewById(R.id.savingNote);
            date = itemView.findViewById(R.id.savingDate);
        }
    }
}
