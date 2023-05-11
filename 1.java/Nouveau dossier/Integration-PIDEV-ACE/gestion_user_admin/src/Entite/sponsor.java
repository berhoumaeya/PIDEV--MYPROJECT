/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entite;

/**
 *
 * @author eyamo
 */

import java.sql.Date;

public class sponsor {
  
    int id_sponsor;  
    String intitule;
    int duree_contrat;
    Date datdebc;
    Date datefinc;

    public sponsor() {
    }

    public sponsor(String intitule, int duree_contrat, Date datdebc, Date datefinc) {
        this.intitule = intitule;
        this.duree_contrat = duree_contrat;
        this.datdebc = datdebc;
        this.datefinc = datefinc;
    }

    public sponsor(int id_sponsor, String intitule, int duree_contrat, Date datdebc, Date datefinc) {
        this.id_sponsor = id_sponsor;
        this.intitule = intitule;
        this.duree_contrat = duree_contrat;
        this.datdebc = datdebc;
        this.datefinc = datefinc;
    }

    public int getId_sponsor() {
        return id_sponsor;
    }

    public void setId_sponsor(int id_sponsor) {
        this.id_sponsor = id_sponsor;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public int getDuree_contrat() {
        return duree_contrat;
    }

    public void setDuree_contrat(int duree_contrat) {
        this.duree_contrat = duree_contrat;
    }

    public Date getDatdebc() {
        return datdebc;
    }

    public void setDatdebc(Date datdebc) {
        this.datdebc = datdebc;
    }

    public Date getDatefinc() {
        return datefinc;
    }

    public void setDatefinc(Date datefinc) {
        this.datefinc = datefinc;
    }

    @Override
    public String toString() {
        return  id_sponsor +"\t                                                      "+ intitule + "\t                                  " + duree_contrat + "\t                  " + datdebc + "\t                                            " + datefinc + '}';
    }
}