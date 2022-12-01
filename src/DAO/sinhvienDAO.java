/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.SinhVien;
import Utils.jdbchelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author Thinkpad T490
 */
public class sinhvienDAO extends QuanLySinhVienDAO<SinhVien, String> {

    String insert_sql = " INSERT INTO tblSINH_VIEN (MaSv, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop) VALUES (?, ?, ?, ?, ?, ?) ";
    String update_sql = " UPDATE tblSINH_VIEN SET HoTen = ?, NgaySinh = ?, GioiTinh = ?, DiaChi = ?, MaLop = ? WHERE MaSv = ? ";
    String delete_sql = " DELETE FROM tblSINH_VIEN WHERE MaSv = ? ";
    String selectAll_sql = " SELECT * FROM tblSINH_VIEN ";
    String selectByID_sql = " SELECT * FROM tblSINH_VIEN WHERE MaSv = ? ";

    @Override
    public void insert(SinhVien entity) {
        try {
            jdbchelper.update(insert_sql, entity.getMaSV(), entity.getHoTen(), entity.getNgaySinh(),
                    entity.isGioitinh(), entity.getDiaChi(), entity.getMaLop());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(SinhVien entity) {
        try {
            jdbchelper.update(update_sql,entity.getHoTen(), entity.getNgaySinh(),
                    entity.isGioitinh(), entity.getDiaChi(), entity.getMaLop(), entity.getMaSV() );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String key) {
        try {
            jdbchelper.update(delete_sql, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SinhVien> selectAll() {
        return selectBySql(selectAll_sql);
    }

    @Override
    public SinhVien selectById(String key) {
        List<SinhVien> List = selectBySql(selectByID_sql, key);
        if (List.isEmpty()) {
            return null;
        }
        return List.get(0);
    }

    @Override
    protected List<SinhVien> selectBySql(String sql, Object... args) {
        List<SinhVien> List = new ArrayList<>();
        try {
            ResultSet rs = jdbchelper.query(sql, args);

            while (rs.next()) {
                SinhVien entity = new SinhVien();
                entity.setMaSV(rs.getString("MaSv"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setNgaySinh(rs.getString("NgaySinh"));
                entity.setGioitinh(rs.getBoolean("GioiTinh"));
                entity.setDiaChi(rs.getString("DiaChi"));
                entity.setMaLop(rs.getString("MaLop"));
                List.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return List;
    }
    
    public List<SinhVien> selectByLop(String malop){
        String sql="select * from tblSINH_VIEN where MaLop=? ";
        return this.selectBySql(sql, malop);
    }

}
