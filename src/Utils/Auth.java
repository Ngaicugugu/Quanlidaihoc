/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import entity.users;


/**
 *
 * @author Thinkpad T490
 */
public class Auth {
    public static users user = null;
    public static void clear(){
        Auth.user = null;
    }    
    public static boolean isLogin(){
        return Auth.user != null;
    }
    public static boolean isManager(){
        return user.isVaitro();
    }
}
