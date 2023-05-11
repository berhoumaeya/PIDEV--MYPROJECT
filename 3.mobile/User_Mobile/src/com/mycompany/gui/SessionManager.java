/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.io.Preferences;

/**
 *
 * @author wiki
 */
public class SessionManager {

    public static Preferences pref;

    private static int id;
    private static String email;
    private static String password;
    private static String nom;
    private static String telephone;
    private static String address;
    private static String login;

    private static String roles;

    public SessionManager() {
    }

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getId() {
        return pref.get("id", id);
    }

    public static void setId(int id) {
        pref.set("id", id);
    }

    public static String getEmail() {
        return pref.get("email", email);
    }

    public static void setEmail(String email) {
        pref.set("email", email);
    }

    public static String getPassword() {
        return pref.get("password", password);
    }

    public static void setPassword(String password) {
        pref.set("password", password);
    }

    public static String getNom() {
        return pref.get("nom", nom);
    }

    public static void setNom(String nom) {
        pref.set("nom", nom);
    }

    public static String getLogin() {
        return pref.get("login", login);
    }

    public static void setLogin(String login) {
        pref.set("login", login);
    }

    public static String getAddress() {
        return pref.get("address", address);
    }

    public static void setAddress(String address) {
        pref.set("address", address);
    }

    public static String getTelephone() {
        return pref.get("telephone", telephone);
    }

    public static void setTelephone(String telephone) {
        pref.set("telephone", telephone);
    }

    public static String getRoles() {
        return pref.get("roles", roles);
    }

    public static void setRoles(String roles) {
        pref.set("roles", roles);
    }

}
