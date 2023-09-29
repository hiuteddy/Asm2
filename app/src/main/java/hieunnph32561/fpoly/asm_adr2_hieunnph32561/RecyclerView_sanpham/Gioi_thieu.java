package hieunnph32561.fpoly.asm_adr2_hieunnph32561.RecyclerView_sanpham;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import hieunnph32561.fpoly.asm_adr2_hieunnph32561.Login_register.Dangnhap;
import hieunnph32561.fpoly.asm_adr2_hieunnph32561.R;

public class Gioi_thieu extends AppCompatActivity {
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioi_thieu);
        NavigationView navigationView = findViewById(R.id.navitionn);
        DrawerLayout drawerLayout = findViewById(R.id.drawerlayoutt);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbarrr);
        setSupportActionBar(toolbar);
        //
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                Gioi_thieu.this,
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
                            startActivity(new Intent(Gioi_thieu.this, Dangnhap.class));
                            Toast.makeText(context, "Successfully Logout", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Gioi_thieu.this, MainActivity.class));
                            Toast.makeText(context, " Logout false", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();

                }
                if (item.getItemId() == R.id.qlsp) {
                    startActivity(new Intent(Gioi_thieu.this, MainActivity.class));
                    Toast.makeText(context, "Main", Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId()==R.id.gioithieu){
                    startActivity(new Intent(Gioi_thieu.this, Gioi_thieu.class));
                    Toast.makeText(context, "Introduce", Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId()==R.id.setting){
                    startActivity(new Intent(Gioi_thieu.this, Setting.class));
                    Toast.makeText(context, "Setting", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });
    }
}