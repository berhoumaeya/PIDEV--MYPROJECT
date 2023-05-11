/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Mail;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eyamo
 */
public class MailsponsorController implements Initializable {

    @FXML
    private TextField password;
    @FXML
    private TextField to;
    @FXML
    private TextField subject;
    @FXML
    private TextField body;
    @FXML
    private TextField from;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void mail(ActionEvent event) {
  Mail.sendEmail(from.getText(), password.getText(), to.getText(), subject.getText(), body.getText());
    }

    @FXML
    private void butthome(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("windowSponsor.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
    
}
