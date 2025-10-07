package wallet.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import wallet.model.SavingEntry;
import wallet.ui.SavingDetailActivity;

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

        // Open SavingDetailActivity on item click
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, SavingDetailActivity.class);
            intent.putExtra("saving_id", entry.getId());
            context.startActivity(intent);
        });
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
