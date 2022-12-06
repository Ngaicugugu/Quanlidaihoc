/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author Admin
 */
public class Mon {
    private String maMon, tenMon, maGV, hocKi, maKhoa;
    private int soTiet;
    
    @Override
    public String toString() {
        return this.maMon;
    }

    @Override
    public boolean equals(Object obj) {
        Mon other = (Mon) obj;
        return other.getMaKhoa().equals(this.getMaMon());
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public String getHocKi() {
        return hocKi;
    }

    public void setHocKi(String hocKi) {
        this.hocKi = hocKi;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public int getSoTiet() {
        return soTiet;
    }

    public void setSoTiet(int soTiet) {
        this.soTiet = soTiet;
    }

    public Mon(String maMon, String tenMon, String maGV, String hocKi, String maKhoa, int soTiet) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.maGV = maGV;
        this.hocKi = hocKi;
        this.maKhoa = maKhoa;
        this.soTiet = soTiet;
    }

    public Mon() {
    }
    
}
