/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Khoa;
import Utils.jdbchelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author Thinkpad T490
 */
public class khoaDAO extends QuanLySinhVienDAO<Khoa, String> {

    String insert_sql = " INSERT INTO tblKHOA (MaKhoa,tblKHOA) VALUES (?, ?) ";
    String update_sql = " UPDATE tblKHOA SET TenKhoa  = ?, WHERE MaKhoa  = ? ";
    String delete_sql = " DELETE FROM tblKHOA WHERE MaKhoa = ? ";
    String selectAll_sql = " SELECT * FROM tblKHOA ";
    String selectByID_sql = " SELECT * FROM tblKHOA WHERE MaKhoa = ? ";

    @Override
    public void insert(Khoa entity) {
        try {
            jdbchelper.update(insert_sql, entity.getMaKhoa(), entity.getTenKhoa());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Khoa entity) {
        try {
            jdbchelper.update(update_sql, entity.getTenKhoa(), entity.getMaKhoa());
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
    public List<Khoa> selectAll() {
        return selectBySql(selectAll_sql);
    }

    @Override
    public Khoa selectById(String key) {
        List<Khoa> List = selectBySql(selectByID_sql, key);
        if (List.isEmpty()) {
            return null;
        }
        return List.get(0);
    }

    @Override
    protected List<Khoa> selectBySql(String sql, Object... args) {
        List<Khoa> List = new ArrayList<>();
        try {
            ResultSet rs = jdbchelper.query(sql, args);
            while (rs.next()) {
                Khoa entity = new Khoa();
                entity.setMaKhoa(rs.getString("MaKhoa"));
                entity.setTenKhoa(rs.getString("TenKhoa"));
                List.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return List;
    }

}
