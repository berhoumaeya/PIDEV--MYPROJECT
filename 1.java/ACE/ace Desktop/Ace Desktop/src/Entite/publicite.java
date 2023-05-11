/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entite;

/**
 *
 * @author farah
 */
public class publicite {
    int id_pub;
    String type;
    int id_event;

    public publicite() {
    }

    public publicite(int id_pub, String type, int id_event) {
        this.id_pub = id_pub;
        this.type = type;
        this.id_event = id_event;
    } 

    public publicite(String type, int id_event) {
        this.type = type;
        this.id_event = id_event;
    }

   
   

    public int getId_pub() {
        return id_pub;
    }

    public void setId_pub(int id_pub) {
        this.id_pub = id_pub;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    @Override
    public String toString() {
        return    id_pub + " \t                                        " + type + "\t                             "  + id_event ;
    }
    
    
    
}
