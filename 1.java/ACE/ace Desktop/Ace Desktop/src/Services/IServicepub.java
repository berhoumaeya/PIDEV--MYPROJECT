/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;


import java.sql.SQLException;
import java.util.List;


public interface IServicepub<P> {

    void ajouterPub(P p) throws SQLException;

    void updatePub(P p) throws SQLException;

    public void DeletePub(int id2) throws SQLException ;

    List<P> readAllPub() throws SQLException;

    P findByIdPub(int id2) throws SQLException;
    
    
   
}
