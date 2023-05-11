/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author Sondes
 */
public class Sponsor {
    String intitule;
    int Durecontrat;
    
    int id;
    Date date_debc, date_finc;

    public Date getDate_debc() {
        return date_debc;
    }

    public Sponsor(String intitule, int Durecontrat, Date date_debc, Date date_finc) {
        this.intitule = intitule;
        this.Durecontrat = Durecontrat;
        this.date_debc = date_debc;
        this.date_finc = date_finc;
    }

    public void setDate_debc(Date date_debc) {
        this.date_debc = date_debc;
    }

    public Date getDate_finc() {
        return date_finc;
    }

    public void setDate_finc(Date date_finc) {
        this.date_finc = date_finc;
    }

    public Sponsor( int id, int Durecontrat, String intitule, Date date_debc, Date date_finc) {
        this.intitule = intitule;
        this.Durecontrat = Durecontrat;
        this.id = id;
        this.date_debc = date_debc;
        this.date_finc = date_finc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sponsor(String intitule, int Durecontrat) {
        this.intitule = intitule;
        this.Durecontrat = Durecontrat;
    }

    public Sponsor() {
    }
public Sponsor(int id,int  Durecontrat , String intitule) {
        this.Durecontrat = Durecontrat;
        this.intitule = intitule;
       
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public int getDurecontrat() {
        return Durecontrat;
    }

    public void setDurecontrat(int Durecontrat) {
        this.Durecontrat = Durecontrat;
    }

    public Sponsor(int Durecontrat, int id) {
        this.Durecontrat = Durecontrat;
        this.id = id;
    }
 
}
