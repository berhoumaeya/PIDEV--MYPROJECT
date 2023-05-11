/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.publicite;
import Services.Servicepublicite;
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
public class AddpubController_1 implements Initializable {

    @FXML
    private Label labeltype;
    @FXML
    private TextField txttype;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addpub(ActionEvent event) throws SQLException {
        String type = txttype.getText();

        publicite c = new publicite(type, 1);
        Servicepublicite sc = new Servicepublicite();

        sc.ajouterPub(c);

        // Display a success message to the user
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText("La publicité a  éte ajouter avec succés.");
        alert.showAndWait();
        // Clear the input fields
        ;
        txttype.clear();
    }

    @FXML
    private void backhome(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
