
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Post;
import Services.ServicePost;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ALPHA
 */
public class UpdatePostController implements Initializable {

    @FXML
    private TextField tfSujet;
    private Post s;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setPost(Post ev) {
            this.s = ev;
            String nomAsString = s.getSujet();
            tfSujet.setText(nomAsString);
            }
    
    @FXML
    private void butmodifier(ActionEvent event) {
  
  
        try {
            String sujet = tfSujet.getText();
            ServicePost sc = new ServicePost();
            
            
            if (sujet.trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs avant de modifier le post");
                alert.showAndWait();
                return;
            }
            
            
            if (sc.PostExiste(sujet)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Sujet déjà existant");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez choisir un autre sujet.");
                alert.showAndWait();
                return;
            }
            
            s.setSujet(sujet);
            
            sc.update(s);
            System.out.println(s.getPostId() +" "+s.getSujet());
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("état de la modification du post");
            alert.setHeaderText(null);
            alert.setContentText("Le post a été modifié avec succès");
            alert.showAndWait();
            
            Stage stage = (Stage) tfSujet.getScene().getWindow();
            stage.close();
        } catch (SQLException e) {
            System.out.println("err");
        }
        
    }
    
    @FXML
    private void buthome(ActionEvent event) throws IOException {
        
          Parent root = FXMLLoader.load(getClass().getResource("Forum.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
    
}

