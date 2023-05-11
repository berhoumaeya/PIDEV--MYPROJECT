package Services;

import Entite.Categorie;
import Utils.DataSource;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eyamo
 */
public class ServiceCategorie implements IService2<Categorie> {

    Connection con = DataSource.getInstance().getConnection();

    private Statement ste;

    public ServiceCategorie() {

        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void ajouterC(Categorie s) throws SQLException {

        String req = "INSERT INTO categories_sponsor (categories, id_sponsor) VALUES (?, ?)";
        PreparedStatement pstmt = con.prepareStatement(req);
        pstmt.setString(1, s.getCategories());
        pstmt.setInt(2, s.getId_sponsor());
        pstmt.executeUpdate();
        // Appeler la méthode de notification
        String message = "Une nouveau catégorie a été ajouter.";
        notifyUser(message);

    }

    public void ajouterPST(Categorie s) throws SQLException {
        String req = "INSERT INTO `categories_sponsor` ( `categories`, `id_sponsor`) VALUES ( ?,?);";

        PreparedStatement pre = con.prepareStatement(req);

        pre.setString(1, s.getCategories());

        pre.executeUpdate();
    }

    @Override
    public void updateC(Categorie s) throws SQLException {
        String req = "update categories_sponsor set  categories=? where id_cat=?";
        PreparedStatement ps = con.prepareStatement(req);

        ps.setString(1, s.getCategories());

        //ps.setInt(2, s.getId_sponsor());
        ps.setInt(2, s.getId_cat());

        ps.executeUpdate();
        // Appeler la méthode de notification
        String message = "Une nouveau catégorie a été modifier.";
        notifyUser(message);
    }

    @Override
    public void DeleteC(int id2) throws SQLException {
        String req = "DELETE FROM categories_sponsor WHERE id_cat=?";
        PreparedStatement pre = con.prepareStatement(req); //executer requete
        pre.setInt(1, id2);

        pre.executeUpdate();

        // Appeler la méthode de notification
        String message = "Une nouveau catégorie a été supprimer.";
        notifyUser(message);

    }

    @Override
    public List<Categorie> readAllC() throws SQLException {
        ArrayList<Categorie> listper = new ArrayList<>();

        String req = "select * from categories_sponsor";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
            int id_cat = res.getInt(1);
            String categories = res.getString(2);

            int id_sponsor = res.getInt(3);
            Categorie s = new Categorie(id_cat, categories, id_sponsor);

            listper.add(s);
        }
        return listper;
    }

    @Override
    public Categorie findByIdC(int id2) throws SQLException {
        String sql = "select * from categories_sponsor where id_cat=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id2);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Categorie s = new Categorie();
            s.setId_cat(rs.getInt("id_cat"));
            s.setCategories(rs.getString("Categories"));

            s.setId_sponsor(rs.getInt("id_sponsor"));
            return s;
        } else {
            return null;
        }

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

}
