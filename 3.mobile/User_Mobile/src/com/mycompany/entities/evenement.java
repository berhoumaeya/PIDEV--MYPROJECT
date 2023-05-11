/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;


/**
 *
 * @author farah
 */
public class evenement {
    
    
    
   private int id_event;
    private float duree;
    private float prix;
    private int id;
    String nom_event;
   Date date_deb;
   Date date_fin;

    public evenement() {
    }
   

    public evenement(float duree, float prix, String nom_event, Date date_deb, Date date_fin) {
        this.duree = duree;
        this.prix = prix;
        this.nom_event = nom_event;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
    }
   

    public evenement(float duree, float prix, int id, String nom_event, Date date_deb, Date date_fin) {
        this.duree = duree;
        this.prix = prix;
        this.id = id;
        this.nom_event = nom_event;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
    }

    public evenement(int id_event, float duree, float prix, int id, String nom_event, Date date_deb, Date date_fin) {
        this.id_event = id_event;
        this.duree = duree;
        this.prix = prix;
        this.id = id;
        this.nom_event = nom_event;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
    }

    public evenement(int id_event, float duree, float prix, String nom_event, Date date_deb, Date date_fin) {
        this.id_event = id_event;
        this.duree = duree;
        this.prix = prix;
        this.nom_event = nom_event;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public float getDuree() {
        return duree;
    }

    public void setDuree(float duree) {
        this.duree = duree;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_event() {
        return nom_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public Date getDate_deb() {
        return date_deb;
    }

    public void setDate_deb(Date date_deb) {
        this.date_deb = date_deb;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

 


  
    
}
