package com.example.room;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.room.Database.AppDatabase;
import com.example.room.Entity.UserEntity;
import com.example.room.Utils.DataUtils;

public class UpdateActivity extends AppCompatActivity {


    private EditText edtNameUpdate, edtAddressUpadte;
    private Button btnUpdateUser, btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);

        edtAddressUpadte = findViewById(R.id.edtAddressUpdate);
        edtNameUpdate = findViewById(R.id.edtNameUpdate);
        btnUpdateUser = findViewById(R.id.btnUpdateUser);
        btnReturn = findViewById(R.id.btnReturn);

        UserEntity entity = (UserEntity) getIntent().getSerializableExtra("object_user");
        edtNameUpdate.setText(entity.getName());
        edtAddressUpadte.setText(entity.getAddress());

        btnUpdateUser.setOnClickListener(v -> {
            String name = edtNameUpdate.getText().toString().trim();
            String address = edtAddressUpadte.getText().toString().trim();
            if (!DataUtils.checkData(name) || !DataUtils.checkData(address)) {
                return;
            }
            entity.setName(name);
            entity.setAddress(address);
            clickUpdateUser(entity);

        });

        btnReturn.setOnClickListener(v -> {
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void clickUpdateUser(UserEntity entity) {
        AppDatabase.getInstance(this).userDao().updateUser(entity);
        Toast.makeText(this, "User is updated successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}