package com.example.roomdb_duan1.DAO;

import com.example.roomdb_duan1.Model.categorys;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CategoryDAO {
      @Query("select * from categorys")
      List<categorys> getAll();
      @Insert
      void insertTL(categorys categorys);
      @Update
      void updateTL(categorys categorys);
      @Delete
      void deleteTL(categorys categorys);
      @Query("select *from categorys where LoaiSach.maLoai = :maLoai limit 1")
      LoaiSach getCategoryByCode(int maLoai);
      @Query("select *from categorys where LoaiSach.tenLoai = :tenLoai limit 1")
      LoaiSach getCategoryByName(String tenLoai);
  }
  }
}
