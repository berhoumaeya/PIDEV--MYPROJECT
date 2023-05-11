/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import Entite.User;
import java.util.List;

/**
 *
 * @author Lenovo
 * @param <T>
 */
public interface IUtilisateurService<User> {


    public boolean ajouterUser(User u);

    public List<User> afficherUsers();

    public void supprimerUser(User u);

    public void modifierUser(User u);

}
