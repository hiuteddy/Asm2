package hieunnph32561.fpoly.asm_adr2_hieunnph32561.RecyclerView_sanpham;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hieunnph32561.fpoly.asm_adr2_hieunnph32561.Class.Sanpham;
import hieunnph32561.fpoly.asm_adr2_hieunnph32561.Database_sqile.sanphamDAO;
import hieunnph32561.fpoly.asm_adr2_hieunnph32561.R;

public class adapter_sanpham extends RecyclerView.Adapter<adapter_sanpham.ViewHodelsanpham> {
    private Context context;
    private ArrayList<Sanpham> list;
    sanphamDAO sanphamDAOO;

    public adapter_sanpham(Context context, ArrayList<Sanpham> list, sanphamDAO sanphamDAOO) {
        this.context = context;
        this.list = list;
        this.sanphamDAOO = sanphamDAOO;
    }

    @NonNull
    @Override
    public ViewHodelsanpham onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).
                inflate(R.layout.item_ddl, parent, false);
        return new ViewHodelsanpham(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodelsanpham holder, @SuppressLint("RecyclerView") int i) {
        holder.txttsp.setText(list.get(i).getTensp());
        holder.txtgsp.setText(String.valueOf(list.get(i).getGiaban()) + "" + "VND" + "--");
        holder.txtsl.setText("SL:" + String.valueOf(list.get(i).getSoluong()));


        holder.txtdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // lấy vị trí của một item trong danh sách sản phẩm được hiển thị bởi một RecyclerView.Adapter.
                int position = holder.getAdapterPosition();
                // Lấy đối tượng khóa học tương ứng
                Sanpham sp = list.get(position);
                // Xây dựng hộp thoại xác nhận xóa
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa sản phẩm này?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xóa khóa học khỏi cơ sở dữ liệu
                        sanphamDAOO = new sanphamDAO(context);
                        long result = sanphamDAOO.delete(sp.getMasp());
                        if (result > 0) {
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            list.remove(position); // Xóa đối tượng khóa học khỏi danh sách
                            notifyDataSetChanged(); // Cập nhật lại dữ liệu trên RecyclerView
                        } else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hủy thao tác xóa
                        dialog.dismiss();
                    }
                });

                // Hiển thị hộp thoại xác nhận xóa
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        holder.txtupdatee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(context);
            }
            // Khởi tạo AlertDialog.Builder với context của ứng dụng

            public void update(Context context) {


                // Khởi tạo AlertDialog.Builder với context của ứng dụng
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
                // Tạo đối tượng LayoutInflater để chuyển đổi tệp layout XML thành View
                LayoutInflater inflater = LayoutInflater.from(context);
                // Chuyển đổi tệp layout XML thành View
                View view = inflater.inflate(R.layout.item_update, null);
                // Đặt View làm nội dung của hộp thoại thông báo
                builder.setView(view);

                Button btnudt = view.findViewById(R.id.btnudtsubmit);
                Button btncanner = view.findViewById(R.id.btnudtcanner);


                EditText txttsp = view.findViewById(R.id.udttensp);
                EditText txtgsp = view.findViewById(R.id.udtgiasp);
                EditText txtslsp = view.findViewById(R.id.udtsl);


                // Hiển thị hộp thoại thông báo

                androidx.appcompat.app.AlertDialog dialog = builder.create();

                // Không cho phép dialog bị huỷ bằng cách bấm nút "back"
                dialog.setCancelable(false);

                // Hiển thị dialog
                dialog.show();

                // Hiển thị thông tin sản phẩm cần cập nhật trên layout
                txttsp.setText(list.get(i).getTensp());
                txtgsp.setText(String.valueOf(list.get(i).getGiaban()));
                txtslsp.setText(String.valueOf(list.get(i).getSoluong()));

                btncanner.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Đóng dialog
                        dialog.dismiss();
                    }
                });

                // Xử lý sự kiện khi người dùng bấm nút "Cập nhật"
                btnudt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //  Lấy thông tin sản phẩm mới từ các EditText trong layout
                        String tsp = txttsp.getText().toString();
                        String giaban = txtgsp.getText().toString();
                        String sl = txtslsp.getText().toString();

                        // Kiểm tra tính hợp lệ của thông tin sản phẩm mới
                        if (tsp.isEmpty() || giaban.isEmpty() || sl.isEmpty()) {
                            Toast.makeText(context, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        int giabanInt = 0, sla = 0;

                        try {
                            giabanInt = (int) Double.parseDouble(giaban);
                            sla = Integer.parseInt(sl);
                        } catch (NumberFormatException e) {
                            Toast.makeText(context, "Giá bán và số lượng phải là số nguyên", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (sla <= 0) {
                            Toast.makeText(context, "Giá bán và số lượng phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // Lấy vị trí của sản phẩm cần cập nhật trong danh sách
                        int position = holder.getAdapterPosition();

                        // Lấy đối tượng Sanpham tương ứng với vị trí trên
                        Sanpham sp = list.get(position);

                        // Lấy mã sản phẩm
                        int masp = sp.getMasp();

                        // Khởi tạo đối tượng sanphamDAO để thao tác với cơ sở dữ liệu
                        sanphamDAOO = new sanphamDAO(context);

                        // Tạo đối tượng Sanpham mới để cập nhật thông tin vào cơ sở dữ liệu
                        Sanpham sanpham = new Sanpham();
                        sanpham.setMasp(masp);
                        sanpham.setTensp(tsp);
                        sanpham.setGiaban(giabanInt);
                        sanpham.setSoluong(sla);

                        // Thực hiện cập nhật thông tin sản phẩm vào cơ sở dữ liệu
                        sanphamDAOO.updatesp(sanpham);

                        // Cập nhật lại đối tượng Sanpham trong danh sách
                        list.set(position,sanpham);

                        // Thông báo cho Adapter biết rằng đã có sự thay đổi trong danh sách
                        notifyItemChanged(position);

                       Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHodelsanpham extends RecyclerView.ViewHolder {
        TextView txttsp, txtgsp, txtsl;
        TextView txtupdatee, txtdelete;

        public ViewHodelsanpham(@NonNull View itemView) {
            super(itemView);
            txttsp = itemView.findViewById(R.id.txttensp);
            txtgsp = itemView.findViewById(R.id.txtgiasp);
            txtsl = itemView.findViewById(R.id.txtsoluong);
            txtupdatee = itemView.findViewById(R.id.textupdate);
            txtdelete = itemView.findViewById(R.id.textdelete);
            txtdelete.setPaintFlags(txtdelete.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            txtupdatee.setPaintFlags(txtupdatee.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        }
    }

}
