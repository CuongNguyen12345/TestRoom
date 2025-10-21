package com.example.room;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.room.Database.AppDatabase;
import com.example.room.Entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edtName, edtAddress;
    private Button btnAdd;
    private ListView listView;
    private ArrayAdapter<UserEntity> adapter;
    private List<UserEntity> list = new ArrayList<>();

    private void initUI() {
        edtName = findViewById(R.id.edtName);
        edtAddress = findViewById(R.id.edtAddress);
        btnAdd = findViewById(R.id.btnAdd);
        listView = findViewById(R.id.listView);

        list = AppDatabase.getInstance(this).userDao().getAllUsers();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initUI();
        btnAdd.setOnClickListener(v -> {
            String name = edtName.getText().toString();
            String address = edtAddress.getText().toString();

            UserEntity entity = new UserEntity(name, address);
            AppDatabase.getInstance(this).userDao().insertUser(entity);
            list = AppDatabase.getInstance(this).userDao().getAllUsers();
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
            listView.setAdapter(adapter);
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}