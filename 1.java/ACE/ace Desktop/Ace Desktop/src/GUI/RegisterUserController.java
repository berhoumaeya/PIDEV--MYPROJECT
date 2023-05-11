/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.User;
import Services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class RegisterUserController implements Initializable {

    @FXML
    private Button RegisterButton;
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
    private Label nomcontrol;
    @FXML
    private Label pwdcontrol;
    @FXML
    private Label emailcontrol;
    @FXML
    private Label logincontrol;
    @FXML
    private Label numcontrol;
    @FXML
    private Label addresscontrol;
    @FXML
    private Button exit;
    @FXML
    private Label verified;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void register(ActionEvent event) throws Exception {
        if (this.isValidated()) {

            Statement stmt;

            ServiceUser us = new ServiceUser();
            User u = new User();
            u.setNom(tf_username.getText());
            u.setPassword(td_pwd.getText());
            u.setEmail(tf_email.getText());
            u.setLogin(tf_login.getText());
            u.setTelephone(Integer.parseInt(tf_number.getText()));
            u.setAddress(tf_address.getText());
            u.setRoles(tf_role.getText());
            u.setIsVerified(Boolean.parseBoolean(verified.getText()));
            us.ajouterUser(u);
            // SendSMS sm = new SendSMS();
            // sm.sendSMS(u);
            tf_username.setText("");
            td_pwd.setText("");
            tf_email.setText("");
            tf_login.setText("");
            tf_number.setText("");
            tf_address.setText("");
            tf_role.setText("");
            Parent page2 = FXMLLoader.load(getClass().getResource("login.fxml"));

            Scene scene2 = new Scene(page2);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(scene2);
            app_stage.show();

        }

    }

    private boolean isValidated() {

        if (tf_username.getText().equals("")) {
            nomcontrol.setText("Ajouter votre nom ");
        } else if (td_pwd.getText().equals("")) {
            pwdcontrol.setText("Ajouter votre password ");
        } else if (tf_email.getText().equals("")) {
            emailcontrol.setText("Ajouter votre email  ");
        } else if (tf_login.getText().equals("")) {
            logincontrol.setText("Ajouter votre login  ");
        } else if (tf_number.getText().equals("")) {
            numcontrol.setText("Ajouter votre numéro de téléphone ");
        } else if (tf_address.getText().equals("")) {
            addresscontrol.setText("Ajouter votre email  ");
        } else {
            return true;
        }
        return false;
    }

    @FXML
    private void nomControl(KeyEvent event) {
        String PATTERN = "^[a-z A-Z]{3,20}$";
        Pattern p = Pattern.compile(PATTERN);
        Matcher match = p.matcher(tf_username.getText());
        if (!match.matches()) {
            nomcontrol.setText("Nom non valide");
        } else {
            nomcontrol.setText(null);
        }
    }

    @FXML
    private void pwdControl(KeyEvent event) {
        String PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*/])(?=\\S+$).{8,15}$";
        Pattern p = Pattern.compile(PATTERN);
        Matcher match = p.matcher(td_pwd.getText());
        if (!match.matches()) {
            pwdcontrol.setText("Mot de passe non valide");
        } else {
            pwdcontrol.setText(null);
        }
    }

    @FXML
    private void emailControl(KeyEvent event) {
        String PATTERN = "^[a-zA-Z0-9]{0,30}[.][a-zA-Z0-9]{0,30}@[a-zA-Z0-9]{0,10}[.][a-zA-Z]{0,5}$";
        Pattern p = Pattern.compile(PATTERN);
        Matcher match = p.matcher(tf_email.getText());
        if (!match.matches()) {
            emailcontrol.setText("Email non valide");
        } else {
            emailcontrol.setText(null);
        }
    }

    @FXML
    private void loginControl(KeyEvent event) {
        String PATTERN = "^[a-zA-Z0-9\\@\\_\\-]{3,20}$";
        Pattern p = Pattern.compile(PATTERN);
        Matcher match = p.matcher(tf_login.getText());
        if (!match.matches()) {
            logincontrol.setText("Login non valide");
        } else {
            logincontrol.setText(null);
        }
    }

    @FXML
    private void numControl(KeyEvent event) {
        String PATTERN = "^(90|92|93|94|95|96|97|98|99|20|21|22|23|24|25|26|27|28|29|50|51|52|53|54|55|40|41|42|43)[0-9]{6}";
        Pattern p = Pattern.compile(PATTERN);
        Matcher match = p.matcher(tf_number.getText());
        if (!match.matches()) {
            numcontrol.setText("Numéro de téléphone non valide");
        } else {
            numcontrol.setText(null);
        }
    }

    @FXML
    private void addressControl(KeyEvent event) {
        String PATTERN = "^[a-z A-Z]{3,20}$";
        Pattern p = Pattern.compile(PATTERN);
        Matcher match = p.matcher(tf_address.getText());
        if (!match.matches()) {
            addresscontrol.setText("Address non valide");
        } else {
            addresscontrol.setText(null);
        }
    }

    @FXML
    private void exit(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            exit.getScene().setRoot(root);
            // JOptionPane.showMessageDialog(null, "Welcome HOME !!");
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
