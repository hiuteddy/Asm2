package hieunnph32561.fpoly.asm_adr2_hieunnph32561.Login_register;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hieunnph32561.fpoly.asm_adr2_hieunnph32561.Class.Login;
import hieunnph32561.fpoly.asm_adr2_hieunnph32561.Database_sqile.sanphamDAO;
import hieunnph32561.fpoly.asm_adr2_hieunnph32561.R;
import hieunnph32561.fpoly.asm_adr2_hieunnph32561.RecyclerView_sanpham.LanguageHelper;

public class Dangky extends AppCompatActivity {

    EditText txtdangkyemail, txtdangkymatkhau,txtdkfullname;
    Button btndangky,btnbackkk;
    sanphamDAO spDAO;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        txtdkfullname=findViewById(R.id.fullname);
        txtdangkyemail = findViewById(R.id.edtdangkyusername);
        txtdangkymatkhau = findViewById(R.id.edtdangkypsw);

        btndangky = findViewById(R.id.btndangky);
        btnbackkk=findViewById(R.id.btnback);
        spDAO = new sanphamDAO(this);
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtdkemail = txtdangkyemail.getText().toString();
                String txtdkmatkhau = txtdangkymatkhau.getText().toString();
                String txtfull = txtdkfullname.getText().toString();
                if (TextUtils.isEmpty(txtdkemail) || TextUtils.isEmpty(txtdkmatkhau)|| TextUtils.isEmpty(txtfull)) {
                    Toast.makeText(Dangky.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isValidEmail(txtdkemail)) {
                    Toast.makeText(Dangky.this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (spDAO.checkIfEmailExists(txtdkemail)) {
                    Toast.makeText(Dangky.this, "Địa chỉ email đã được đăng ký", Toast.LENGTH_SHORT).show();
                    return;
                };
                if (txtdkmatkhau.length() < 8) {
                    Toast.makeText(Dangky.this, "Mật khẩu phải có ít nhất 8 ký tự", Toast.LENGTH_SHORT).show();
                    return;
                }
                Pattern uppercase = Pattern.compile("[A-Z]");
                Matcher matcher = uppercase.matcher(txtdkmatkhau);
                if (!matcher.find()) {
                    Toast.makeText(Dangky.this, "Mật khẩu phải chứa ít nhất một chữ cái viết hoa", Toast.LENGTH_SHORT).show();
                    return;
                }
                Pattern specialChar = Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]");
                Matcher matcherr = specialChar.matcher(txtdkmatkhau);
                if (!matcherr.find()) {
                    Toast.makeText(Dangky.this, "Mật khẩu phải chứa ít nhất một ký tự đặc biệt", Toast.LENGTH_SHORT).show();
                    return;
                }
                Login login = new Login(txtfull, txtdkemail, txtdkmatkhau);
                if (spDAO.addsig(login) > 0) {
                    Toast.makeText(Dangky.this, "Đăng Ký Thành Công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Dangky.this, Dangnhap.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Dangky.this, "Tên đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();
                }
            }

            private boolean isValidEmail(String email) {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                return email.matches(emailPattern);
            }
        });


        btnbackkk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnbackkk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       finish(); // kết thúc activity hiện tại và trở về activity trước đó
                       // Intent intent = new Intent(Dangky.this, Dangnhap.class);
                      //  startActivity(intent);
                    }
                });
            }
        });
    }
}