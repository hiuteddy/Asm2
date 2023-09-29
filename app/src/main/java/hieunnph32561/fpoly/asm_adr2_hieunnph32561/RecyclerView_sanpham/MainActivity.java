package hieunnph32561.fpoly.asm_adr2_hieunnph32561.RecyclerView_sanpham;

import static hieunnph32561.fpoly.asm_adr2_hieunnph32561.RecyclerView_sanpham.ConfigNotifcation.CHANNEL_ID;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Date;

import hieunnph32561.fpoly.asm_adr2_hieunnph32561.Class.Sanpham;
import hieunnph32561.fpoly.asm_adr2_hieunnph32561.Database_sqile.sanphamDAO;
import hieunnph32561.fpoly.asm_adr2_hieunnph32561.Login_register.Dangnhap;
import hieunnph32561.fpoly.asm_adr2_hieunnph32561.R;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    ArrayList<Sanpham> list = new ArrayList<>();

    adapter_sanpham adapter;
    sanphamDAO spdao;
    Context context = this;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.rclview);
        spdao = new sanphamDAO(context);
        list = spdao.getALLSP();
        adapter = new adapter_sanpham(context, list, spdao);


        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

//drawframword
        NavigationView navigationView = findViewById(R.id.navition);
        DrawerLayout drawerLayout = findViewById(R.id.drawerlayout);
        //  recyclerView = findViewById(R.id.rclview);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);
        //
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                MainActivity.this,
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
                            startActivity(new Intent(MainActivity.this, Dangnhap.class));
                            Toast.makeText(context, "Successfully Logout", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(MainActivity.this, MainActivity.class));
                            Toast.makeText(context, " Logout false", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();

                }
                if (item.getItemId() == R.id.qlsp) {
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                    Toast.makeText(context, "Main", Toast.LENGTH_SHORT).show();
                }
                if (item.getItemId() == R.id.gioithieu) {
                    startActivity(new Intent(MainActivity.this, Gioi_thieu.class));
                    Toast.makeText(context, "Introduce", Toast.LENGTH_SHORT).show();
                }
                if (item.getItemId() == R.id.setting) {
                    startActivity(new Intent(MainActivity.this, Setting.class));
                    Toast.makeText(context, "Setting", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });


        //
        ImageView btnShow = (ImageView) findViewById(R.id.imwadd);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });

    }

    //thêm
    public void add() {
        EditText txttsp, txtgiaban, txtsoluong;
        Button btnlogin, btncaner;
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_add, null);

        txttsp = view.findViewById(R.id.edttensp);
        txtgiaban = view.findViewById(R.id.edtgiasp);
        txtsoluong = view.findViewById(R.id.edtsl);
        btnlogin = view.findViewById(R.id.btnaddsubmit);
        btncaner = view.findViewById(R.id.btncanner);
        builder.setView(view);
        androidx.appcompat.app.AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
        btncaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tsp = txttsp.getText().toString();
                String giaban = txtgiaban.getText().toString();
                String sl = txtsoluong.getText().toString();

                if (tsp.isEmpty() || giaban.isEmpty() || sl.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                int giabanInt, sla;

                try {
                    giabanInt = (int) Double.parseDouble(giaban);
                    sla = Integer.parseInt(sl);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Giá bán và số lượng phải là số nguyên", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (giabanInt <= 0 || sla <= 0) {
                    Toast.makeText(MainActivity.this, "Giá bán và số lượng phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                    return;
                }

                Sanpham sanpham = new Sanpham(tsp, giabanInt, sla);

                if (spdao.addsp(sanpham) > 0) {
                    list = spdao.getALLSP();
                    adapter = new adapter_sanpham(context, list, spdao);
                    LinearLayoutManager linearLayoutManager =
                            new LinearLayoutManager(context);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapter);
                    Toast.makeText(MainActivity.this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                    NotificationCompat.Builder builder = new NotificationCompat.
                            Builder(getApplicationContext(), CHANNEL_ID)
                            .setSmallIcon(R.drawable.baseline_notifications_24)
                            .setContentTitle("Thông báo")
                            .setContentText("Bạn đã đăng nhập thành công");
                    NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplicationContext());
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.POST_NOTIFICATIONS) ==
                            PackageManager.PERMISSION_GRANTED) {
                        managerCompat.notify((int) new Date().getTime(), builder.build());

                    }
                } else {
                    Toast.makeText(MainActivity.this, "Thêm sản phẩm không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}



