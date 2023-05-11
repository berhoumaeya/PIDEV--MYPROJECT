/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.User;
import Services.ServiceUser;
import Utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class LoginController implements Initializable {

    @FXML
    private TextField loginUser;
    @FXML
    private PasswordField pwdUsers;
    @FXML
    private Button Connect;
    @FXML
    private Hyperlink mdp_oub;
    @FXML
    private Button exit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void ConnectUser(ActionEvent event) {
        // Récupérer les informations de l'utilisateur entrées par l'utilisateur
        String email = loginUser.getText();
        String pwd = pwdUsers.getText();

        // Effectuer une requête SELECT pour récupérer l'utilisateur correspondant dans la base de données
        String query = "SELECT * FROM user WHERE email = ? AND password = ?";
        try {
            PreparedStatement preparedStatement = DataSource.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, pwd);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Vérifier si l'utilisateur existe
            if (resultSet.next()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Connection ");
                alert.setHeaderText(null);
                alert.setContentText("Etes sure de vous connecter ?");
                Optional<ButtonType> action = alert.showAndWait();
                JOptionPane.showMessageDialog(null, "BONJOUR");
                Parent page1;
                try {
                    page1 = FXMLLoader.load(getClass().getResource("Gestion.fxml"));

                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(GestionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // L'utilisateur n'existe pas, afficher un message d'erreur
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setContentText("Vous n'avez pas de compte.");
                alert1.showAndWait();

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void exit(ActionEvent event) {
               try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("choisir_login.fxml"));
            Parent root = loader.load();
            exit.getScene().setRoot(root);
            // JOptionPane.showMessageDialog(null, "Welcome HOME !!");
        } catch (IOException ex) {
            Logger.getLogger(Choisir_loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void GoToRegister(MouseEvent event) {
         FXMLLoader LOADER = new FXMLLoader(getClass().getResource("RegisterUser.fxml"));
                try {
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                      RegisterUserController cntr = LOADER.getController();
                    Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
              
                    window.setScene(sc);
                    window.show();
                } catch (IOException ex) {
                  
    }
    }

    @FXML
    private void ForgetPassword(ActionEvent event) {
               try {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("RecupererMdpController.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 400);
            Stage stage = (Stage) loginUser.getScene().getWindow();
            stage.setScene(scene);
               }catch (IOException ex){
            System.out.println(ex);
        }
    }


    }


