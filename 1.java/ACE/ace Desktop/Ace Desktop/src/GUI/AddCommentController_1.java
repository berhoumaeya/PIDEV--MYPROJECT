
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Post;
import Entite.Message;
import Services.ServiceMessage;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ALPHA
 */
public class AddCommentController_1 implements Initializable {

    @FXML
    private TextField tfSujet;
    @FXML
    private Button btnajouter;
    int id_post;
    private Message s;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setIdPost(int id){
        this.id_post = id;
    }

    public void setMessage(Message ev) {
            this.s = ev;
            }
    
    @FXML
    private void savePost(ActionEvent event) {
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
            ServiceMessage pc = new ServiceMessage();

            if (pc.MessageExiste(Sujet)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Un Post dont le sujet est : \"" + Sujet + "\" existe déjà.");
                alert.showAndWait();
                return;
            }
            
            System.out.println(s.getPostId());
            s.setContenu(Sujet);

            pc.ajouterMsg(s);


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Data inserted successfully!");
            alert.showAndWait();
        } catch (SQLException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while inserting data into the database: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void buthome(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Forum.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}

