/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Admin;
import static GUI.AjoutUserController.isEmailValid;
import Interface.IAdminService;
import Services.ServiceAdmin;
import Utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
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
public class AjouterAdminController implements Initializable {

    @FXML
    private ComboBox<Integer> cmb_id_user;
    @FXML
    private TextField tf_login;
    @FXML
    private TextField tf_pwd;
    @FXML
    private TextField tf_email;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button home;
    Connection mc;

    public static boolean isEmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pattern.matcher(email).matches();
    }

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
    }

    @FXML
    private void menu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AccueilAd.fxml"));
            Parent root = loader.load();
            home.getScene().setRoot(root);
            // JOptionPane.showMessageDialog(null, "Welcome HOME !!");
        } catch (IOException ex) {
            Logger.getLogger(AccueilAdController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouter_admin(MouseEvent event) {

        String id_user = cmb_id_user.getSelectionModel().getSelectedItem().toString();
        String login = tf_login.getText();
        String pwd = tf_pwd.getText();
        String email = tf_email.getText();

        if (pwd.isEmpty() || email.isEmpty() || login.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Les champs sont Vides");
            alert.showAndWait();
        } else if (isEmailValid(email) == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("L'email est non valide");
            alert.showAndWait();
        } /*else if (checkAdminExistence(login, email) == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("L'admin déja existe ");
            alert.showAndWait();
        } */else {
            Admin a = new Admin(Integer.parseInt(id_user), login, pwd, email);
            IAdminService as = new ServiceAdmin();
            as.ajouterAdmin(a);
            JOptionPane.showMessageDialog(null, "Administrateur ajouté avec succés ");
            tf_pwd.setText(null);
            tf_email.setText(null);
            tf_login.setText(null);

        }

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

    private boolean checkAdminExistence(String login, String email) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ace", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM admin WHERE login='" + login + "' AND email='" + email + "'");
            if (rs.next()) {
                return true;
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la vérification de l'existence de l'administrateur : " + ex.getMessage());
        }
        return false;
    }
}
