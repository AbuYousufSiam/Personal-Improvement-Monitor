package com.wallet.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.improvementmonitor.R;
import com.wallet.adapter.WalletAdapter;
import com.wallet.data.DBHelper;
import com.wallet.model.Wallet;

import java.util.List;

public class WalletListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btnAddWallet;
    private DBHelper dbHelper;
    private WalletAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_list);

        recyclerView = findViewById(R.id.recyclerWallets);
        btnAddWallet = findViewById(R.id.btnAddWallet);
        dbHelper = new DBHelper(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnAddWallet.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddWalletActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadWallets();
    }

    private void loadWallets() {
        List<Wallet> walletList = dbHelper.getAllWallets();
        adapter = new WalletAdapter(this, walletList);
        recyclerView.setAdapter(adapter);
    }
}
