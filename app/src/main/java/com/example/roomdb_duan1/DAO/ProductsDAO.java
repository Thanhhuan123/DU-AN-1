package com.example.roomdb_duan1.DAO;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;




import com.example.roomdb_duan1.Model.products;


import java.util.List;

public interface ProductsDAO {
    @Query("select * from products")
    List<products> getAll();
    @Insert
    void insert(products products);
    @Update
    void update(products products);
    @Delete
    void delete(products products);
}
