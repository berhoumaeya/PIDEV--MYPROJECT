/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Destination;
import Services.ServiceDestination;
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
public class Ajoute_desController implements Initializable {

    @FXML
    private TextField tfville1;
    @FXML
    private TextField tfpays1;
    @FXML
    private Button btnadd;
    @FXML
    private ImageView img2;
    @FXML
    private Button seDeconnecter;

    /**
     * Initializes the controller class.
     */
    
      
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Image myImage = new Image(getClass().getResourceAsStream("../Images/ace.jpg"));
         img2.setImage(myImage);
    }    

    @FXML
    private void add(ActionEvent event) throws SQLException {
            //vide
         if (tfpays1.getText().isEmpty() || tfville1.getText().isEmpty()) {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;
    }
         Destination d = new Destination();
         d.setPays(tfpays1.getText());
         d.setVille(tfville1.getText());
        
        ServiceDestination ser = new ServiceDestination();
      // meme input
        if (ser.getDestinationByCountryAndCity(d.getPays(), d.getVille()) != null) {
        Alert alert = new Alert (Alert.AlertType.ERROR);
        alert.setTitle("Erreur lors de l'ajout d'une destination");
        alert.setHeaderText(null);
        alert.setContentText("Une destination avec le même pays et la même ville existe déjà.");
        alert.showAndWait();
        return;
    }
   
   ser.addDes(d);
   
   Alert alert = new Alert (Alert.AlertType.INFORMATION);
   alert.setTitle("Ajout e'une destination");
   alert.setHeaderText(null);
   alert.setContentText("La destination a éte ajouter avec succés");
   alert.showAndWait();
   
   Stage stage = (Stage) tfville1.getScene().getWindow();
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
