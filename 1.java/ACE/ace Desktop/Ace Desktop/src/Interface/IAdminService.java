/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.util.List;

/**
 *
 * @author TECHN
 */
public interface IAdminService<Admin> {

    public void ajouterAdmin(Admin a);

    public List<Admin> afficherAdmins();

    public void supprimerAdmin(Admin a);

    public void modifierAdmin(Admin a);


}
