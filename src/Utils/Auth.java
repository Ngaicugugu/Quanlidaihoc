/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import Entity.LoginUser;



/**
 *
 * @author Thinkpad T490
 */
public class Auth {
    public static LoginUser user = null;
    public static void clear(){
        Auth.user = null;
    }    
    public static boolean isLogin(){
        return Auth.user != null;
    }
    public static String isManager(){
        return user.getRole();
    }
}
