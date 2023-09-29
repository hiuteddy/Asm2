package hieunnph32561.fpoly.asm_adr2_hieunnph32561.Login_register;

import static hieunnph32561.fpoly.asm_adr2_hieunnph32561.RecyclerView_sanpham.ConfigNotifcation.CHANNEL_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import hieunnph32561.fpoly.asm_adr2_hieunnph32561.Class.Login;
import hieunnph32561.fpoly.asm_adr2_hieunnph32561.Database_sqile.sanphamDAO;
import hieunnph32561.fpoly.asm_adr2_hieunnph32561.R;
import hieunnph32561.fpoly.asm_adr2_hieunnph32561.RecyclerView_sanpham.MainActivity;

public class Dangnhap extends AppCompatActivity {

    EditText txtuserdangnhap, txtpswdangnhap;
    Button btndangnhap;
    sanphamDAO spdao;
    TextView textView,txtgachchan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        txtuserdangnhap = findViewById(R.id.edtdangnhapuser);
        txtpswdangnhap = findViewById(R.id.edtdangnhappasw);
        btndangnhap = findViewById(R.id.btndangnhap);
        spdao = new sanphamDAO(this);
        txtgachchan=findViewById(R.id.txtgach);
        textView = findViewById(R.id.myTextView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dangnhap.this, Dangky.class);
                startActivity(intent);
            }
        });
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtus = txtuserdangnhap.getText().toString();
                String txtpsw = txtpswdangnhap.getText().toString();

                if (TextUtils.isEmpty(txtus) || TextUtils.isEmpty(txtpsw)) {
                    Toast.makeText(Dangnhap.this, "K được để trống thông tin ", Toast.LENGTH_SHORT).show();
                    return;
                }
                Boolean login = spdao.checklogin(txtus, txtpsw);
                if (login) {
                    // Hiển thị thông báo "Đăng nhập thành công"
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    // Chuyển đến màn hình chính
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
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
                    // Hiển thị thông báo "Tên đăng nhập hoặc mật khẩu không đúng"
                    Toast.makeText(getApplicationContext(), "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
        txtgachchan.setPaintFlags(txtgachchan.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
}


