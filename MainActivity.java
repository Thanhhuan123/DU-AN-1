package com.example.library;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.library.database.DbRoom;
import com.example.library.databinding.ActivityMainBinding;
import com.example.library.model.ThuThu;
import com.example.library.model.UserViewModel;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    AppBarConfiguration mAppBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        setSupportActionBar(binding.toolbar);
        DbRoom db = DbRoom.getInstance(this);
        //Set up toobar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,binding.drawerLayout, binding.toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //welcome user
        View headerView = binding.navigationView.getHeaderView(0);
        ThuThu user = (ThuThu) getIntent().getSerializableExtra("user");
        TextView tvHoTen = headerView.findViewById(R.id.tvHoTen);
        tvHoTen.setText("Welcome "+ user.getHoTen());
        //Set user model
        UserViewModel model = new ViewModelProvider(this).get(UserViewModel.class);
        model.setUser(user);
        //Phân quyền người dùng
        if(!user.getChucVu().equals("admin"))
            binding.navigationView.getMenu().findItem(R.id.nav_ThuThu).setVisible(false);
        //Set title toolbar
        AppBarConfiguration mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_PhieuMuon, R.id.nav_LoaiSach, R.id.nav_Sach,
                R.id.nav_top10,R.id.nav_ThuThu,R.id.nav_ThanhVien,
                R.id.nav_sachIT,R.id.nav_phieuMuon,R.id.nav_phieuMuon2,R.id.nav_ChangePass,R.id.nav_DoanhThu)
                .setOpenableLayout(binding.drawerLayout)
                .build();
        //Set up items navigation  for fragments
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(binding.navigationView, navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(NavController controller, NavDestination destination, Bundle arguments) {
                if (destination.getId() == R.id.nav_LogOut) finish();
            }
        });
    }
    //Tạo button NavigateUp in toolbar
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}