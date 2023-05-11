/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.sponsor;

import Services.ServiceSponsor;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eyamo
 */
public class UpdateSponsorController implements Initializable {

    @FXML
    private TextField tfduree;
    private sponsor s;
    @FXML
    private TextField tfintitule;
    @FXML
    private DatePicker tfdatedeb;
    @FXML
    private DatePicker tfdatefin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setSponsor(sponsor ev) {
        this.s = ev;

        String nomAsString = s.getIntitule();

        tfintitule.setText(nomAsString);
        LocalDate localDate = s.getDatdebc().toLocalDate(); // conversion en LocalDate
        String dateDeb = localDate.toString(); // conversion en String

        int duree = s.getDuree_contrat();
        String dureeAsString = Integer.toString(duree);

        tfduree.setText(dureeAsString);

        tfdatedeb.setValue(localDate);
     tfdatefin.setValue(localDate);
    }

    @FXML
    private void buthome(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("window.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void butmodifier(ActionEvent event) throws SQLException {

        String intitule = tfintitule.getText();
        String dureeStr = tfduree.getText();
        LocalDate dateFin = tfdatefin.getValue();
        LocalDate dateDeb = tfdatedeb.getValue();
        ServiceSponsor sc = new ServiceSponsor();

        if (intitule.trim().isEmpty() || dureeStr.trim().isEmpty() || dateFin == null || dateDeb == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs avant de modifier le sponsor");
            alert.showAndWait();
            return;
        }

        if (intitule.equals(s.getIntitule())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nom d'événement identique");
            alert.setHeaderText(null);
            alert.setContentText("Le nom d'événement que vous avez entré est identique à celui existant. Veuillez entrer un nouveau nom.");
            alert.showAndWait();
            return;
        }

        if (sc.sponsorExiste(intitule)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nom d'événement déjà utilisé");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez choisir un autre nom d'événement.");
            alert.showAndWait();
            return;
        }

        s.setDatdebc(java.sql.Date.valueOf(dateDeb));
        s.setDatefinc(java.sql.Date.valueOf(dateFin));

        int duree = Integer.parseInt(dureeStr);
        s.setDuree_contrat(duree);

        sc.update(s);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification d'un sponsor");
        alert.setHeaderText(null);
        alert.setContentText("Le sponsor a été modifié avec succès");
        alert.showAndWait();

        Stage stage = (Stage) tfintitule.getScene().getWindow();
        stage.close();

    }

}
