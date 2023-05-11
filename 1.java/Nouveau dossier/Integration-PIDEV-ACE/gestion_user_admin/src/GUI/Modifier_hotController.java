/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Destination;
import Entite.hotel;
import Services.ServiceHotel;
import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class Modifier_hotController implements Initializable {

    @FXML
    private TextField tfetoile;
    @FXML
    private Button btnmodify;
    @FXML
    private TextField tftype;
    @FXML
    private ImageView img1;
    @FXML
    private TextField tfnom;
    private hotel hotels;
    @FXML
    private Button seDeconnecter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void modify(ActionEvent event) throws SQLException {
        
        if (tfnom.getText().isEmpty() || tfetoile.getText().isEmpty() || tftype.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;
        }
        if (!tfetoile.getText().matches("[*]{1,5}")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le champ etoile ne peut contenir que des étoiles (*) de 1 à 5.");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de modification");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous vraiment modifier l'hotel ?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            hotels.setNom(tfnom.getText());
            hotels.setEtoile(tfetoile.getText());
            hotels.setType(tftype.getText());
            ServiceHotel serviceDest = new ServiceHotel();

            if (serviceDest.getho(hotels.getNom(), hotels.getEtoile(), hotels.getType()) != null) {
                Alert alerte = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur lors de l'ajout d'un hotel");
                alert.setHeaderText(null);
                alert.setContentText("Un hotel avec le même données existe déjà.");
                alert.showAndWait();
                return;
            }
            serviceDest.updatehotel(hotels);

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Modification d'un hotel");
            successAlert.setHeaderText(null);
            successAlert.setContentText("L'hotel a été modifiée avec succès");
            successAlert.showAndWait();

            Stage stage = (Stage) tfnom.getScene().getWindow();
            stage.close();
        }

    }

    public void sethotel(hotel hot) {
        this.hotels = hot;

        // Set the destination data to the UI fields
        tfnom.setText(hotels.getNom());
        tfetoile.setText(hotels.getEtoile());
        tftype.setText(hotels.getType());
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
