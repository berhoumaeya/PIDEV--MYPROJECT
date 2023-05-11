
package Entite;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author eyamo
 */
public class Post {
  private  int id_post;
   private String sujet;

    public Post() {
    }

    public Post(String sujet) {
        this.sujet = sujet;
    }
    



    public Post(int id_post, String sujet) {
        this.id_post = id_post;
        this.sujet = sujet;
    }
    

    public int getPostId() {
        return id_post;
    }

    public void setPostId(int id_post) {
        this.id_post = id_post;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }
    


     @Override
    public String toString() {
        return id_post + "| \t\t\t\t\t\t\t\t " + sujet;
    }
    
    
}

