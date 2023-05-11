/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.User;
import Interface.IUtilisateurService;
import Services.ServiceUser;
import Utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ModifierUserController implements Initializable {

    @FXML
    private Button home;

    private Button btn_modifer;
    @FXML
    private TextField tf_username;
    private TextField tf_email;
    @FXML
    private TextField tf_login;
    @FXML
    private TextField tf_number;
    @FXML
    private TextField tf_address;
    @FXML
    private Button modifierUser;

    @FXML
    private ComboBox<Integer> combo_id;
    Connection mc;
    @FXML
    private TextField tf_role;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mc = DataSource.getInstance().getConnection();
        try {

            String requete = "SELECT id FROM user u ";
            Statement st = DataSource.getInstance().getConnection()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            ObservableList<Integer> id = null;
            List<Integer> list = new ArrayList<>();
            while (rs.next()) {

                list.add(rs.getInt("id"));

            }
            id = FXCollections
                    .observableArrayList(list);
            combo_id.setItems(id);
        } catch (SQLException ex) {
            Logger.getLogger(ModifierUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void Exit(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "Are you sure ? :(");
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();

    }

    private void modifier(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierUser.fxml"));
            Parent root = loader.load();
            btn_modifer.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(AjoutUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void menu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
            Parent root = loader.load();
            home.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void updateUser(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Warning");
        alert.setContentText("Voulez-vous modifier cette ligne?");

        String id = combo_id.getSelectionModel().getSelectedItem().toString();
        String user = tf_username.getText();
        String email = tf_email.getText();
        String login = tf_login.getText();
        String number = tf_number.getText();
        String address = tf_address.getText();

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            User u = new User(Integer.parseInt(id), user, email, login, Integer.parseInt(number), address);
            IUtilisateurService es = new ServiceUser();
            es.modifierUser(u);
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Mr/Mme   " + tf_username.getText() + " " + " Vos donnés personelles sont modifiés !", ButtonType.CLOSE);
            alert1.show();

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

}
