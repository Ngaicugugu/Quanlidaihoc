/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.KetQua;
import Utils.jdbchelper;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author Admin
 */
public class KetQuaDAO extends QuanLySinhVienDAO<KetQua, String>{
    String insert_sql = " INSERT INTO tblKET_QUA (MaSV, HoTen, MaMon, DiemTB, DiemTongKet, HanhKiem, HocKi, GhiChu) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
    String update_sql = " UPDATE tblKET_QUA SET HoTen = ?, DiemTB = ?, DiemTongKet = ?, HanhKiem = ?, HocKi = ?, GhiChu = ? WHERE MaSV = ? AND MaMon = ?";
    String delete_sql = " DELETE FROM tblKET_QUA WHERE MaSV = ? AND MaMon = ? ";
    String selectAll_sql = " SELECT * FROM tblKET_QUA ";
    String selectByID_sql = " SELECT * FROM tblKET_QUA WHERE MaSV = ? ";

    @Override
    public void insert(KetQua entity) {
        try {
                jdbchelper.update(insert_sql, entity.getMaSV(), entity.getHoTen(), entity.getMaMon()
                        , entity.getDiemTB(),entity.getDiemTongKet(), entity.getHanhKiem(),
                        entity.getHocKi(), entity.getGhiChu() );
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(KetQua entity) {
        try {
                jdbchelper.update(update_sql, entity.getHoTen()
                        , entity.getDiemTB(),entity.getDiemTongKet(), entity.getHanhKiem(),
                        entity.getHocKi(), entity.getGhiChu(), entity.getMaSV(), entity.getMaMon() );
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String key) {
        try {
			jdbchelper.update(delete_sql,key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void delete(String key, String maMon) {
        try {
			jdbchelper.update(delete_sql,key, maMon);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @Override
    public List<KetQua> selectAll() {
       return selectBySql(selectAll_sql);
    }

    @Override
    public KetQua selectById(String key) {
        List<KetQua> List = selectBySql(selectByID_sql, key);
        if(List.isEmpty()) {
                return null;
        }
        return List.get(0);
    }

    @Override
    protected List<KetQua> selectBySql(String sql, Object... args) {
        List<KetQua> List = new ArrayList<>();
        try {
                ResultSet rs = jdbchelper.query(sql, args);

                while(rs.next()) {
                        KetQua entity = new KetQua();
                        
                        entity.setMaSV(rs.getString(1));
                        entity.setHoTen(rs.getString(2));
                        entity.setMaMon(rs.getString(3));
                        entity.setDiemTB(rs.getFloat(4));
                        entity.setDiemTongKet(rs.getFloat(5));
                        entity.setHanhKiem(rs.getString(6));
                        entity.setHocKi(rs.getInt(7));
                        entity.setGhiChu(rs.getString(8));
                        
                        List.add(entity);
                }
            } catch (Exception e) {
                    throw new RuntimeException();
            }
        return List;
    }
    public List<KetQua> selectByMaMon(String maMon){
        String sql = " SELECT * FROM tblKET_QUA WHERE MaMon = ? ";
        return this.selectBySql(sql, maMon);
    }
}
