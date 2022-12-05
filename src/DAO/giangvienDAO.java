/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.GiangVien;
import Utils.jdbchelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author Thinkpad T490
 */
public class giangvienDAO extends QuanLySinhVienDAO<GiangVien, String> {

    String insert_sql = " INSERT INTO tblGIANG_VIEN (MaGV,TenGV,GioiTinh,Phone,Email,Anh) VALUES (?,?,?,?,?,?) ";
    String update_sql = " UPDATE tblGIANG_VIEN SET TenGV  = ?,GioiTinh=?,Phone=?,Email=?, Anh=? WHERE MaGV  = ? ";
    String delete_sql = " DELETE FROM tblGIANG_VIEN WHERE MaGV = ? ";
    String selectAll_sql = " SELECT * FROM tblGIANG_VIEN ";
    String selectByID_sql = " SELECT * FROM tblGIANG_VIEN WHERE MaGV = ? ";

    @Override
    public void insert(GiangVien entity) {
        try {
            jdbchelper.update(insert_sql, entity.getMaGV(), entity.getTenGV(), entity.isGioitinh(), entity.getPhone(),
                     entity.getEmail(), entity.getAnh());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GiangVien entity) {
        try {
            jdbchelper.update(update_sql, entity.getTenGV(), entity.isGioitinh(), entity.getPhone(),
                     entity.getEmail(), entity.getAnh(), entity.getMaGV());
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
    public List<GiangVien> selectAll() {
        return selectBySql(selectAll_sql);
    }

    @Override
    public GiangVien selectById(String key) {
        List<GiangVien> List = selectBySql(selectByID_sql, key);
        if (List.isEmpty()) {
            return null;
        }
        return List.get(0);
    }

    @Override
    protected List<GiangVien> selectBySql(String sql, Object... args) {
        List<GiangVien> List = new ArrayList<>();
        try {
            ResultSet rs = jdbchelper.query(sql, args);
            while (rs.next()) {
                GiangVien entity = new GiangVien();
                entity.setMaGV(rs.getString("MaGV"));
                entity.setTenGV(rs.getString("TenGV"));
                entity.setGioitinh(rs.getBoolean("GioiTinh"));
                entity.setPhone(rs.getString("Phone"));
                entity.setEmail(rs.getString("Email"));
                entity.setAnh(rs.getString("Anh"));
                List.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return List;
    }

}
