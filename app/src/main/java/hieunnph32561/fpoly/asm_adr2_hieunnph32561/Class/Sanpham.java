package hieunnph32561.fpoly.asm_adr2_hieunnph32561.Class;

public class Sanpham {
    private int masp;
    private String tensp;
    private Double giaban;
    private int soluong;

    public Sanpham() {
    }

    public Sanpham(String tensp, double giaban, int soluong) {
        this.masp=masp;
        this.tensp = tensp;
        this.giaban = giaban;
        this.soluong = soluong;
    }


    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public double getGiaban() {
        return giaban;
    }

    public void setGiaban(double giaban) {
        this.giaban = giaban;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
