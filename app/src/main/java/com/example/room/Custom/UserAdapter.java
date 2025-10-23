package com.example.room.Custom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.room.Entity.UserEntity;
import com.example.room.MainActivity;
import com.example.room.R;
import com.example.room.UpdateActivity;

import java.util.List;

public class UserAdapter extends BaseAdapter {

    private static final int MY_REQUEST_CODE = 1;
    private Context context;
    private List<UserEntity> lstUser;

    public UserAdapter(Context context, List<UserEntity> lstUser) {
        this.context = context;
        this.lstUser = lstUser;
    }

    @Override
    public int getCount() {
        return lstUser.size();
    }

    @Override
    public Object getItem(int i) {
        return lstUser.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        if(row == null) {
            row = LayoutInflater.from(context).inflate(R.layout.listitem, viewGroup, false);
        }

        UserEntity entity = lstUser.get(i);

        TextView txtName = row.findViewById(R.id.txtName);
        TextView txtAddress = row.findViewById(R.id.txtAddress);
        Button btnUpdate = row.findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(v -> {
            Intent intent = new Intent(context, UpdateActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("object_user", entity);
            intent.putExtras(bundle);
            context.startActivity(intent);
        });

        txtName.setText(entity.getName());
        txtAddress.setText(entity.getAddress());

        return row;
    }


}
