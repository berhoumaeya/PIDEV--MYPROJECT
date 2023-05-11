/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Admin;
import Entite.User;
import Interface.IAdminService;
import Services.ServiceAdmin;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author TECHN
 */
public class AfficherAdminController implements Initializable {

    @FXML
    private Button home;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView<Admin> tableViewadmin;
    @FXML
    private TableColumn<Admin, Integer> id_admin;
    @FXML
    private TableColumn<Admin, Integer> id_user;
    @FXML
    private TableColumn<Admin, String> col_login;
    @FXML
    private TableColumn<Admin, String> col_pwd;
    @FXML
    private TableColumn<Admin, String> col_email;

    ObservableList<Admin> AdminList;
    @FXML
    private TextArea idadmin_txt;
    @FXML
    private TextArea rechercher;
    @FXML
    private Button PdfButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherAdmin();
    }

    public void afficherAdmin() {

        AdminList = FXCollections.observableArrayList();
        try {
            String requete;
            requete = "SELECT * FROM admin a  ";
            Statement st = DataSource.getInstance().getConnection()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Admin a = new Admin();
                a.setId_admin(rs.getInt("id_admin"));
                a.setId_user(rs.getInt("id_user"));
                a.setLogin(rs.getString("login"));
                a.setPwd(rs.getString("pwd"));
                a.setEmail(rs.getString("email"));

                AdminList.add(a);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        id_admin.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getId_admin()));
        id_user.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getId()));

        col_login.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLogin()));
        col_pwd.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPwd()));
        col_email.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));

        tableViewadmin.setItems(AdminList);
        search();
    }

    @FXML
    private void menu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AccueilAd.fxml"));
            Parent root = loader.load();
            home.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AccueilAdController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deleteAdmin(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Warning");
        alert.setContentText("Voulez-vous supprimer cette ligne ?");

        String value1 = idadmin_txt.getText();
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            Admin a = new Admin(Integer.parseInt(value1));

            IAdminService us = new ServiceAdmin();
            us.supprimerAdmin(a);
            refresh();

            idadmin_txt.setText(null);

        }

    }

    @FXML
    private void selectid(MouseEvent event) {
        Admin clicked = tableViewadmin.getSelectionModel().getSelectedItem();
        idadmin_txt.setText(String.valueOf(clicked.getId_admin()));
    }

    public void refresh() {

        AdminList.clear();
        AdminList = FXCollections.observableArrayList();
        try {
            String requete;
            requete = "SELECT * FROM admin a  ";
            Statement st = DataSource.getInstance().getConnection()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Admin a = new Admin();
                a.setId_admin(rs.getInt("id_admin"));
                a.setId_user(rs.getInt("id_user"));
                a.setLogin(rs.getString("login"));
                a.setPwd(rs.getString("pwd"));
                a.setEmail(rs.getString("email"));

                AdminList.add(a);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        tableViewadmin.setItems(AdminList);
        search();
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

    private void search() {

        FilteredList<Admin> filteredData = new FilteredList<>(AdminList, b -> true);
        rechercher.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(Admin -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(Admin.getLogin()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Admin> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableViewadmin.comparatorProperty());
        tableViewadmin.setItems(sortedData);
    }

    @FXML
    private void addPDF(MouseEvent event) throws FileNotFoundException, DocumentException, SQLException, IOException {

        String sql = "SELECT * from admin";
        PreparedStatement pst = DataSource.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream("./ListeDesAdmins.pdf"));

        doc.open();

        doc.add(new Paragraph("   "));
        doc.add(new Paragraph(" *********************************** Liste Des Administrateurs *********************************** "));
        doc.add(new Paragraph("   "));
        doc.add(new Paragraph("   "));

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(50);
        PdfPCell cell;

        cell = new PdfPCell(new Phrase("Login", FontFactory.getFont("Comic Sans MS", 14)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Password", FontFactory.getFont("Comic Sans MS", 14)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Email", FontFactory.getFont("Comic Sans MS", 14)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(cell);

        while (rs.next()) {

            User e = new User();

            e.setLogin(rs.getString("login"));
            e.setPwd(rs.getString("pwd"));
            e.setEmail(rs.getString("email"));

            cell = new PdfPCell(new Phrase(e.getLogin(), FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(e.getPwd(), FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(e.getEmail(), FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

        }

        doc.add(table);
        doc.close();
        Desktop.getDesktop().open(new File("./ListeDesAdmins.pdf"));

    }

}
