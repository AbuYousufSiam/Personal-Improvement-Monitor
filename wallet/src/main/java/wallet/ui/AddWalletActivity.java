package wallet.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import wallet.data.DBHelper;

public class AddWalletActivity extends AppCompatActivity {

    private EditText etName, etTargetAmount;
    private Button btnSave;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wallet);

        etName = findViewById(R.id.etWalletName);
        etTargetAmount = findViewById(R.id.etWalletTarget);
        btnSave = findViewById(R.id.btnSaveWallet);
        dbHelper = new DBHelper(this);

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String targetStr = etTargetAmount.getText().toString().trim();

            if (name.isEmpty() || targetStr.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            double targetAmount = Double.parseDouble(targetStr);
            long id = dbHelper.addWallet(name, targetAmount);

            if (id > 0) {
                Toast.makeText(this, "Wallet created!", Toast.LENGTH_SHORT).show();
                finish(); // go back to list
            } else {
                Toast.makeText(this, "Failed to create wallet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
