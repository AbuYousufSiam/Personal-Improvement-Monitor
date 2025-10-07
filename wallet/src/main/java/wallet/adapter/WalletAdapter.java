package wallet.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import wallet.model.Wallet;
import wallet.ui.WalletDetailActivity;

import java.util.List;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.WalletViewHolder> {
    private Context context;
    private List<Wallet> walletList;

    public WalletAdapter(Context context, List<Wallet> walletList) {
        this.context = context;
        this.walletList = walletList;
    }

    @NonNull
    @Override
    public WalletViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_wallet, parent, false);
        return new WalletViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WalletViewHolder holder, int position) {
        Wallet wallet = walletList.get(position);

        holder.name.setText(wallet.getName());
        holder.target.setText("Target: " + wallet.getTargetAmount());
        holder.saved.setText("Saved: " + wallet.getSavedAmount());

        // On wallet click → open details screen
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, WalletDetailActivity.class);
            intent.putExtra("wallet_id", wallet.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return walletList.size();
    }

    public static class WalletViewHolder extends RecyclerView.ViewHolder {
        TextView name, target, saved;

        public WalletViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.walletName);
            target = itemView.findViewById(R.id.walletTarget);
            saved = itemView.findViewById(R.id.walletSaved);
        }
    }
}
