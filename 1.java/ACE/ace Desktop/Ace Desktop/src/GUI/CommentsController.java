

package GUI;

import Entite.Message;
import Entite.Post;
import Services.ServiceMessage;
import Services.ServicePost;
import Services.ServiceMessage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;




public class CommentsController implements Initializable {

    @FXML
    private ListView<Message> listview;
    private int id;
    @FXML
    Rating rating;
    
    public void setId(int id){
        this.id=id;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void butmodifier(ActionEvent event) {
    }

    @FXML
    private void butsupp(ActionEvent event) {
        ServicePost ser = new ServicePost();
        ServiceMessage serM = new ServiceMessage();
    Message selected = listview.getSelectionModel().getSelectedItem();
    try {
        //ser.Delete(selected.getPostId());
        serM.DeleteMsg(selected.getPostId(), selected.getContenu());
        
        // Remove the selected item from the listview
        listview.getItems().remove(selected);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Suppression d'un commentaire");
        alert.setHeaderText(null);
        alert.setContentText("Le commentaire a été supprimé avec succès!");
        alert.showAndWait();
    } catch (SQLException e) {
    }
    }

    @FXML
    private void butafficher(ActionEvent event) {
        try {
            
            ServiceMessage sc = new ServiceMessage();
            List<Message> ls = sc.readAllMsgs();

            for (int i = 0; i < ls.size(); i++) {
               Message s = ls.get(i); 
                String text = s.getPostId()+ " - " + s.getContenu();
                if (s.getPostId()==id){
                    System.out.println(s.getMsgId());
                listview.getItems().add(s);}
            }

        } catch (SQLException ex) {
            Logger.getLogger(ForumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void toTranslate(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Translate.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @FXML
    private void toHome(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Forum.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void noter(ActionEvent event) throws IOException{
        try {
            ServiceMessage sc = new ServiceMessage();
            Message selected = listview.getSelectionModel().getSelectedItem();
            selected.setNote(rating.getRating());
            sc.updateNote(selected);
            System.out.println(selected.note);
        } catch (SQLException ex) {
            Logger.getLogger(CommentsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    
    }

    
    }
