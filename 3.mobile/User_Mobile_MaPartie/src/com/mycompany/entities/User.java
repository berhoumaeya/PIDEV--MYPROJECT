/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author Lenovo
 */
public class User {

    private int id;
    private String email;
    private String password;
    private String login;
    private String nom;
    private String address;
    private String telephone;
    private String roles;
    private boolean isVerified;

    public User() {
    }

    public User(int id, String email, String password, String login, String nom, String address, String telephone, String roles, boolean isVerified) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.login = login;
        this.nom = nom;
        this.address = address;
        this.telephone = telephone;
        this.roles = roles;
        this.isVerified = isVerified;
    }

    public User(String email, String password, String login, String nom, String address, String telephone, String roles, boolean isVerified) {
        this.email = email;
        this.password = password;
        this.login = login;
        this.nom = nom;
        this.address = address;
        this.telephone = telephone;
        this.roles = roles;
        this.isVerified = isVerified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", password=" + password + ", login=" + login + ", nom=" + nom + ", address=" + address + ", telephone=" + telephone + ", roles=" + roles + ", isVerified=" + isVerified + '}';
    }

}
