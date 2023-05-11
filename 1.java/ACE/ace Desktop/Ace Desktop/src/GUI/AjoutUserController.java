/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import API.SendSMS;
import Entite.User;
import Services.ServiceUser;
import Utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static jdk.nashorn.tools.ShellFunctions.input;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class AjoutUserController implements Initializable {

    @FXML
    private TextField tf_username;
    @FXML
    private TextField td_pwd;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_login;
    @FXML
    private TextField tf_number;
    @FXML
    private TextField tf_address;
    @FXML
    private TextField tf_role;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button home;
    private Button btnA;
    private Button btn_modifer;
    private Button btnAff;

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

    public boolean isPhoneNumberValid(String phoneNumber) {
        // Expression régulière pour un numéro de téléphone de 8 chiffres (sans les indicateurs de pays)
        String regex = "^[0-9]{8}$";
        return phoneNumber.matches(regex);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void Exit(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "Are you sure ? :(");
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();

    }

    private void btnA(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutUser.fxml"));
            Parent root = loader.load();
            btnA.getScene().setRoot(root);
            // JOptionPane.showMessageDialog(null, "Welcome HOME !!");
        } catch (IOException ex) {
            Logger.getLogger(AjoutUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modifier(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierUser.fxml"));
            Parent root = loader.load();
            btn_modifer.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(AjoutUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnAff(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherUser.fxml"));
            Parent root = loader.load();
            btnAff.getScene().setRoot(root);
            // JOptionPane.showMessageDialog(null, "Welcome HOME !!");
        } catch (IOException ex) {
            Logger.getLogger(AjoutUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void menu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
            Parent root = loader.load();
            home.getScene().setRoot(root);
            // JOptionPane.showMessageDialog(null, "Welcome HOME !!");
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouter_user(MouseEvent event) {
        String username = tf_username.getText();
        String pwd = td_pwd.getText();
        String email = tf_email.getText();
        String login = tf_login.getText();
        String number = tf_number.getText();
        String address = tf_address.getText();
        String role = tf_role.getText();

        String admin = "admin";
        String userr = "user";

        if (username.isEmpty() || pwd.isEmpty() || email.isEmpty() || login.isEmpty() || number.isEmpty() || address.isEmpty() || role.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Les champs sont Vides");
            alert.showAndWait();

        } else if ((!role.equals("ROLE_ADMIN")) && (!role.equals("ROLE_USER"))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Les roles ne sont pas bien définis");
            alert.showAndWait();
        } else if (isEmailValid(email) == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("L'email est non valide");
            alert.showAndWait();
        } else if (isPhoneNumberValid(number) == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Numero non valide");
            alert.showAndWait();
        } else {
            User u = new User(username, pwd, email, login, Integer.parseInt(number), address, role);

            ServiceUser user = new ServiceUser();
            user.ajouterUser(u);
            // SendSMS sm = new SendSMS();
            // sm.sendSMS(u);
            JOptionPane.showMessageDialog(null, "Welcome ♥");

            tf_username.setText(null);
            td_pwd.setText(null);
            tf_email.setText(null);
            tf_login.setText(null);
            tf_number.setText(null);
            tf_address.setText(null);
            tf_role.setText(null);
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

}
