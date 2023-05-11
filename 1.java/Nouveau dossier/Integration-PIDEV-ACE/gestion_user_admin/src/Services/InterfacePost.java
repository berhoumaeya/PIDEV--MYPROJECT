

package Services;

import java.sql.SQLException;
import java.util.List;


public interface InterfacePost<T> {
    
    void ajouter(T t) throws SQLException;

    void update(T t) throws SQLException;


    public void Delete (int id) throws SQLException;

    List<T> readAll() throws SQLException; //afficher 

    T findById(int id) throws SQLException;
}

