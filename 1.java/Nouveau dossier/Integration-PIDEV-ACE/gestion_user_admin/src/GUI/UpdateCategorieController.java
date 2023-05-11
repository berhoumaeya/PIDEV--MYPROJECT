/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Categorie;
import Services.ServiceCategorie;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eyamo
 */
public class UpdateCategorieController implements Initializable {

    @FXML
    private TextField tfcatg;

    private Categorie c;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void buttmodifiercat(ActionEvent event) throws SQLException {

        String categories = tfcatg.getText();
        c.setCategories(categories);

        ServiceCategorie servicepub = new ServiceCategorie();
        servicepub.updateC(c);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification d'une catégorie");
        alert.setHeaderText(null);
        alert.setContentText("La catégorie a éte modifier avec succés");
        alert.showAndWait();
        Stage stage = (Stage) tfcatg.getScene().getWindow();
        stage.close();

    }

    public void setcategorie(Categorie ev) {
        this.c = ev;

        String nomAsString = c.getCategories();

        tfcatg.setText(nomAsString);

    }

}
