/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author Admin
 */
public class GiangVien {
    private String maGV, tenGV, gioiTinh, Phone, Email;
    private boolean Anh;
    public String getMaGV() {
        return maGV;
    }

    public String getTenGV() {
        return tenGV;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public String getPhone() {
        return Phone;
    }

    public String getEmail() {
        return Email;
    }

    public boolean isAnh() {
        return Anh;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public void setTenGV(String tenGV) {
        this.tenGV = tenGV;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setAnh(boolean Anh) {
        this.Anh = Anh;
    }

    public GiangVien(String maGV, String tenGV, String gioiTinh, String Phone, String Email, boolean Anh) {
        this.maGV = maGV;
        this.tenGV = tenGV;
        this.gioiTinh = gioiTinh;
        this.Phone = Phone;
        this.Email = Email;
        this.Anh = Anh;
    }

    public GiangVien() {
    }

   
    
}
