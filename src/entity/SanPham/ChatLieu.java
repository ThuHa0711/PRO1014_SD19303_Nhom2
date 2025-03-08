/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.SanPham;

/**
 *
 * @author ADMIN
 */
public class ChatLieu {
    private int IDChatLieu ;
    private String tenChatLieu;

    public ChatLieu() {
    }

    public ChatLieu(int IDChatLieu, String tenChatLieu) {
        this.IDChatLieu = IDChatLieu;
        this.tenChatLieu = tenChatLieu;
    }

    public int getIDChatLieu() {
        return IDChatLieu;
    }

    public void setIDChatLieu(int IDChatLieu) {
        this.IDChatLieu = IDChatLieu;
    }

    public String getTenChatLieu() {
        return tenChatLieu;
    }

    public void setTenChatLieu(String tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
    }
    
    
}
