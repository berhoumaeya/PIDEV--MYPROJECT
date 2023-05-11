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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author farah
 */
public class AddpubController implements Initializable {

    @FXML
    private Label labeltype;
    @FXML
    private TextField txttype;
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
