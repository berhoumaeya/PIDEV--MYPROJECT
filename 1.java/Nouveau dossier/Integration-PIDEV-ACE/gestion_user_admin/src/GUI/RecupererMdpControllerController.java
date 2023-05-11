/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.User;
import Services.ServiceUser;
import Services.sendMail;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class RecupererMdpControllerController implements Initializable {

    @FXML
    private TextField emailTf;
private final String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
  ServiceUser us = new ServiceUser();
    /**
     * Initializes the controller class.
     */
    
public static String Randompwd()
    {
        //choisissez un caractÃ©re au hasard Ã  partir de cette chaÃ®ne
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder s = new StringBuilder(9);

        for (int i = 0; i < 9; i++) {
            int index = (int)(str.length() * Math.random());
            s.append(str.charAt(index));
        }
        return s.toString();
    }
    @FXML
    private Button exit;

    @FXML
    private void RecupererMdp(ActionEvent event) {
          if (!emailTf.getText().matches(emailRegex) ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur d'inscription");
            alert.setHeaderText("L'e-mail n'est pas valide.");
            alert.showAndWait();
            Parent loader = null;
            try {
                loader = FXMLLoader.load(getClass().getResource("login.fxml"));
                emailTf.getScene().setRoot(loader);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
              try {
                  User u = us.GetByMail(emailTf.getText());
                  System.out.println(u.getEmail());
                  if(u != null){
                      String newPwd =Randompwd();
                      us.updatePwd(u.getEmail(),newPwd);
                      sendMail.sendMail(emailTf.getText(),"Recuperer votre compte","Bonjour "+u.getNom() +"\nvotre nouveau mot de passe est :"+newPwd);
                      try{      Parent loader = null;
                      
                      loader = FXMLLoader.load(getClass().getResource("login.fxml"));
                      emailTf.getScene().setRoot(loader);
                      }catch (IOException ex){
                          System.out.println(ex);
                      }
                      
                  }
                  else {
                      System.out.println("email inexisant");
                  } } catch (SQLException ex) {
                  Logger.getLogger(RecupererMdpControllerController.class.getName()).log(Level.SEVERE, null, ex);
              }
        }
    }
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  

    @FXML
    private void exit(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            exit.getScene().setRoot(root);
            // JOptionPane.showMessageDialog(null, "Welcome HOME !!");
        } catch (IOException ex) {
            Logger.getLogger(Choisir_loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
