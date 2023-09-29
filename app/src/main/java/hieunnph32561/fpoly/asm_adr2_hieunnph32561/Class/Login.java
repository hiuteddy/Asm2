package hieunnph32561.fpoly.asm_adr2_hieunnph32561.Class;

public class Login {
    private String tendangnhap;
    private String username;
    private String password;

    public Login() {
    }

    public Login(String tendangnhap, String username, String password) {
        this.tendangnhap = tendangnhap;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login{" +
                "tendangnhap='" + tendangnhap + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
