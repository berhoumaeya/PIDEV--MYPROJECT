/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author achref
 */
public class Reservation {
    private int id_res;
    private int id_user;
    private int id_event;
    private int qte;
    
    public Reservation(int id_res, int id_user, int id_event, int qte) {
        this.id_res = id_res;
        this.id_user = id_user;
        this.id_event = id_event;
        this.qte = qte;
    }

    public Reservation() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // Getter methods
    public int getIdRes() {
        return id_res;
    }
    public int getIdUser() {
        return id_user;
    }
    
    public int getIdEvent() {return id_event;
    }
    
    public int getQte() {
        return qte;
    }
    
    // Setter methods
    public void setIdRes(int id_res) {
        this.id_res = id_res;
    }
    public void setIdUser(int id_user) {
        this.id_user = id_user;
    }
    
    public void setIdEvent(int id_event) {
        this.id_event = id_event;
    }
    
    public void setQte(int qte) {
        this.qte = qte;
    }

    public String getIdevent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getqte() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    }
  
