/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Thinkpad T490
 */
public class XDate {
   static final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    
    public static Date toDate(String date, String... pattern) {
        try {
            if(pattern.length > 0) {
                format.applyPattern(pattern[0]);
            }
            if(date == null) {
                return now();
            }
            return format.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
    }
    
    public static String toString(Date date, String... pattern) {
        try {
            if(pattern.length > 0) {
                format.applyPattern(pattern[0]);
            }
            if(date == null) {
                date = now();
            }
            return format.format(date);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    
    public static Date addDay(Date date, int day) {
        date.setTime(date.getTime() + day * 24 * 60 * 60 * 1000);
        return date;
    }
    
    public static Date add(int days) {
        Date date = now();
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        return date;
    }
    
    public static Date now() {
        return new Date();
    }
}
