/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;

import com.codename1.ui.Form;
import java.io.IOException;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.html.DocumentInfo;
import com.mycompany.entities.evenement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;

/**
 *
 * @author farah
 */
public class ServiceEvenement {

    public static ServiceEvenement instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<evenement> listCategory = new ArrayList<>();

    public ServiceEvenement() {
        req = new ConnectionRequest();
    }

    public static ServiceEvenement getInstance() {
        if (instance == null) {
            instance = new ServiceEvenement();
        }
        return instance;
    }
//ajout

    public boolean addEvenement(evenement t) {

        float duree = t.getDuree();
        float prix = t.getPrix();
        String nom_event = t.getNom_event();
     
        Date date_deb = t.getDate_deb();
        Date date_fin = t.getDate_fin();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
           String s=inputFormat.format(date_deb);
           String s1=inputFormat.format(date_fin);
        System.out.println(s);
//String url="http://localhost/ace2/public/index.php/eya/addsponsor?duree_contrat=" + duree_contrat + "&date_debc=" + s + "&date_finc=" + s1+ "&intitule=" + t.getIntitule();
        String url = "http://127.0.0.1:8000/" + "evenement/newjson?duree=" + t.getDuree() + "&prix=" + t.getPrix() + "&date_deb=" + s + "&date_fin=" +  s1 + "&nom_event=" + t.getNom_event();
        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    /**
     * ******** Affichage*******************
     */
    public ArrayList<evenement> affichageEvenement() {

        ArrayList<evenement> result = new ArrayList<>();
        String url = "http://127.0.0.1:8000/" + "evenement/displayjson1";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapR = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> ListOfMaps = (List<Map<String, Object>>) mapR.get("root");
                    System.out.println(mapR);
                    for (Map<String, Object> obj : ListOfMaps) {
                        System.out.println(obj);
                        evenement c = new evenement();

                        //  String contenu = obj.get("contenu").toString();
                        float duree = Float.parseFloat(obj.get("duree").toString());
                        float prix = Float.parseFloat(obj.get("prix").toString());
                        String nom_event = obj.get("nom_event").toString();
                    //   String dateString = obj.get("date_deb").toString();
                     //  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                     ////  Date date_deb = format.parse(dateString);

                     //  String dateStrin = obj.get("date_fin").toString();
                      //  SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                     //   Date date_fin = formate.parse(dateStrin);
                        c.setDuree((int) duree);
                        c.setPrix(prix);
                        c.setNom_event(nom_event);
                       // c.setDate_deb(date_deb);
                        //c.setDate_fin(date_fin);

                        result.add(c);
                        System.out.println(c.getDuree() + c.getPrix() + c.getNom_event() );
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;

    }

    //  * *******************************************//update**************************************************
    public boolean modifierEvemenent(evenement t) {
        int id = t.getId_event();
        float duree = t.getDuree();
        float prix = t.getPrix();
        String nom_event = t.getNom_event();
     
        Date date_deb = t.getDate_deb();
        Date date_fin = t.getDate_fin();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
           String s=inputFormat.format(date_deb);
           String s1=inputFormat.format(date_fin);
        System.out.println(s);
        //http://localhost/acee/public/index.php/evenement/updatejson/%7Bid_event%7D?duree=6&prix=5&date_deb=2003-06-05%2014:30:00&date_fin=2003-4-5&nom_event=farah
              String url = "http://127.0.0.1:8000/" + "evenement/updatejson/"+t.getId_event()+"?duree=" + t.getDuree() + "&prix=" + t.getPrix() + "&date_deb=" + s + "&date_fin=" + s1 + "&nom_event=" + t.getNom_event();

       // String url = "http://localhost/acee/public/index.php/" + "evenement/updatejson?id=" + t.getId() + "&contenu=";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy 
        return resultOK;

    }

    //////////////////////////////////delete///////////////////////////////////////
    public boolean deleteEvemenent(int id) {
        //http://localhost/acee/public/index.php/evenement/deletejson/92

        String url = "http://127.0.0.1:8000/"+"evenement/deletejson?id_event="+id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

}
