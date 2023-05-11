 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;
import Entite.publicite;
import Entite.publicite;
import Utils.DataSource;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author farah
 */
public class Servicepublicite implements IServicepub <publicite> {

    Connection con = DataSource.getInstance().getConnection();

    private Statement ste;

    public Servicepublicite() {

        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
@Override
    public void ajouterPub(publicite p) throws SQLException {
        String req = "INSERT INTO `publicite` (   `type`,`id_event`) VALUES "
                + "(    '" + p.getType() + "', '" + p.getId_event() + "' );";

        ste.executeUpdate(req);
         String message = "Une nouvelle publicité a été ajouté.";
        notifyUser(message);
    }

  

    @Override
    public void updatePub(publicite p) throws SQLException {
        String req="update publicite set id_event=?,type=? where id_pub=?";
            PreparedStatement ps=con.prepareStatement(req);
            ps.setInt(3,p.getId_pub());
            ps.setString(2, p.getType());
            ps.setInt(1, p.getId_event());
          
            
            ps.executeUpdate();
            String message = "Une  publicité a été modifiée.";
        notifyUser(message);
    }

  @Override
    public void DeletePub( int id2) throws SQLException {
    String req = "DELETE FROM publicite WHERE id_pub =?";
 
    PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, id2);
 
    pre.executeUpdate();
       String message = "Une  publicité a été supprimée.";
        notifyUser(message);
 }
   
    @Override
    public List<publicite> readAllPub() throws SQLException {
        ArrayList<publicite> listper = new ArrayList<>();

        String req = "select * from publicite";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
            int id_pub = res.getInt(1);
            String type = res.getString(2);
            int id_event=res.getInt(3);

            publicite p = new publicite( id_pub,  type,  id_event);
            // System.out.println(p);
            listper.add(p);
        }
        return listper;
    }

    @Override
    public publicite findByIdPub(int id2) throws SQLException {
        
        String sql="select * from publicite where id_pub=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, id2);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                publicite e=new publicite();
                e.setId_pub(rs.getInt("id_pub"));
                  e.setId_event(rs.getInt("id_event"));
                 e.setType(rs.getString("type"));
                 
                                

                   
                return e;
            }
            else{
                return null;
            }}
    
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
