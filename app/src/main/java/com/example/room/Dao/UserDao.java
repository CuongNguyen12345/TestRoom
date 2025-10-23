package com.example.room.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.room.Entity.UserEntity;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUser(UserEntity entity);

    @Query("SELECT * FROM user")
    List<UserEntity> getAllUsers();

    @Query("SELECT * FROM user WHERE name = :userName")
    List<UserEntity> findUserByName(String userName);

    @Update
    void updateUser(UserEntity entity);

    @Delete
    void deleteUser(UserEntity entity);
}
