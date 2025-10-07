package wallet.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.improvementmonitor.R;
import wallet.data.DBHelper;
import wallet.model.SavingEntry;

public class SavingDetailActivity extends AppCompatActivity {

    private TextView tvAmount, tvNote, tvDate;
    private Button btnUpdate, btnDelete;
    private DBHelper dbHelper;
    private int savingId;
    private SavingEntry savingEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_detail);

        // Set dialog-style appearance
        setFinishOnTouchOutside(true);

        tvAmount = findViewById(R.id.tvAmountDetail);
        tvNote = findViewById(R.id.tvNoteDetail);
        tvDate = findViewById(R.id.tvDateDetail);
        btnUpdate = findViewById(R.id.btnUpdateSaving);
        btnDelete = findViewById(R.id.btnDeleteSaving);

        dbHelper = new DBHelper(this);
        savingId = getIntent().getIntExtra("saving_id", -1);

        if (savingId != -1) {
            loadSavingDetails();
        } else {
            Toast.makeText(this, "Error: Invalid Saving Entry", Toast.LENGTH_SHORT).show();
            finish();
        }

        btnDelete.setOnClickListener(v -> confirmDelete());
        btnUpdate.setOnClickListener(v -> showUpdateDialog());
    }

    private void loadSavingDetails() {
        savingEntry = dbHelper.getSavingById(savingId);
        if (savingEntry != null) {
            tvAmount.setText("৳ " + savingEntry.getAmount());
            tvNote.setText("Note: " + (savingEntry.getNote() == null ? "No note" : savingEntry.getNote()));
            tvDate.setText("Date: " + savingEntry.getDate());
        }
    }

    private void confirmDelete() {
        new AlertDialog.Builder(this)
                .setTitle("Delete Saving")
                .setMessage("Are you sure you want to delete this saving?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    dbHelper.deleteSaving(savingId);
                    Toast.makeText(this, "Saving deleted", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void showUpdateDialog() {
        final EditText etAmount = new EditText(this);
        etAmount.setHint("New amount");
        etAmount.setText(String.valueOf(savingEntry.getAmount()));

        final EditText etNote = new EditText(this);
        etNote.setHint("New note");
        etNote.setText(savingEntry.getNote());

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 40, 50, 10);
        layout.addView(etAmount);
        layout.addView(etNote);

        new AlertDialog.Builder(this)
                .setTitle("Update Saving")
                .setView(layout)
                .setPositiveButton("Save", (dialog, which) -> {
                    try {
                        double newAmount = Double.parseDouble(etAmount.getText().toString());
                        String newNote = etNote.getText().toString();
                        dbHelper.updateSaving(savingId, newAmount, newNote);
                        Toast.makeText(this, "Saving updated", Toast.LENGTH_SHORT).show();
                        loadSavingDetails(); // refresh
                    } catch (NumberFormatException e) {
                        Toast.makeText(this, "Invalid amount", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
