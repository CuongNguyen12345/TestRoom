package com.example.room.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "user")
public class UserEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String address;

    public UserEntity() {}
    public UserEntity(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @NonNull
    @Override
    public String toString() {
        return id + " " + name + " " + address;
    }
}
