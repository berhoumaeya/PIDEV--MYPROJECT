/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Admin;
import Interface.IAdminService;
import Services.ServiceAdmin;
import Utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author TECHN
 */
public class ModifierAdminController implements Initializable {

    @FXML
    private Button home;
    @FXML
    private ComboBox<Integer> combo_id_admin;
    @FXML
    private ComboBox<Integer> cmb_id_user;
    @FXML
    private TextField tf_login;
    @FXML
    private TextField tf_pwd;
    @FXML
    private TextField tf_email;
    Connection mc;
    @FXML
    private Button modifierAdmin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mc = DataSource.getInstance().getConnection();
        try {
            String requete = "SELECT id_user FROM utilisateur u ";
            Statement st = DataSource.getInstance().getConnection()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            ObservableList<Integer> id = null;
            List<Integer> list = new ArrayList<>();
            while (rs.next()) {

                list.add(rs.getInt("id_user"));

            }
            id = FXCollections
                    .observableArrayList(list);
            cmb_id_user.setItems(id);
        } catch (SQLException ex) {
            Logger.getLogger(AjouterAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            String requete = "SELECT id_admin FROM admin a ";
            Statement st = DataSource.getInstance().getConnection()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            ObservableList<Integer> id = null;
            List<Integer> list = new ArrayList<>();
            while (rs.next()) {

                list.add(rs.getInt("id_admin"));

            }
            id = FXCollections
                    .observableArrayList(list);
            combo_id_admin.setItems(id);
        } catch (SQLException ex) {
            Logger.getLogger(AjouterAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void menu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AccueilAd.fxml"));
            Parent root = loader.load();
            home.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AccueilAdController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void updateAdmin(MouseEvent event) {
        String id_admin = combo_id_admin.getSelectionModel().getSelectedItem().toString();
        String id_user = cmb_id_user.getSelectionModel().getSelectedItem().toString();

        String login = tf_login.getText();
        String pwd = tf_pwd.getText();
        String email = tf_email.getText();

        Admin a = new Admin(Integer.parseInt(id_admin), Integer.parseInt(id_user), login, pwd, email);
        IAdminService as = new ServiceAdmin();

        as.modifierAdmin(a);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Mr/Mme   " + tf_login.getText() + " " + " Vos donnés personelles sont modifiés !", ButtonType.CLOSE);
        alert.show();

    }

    @FXML
    private void deleteUser(MouseEvent event) {
    }

    @FXML
    private void seDeconnecter(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginAdmin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 400);
            Stage stage = (Stage) home.getScene().getWindow();
            stage.setScene(scene);
            // stage.show();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Succes");
            alert.setContentText("Déconnexion  avec succes");
            alert.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(LoginAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
