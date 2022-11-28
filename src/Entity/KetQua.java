/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author Admin
 */
public class KetQua {
    private String maSV, hoTen, maMon, hanhKiem, ghiChu;
    private float diemTB, diemTongKet;
    private int hocKi;

    public String getMaSV() {
        return maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getMaMon() {
        return maMon;
    }

    public String getHanhKiem() {
        return hanhKiem;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public float getDiemTB() {
        return diemTB;
    }

    public float getDiemTongKet() {
        return diemTongKet;
    }

    public int getHocKi() {
        return hocKi;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public void setHanhKiem(String hanhKiem) {
        this.hanhKiem = hanhKiem;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public void setDiemTB(float diemTB) {
        this.diemTB = diemTB;
    }

    public void setDiemTongKet(float diemTongKet) {
        this.diemTongKet = diemTongKet;
    }

    public void setHocKi(int hocKi) {
        this.hocKi = hocKi;
    }

    public KetQua(String maSV, String hoTen, String maMon, String hanhKiem, String ghiChu, float diemTB, float diemTongKet, int hocKi) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.maMon = maMon;
        this.hanhKiem = hanhKiem;
        this.ghiChu = ghiChu;
        this.diemTB = diemTB;
        this.diemTongKet = diemTongKet;
        this.hocKi = hocKi;
    }

    public KetQua() {
    }
    
}
