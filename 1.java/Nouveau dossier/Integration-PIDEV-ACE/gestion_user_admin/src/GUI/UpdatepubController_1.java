/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.publicite;
import Services.Servicepublicite;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author farah
 */
public class UpdatepubController_1 implements Initializable {

    @FXML
    private TextField txttype;
    private publicite pub;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void updatepub(ActionEvent event) throws SQLException {
        
 String type = txttype.getText();
       pub.setType(type);

       Servicepublicite  servicepub = new Servicepublicite();
        servicepub.updatePub(pub);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification d'une publicité");
        alert.setHeaderText(null);
        alert.setContentText("La publicité a éte modifier avec succés");
        alert.showAndWait();
        Stage stage = (Stage) txttype.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void backhome(ActionEvent event) throws IOException {
              Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

    public void setPub(publicite p) {
        this.pub = p;
       String type = p.getType();

        txttype.setText(type);

    }

}
