/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.KetQua;
import Utils.jdbchelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class timdiemDAO extends QuanLySinhVienDAO<KetQua, String>{
    
    String selectAll_sql = " SELECT * FROM tblKET_QUA ";
    String selectByID_sql = " SELECT * FROM tblKET_QUA WHERE MaSV = ? ";
    
    @Override
    public void insert(KetQua entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(KetQua entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<KetQua> selectAll() {
        return selectBySql(selectAll_sql);
    }

    @Override
    public KetQua selectById(String key) {
        List<KetQua> List = selectBySql(selectByID_sql, key);
        if (List.isEmpty()) {
            return null;
        }
        return List.get(0);
    }

    @Override
    protected List<KetQua> selectBySql(String sql, Object... args) {
        List<KetQua> List = new ArrayList<>();
        try {
            ResultSet rs = jdbchelper.query(sql, args);
            while (rs.next()) {
                KetQua entity = new KetQua();
                entity.setMaSV(rs.getString("MaSV"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setMaMon(rs.getString("MaMon"));
                entity.setDiemTB(rs.getFloat("DiemTB"));
                entity.setDiemTongKet(rs.getFloat("DiemTongKet"));
                entity.setHanhKiem(rs.getString("HanhKiem"));
                entity.setHocKi(rs.getInt("HocKi"));
                entity.setGhiChu(rs.getString("GhiChu"));
                List.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return List;
    }
    public List<KetQua> selectByKeyword(String keyword){
        String sql = "SELECT * FROM tblKET_QUA WHERE MaSV LIKE ?";
        return this.selectBySql(sql, "%"+keyword+"%");
    }
}
