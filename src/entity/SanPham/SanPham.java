/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.SanPham;

/**
 *
 * @author ADMIN
 */
public class SanPham {
    private int id;
    private String maSp;
    private String tenSp;
    private String anh;
    private String moTa;
    private String theLoai;
    private String chatLieu;
    private String mauSac;
    private String kichCo;
    private Integer soLuong;
    private float donGia;
    private int idTheLoai;
    private int idChatLieu;
    private int idMauSac;
    private int idKichCo;

    public SanPham() {
    }

    public SanPham(int id, String maSp, String tenSp, String anh, String moTa, String theLoai, String chatLieu, String mauSac, String kichCo, int soLuong, float donGia, int idTheLoai, int idChatLieu, int idMauSac, int idKichCo) {
        this.id = id;
        this.maSp = maSp;
        this.tenSp = tenSp;
        this.anh = anh;
        this.moTa = moTa;
        this.theLoai = theLoai;
        this.chatLieu = chatLieu;
        this.mauSac = mauSac;
        this.kichCo = kichCo;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.idTheLoai = idTheLoai;
        this.idChatLieu = idChatLieu;
        this.idMauSac = idMauSac;
        this.idKichCo = idKichCo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaSp() {
        return maSp;
    }

    public void setMaSp(String maSp) {
        this.maSp = maSp;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getKichCo() {
        return kichCo;
    }

    public void setKichCo(String kichCo) {
        this.kichCo = kichCo;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public int getIdTheLoai() {
        return idTheLoai;
    }

    public void setIdTheLoai(int idTheLoai) {
        this.idTheLoai = idTheLoai;
    }

    public int getIdChatLieu() {
        return idChatLieu;
    }

    public void setIdChatLieu(int idChatLieu) {
        this.idChatLieu = idChatLieu;
    }

    public int getIdMauSac() {
        return idMauSac;
    }

    public void setIdMauSac(int idMauSac) {
        this.idMauSac = idMauSac;
    }

    public int getIdKichCo() {
        return idKichCo;
    }

    public void setIdKichCo(int idKichCo) {
        this.idKichCo = idKichCo;
    }
    
    public String getTrangthai(){
        if (soLuong > 0) {
            return "còn hàng";
        }else if(soLuong == 0){
            return "Hết hàng";
        }else{
            return "Số ko thực";
        }
                
    }
    
}
