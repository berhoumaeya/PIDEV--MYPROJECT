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
public class Promotion {
    private int id_prom;
    private int id_user;
    private int remise;
    
    public Promotion( int id_prom,int id_user, int remise) {
        
        this.id_prom = id_prom;
        this.id_user = id_user;
        this.remise = remise;
    }

 public Promotion() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
    // Getter methods
        public int getIdProm() {return id_prom;
    }
    
    public int getIdUser() {
        return id_user;
    }
    

    
    public int getRemise() {
        return remise;
    }
    
    // Setter methods
       public void setIdProm(int id_prom) {
        this.id_prom = id_prom;
    }
    
    public void setIdUser(int id_user) {
        this.id_user = id_user;
    }
    

    
    public void setRemise(int remise) {
        this.remise = remise;
    }

}
