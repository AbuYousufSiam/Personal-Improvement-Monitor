package com.wallet.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.improvementmonitor.R;
import com.wallet.data.DBHelper;

public class AddSavingActivity extends AppCompatActivity {

    private EditText etAmount, etNote;
    private Button btnSave;
    private DBHelper dbHelper;
    private int walletId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_saving);

        etAmount = findViewById(R.id.etSavingAmount);
        etNote = findViewById(R.id.etSavingNote);
        btnSave = findViewById(R.id.btnSaveSaving);
        dbHelper = new DBHelper(this);

        // Receive wallet id from WalletDetailActivity
        walletId = getIntent().getIntExtra("wallet_id", -1);

        btnSave.setOnClickListener(v -> {
            String amountStr = etAmount.getText().toString().trim();
            String note = etNote.getText().toString().trim();

            if (amountStr.isEmpty()) {
                Toast.makeText(this, "Please enter an amount", Toast.LENGTH_SHORT).show();
                return;
            }

            double amount = Double.parseDouble(amountStr);
            long id = dbHelper.addSaving(walletId, amount, note);

            if (id > 0) {
                Toast.makeText(this, "Saving added!", Toast.LENGTH_SHORT).show();
                finish(); // go back to wallet detail
            } else {
                Toast.makeText(this, "Failed to add saving", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
