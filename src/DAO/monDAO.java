/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Mon;
import Utils.jdbchelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class monDAO extends QuanLySinhVienDAO<Mon, String>{
    
    String insert_sql = " INSERT INTO tblMON (MaMon, TenMon, Sotiet, MaGV, HocKi, MaKhoa) VALUES (?, ?, ?, ?, ?, ?) ";
    String update_sql = " UPDATE tblMON SET TenMon = ?, Sotiet = ?, MaGV = ?, HocKi = ?, MaKhoa = ? WHERE MaMon = ? ";
    String delete_sql = " DELETE FROM tblMON WHERE MaMon = ? ";
    String selectAll_sql = " SELECT * FROM tblMON ";
    String selectByID_sql = " SELECT * FROM tblMON WHERE MaMon = ? ";
    
    @Override
    public void insert(Mon entity) {
        try {
            jdbchelper.update(insert_sql, entity.getMaMon(), entity.getTenMon(), entity.getSoTiet(), entity.getMaGV(), entity.getHocKi(), entity.getMaKhoa());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Mon entity) {
        try {
            jdbchelper.update(update_sql, entity.getTenMon(), entity.getSoTiet(), entity.getMaGV(), entity.getHocKi(), entity.getMaKhoa(), entity.getMaMon());
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
    public List<Mon> selectAll() {
        return selectBySql(selectAll_sql);
    }

    @Override
    public Mon selectById(String key) {
        List<Mon> List = selectBySql(selectByID_sql, key);
        if (List.isEmpty()) {
            return null;
        }
        return List.get(0);
    }

    @Override
    protected List<Mon> selectBySql(String sql, Object... args) {
        List<Mon> List = new ArrayList<>();
        try {
            ResultSet rs = jdbchelper.query(sql, args);
            while (rs.next()) {
                Mon entity = new Mon();
                entity.setMaMon(rs.getString("MaMon"));
                entity.setTenMon(rs.getString("TenMon"));
                entity.setSoTiet(rs.getInt("Sotiet"));
                entity.setMaGV(rs.getString("MaGV"));
                entity.setHocKi(rs.getString("HocKi"));
                entity.setMaKhoa(rs.getString("MaKhoa"));
                List.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return List;
    }
    public List<Mon> selectByKhoa(String makhoa){
        String sql="select * from tblMON where MaKhoa=?";
        return this.selectBySql(sql, makhoa);
    }
}
