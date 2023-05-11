
package Services;
import java.sql.SQLException;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author eyamo
 */
public  interface InterfaceMessage <C> {
    void ajouterMsg(C t) throws SQLException;

    void updateMsg(C t) throws SQLException; //modifier 

    public void DeleteMsg (int id1, int id2) throws SQLException;

    List<C> readAllMsgs() throws SQLException; //afficher 

    C findByIdMsg(int id1, int id2) throws SQLException;
    

    
}

