/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entite.sponsor;
import Utils.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
//imports notification
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author eyamo
 */
public class ServiceSponsor implements IService<sponsor> {

    Connection con = DataSource.getInstance().getConnection();

    private Statement ste;

    public ServiceSponsor() {

        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void ajouter(sponsor s) throws SQLException {
        String req = "INSERT INTO `sponsor` ( `intitule`,`duree_contrat`,`date_debc`,`date_finc`) VALUES "
                + "( '" + s.getIntitule() + "','" + s.getDuree_contrat() + "','" + s.getDatdebc() + "','" + s.getDatefinc() + "');";

        ste.executeUpdate(req);
        // Appeler la méthode de notification
        String message = "Un nouveau sponsor a été ajouté.";
        notifyUser(message);

    }

    /*   public void ajouterPST(sponsor s) throws SQLException
    {
    String req = "INSERT INTO `sponsor` ( `nom`, `prenom`,`duree_contrat`) VALUES ( ?,?,?);";

     PreparedStatement pre=con.prepareStatement(req);
        
     
     pre.setString(1,s.getNom() );
     pre.setString(2, s.getPrenom());
     pre.setInt(3, s.getDuree_contrat());
    
    
      
     pre.executeUpdate();
    }/*/
    @Override
    public void update(sponsor s) throws SQLException {
        String req = "update sponsor set  intitule=?,duree_contrat=?,date_debc=?,date_finc=?  where id_sponsor=?";
        PreparedStatement ps = con.prepareStatement(req);

        ps.setString(1, s.getIntitule());

        ps.setInt(5, s.getId_sponsor());
        ps.setInt(2, s.getDuree_contrat());
        ps.setDate(3, s.getDatdebc());
        ps.setDate(4, s.getDatefinc());

        ps.executeUpdate();
        // Appeler la méthode de notification
        String message = "Un nouveau sponsor a été modifier.";
        notifyUser(message);
    }

    @Override
    public void Delete(int id) throws SQLException {
        String req = "DELETE FROM sponsor WHERE id_sponsor=?";
        PreparedStatement pre = con.prepareStatement(req); //executer requete
        pre.setInt(1, id);

        pre.executeUpdate();
        // Appeler la méthode de notification
        String message = "Un nouveau sponsor a été supprimer.";
        notifyUser(message);

    }

    @Override
    public List<sponsor> readAll() throws SQLException {
        ArrayList<sponsor> listper = new ArrayList<>();

        String req = "select * from sponsor";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
            int id_sponsor = res.getInt(1);
            Date datefinc = res.getDate(5);
            Date datdebc = res.getDate(4);
            String intitulé = res.getString(2);

            int duree_contrat = res.getInt(3);
            sponsor s = new sponsor(id_sponsor, intitulé, duree_contrat, datdebc, datefinc);

            listper.add(s);
        }

        Collections.sort(listper, new Comparator<sponsor>() {
            @Override
            public int compare(sponsor e1, sponsor e2) {
                return Integer.compare(e1.getDuree_contrat(), e2.getDuree_contrat());
            }
        });

        return listper;
        

    }

    @Override
    public sponsor findById(int id) throws SQLException {
        String sql = "select * from sponsor where id_sponsor=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            sponsor s = new sponsor();
            s.setId_sponsor(rs.getInt("id_sponsor"));
            s.setIntitule(rs.getString("intitulé"));
            System.out.println(rs.getString("intitulé"));

            s.setDatdebc(rs.getDate("datdebc"));
            System.out.println(rs.getDate("datdebc"));

            s.setDatefinc(rs.getDate("datfinc"));
            System.out.println(rs.getDate("datfinc"));

            s.setDuree_contrat(rs.getInt("duree_contrat"));

            return s;
        } else {
            return null;
        }

    }

    public boolean sponsorExiste(String intitule) throws SQLException {
        Connection cnx = DataSource.getInstance().getConnection();

        String query = "SELECT * FROM sponsor WHERE intitule=?";
        PreparedStatement preparedStmt = cnx.prepareStatement(query);
        preparedStmt.setString(1, intitule);
        ResultSet rs = preparedStmt.executeQuery();

        // Vérifier si le résultat de la requête contient des données
        boolean sponsorExiste = rs.next();

        // Fermer les ressources JDBC
        rs.close();
        preparedStmt.close();

        return sponsorExiste;
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

    public List<sponsor> rechercheParIntitule(String intitule) throws SQLException {
        ArrayList<sponsor> resultats = new ArrayList<>();

        // Préparer la requête SQL
        String requete = "SELECT * FROM sponsor WHERE intitule LIKE ?";
        PreparedStatement statement = con.prepareStatement(requete);
        statement.setString(1, "%" + intitule + "%");

        // Exécuter la requête
        ResultSet resultSet = statement.executeQuery();

        // Parcourir les résultats et les ajouter à la liste de résultats
        while (resultSet.next()) {
            int id = resultSet.getInt("id_sponsor");
            String intituleResultat = resultSet.getString("intitule");
            int dureeContrat = resultSet.getInt("duree_contrat");
            Date dateDebut = resultSet.getDate("date_debc");
            Date dateFin = resultSet.getDate("date_finc");

            sponsor resultat = new sponsor(id, intituleResultat, dureeContrat, dateDebut, dateFin);
            resultats.add(resultat);
        }

        // Fermer les ressources JDBC
        resultSet.close();
        statement.close();

        return resultats;
    }

    public List<sponsor> findAllSponsors() throws SQLException {

        String sql = "SELECT * FROM sponsor";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<sponsor> sponsors = new ArrayList<>();

        while (rs.next()) {
            sponsor s = new sponsor();
            s.setId_sponsor(rs.getInt("id_sponsor"));
            s.setIntitule(rs.getString("intitule"));
            s.setDuree_contrat(rs.getInt("duree_contrat"));
            s.setDatdebc(rs.getDate("date_debc"));
            s.setDatefinc(rs.getDate("date_finc"));

            sponsors.add(s);
        }

        return sponsors;
    }

}
