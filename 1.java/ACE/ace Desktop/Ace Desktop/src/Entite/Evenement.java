/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entite;
import java.sql.Date;
//mport java.util.Date;
/**
 *
 * @author farah
 * 
 */
public class Evenement {
  
     private int id_event;
    private float duree;
    private float prix;
    private int id;
    String nom_event;
   Date date_deb;
   Date date_fin;

    public Evenement(int id_event, float duree, float prix, int id, String nom_event, Date date_deb, Date date_fin) {
        this.id_event = id_event;
        this.duree = duree;
        this.prix = prix;
        this.id = id;
        this.nom_event = nom_event;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
    }

    public Evenement(float duree, float prix, int id, String nom_event, Date date_deb, Date date_fin) {
        this.duree = duree;
        this.prix = prix;
        this.id = id;
        this.nom_event = nom_event;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
    }

    public Evenement(float duree, float prix, int id, Date date_deb, Date date_fin) {
        this.duree = duree;
        this.prix = prix;
        this.id = id;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
    }

  
    

   
    
      

    public Evenement() {
    }

    public Evenement(float duree, float prix) {
        this.duree = duree;
        this.prix = prix;
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

    public void setNom_event(String nom__event) {
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

    @Override
    public String toString() {
        return   id_event + "\t         " + duree +"\t             "+ prix + "\t             " + id + "\t                   "+ nom_event +"\t                 " + date_deb + "\t              " + date_fin ;
    }

    
  


   
    
 

   
}
