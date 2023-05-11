
package GUI;

import Entite.Post;
import Services.ServicePost;
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


public class AddPostController implements Initializable {

    
    @FXML
    private TextField tfSujet;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private void savePost(ActionEvent event) throws SQLException {

 
        String Sujet = tfSujet.getText();


        if (Sujet.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all required fields!");
            alert.showAndWait();
            return;
        }

        try {
            ServicePost pc = new ServicePost();

            // Vérifier si un sponsor avec le même intitulé existe déjà
            if (pc.PostExiste(Sujet)) {
             // Afficher un message d'erreur à l'utilisateur
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Un Post dont le sujet est : \"" + Sujet + "\" existe déjà.");
                alert.showAndWait();
                return;
            }

            pc.ajouter(new Post(Sujet));

            // Display a success alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Data inserted successfully!");
            alert.showAndWait();
        } catch (SQLException e) {
            // Display an error alert
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while inserting data into the database: " + e.getMessage());
            alert.showAndWait();
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
