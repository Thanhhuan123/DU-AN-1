package com.example.roomdb_duan1.DataBase;

import android.content.Context;

import com.example.roomdb_duan1.DAO.CategoryDAO;
import com.example.roomdb_duan1.DAO.OrderDAO;
import com.example.roomdb_duan1.DAO.OrderDetailDAO;
import com.example.roomdb_duan1.DAO.ProductsDAO;
import com.example.roomdb_duan1.DAO.UserDAO;
import com.example.roomdb_duan1.Model.categorys;
import com.example.roomdb_duan1.Model.order;
import com.example.roomdb_duan1.Model.order_detail;
import com.example.roomdb_duan1.Model.products;
import com.example.roomdb_duan1.Model.user;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {categorys.class, order.class, products.class, order_detail.class, user.class}, exportSchema = false , version = 2)
public abstract class DbRoom extends RoomDatabase {
    private static final String DB_NAME = "DA1";
    private static DbRoom instance;
    public static synchronized DbRoom getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),DbRoom.class,DB_NAME)
                    .allowMainThreadQueries().build();
        }
        return instance;
    }
    public abstract ProductsDAO productsDAO();
    public abstract UserDAO userDAO();
    public abstract OrderDAO orderDAO();
    public abstract OrderDetailDAO orderDetailDAO();
    public abstract CategoryDAO categoryDAO();
}
