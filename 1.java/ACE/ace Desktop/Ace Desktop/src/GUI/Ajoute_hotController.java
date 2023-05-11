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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class Ajoute_hotController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfetoile;
    @FXML
    private Button btnajout;
    @FXML
    private TextField tftype;
    @FXML
    private ImageView img1;
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
    private void ajouter(ActionEvent event) throws SQLException {/*
          hotel d = new hotel();
         d.setNom(tfnom.getText());
         d.setEtoile(tfetoile.getText());
         d.setType(tftype.getText());
        
      ServiceHotel ser = new ServiceHotel();
   
   ser.addhotel(d);
   
   Alert alert = new Alert (Alert.AlertType.INFORMATION);
   alert.setTitle("Ajout e'une destination");
   alert.setHeaderText(null);
   alert.setContentText("La destination a éte ajouter avec succés");
   alert.showAndWait();
   
   Stage stage = (Stage) tfnom.getScene().getWindow();
        stage.close();
        
    */ //vide
         if (tfnom.getText().isEmpty() || tfetoile.getText().isEmpty()|| tftype.getText().isEmpty()) {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;
    }
         // if (!tfetoile.getText().matches("[*]{1,5}")) {
                if (!tfetoile.getText().matches("(1|2|3|4|5)")) {

        Alert alert = new Alert (Alert.AlertType.ERROR);
        alert.setTitle("Erreur lors de l'ajout d'un hotel");
        alert.setHeaderText(null);
        alert.setContentText("Le champ 'Etoile' doit contenir des nombres 1 à 5 .");
        alert.showAndWait();
        return;
    }
        hotel d = new hotel();
        d.setNom(tfnom.getText());
         d.setEtoile(tfetoile.getText());
         d.setType(tftype.getText());
        
        ServiceHotel ser = new ServiceHotel();
      // meme input
        if (ser.getho(d.getNom(), d.getEtoile(),d.getType()) != null) {
        Alert alert = new Alert (Alert.AlertType.ERROR);
        alert.setTitle("Erreur lors de l'ajout d'un hotel");
        alert.setHeaderText(null);
        alert.setContentText("Un hotel avec le même données existe déjà.");
        alert.showAndWait();
        return;
    }
   
   ser.addhotel(d);
   
   Alert alert = new Alert (Alert.AlertType.INFORMATION);
   alert.setTitle("Ajout d'un hotel");
   alert.setHeaderText(null);
   alert.setContentText("L'hotel a éte ajouter avec succés");
   alert.showAndWait();
   
   Stage stage = (Stage) tfnom.getScene().getWindow();
        stage.close();
    
    
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
