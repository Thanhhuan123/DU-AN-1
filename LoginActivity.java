package com.example.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.library.database.DbRoom;
import com.example.library.databinding.ActivityLoginBinding;
import com.example.library.model.ThuThu;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);

        DbRoom db = DbRoom.getInstance(this);
        //Get user,pass in SharedPreferences
        SharedPreferences preferences = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        binding.edUserName.getEditText().setText(preferences.getString("USERNAME",""));
        binding.edPassWord.getEditText().setText(preferences.getString("PASSWORD",""));
        binding.chkRememberPass.setChecked(preferences.getBoolean("REMEMBER",false));

        binding.btnLogin.setOnClickListener(view -> {
            String username = binding.edUserName.getEditText().getText().toString();
            String password = binding.edPassWord.getEditText().getText().toString();
            binding.edUserName.setError("");
            binding.edPassWord.setError("");
            if(username.isEmpty()){
                binding.edUserName.setError("Vui lòng nhập tên đăng nhập");
                return;
            }
            if(password.isEmpty()){
                binding.edPassWord.setError("Vui lòng nhập mật khẩu");
                return;
            }
            ThuThu user = db.thuThuDAO().checkLogin(username,password);
            if(user != null){
                //remember
                rememberUser(username,password,binding.chkRememberPass.isChecked());
                //Start intent
                Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }else {
                Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không xác!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void rememberUser(String strUser, String strPass, boolean checked) {
        SharedPreferences preferences = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        if(checked){
            edit.putString("USERNAME",strUser);
            edit.putString("PASSWORD",strPass);
            edit.putBoolean("REMEMBER",true);
        }else{
            edit.clear();
        }
        edit.commit();
    }
}