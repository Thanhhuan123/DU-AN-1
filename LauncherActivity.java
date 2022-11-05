package com.example.library;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.library.database.DbRoom;
import com.example.library.model.ThuThu;

public class LauncherActivity extends AppCompatActivity {
    Handler handler;
    ProgressBar p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        DbRoom db = DbRoom.getInstance(this);
        db.thuThuDAO().insertTT(new ThuThu("huandtph28593","Dinh Thanh Huan","123","admin"));
        p = findViewById(R.id.progressBar);
        p.setMax(100);
        p.setProgress(0);

        //thread chạy tiến trình
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (p.getProgress()<=p.getMax()){
                    try{
                        Thread.sleep(150);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    p.incrementProgressBy(5);
                }
            }
        }).start();
        //handler delay 3s next activity
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LauncherActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        }, 3000);
    }
}