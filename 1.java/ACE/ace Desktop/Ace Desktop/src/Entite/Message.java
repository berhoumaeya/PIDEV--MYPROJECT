
package Entite;

import java.sql.Date;


public class Message {
    public  int id_message;
  private  int id_post;
   private String contenu;
   public Number note;
   private int likes;

    public Message() {
    }

    public Message(String contenu, Number note) {
        this.contenu = contenu;
    }


    public Message(int id_post, String contenu) {
        this.id_post = id_post;
        this.contenu = contenu;
    }
    
    
    public Message(int id_post, int id_message, String contenu, Number note) {
        this.id_message=id_message;
        this.id_post = id_post;
        this.contenu = contenu;
    }
    
    
    

    public int getPostId() {
        return id_post;
    }

    public void setPostId(int id_post) {
        this.id_post = id_post;
    }
    
    public int getMsgId() {
        return id_message;
    }
    
    public void setMessageId(int id_message) {
        this.id_message = id_message;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    
    
    public Number getNote() {
        return note;
    }
    
    public void setNote(Number note) {
        this.note = note;
    }

     @Override
    public String toString() {
        return id_message + " | \t\t\t\t\t\t\t\t"+contenu;
    }
    
    
}
