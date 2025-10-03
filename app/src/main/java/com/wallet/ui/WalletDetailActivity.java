package com.wallet.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.improvementmonitor.R;
import com.wallet.adapter.SavingAdapter;
import com.wallet.data.DBHelper;
import com.wallet.model.SavingEntry;
import com.wallet.model.Wallet;

import java.util.List;

public class WalletDetailActivity extends AppCompatActivity {

    private TextView tvName, tvTarget, tvSaved;
    private RecyclerView recyclerView;
    private Button btnAddSaving;
    private DBHelper dbHelper;
    private SavingAdapter adapter;
    private int walletId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_detail);

        tvName = findViewById(R.id.tvWalletName);
        tvTarget = findViewById(R.id.tvWalletTarget);
        tvSaved = findViewById(R.id.tvWalletSaved);
        recyclerView = findViewById(R.id.recyclerSavings);
        btnAddSaving = findViewById(R.id.btnAddSaving);
        dbHelper = new DBHelper(this);

        walletId = getIntent().getIntExtra("wallet_id", -1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnAddSaving.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddSavingActivity.class);
            intent.putExtra("wallet_id", walletId);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadWalletDetails();
        loadSavings();
    }

    private void loadWalletDetails() {
        Wallet wallet = dbHelper.getWalletById(walletId);
        if (wallet != null) {
            tvName.setText(wallet.getName());
            tvTarget.setText("Target: ৳ " + wallet.getTargetAmount());
            tvSaved.setText("Saved: ৳ " + wallet.getSavedAmount());
        }
    }

    private void loadSavings() {
        List<SavingEntry> savingList = dbHelper.getSavingsForWallet(walletId);
        adapter = new SavingAdapter(this, savingList);
        recyclerView.setAdapter(adapter);
    }
}
