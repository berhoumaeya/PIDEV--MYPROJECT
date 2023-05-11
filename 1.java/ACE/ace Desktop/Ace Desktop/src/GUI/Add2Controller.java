/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Evenement;
import Services.Servicesevent;

import com.sun.javafx.util.Utils;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;


/**
 * FXML Controller class
 *
 * @author farah
 */
public class Add2Controller implements Initializable {


    private TextField txtduree;

    private TextField txtprix;
    private DatePicker datedeb;
    private DatePicker datefin;
    private TextField txtnomevent;
    @FXML
    private DatePicker tfdatedeb;
    @FXML
    private DatePicker tfdatefin;
    @FXML
    private TextField tfintitule;
    @FXML
    private TextField tfduree;
    @FXML
    private Button btnajouter;
    @FXML
    private Button seDeconnecter;

    
    
    void Add(ActionEvent event) throws SQLException {
        LocalDate date_fin = datefin.getValue();
        LocalDate date_deb = datedeb.getValue();
        String nom_event = txtnomevent.getText();

        // Vérification des champs vides
        if (date_fin == null || date_deb == null || nom_event.isEmpty() || txtduree.getText().isEmpty() || txtprix.getText().isEmpty()) {
            // Affichage d'un message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;
        }

        // Vérification que le champ de prix ne contient pas de caractères alphabétiques
        try {
            float prix = Float.parseFloat(txtprix.getText());
        } catch (NumberFormatException e) {
            // Affichage d'un message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le champ de prix ne doit pas contenir de caractères alphabétiques !");
            alert.showAndWait();
            return;
        }

        float duree = Float.parseFloat(txtduree.getText());
        float prix = Float.parseFloat(txtprix.getText());

        Servicesevent pc = new Servicesevent();
        // Vérification de l'unicité de l'événement
        if (pc.verifierUniciteEvenement(nom_event)) {
            // Affichage d'un message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Cet événement existe déjà !");
            alert.showAndWait();
            return;
        }

        pc.ajouter(new Evenement(duree, prix, 25, nom_event, java.sql.Date.valueOf(date_deb), java.sql.Date.valueOf(date_fin)));

        Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("Ajout d'événement");
alert.setHeaderText(null);
alert.setContentText("L'événement a été ajouté avec succès !");
alert.showAndWait();



    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void backbtn(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buthome(ActionEvent event) {
    }

    @FXML
    private void savesponsor(ActionEvent event) {
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
   

}
