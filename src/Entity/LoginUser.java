/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author Admin
 */
public class LoginUser {
    private String loginUser, Pass, Name, Gender, Phone, Email, Role;

    public String getLoginUser() {
        return loginUser;
    }

    public String getPass() {
        return Pass;
    }

    public String getName() {
        return Name;
    }

    public String getGender() {
        return Gender;
    }

    public String getPhone() {
        return Phone;
    }

    public String getEmail() {
        return Email;
    }

    public String getRole() {
        return Role;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public LoginUser(String loginUser, String Pass, String Name, String Gender, String Phone, String Email, String Role) {
        this.loginUser = loginUser;
        this.Pass = Pass;
        this.Name = Name;
        this.Gender = Gender;
        this.Phone = Phone;
        this.Email = Email;
        this.Role = Role;
    }

    public LoginUser() {
    }
    
}
