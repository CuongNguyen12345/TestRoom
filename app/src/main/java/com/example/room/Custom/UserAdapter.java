package com.example.room.Custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.room.Entity.UserEntity;
import com.example.room.R;

import java.util.List;

public class UserAdapter extends BaseAdapter {

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

        txtName.setText(entity.getName());
        txtAddress.setText(entity.getAddress());

        return row;
    }


}
