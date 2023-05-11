/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entite.Evenement;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import Utils.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Servicesevent implements IService<Evenement> {

    Connection con = DataSource.getInstance().getConnection();

    private Statement ste;

    public Servicesevent() {

        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void ajouter(Evenement t) throws SQLException {
        String req = "INSERT INTO evenement (nom_event, date_deb, date_fin, duree, prix, destin_id) VALUES "
                + "( '" + t.getNom_event() + "', '" + t.getDate_deb() + "', '" + t.getDate_fin() + "', '" + t.getDuree() + "', '" + t.getPrix() + "', '" + t.getId() + "');";

        ste.executeUpdate(req);
        String message = "Un nouveau événement a été ajouté.";
        notifyUser(message);
    }

    @Override
    public void update(Evenement t) throws SQLException {
        String req = "UPDATE evenement SET nom_event=?, date_deb=?, date_fin=?, duree=?, prix=?, destin_id=? WHERE id_event=?";
        PreparedStatement ps = con.prepareStatement(req);
        ps.setString(1, t.getNom_event());
        ps.setDate(2, t.getDate_deb());
        ps.setDate(3, t.getDate_fin());
        ps.setFloat(4, t.getDuree());
        ps.setFloat(5, t.getPrix());
        ps.setInt(6, t.getId());
        ps.setInt(7, t.getId_event());

        ps.executeUpdate();

       
        String message = "Un  événement a été modifié.";
        notifyUser(message);

    }

    @Override
    public void Delete(int id) throws SQLException {
        String req = "DELETE FROM evenement WHERE id_event =?";

        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, id);

        pre.executeUpdate();
        String message = "Un événement a été supprimée , on va vous informer encore plus de detail par un mailing 🙁.";
        notifyUser(message);
    }

    @Override
    public List<Evenement> readAll() throws SQLException {
        ArrayList<Evenement> listper = new ArrayList<>();

        String req = "SELECT * FROM evenement";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
            int id_event = res.getInt("id_event");
            String nom_event = res.getString("nom_event");
            Date date_deb = res.getDate("date_deb");
            Date date_fin = res.getDate("date_fin");
            float duree = res.getFloat("duree");
            float prix = res.getFloat("prix");
            int id = res.getInt("destin_id");

            Evenement e = new Evenement(id_event, duree, prix, id, nom_event, date_deb, date_fin);
            listper.add(e);
        }

        // Sort the events by price using a custom Comparator
        Collections.sort(listper, new Comparator<Evenement>() {
            @Override
            public int compare(Evenement e1, Evenement e2) {
                return Float.compare(e1.getPrix(), e2.getPrix());
            }
        });

        return listper;
    }

    @Override
    public Evenement findById(int id) throws SQLException {
        String sql = "SELECT * FROM evenement WHERE id_event=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id_event = rs.getInt("id_event");
            String nom_event = rs.getString("nom_event");
            Date date_deb = rs.getDate("date_deb");
            Date date_fin = rs.getDate("date_fin");
            float duree = rs.getFloat("duree");
            float prix = rs.getFloat("prix");
            int id_user = rs.getInt("id");

            Evenement e = new Evenement(id_event, duree, prix, id, nom_event, date_deb, date_fin);
            return e;

        } else {
            return null;
        }
    }

    public boolean verifierUniciteEvenement(String nom_event) {
        boolean existeDeja = false;
        try {
            Connection conn = DataSource.getInstance().getConnection();;
            String sql = "SELECT * FROM evenement WHERE nom_event = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, nom_event);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                existeDeja = true;
            }

        } catch (SQLException e) {
            // gérer l'exception, par exemple en affichant un message d'erreur
        }
        return existeDeja;
    }

    private void notifyUser(String message) {
        if (SystemTray.isSupported()) {
            try {
                // Initialiser SystemTray
                SystemTray tray = SystemTray.getSystemTray();

                // Créer une icône pour la notification
                Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
                TrayIcon trayIcon = new TrayIcon(image, "Notification");

                // Ajouter l'icône au SystemTray
                tray.add(trayIcon);

                // Afficher la notification
                trayIcon.displayMessage("Notification", message, TrayIcon.MessageType.INFO);
            } catch (AWTException e) {
                System.err.println("Erreur lors de l'initialisation du SystemTray: " + e);
            }
        } else {
            System.out.println("SystemTray n'est pas pris en charge");
        }
    }

    public List<Evenement> findAllEvents() throws SQLException {

        String sql = "SELECT * FROM evenement";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Evenement> event = new ArrayList<>();

        while (rs.next()) {
            Evenement e = new Evenement();

               e.setId_event(rs.getInt("id_event"));
            e.setDuree(rs.getFloat("duree"));
            e.setPrix(rs.getFloat("prix"));
            e.setId(rs.getInt("id"));
            e.setNom_event(rs.getString("nom_event"));
            e.setDate_deb(rs.getDate("date_deb"));
            e.setDate_fin(rs.getDate("date_fin"));

            event.add(e);
        }

        return event;
    }
}