package hieunnph32561.fpoly.asm_adr2_hieunnph32561.Database_sqile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hieunnph32561.fpoly.asm_adr2_hieunnph32561.Class.Login;
import hieunnph32561.fpoly.asm_adr2_hieunnph32561.Class.Sanpham;

public class sanphamDAO {

    private DBhelper dBhelper;

    public sanphamDAO(Context context) {
        dBhelper = new DBhelper(context);
    }

//    public ArrayList<Login> getALLLOGIN() {
//        ArrayList<Login> list = new ArrayList<>();
//        SQLiteDatabase database = dBhelper.getReadableDatabase();
//        Cursor cursor = database.rawQuery("SELECT * FROM sig", null);
//
//        while (cursor.moveToNext()) {
//            Login login = new Login(
//                   cursor.getString(0),
//                    cursor.getString(1),
//                    cursor.getString(2));
//          // login.setTendangnhap(cursor.getString(0));
//            list.add(login);
//        }
//        return list;
//    }


    public ArrayList<Sanpham> getALLSP() {
        ArrayList<Sanpham> list = new ArrayList<>();
        SQLiteDatabase database = dBhelper.getReadableDatabase(); // thêm this.dBhelper
        Cursor cursor = database.rawQuery("SELECT * FROM SanPham", null);

        while (cursor.moveToNext()) {
            Sanpham sanpham = new Sanpham(
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getInt(3));
            sanpham.setMasp(cursor.getInt(0));
            list.add(sanpham);
        }
        cursor.close(); // đóng con trỏ khi hoàn thành công việc
        return list;
    }
    public long addsig(Login login){
        SQLiteDatabase database=dBhelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("TENDANGKY",login.getTendangnhap());
        values.put("USERNAME",login.getUsername());
        values.put("PASSWORD",login.getPassword());
        return database.insert("sig",null,values);
    }

    public long addsp(Sanpham sanpham){
        SQLiteDatabase  database=dBhelper.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("TENSP",sanpham.getTensp());
        values.put("GIABAN",sanpham.getGiaban());
        values.put("SOLUONG",sanpham.getSoluong());
        return database.insert("SanPham",null,values);
    }


    public long updatesp(Sanpham sanpham){
        SQLiteDatabase  database=dBhelper.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("TENSP",sanpham.getTensp());
        values.put("GIABAN",sanpham.getGiaban());
        values.put("SOLUONG",sanpham.getSoluong());
        return database.update("SanPham",values,"MASP=?",new String[]{
                String.valueOf(sanpham.getMasp())
        });


    }
    public long delete(int mssp){
        SQLiteDatabase database=dBhelper.getWritableDatabase();
        long check=database.delete("SanPham","MASP=?",new String[]{
                String.valueOf(mssp)
        });
        return  check;
    }
public boolean checklogin(String username, String password) {

    SQLiteDatabase database = dBhelper.getReadableDatabase();
    // Tạo câu truy vấn SQL để lấy dữ liệu từ bảng 'sig' với 'USERNAME' và 'PASSWORD' đã cung cấp
    String sql = "SELECT * FROM sig WHERE USERNAME = ? AND PASSWORD = ?";
    Cursor cs = database.rawQuery(sql, new String[]{username, password});
    // Đếm số lượng dòng trong Cursor
    int count = cs.getCount();
    // Đóng Cursor
    cs.close();

    // Trả về true nếu có ít nhất một dòng được trả về từ cơ sở dữ liệu, ngược lại trả về false
    return (count > 0);

}
    public boolean checkIfEmailExists(String email) {
        // đọc dữ liệu
        SQLiteDatabase db = this.dBhelper.getReadableDatabase();
        String query = "SELECT * FROM sig" + " WHERE USERNAME = ?";
        // Thực thi câu truy vấn và trả về đối tượng Cursor
        Cursor cursor = db.rawQuery(query, new String[]{email});
        // Kiểm tra xem có ít nhất một dòng được trả về từ cơ sở dữ liệu hay không
        boolean exists = cursor.moveToFirst();
        cursor.close();
        // Trả về true nếu có ít nhất một dòng được trả về từ cơ sở dữ liệu, ngược lại trả về false
        return exists;
    }

}
