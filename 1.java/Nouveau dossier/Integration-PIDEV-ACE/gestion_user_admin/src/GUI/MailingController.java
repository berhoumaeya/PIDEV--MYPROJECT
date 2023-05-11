/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author farah
 */
public class MailingController implements Initializable {

    @FXML
    private TextField from;
    @FXML
    private TextField to;
    @FXML
    private TextField subject;
    @FXML
    private TextField body;
    @FXML
    private TextField password;
    @FXML
    private Button seDeconnecter;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoyer(ActionEvent event) {
        mailingevent.sendEmail(from.getText(), password.getText(), to.getText(), subject.getText(), body.getText());
        
    }

    @FXML
    private void home(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void deleteUser(MouseEvent event) {
    }

    @FXML
    private void seDeconnecter(ActionEvent event) {
            try {
        Parent page1 = FXMLLoader.load(getClass().getResource("LoginAdmin.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
          } catch (IOException ex) {
            Logger.getLogger(LoginAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    @FXML
    private void Retour(ActionEvent event) {
            try {
        Parent page1 = FXMLLoader.load(getClass().getResource("windowSponsor.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
          } catch (IOException ex) {
            Logger.getLogger(LoginAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
}
