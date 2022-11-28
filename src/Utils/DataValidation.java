/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class DataValidation {
        public static void validateEmp(JTextField field, StringBuilder sb, String Error) {
		if(field.getText().equals("")) {
			sb.append(Error).append("\n");
			field.setBackground(Color.RED);
			field.requestFocus();
		}else {
			field.setBackground(Color.WHITE);
		}
	}
	public static void validateEmp(JTextArea field, StringBuilder sb, String Error) {
		if(field.getText().equals("")) {
			sb.append(Error).append("\n");
			field.setBackground(Color.RED);
			field.requestFocus();
		}else {
			field.setBackground(Color.WHITE);
		}
	}
	public static void validatePassword(JPasswordField field, StringBuilder sb, String Error) {
		String Pass= new String (field.getPassword());
		if(Pass.equals("")) {
			sb.append(Error).append("\n");
			field.setBackground(Color.RED);
			field.requestFocus();
		}else {
			field.setBackground(Color.WHITE);
		} 	
	}
	public static void validateRole(ButtonGroup bgr , StringBuilder sb, String Error) {
		if(bgr.isSelected(null)){
			sb.append(Error).append("\n");
		}
	}
	public static void validateEmail(JTextField field, StringBuilder sb, String Error) {
		String emailMau="\\w+@\\w+(\\.\\w+){1,2}";
		if(field.getText().equals("") || !field.getText().matches(emailMau)) {
			sb.append(Error).append("\n");
			field.setBackground(Color.RED);
			field.requestFocus();
		}else {
			field.setBackground(Color.WHITE);
		}
	}
	public static void validatePhoneNumber(JTextField field, StringBuilder sb, String Error) {
		String Number = "^\\d{10}$";
		try {
			if(field.getText().equals("") || !field.getText().trim().matches(Number)) {
				sb.append(Error).append("\n");
				field.setBackground(Color.RED);
				field.requestFocus();
			}else {
				field.setBackground(Color.WHITE);
			}
		} catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.print(e.getMessage());
			JOptionPane.showMessageDialog(field, "Phải Nhập Số");
			
		}
	}
	public static void validateNumber(JTextField field, StringBuilder sb, String Error) {
		String Number = "^\\d{10}$";
		try {
			if(field.getText().equals("")) {
				sb.append(Error).append("\n");
				field.setBackground(Color.RED);
				field.requestFocus();
			}else {
				field.setBackground(Color.WHITE);
			}
		} catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.print(e.getMessage());
			field.setBackground(Color.RED);
			JOptionPane.showMessageDialog(field, "Phải Nhập Số");
			
		}
	}
	private static boolean isLeapYear(int year) {
       return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }
	public static boolean validateYear(JTextField field, StringBuilder sb, String Error){
		String getYear = "((?:19|20)[0-9][0-9])";
		String getMonth = "(0?[1-9]|1[012])";
		String getDay = "(0?[1-9]|[12][0-9]|3[01])";
		boolean result = false;
		
		if(field.getText().equals("")) {
			sb.append(Error).append("\n");
			field.setBackground(Color.RED);
			field.requestFocus();
		}else {
			field.setBackground(Color.WHITE);
		
		
		String regex = "^"+ getYear + "-"+ getMonth +"-" + getDay + "$";
		String Year =field.getText().trim().split("-")[0];
		String MonTh = field.getText().trim().split("-")[1];
		String Day=field.getText().trim().split("-")[2];
		

		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			if(!formatter.format(formatter.parse(field.getText().trim())).matches(regex)) {	
				sb.append(Error).append("\n");
				JOptionPane.showMessageDialog(field, "Lỗi Định Dạng Ngày");
				field.setBackground(Color.RED);
				field.requestFocus();
				result = false;
				
			}else if ((MonTh.matches("4") || MonTh.matches("6") || MonTh.matches("9") ||
					MonTh.matches("04") || MonTh.matches("06") || MonTh.matches("09") ||
					MonTh.matches("11")) && Day.matches("31")) {
            	sb.append(Error).append("\n");
				JOptionPane.showMessageDialog(field, "Lỗi Định Dạng Ngày");
    			field.setBackground(Color.RED);
    			field.requestFocus();
    			result = false;
			}else if (MonTh.matches("2") || MonTh.matches("02")) {
                if (Day.matches("30") || Day.matches("31")) {
                	sb.append(Error).append("\n");
                	JOptionPane.showMessageDialog(field, "Lỗi Định Dạng Ngày");
        			field.setBackground(Color.RED);
        			field.requestFocus();
        			result = false;
                }else if (Day.matches("29")) {  // leap year? feb 29 days.
                    if (!isLeapYear(Integer.valueOf(Year))) {
                    	sb.append(Error).append("\n");
                    	JOptionPane.showMessageDialog(field, "Lỗi Định Dạng Ngày");
            			field.setBackground(Color.RED);
            			field.requestFocus();
            			result = false;
                    }
                }
			}
			else{
				field.setBackground(Color.WHITE);
				result = true;
				}
			
		} catch (ParseException e) {
			// TODO: handle exception
			sb.append(Error).append("\n");
			field.setBackground(Color.RED);
			field.requestFocus();
			result = false;
		}
	}
		return result = true;
	}
}
