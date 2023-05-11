/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

/**
 *
 * @author farah
 */
import java.sql.SQLException;
import java.util.List;


public interface IService<T> {

    void ajouter(T t) throws SQLException;

    void update(T t) throws SQLException;

    public void Delete(int id) throws SQLException ;

    List<T> readAll() throws SQLException;

    T findById(int id) throws SQLException;
    // public boolean verifierUniciteEvenement(String nom_event) ;
     
   
    
    
   
}

