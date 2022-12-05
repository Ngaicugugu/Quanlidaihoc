
package Entity;

/**
 *
 * @author Admin
 */
public class SinhVien {
    private String maSV, hoTen, ngaySinh, diaChi, maLop;
    private boolean gioitinh;

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }
//
//    public Boolean isGioitinh() {
//        return gioitinh;
//    }
//
//    public void setGioitinh(Boolean gioitinh) {
//        this.gioitinh = gioitinh;
//    }

    public boolean isGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public SinhVien() {
    }

    public SinhVien(String maSV, String hoTen, String ngaySinh, String diaChi, String maLop, Boolean gioitinh) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.maLop = maLop;
        this.gioitinh = gioitinh;
    }

  

  
    
}
