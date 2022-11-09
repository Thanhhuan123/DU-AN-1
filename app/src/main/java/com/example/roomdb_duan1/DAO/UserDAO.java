package com.example.roomdb_duan1.DAO;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;



import com.example.roomdb_duan1.Model.user;

import java.util.List;

public interface UserDAO {
    @Query("select * from user")
    List<user> getAll();
    @Insert
    void insert(user user);
    @Update
    void update(user user);
    @Delete
    void delete(user user);
}
