/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entite;

/**
 *
 * @author Lenovo
 */
public class Admin {
    
    private int id_admin;
     private int id_user;
    private String login;

    private String pwd;
    private String email;
    
    
    
     public Admin(int id_user, String login, String pwd, String email) {
        this.id_user = id_user;
        this.login = login;
        this.pwd = pwd;
        this.email = email;
    }

    public Admin(int id_admin, int id_user, String login, String pwd, String email) {
        this.id_admin = id_admin;
        this.id_user = id_user;
        this.login = login;
        this.pwd = pwd;
        this.email = email;
    }
   

    
     public Admin(int id_admin) {
        this.id_admin = id_admin;
    }
    
    
    
    
    public Admin() {
    }

    

    

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        String hashedPwd=Crypt.hash(pwd);
        this.pwd = hashedPwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    /*@Override
    public String toString() {
        return "\nAdministrateur{" + "id_admin=" + id_admin + ", login=" + login + ", pwd=" + pwd + ", email=" + email + '}';
    }*/

    @Override
    public String toString() {
        return "Admin{" + "id_admin=" + id_admin + ", login=" + login + ", pwd=" + pwd + ", email=" + email + ", id_user=" + id_user + '}';
    }

    
  
   
}
