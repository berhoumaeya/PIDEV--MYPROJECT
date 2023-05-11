/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entite.Admin;
import Entite.User;
import Interface.IAdminService;

import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Interface.IUtilisateurService;

/**
 *
 * @author Lenovo
 */
public class ServiceAdmin implements IAdminService<Admin> {

  


    @Override
    public void ajouterAdmin(Admin a) {
        try {
 String requete= "INSERT INTO admin (id_user,login,pwd,email)"
                    + "VALUES (?,?,?,?)";
            PreparedStatement pst = DataSource.getInstance().getConnection()
                    .prepareStatement(requete);
           
            pst.setInt(1,a.getId_user());
            pst.setString(2, a.getLogin());
            pst.setString(3, a.getPwd());
            pst.setString(4, a.getEmail());
            
           pst.executeUpdate();
            System.out.println("admin ajoutée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Admin> afficherAdmins() {
         List<Admin> adminList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM Admin a ";
            Statement st = DataSource.getInstance().getConnection()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Admin a = new Admin();
                a.setId_admin(rs.getInt("id_user"));
                a.setId_admin(rs.getInt("id_admin"));
                a.setLogin(rs.getString("login"));
                a.setPwd(rs.getString("pwd"));
                a.setEmail(rs.getString("email"));
                
                System.out.println("the added admins are :"+a.toString());
                adminList.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return adminList;
    }

    @Override
    public void supprimerAdmin(Admin a) {
                try {
            String requete = "DELETE FROM admin where id_admin=?";
            PreparedStatement pst = DataSource.getInstance().getConnection()
                    .prepareStatement(requete);
            pst.setInt(1, a.getId_admin());
            pst.executeUpdate();
            System.out.println("Admin supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierAdmin(Admin u) {
        try {
            String requete = "UPDATE admin SET id_user=?,login=?,pwd=?,email=?  WHERE id_admin=?";
            PreparedStatement pst = DataSource.getInstance().getConnection()
                    .prepareStatement(requete);
            
            
            pst.setInt(1, u.getId_user());
            pst.setString(2, u.getLogin());
            pst.setString(3, u.getPwd());
            pst.setString(4, u.getEmail());
            pst.setInt(5, u.getId_admin());

            pst.executeUpdate();
            System.out.println("Administrateur modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    

}
