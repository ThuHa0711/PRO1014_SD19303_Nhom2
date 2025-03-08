
package entity.SanPham;
/**
 *
 * @author ADMIN
 */
public class TheLoai {
    private int IDTheLoai;
    private String tenTheloai;

    public TheLoai() {
    }

    public TheLoai(int IDTheLoai, String tenTheloai) {
        this.IDTheLoai = IDTheLoai;
        this.tenTheloai = tenTheloai;
    }

    public int getIDTheLoai() {
        return IDTheLoai;
    }

    public void setIDTheLoai(int IDTheLoai) {
        this.IDTheLoai = IDTheLoai;
    }

    public String getTenTheloai() {
        return tenTheloai;
    }

    public void setTenTheloai(String tenTheloai) {
        this.tenTheloai = tenTheloai;
    }
    
    
}
