/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.LoginUser;
import Utils.jdbchelper;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Admin
 */
public class LoginUserDAO extends QuanLySinhVienDAO<LoginUser, String>{
        String insert_sql = " INSERT INTO tblLOGIN (TenDN, MatKhau, HoTen, GioiTinh, Phone, Email, Quyen) VALUES (?, ?, ?, ?, ?, ?, ?) ";
	String update_sql = " UPDATE tblLOGIN SET MatKhau = ?, HoTen = ?, GioiTinh = ?, Phone = ?, Email = ?, Quyen = ? WHERE TenDN = ? ";
	String delete_sql = " DELETE FROM tblLOGIN WHERE TenDN = ? ";
	String selectAll_sql = " SELECT * FROM tblLOGIN ";
	String selectByID_sql = " SELECT * FROM tblLOGIN WHERE TenDN = ? ";

    @Override
    public void insert(LoginUser entity) {
            try {
			jdbchelper.update(insert_sql, entity.getLoginUser(),entity.getPass(), entity.getName(),
                                entity.isGioitinh(), entity.getPhone(), entity.getEmail(), entity.getRole());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void update(LoginUser entity) {
           try {
			jdbchelper.update(update_sql,entity.getPass(), entity.getName(),
                                entity.isGioitinh(), entity.getPhone(), entity.getEmail(),
                                entity.getRole(), entity.getLoginUser());
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

    @Override
    public List<LoginUser> selectAll() {
       return selectBySql(selectAll_sql);
    }

    @Override
    public LoginUser selectById(String key) {
        List<LoginUser> List = selectBySql(selectByID_sql, key);
		if(List.isEmpty()) {
			return null;
		}
		return List.get(0);
    }

    @Override
    protected List<LoginUser> selectBySql(String sql, Object... args) {
        	List<LoginUser> List = new ArrayList<>();
		try {
			ResultSet rs = jdbchelper.query(sql, args);
			
			while(rs.next()) {
				LoginUser entity = new LoginUser();
				entity.setLoginUser(rs.getString(1));
                                entity.setPass(rs.getString(2));
                                entity.setName(rs.getString(3));
                                entity.setGioitinh(rs.getBoolean(4));
                                entity.setPhone(rs.getString(5));
                                entity.setEmail(rs.getString(6));
                                entity.setRole(rs.getString(7));
				List.add(entity);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return List;
    }

    
}
