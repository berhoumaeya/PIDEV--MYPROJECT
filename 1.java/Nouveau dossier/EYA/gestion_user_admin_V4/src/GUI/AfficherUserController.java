/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.User;
import Services.ServiceUser;
import Utils.DataSource;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class AfficherUserController implements Initializable {

    @FXML
    private TableView<User> tableViewuser;
    @FXML
    private TableColumn<User, Integer> idcol;
    @FXML
    private TableColumn<User, String> colnom;
    @FXML
    private TableColumn<User, String> colpwd;
    @FXML
    private TableColumn<User, String> colmail;
    @FXML
    private TableColumn<User, String> collogin;
    @FXML
    private TableColumn<User, Integer> colnumber;
    @FXML
    private TableColumn<User, String> coladdress;
    @FXML
    private TableColumn<User, String> colrole;
    @FXML
    private Button home;
    private Button btn_modifer;
    private Button btnA;
    private Button btnAff;
    ObservableList<User> UserList;
    @FXML
    private Button deleteButton;
    @FXML
    private TextArea idtxt;
    @FXML
    private Button PdfButton;
    Connection mc;
    @FXML
    private TextArea rechercher;

    //PrepareStatement ste ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherUser();

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

    public void afficherUser() {

        UserList = FXCollections.observableArrayList();
        try {
            String requete;
            requete = "SELECT * FROM user u  ";
            Statement st = DataSource.getInstance().getConnection()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setNom(rs.getString("nom"));
                u.setPassword(rs.getString("password"));
                u.setEmail(rs.getString("email"));
                u.setLogin(rs.getString("login"));
                u.setTelephone(rs.getInt("telephone"));
                u.setAddress(rs.getString("address"));
                u.setRoles(rs.getString("roles"));

                UserList.add(u);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        idcol.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getId()));
        colnom.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNom()));
        colpwd.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPassword()));
        colmail.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        collogin.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLogin()));
        colnumber.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getTelephone()));
        coladdress.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNom()));
        colrole.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRoles()));
        tableViewuser.setItems(UserList);
        search();
    }

    @FXML
    private void deleteUser(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Warning");
        alert.setContentText("Voulez-vous supprimer cette ligne ?");

        String value1 = idtxt.getText();
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            User u = new User(Integer.parseInt(value1));

            ServiceUser us = new ServiceUser();
            us.supprimerUser(u);
            refresh();

            idtxt.setText(null);

        }
    }

    @FXML
    private void selectid(MouseEvent event) {
        User clicked = tableViewuser.getSelectionModel().getSelectedItem();
        idtxt.setText(String.valueOf(clicked.getId()));

    }

    public void refresh() {

        UserList.clear();
        UserList = FXCollections.observableArrayList();
        try {
            String requete;
            requete = "SELECT * FROM user u  ";
            Statement st = DataSource.getInstance().getConnection()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setNom(rs.getString("nom"));
                u.setPassword(rs.getString("password"));
                u.setEmail(rs.getString("email"));
                u.setLogin(rs.getString("login"));
                u.setTelephone(rs.getInt("telephone"));
                u.setAddress(rs.getString("address"));
                u.setRoles(rs.getString("roles"));

                UserList.add(u);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        tableViewuser.setItems(UserList);
        search();
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
    private void addPDF(MouseEvent event) throws FileNotFoundException, DocumentException, SQLException, IOException {

        String sql = "SELECT * from user";
        PreparedStatement pst = DataSource.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream("./ListeDesUtilisateurs.pdf"));

        doc.open();

        doc.add(new Paragraph("   "));
        doc.add(new Paragraph(" *********************************** Liste Des Utilisateurs *********************************** "));
        doc.add(new Paragraph("   "));
        doc.add(new Paragraph("   "));

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(50);
        PdfPCell cell;

        cell = new PdfPCell(new Phrase("Nom", FontFactory.getFont("Comic Sans MS", 14)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Login", FontFactory.getFont("Comic Sans MS", 14)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Email", FontFactory.getFont("Comic Sans MS", 14)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Roles", FontFactory.getFont("Comic Sans MS", 14)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(cell);

        while (rs.next()) {

            User e = new User();

            e.setNom(rs.getString("nom"));
            e.setLogin(rs.getString("login"));
            e.setEmail(rs.getString("email"));
            e.setRoles(rs.getString("s"));

            cell = new PdfPCell(new Phrase(e.getNom(), FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(e.getLogin(), FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(e.getEmail(), FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(e.getRoles(), FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }

        doc.add(table);
        doc.close();
        Desktop.getDesktop().open(new File("./ListeDesUtilisateurs.pdf"));

    }

    private void search() {

        FilteredList<User> filteredData = new FilteredList<>(UserList, b -> true);
        rechercher.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(User -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(User.getLogin()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(User.getNom()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<User> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableViewuser.comparatorProperty());
        tableViewuser.setItems(sortedData);
    }

    @FXML
    private void lbUsernameSS_Changed(InputMethodEvent event) {
    }

    @FXML
    private void lbUsernameSS_Released(KeyEvent event) {
    }

}
