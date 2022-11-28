/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Thinkpad T490
 */
public class jdbchelper {
    static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDataSource";
    static String dburl = "jdbc:sqlserver://localhost:1433;databaseName=garan;"
                + "encrypt=true;trustServercertificate=true;sslProtocol=TLSv1.2";
    static String user="sa";
    static String pass="1510";
    static{
        try {
            Class.forName(driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static PreparedStatement getStmt(String sql,Object...args)throws SQLException{
        Connection conn=DriverManager.getConnection(dburl,user,pass);
        PreparedStatement stmt;
        if(sql.trim().startsWith("{")){
            stmt=conn.prepareCall(sql);
        }else{
            stmt=conn.prepareStatement(sql);
        }
        for(int i=0;i<args.length;i++){
            stmt.setObject(i+1, args[i]);
        }
        return stmt;
    }
    
    public static ResultSet query(String sql,Object...args)throws SQLException{
        PreparedStatement stmt=jdbchelper.getStmt(sql, args);
        return stmt.executeQuery();
    }
    
    public static Object vaule(String sql,Object...args){
        try {
            ResultSet rs=jdbchelper.query(sql, args);
            if(rs.next()){
                return rs.getObject(1);
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static int update(String sql,Object...args){
        try {
            PreparedStatement stmt=jdbchelper.getStmt(sql, args);
            try{
                return stmt.executeUpdate();
            }
            finally{
                stmt.getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
