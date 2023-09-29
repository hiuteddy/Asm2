package hieunnph32561.fpoly.asm_adr2_hieunnph32561.RecyclerView_sanpham;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Locale;

import hieunnph32561.fpoly.asm_adr2_hieunnph32561.Login_register.Dangnhap;
import hieunnph32561.fpoly.asm_adr2_hieunnph32561.R;

public class Setting extends AppCompatActivity {
    Context context = this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button btnEnglish = findViewById(R.id.button_english);
        Button btnVietnamese = findViewById(R.id.button_vietnamese);

// Lấy ngôn ngữ hiện tại và thiết lập cho các nút
        String currentLanguage = LanguageHelper.getLanguage(this);
        if (currentLanguage.equals("en")) {
            btnEnglish.setEnabled(false);
            btnVietnamese.setEnabled(true);
        } else {
            btnEnglish.setEnabled(true);
            btnVietnamese.setEnabled(false);
        }

        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lưu ngôn ngữ mới là "en" vào SharedPreferences
                LanguageHelper.setLanguage(Setting.this, "en");
                // Khởi động lại hoạt động Setting
                Intent intent = new Intent(Setting.this, Setting.class);
                finish();
                startActivity(intent);

                // Cập nhật ngôn ngữ sau khi hoạt động Setting khởi động lại
                String currentLanguage = LanguageHelper.getLanguage(Setting.this);
                LanguageHelper.updateLanguage(Setting.this, currentLanguage);
            }
        });

        btnVietnamese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lưu ngôn ngữ mới là "vi" vào SharedPreferences
                LanguageHelper.setLanguage(Setting.this, "vi");
                // Khởi động lại hoạt động Dangnhap
                Intent intent = new Intent(Setting.this, Setting.class);
                finish();
                startActivity(intent);

                // Cập nhật ngôn ngữ sau khi hoạt động Dangnhap khởi động lại
                String currentLanguage = LanguageHelper.getLanguage(Setting.this);
                LanguageHelper.updateLanguage(Setting.this, currentLanguage);
            }
        });


    // Phương thức khởi động lại hoạt động Setting


    //... Tiếp tục phần code của bạn ở đây


        NavigationView navigationView = findViewById(R.id.navitionnn);
        DrawerLayout drawerLayout = findViewById(R.id.drawerlayouttt);
        Toolbar toolbar = findViewById(R.id.toolbarrrr);
        setSupportActionBar(toolbar);
        //
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                Setting.this,
                drawerLayout,
                toolbar,
                R.string.open,
                R.string.close

        );
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.loguot) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Xác nhận thoát ứng dụng");
                    builder.setMessage("Bạn có chắc chắn muốn thoát ứng dụng này k này?");
                    builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Setting.this, Dangnhap.class));
                            Toast.makeText(context, "Successfully Logout", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Setting.this, Setting.class));
                            Toast.makeText(context, " Logout false", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();

                }
                if (item.getItemId() == R.id.qlsp) {
                    startActivity(new Intent(Setting.this, MainActivity.class));
                    Toast.makeText(context, "Main", Toast.LENGTH_SHORT).show();
                }
                if (item.getItemId() == R.id.gioithieu) {
                    startActivity(new Intent(Setting.this, Gioi_thieu.class));
                    Toast.makeText(context, "Introduce", Toast.LENGTH_SHORT).show();
                }
                if (item.getItemId() == R.id.setting) {
                    startActivity(new Intent(Setting.this, Setting.class));
                    Toast.makeText(context, "Setting", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });
    }


}
