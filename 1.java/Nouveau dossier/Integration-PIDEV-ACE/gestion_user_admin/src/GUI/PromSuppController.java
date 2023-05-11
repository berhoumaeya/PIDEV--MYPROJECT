/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Promotion;
import Services.PromotionCrud;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author achref
 */
public class PromSuppController implements Initializable {

    @FXML
    private Label idprom;
    @FXML
    private TextField txtidprom;
    @FXML
    private Button home;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        @FXML
    public void deletePromotion(ActionEvent event) throws SQLException {
         int id_prom = Integer.parseInt(txtidprom.getText());
        PromotionCrud rc = new PromotionCrud();
        rc.deletePromotion(id_prom);
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



