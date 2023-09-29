package hieunnph32561.fpoly.asm_adr2_hieunnph32561.Database_sqile;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import hieunnph32561.fpoly.asm_adr2_hieunnph32561.Class.Sanpham;
import hieunnph32561.fpoly.asm_adr2_hieunnph32561.R;

public class DBhelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ASM";
    private static final int DATABASE_VERSION = 1;

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTABLEsig = "CREATE TABLE sig(TENDANGKY TEXT PRIMARY KEY ," +
                "USERNAME TEXT," +
                "PASSWORD TEXT)";
        db.execSQL(createTABLEsig);

        String qsp = "CREATE TABLE SanPham(MASP INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TENSP TEXT NOT NULL," +
                "GIABAN DOUBLE NOT NULL," +
                "SOLUONG INTEGER NOT NULL)";
        db.execSQL(qsp);

        String qqsp = "INSERT INTO SanPham (TENSP, GIABAN, SOLUONG) VALUES" +
                " ('Bánh quy bơ LU Pháp', 45.000, 10)," +
                " ('Snack mực lăn muối ớt', 8.000, 52), " +
                "('Snack khoai tây Lays', 12.000, 38)," +
                " ('Bánh gạo One One', 30.000, 11), " +
                "('Kẹo sữa sô cô la', 25.000, 30)";
        db.execSQL(qqsp);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa bảng cũ nếu tồn tại
        String dropTABLEsig = "DROP TABLE IF EXISTS sig";
        db.execSQL(dropTABLEsig);

        String qsp = "DROP TABLE IF EXISTS SanPham";
        db.execSQL(qsp);
        onCreate(db);
        // Tạo lại bảng và thêm dữ liệu mẫu vào bảng

    }
}
