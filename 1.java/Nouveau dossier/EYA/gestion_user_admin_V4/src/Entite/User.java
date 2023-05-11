/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entite;

/**
 *
 * @author Lenovo
 */
public class User {

    private int id;
    private String nom;
    private String password;
    private String email;
    private String login;
    private int telephone;
    private String address;
    private String roles;
    private boolean isVerified;

    public User() {
    }

    public User(int id, String nom, String password, String email, String login, int telephone, String address, String roles, boolean isVerified) {
        this.id = id;
        this.nom = nom;
        this.password = password;
        this.email = email;
        this.login = login;
        this.telephone = telephone;
        this.address = address;
        this.roles = roles;
        this.isVerified = isVerified;
    }

    public User(int id, String nom, String password, String email, String login, int telephone, String address, String roles) {
        this.id = id;
        this.nom = nom;
        this.password = password;
        this.email = email;
        this.login = login;
        this.telephone = telephone;
        this.address = address;
        this.roles = roles;
    }

    public User(int id, String nom, String password, String email, String login, int telephone, String address) {
        this.id = id;
        this.nom = nom;
        this.password = password;
        this.email = email;
        this.login = login;
        this.telephone = telephone;
        this.address = address;
    }

    public User(String nom, String password, String email, String login, int telephone, String address, String roles) {
        this.nom = nom;
        this.password = password;
        this.email = email;
        this.login = login;
        this.telephone = telephone;
        this.address = address;
        this.roles = roles;
    }

    public User(String nom, String password, String email, String login, String address, String roles) {
        this.nom = nom;
        this.password = password;
        this.email = email;
        this.login = login;
        this.address = address;
        this.roles = roles;
    }

    public User(String password, String email, String login) {
        this.password = password;
        this.email = email;
        this.login = login;
    }

    public User(int id, String password, String email, String login) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.login = login;
    }

    public User(int id, String password, String login) {
        this.id = id;
        this.password = password;
        this.login = login;
    }

    public User(String password, String login) {
        this.password = password;
        this.login = login;
    }

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        if (roles.equals("ROLE_USER") || roles.equals("ROLE_ADMIN")) {
            this.roles = roles;
        } else {
            throw new IllegalArgumentException("Le rôle doit être soit 'role_User' ou 'role_Admin'");
        }
    }

    public boolean isIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", password=" + password + ", email=" + email + ", login=" + login + ", telephone=" + telephone + ", address=" + address + ", roles=" + roles + ", isVerified=" + isVerified + '}';
    }

    public void getId(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
