/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Lop;
import Utils.jdbchelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author Thinkpad T490
 */
public class lopDAO extends QuanLySinhVienDAO<Lop, String> {

    String insert_sql = " INSERT INTO tblLOP (MaKhoa,MaLop,TenLop) VALUES (?, ?, ?) ";
    String update_sql = " UPDATE tblLOP SET MaKhoa = ?, TenLop = ?, WHERE MaLop = ? ";
    String delete_sql = " DELETE FROM tblLOP WHERE MaLop = ? ";
    String selectAll_sql = " SELECT * FROM tblLOP ";
    String selectByID_sql = " SELECT * FROM tblLOP WHERE MaLop = ? ";

    @Override
    public void insert(Lop entity) {
        try {
            jdbchelper.update(insert_sql, entity.getMaKhoa(), entity.getMaLop(), entity.getTenLop());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Lop entity) {
        try {
            jdbchelper.update(update_sql, entity.getMaKhoa(), entity.getTenLop(), entity.getMaLop());
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
    public List<Lop> selectAll() {
        return selectBySql(selectAll_sql);
    }

    @Override
    public Lop selectById(String key) {
        List<Lop> List = selectBySql(selectByID_sql, key);
        if (List.isEmpty()) {
            return null;
        }
        return List.get(0);
    }

    @Override
    protected List<Lop> selectBySql(String sql, Object... args) {
        List<Lop> List = new ArrayList<>();
        try {
            ResultSet rs = jdbchelper.query(sql, args);

            while (rs.next()) {
                Lop entity = new Lop();
                entity.setMaKhoa(rs.getString("MaKhoa"));
                entity.setMaLop(rs.getString("MaLop"));
                entity.setTenLop(rs.getString("TenLop"));
                List.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return List;
    }
    
    public List<Lop> selectByKhoa(String makhoa){
        String sql="select * from tblLop where MaKhoa=?";
        return this.selectBySql(sql, makhoa);
    }

}
