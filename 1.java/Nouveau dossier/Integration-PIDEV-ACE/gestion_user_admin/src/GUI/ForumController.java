
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Message;
import Entite.Post;
import Services.ServicePost;
import java.awt.Label;
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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ALPHA
 */
public class ForumController implements Initializable {

    @FXML
    private ListView<Post> listview;
  
    private Post selectedPost;
    @FXML
    private Button home;
    @FXML
    private Button home1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ServicePost sc = new ServicePost();
            List<Post> ls = sc.readAll();

            for (int i = 0; i < ls.size(); i++) {
               Post s = ls.get(i);
                String text = s.getPostId()+ " - " + s.getSujet();
                listview.getItems().add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ForumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void butmodifier(ActionEvent event) throws IOException {
        Post selectedPost = listview.getSelectionModel().getSelectedItem();
            if (selectedPost != null) {
                try {
                    // Load the "UpdatePost" FXML file
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdatePost.fxml"));
                    Parent root = loader.load();

                    // Get the controller for the "UpdatePost" scene
                    UpdatePostController controller = loader.getController();

                    // Set the selected post in the controller
                    controller.setPost(selectedPost);

                    // Create the update scene
                    Scene updateScene = new Scene(root);

                    // Show the update scene
                    Stage updateStage = new Stage();
                    updateStage.setScene(updateScene);
                    updateStage.show();

                    // Update the post's sujet attribute if the user clicked "Save"
                    if (updateScene.getUserData() != null) {
                        String newSujet = (String) updateScene.getUserData();
                        selectedPost.setSujet(newSujet);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

    }
    
    @FXML
    private void comment(ActionEvent event) throws IOException {
        Post selectedPost = listview.getSelectionModel().getSelectedItem();
            if (selectedPost != null) {
                try {
                    // Load the "UpdatePost" FXML file
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AddComment.fxml"));
                    Parent root = loader.load();

                    // Get the controller for the "UpdatePost" scene
                    AddCommentController controller = loader.getController();

                    // Set the selected post in the controller
                    
                    Message m = new Message();
                    m.setPostId(selectedPost.getPostId());
                    controller.setMessage(m);
                    
                    
                    // Create the update scene
                    Scene updateScene = new Scene(root);

                    // Show the update scene
                    Stage updateStage = new Stage();
                    updateStage.setScene(updateScene);
                    updateStage.show();

                    // Update the post's sujet attribute if the user clicked "Save"
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

    }
    
    @FXML
    private void AccPost(ActionEvent event) throws IOException, SQLException {
        Post selectedPost = listview.getSelectionModel().getSelectedItem();
            if (selectedPost != null) {
                try {
                    // Load the "UpdatePost" FXML file
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Comments.fxml"));
                    Parent root = loader.load();

                    // Get the controller for the "UpdatePost" scene
                    CommentsController controller = loader.getController();

                    // Set the selected post in the controller
                    
                    controller.setId(selectedPost.getPostId());
                    
                    
                    
                    
                    
                    
                    // Create the update scene
                    Scene updateScene = new Scene(root);

                    // Show the update scene
                    Stage updateStage = new Stage();
                    updateStage.setScene(updateScene);
                    updateStage.show();

                    // Update the post's sujet attribute if the user clicked "Save"
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

    }

    @FXML
    private void butsupp(ActionEvent event) {
        ServicePost ser = new ServicePost();
    Post selected = listview.getSelectionModel().getSelectedItem();
    try {
        ser.Delete(selected.getPostId());
        // Remove the selected item from the listview
        listview.getItems().remove(selected);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Suppression d'un post");
        alert.setHeaderText(null);
        alert.setContentText("Le post a été supprimé avec succès!");
        alert.showAndWait();
    } catch (SQLException e) {
    }
    }
    

    @FXML
    private void butajouter(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AddPost.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
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
    private void deleteUser(MouseEvent event) {
    }

    @FXML
    private void seDeconnecter(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginAdmin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 400);
            Stage stage = (Stage) home.getScene().getWindow();
            stage.setScene(scene);
            // stage.show();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Succes");
            alert.setContentText("Déconnexion  avec succes");
            alert.showAndWait();
         
        } catch (IOException ex) {
            Logger.getLogger(LoginAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void menu(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionAdmin.fxml"));
            JOptionPane.showMessageDialog(null, "Welcome HOME !!");
            Parent root = loader.load();
            home.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(GestionAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

