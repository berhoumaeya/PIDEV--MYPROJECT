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
public  interface IService2 <C> {
    void ajouterC(C t) throws SQLException;

    void updateC(C t) throws SQLException; //modifier 

    public void DeleteC (int id2) throws SQLException;

    List<C> readAllC() throws SQLException; //afficher 

    C findByIdC(int id2) throws SQLException;
    
}
