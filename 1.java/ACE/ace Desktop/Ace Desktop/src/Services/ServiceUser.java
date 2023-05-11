/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entite.Crypt;
import Entite.User;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Interface.IUtilisateurService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Lenovo
 */
public class ServiceUser implements IUtilisateurService<User> {

    private Connection con;
    private Statement ste;

    @Override
    public boolean ajouterUser(User u) {
        try {

            String checkQuery = "SELECT COUNT(*) FROM user WHERE login = ?";
            PreparedStatement checkStatement = DataSource.getInstance().getConnection().prepareStatement(checkQuery);
            checkStatement.setString(1, u.getLogin());
            ResultSet checkResult = checkStatement.executeQuery();
            checkResult.next();
            int count = checkResult.getInt(1);
            if (count > 0) {
                // L'événement existe déjà, ne pas l'ajouter
                System.out.println("L'user existe déjà dans la base de données");
                return false;

            }

            String requete = "INSERT INTO user (nom,password,email,login,telephone,address,roles,is_verified)"
                    + "VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = DataSource.getInstance().getConnection()
                    .prepareStatement(requete);
            pst.setString(1, u.getNom());
            pst.setString(2, u.getPassword());
            pst.setString(3, u.getEmail());
            pst.setString(4, u.getLogin());
            pst.setInt(5, u.getTelephone());
            pst.setString(6, u.getAddress());
            pst.setString(7, u.getRoles());
            pst.setBoolean(8, u.isIsVerified());
            pst.executeUpdate();
            System.out.println("user inserée");

        } catch (SQLException e) {
            System.err.print(e.getMessage());

        }
        return false;
    }

    @Override
    public List<User> afficherUsers() {
        List<User> UserList = new ArrayList<>();
        try {
            String requete;
            requete = "SELECT * FROM user WHERE roles LIKE 'ROLE_USER'  ";
            Statement st = DataSource.getInstance().getConnection()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id_user"));
                u.setNom(rs.getString("nom"));
                u.setPassword(rs.getString("password"));
                u.setEmail(rs.getString("email"));
                u.setLogin(rs.getString("login"));
                u.setTelephone(rs.getInt("telephone"));
                u.setAddress(rs.getString("address"));
                //  u.setRoles(rs.getString("roles"));
                System.out.println("the added users : " + u.toString());

                UserList.add(u);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return UserList;
    }

    @Override
    public void modifierUser(User u) {
        try {
            String requete = "UPDATE user SET nom=?,email=?,login=?,telephone=?,address=?  WHERE id=?";
            PreparedStatement pst = DataSource.getInstance().getConnection()
                    .prepareStatement(requete);
            pst.setString(1, u.getNom());
            pst.setString(2, u.getEmail());
            pst.setString(3, u.getLogin());

            pst.setInt(4, u.getTelephone());
            pst.setString(5, u.getAddress());
            pst.setInt(6, u.getId());

            pst.executeUpdate();
            System.out.println("user modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerUser(User u) {
        try {
            String requete = "DELETE FROM user where id=?";
            PreparedStatement pst = DataSource.getInstance().getConnection()
                    .prepareStatement(requete);
            pst.setInt(1, u.getId());
            pst.executeUpdate();
            System.out.println("user supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updatePwd(String email, String newPassword) throws SQLException {
        String req = "UPDATE user SET password=? where email = ?";
        PreparedStatement pst = DataSource.getInstance().getConnection()
                .prepareStatement(req);
        pst.setString(1, newPassword);
        pst.setString(2, email);

        pst.executeUpdate();
    }

    public User GetByMail(String email) throws SQLException {
        List<User> users = new ArrayList<>();
        String req = "Select * from user where email = ?";

        PreparedStatement pst = DataSource.getInstance().getConnection()
                .prepareStatement(req);
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        User u = new User();
        rs.next();
        if (rs.getRow() != 0) {
            u.setId(rs.getInt(1));
            u.setNom(rs.getString(2));
            u.setPassword(rs.getString(3));
            u.setEmail(email);
            u.setLogin(rs.getString(5));
            u.setTelephone(rs.getInt(6));
            u.setAddress(rs.getString(7));
            u.setRoles(rs.getString(8));

            return u;
        } else {
            System.out.println("Utilisateur inexistant");
            return null;
        }
    }

}
