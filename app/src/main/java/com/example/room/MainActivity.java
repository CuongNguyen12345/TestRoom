package com.example.room;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.room.Custom.UserAdapter;
import com.example.room.Database.AppDatabase;
import com.example.room.Entity.UserEntity;
import com.example.room.Utils.DataUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edtName, edtAddress;
    private Button btnAdd, btnUpdate, btnDelete;
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

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void addUser(String name, String address) {
        if(!DataUtils.checkData(name) || !DataUtils.checkData(address)) {
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
        adapter = new UserAdapter(this, list, new UserAdapter.OnUserItemClick() {
            @Override
            public void onUpdateUser(UserEntity user) {
                clickUpdateUser(user);
            }

            @Override
            public void onDeleteUser(UserEntity user) {
                clickDeleteUser(user);
            }
        });
        listView.setAdapter(adapter);
    }

    private void clickUpdateUser(UserEntity user) {
        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_user", user);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void clickDeleteUser(UserEntity user) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Confirm delete user")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AppDatabase.getInstance(MainActivity.this).userDao().deleteUser(user);
                        Toast.makeText(MainActivity.this, "Delete user successfully", Toast.LENGTH_SHORT).show();
                        loadData();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void initUI() {
        edtName = findViewById(R.id.edtNameUpdate);
        edtAddress = findViewById(R.id.edtAddressUpdate);
        btnAdd = findViewById(R.id.btnUpdateUser);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        listView = findViewById(R.id.listView);
    }
}