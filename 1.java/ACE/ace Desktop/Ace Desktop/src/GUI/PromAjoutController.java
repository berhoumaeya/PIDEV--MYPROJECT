/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Promotion;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Services.PromotionCrud;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author achref
 */
public class PromAjoutController implements Initializable {
    @FXML
    private Label idclient;
    @FXML
    private TextField txtidclient;
    @FXML
    private TextField txtidprom;
    @FXML
    private TextField txtremise;
    @FXML
    private Label remplissez;
    @FXML
    private Label idevent;
    @FXML
    private Label qte;
    @FXML
    private Button home;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void createPromotion(ActionEvent event) throws SQLException {
                int id_user = Integer.parseInt(txtidclient.getText());
        int remise = Integer.parseInt(txtremise.getText());
        int idprom = 0;
        
        Promotion p = new Promotion (idprom, id_user, remise);
        PromotionCrud rc = new PromotionCrud();
        rc.createPromotion(p);
    }

    @FXML
    private void menu(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil2.fxml"));
            Parent root = loader.load();
            home.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Accueil2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }




    
}
