package com.example.room.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.room.Entity.UserEntity;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUser(UserEntity entity);

    @Query("SELECT * FROM user")
    List<UserEntity> getAllUsers();
}
