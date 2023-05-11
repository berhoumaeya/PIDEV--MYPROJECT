/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.Servicesevent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author farah
 */
public class SupprimerController_1 implements Initializable {

        @FXML
    private Label labelsupprimer;

    @FXML
    private TextField txtsupprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Supprimer(ActionEvent event) throws SQLException {
  
    int id_event = Integer.parseInt(txtsupprimer.getText());


Servicesevent service = new Servicesevent();

    service.Delete(id_event);
   
   

        
    }

    @FXML
    private void backbtn(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
    
}
