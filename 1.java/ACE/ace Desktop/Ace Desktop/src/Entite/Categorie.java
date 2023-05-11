package Entite;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author eyamo
 */
public class Categorie {
  private  int id_cat;
   private String categories;
   private int id_sponsor;

    public Categorie() {
    }

    public Categorie(String categories) {
        this.categories = categories;
    }
    

    public Categorie(String categories, int id_sponsor) {
        this.categories = categories;
        this.id_sponsor = id_sponsor;
    }

    public Categorie(int id_cat, String categories) {
        this.id_cat = id_cat;
        this.categories = categories;
    }
    

    public Categorie(int id_cat, String categories, int id_sponsor) {
        this.id_cat = id_cat;
        this.categories = categories;
        this.id_sponsor = id_sponsor;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public int getId_sponsor() {
        return id_sponsor;
    }

    public void setId_sponsor(int id_sponsor) {
        this.id_sponsor = id_sponsor;
    }
     @Override
    public String toString() {
        return  id_cat + "\t       " + categories + "\t        " + id_sponsor + "\t        " ;
    }
    
    
}
