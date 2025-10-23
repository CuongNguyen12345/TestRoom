package com.example.room;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.room.Custom.UserAdapter;
import com.example.room.Database.AppDatabase;
import com.example.room.Entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edtName, edtAddress;
    private Button btnAdd, btnUpdate;
    private ListView listView;
    private UserAdapter adapter;
    private List<UserEntity> list = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initUI();
        loadData();
        btnAdd.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String address = edtAddress.getText().toString().trim();
            addUser(name, address);
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addUser(String name, String address) {
        if(name == null || address == null) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", LENGTH_SHORT).show();
            return;
        }
        if(isUserExist(name)) {
            Toast.makeText(this, "Người dùng đã tồn tại", LENGTH_SHORT).show();
            return;
        }
        UserEntity entity = new UserEntity(name, address);
        AppDatabase.getInstance(this).userDao().insertUser(entity);
        loadData();
        edtName.setText("");
        edtAddress.setText("");
    }
    private boolean isUserExist(String userName) {
        List<UserEntity> list = AppDatabase.getInstance(this).userDao().findUserByName(userName);
        return list != null && !list.isEmpty();
    }

    private void loadData() {
        list = AppDatabase.getInstance(this).userDao().getAllUsers();
        adapter = new UserAdapter(this, list);
        listView.setAdapter(adapter);
    }
    private void initUI() {
        edtName = findViewById(R.id.edtNameUpdate);
        edtAddress = findViewById(R.id.edtAddressUpdate);
        btnAdd = findViewById(R.id.btnUpdateUser);
        btnUpdate = findViewById(R.id.btnUpdate);
        listView = findViewById(R.id.listView);
    }
}