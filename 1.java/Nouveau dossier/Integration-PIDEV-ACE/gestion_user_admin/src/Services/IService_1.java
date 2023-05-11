/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author eyamo
 */
public interface IService_1<T> {
    
    void ajouter(T t) throws SQLException;

    void update(T t) throws SQLException; //modifier 

    public void Delete (int id) throws SQLException;

    List<T> readAll() throws SQLException; //afficher 

    T findById(int id) throws SQLException;
}
