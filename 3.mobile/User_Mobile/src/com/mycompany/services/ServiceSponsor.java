/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Sponsor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * HP
 */
public class ServiceSponsor {
    public static ServiceSponsor instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<Sponsor> listCategory=new ArrayList<>();

    public ServiceSponsor() {
        req = new ConnectionRequest();
    }

    public static ServiceSponsor getInstance() {
        if (instance == null) {
            instance = new ServiceSponsor();
        }
        return instance;
    }
//ajout
    public boolean addSponsor(Sponsor t) {

        String intitule = t.getIntitule();
        int  duree_contrat=t.getDurecontrat();
      
        Date date_debc = t.getDate_debc();
        Date date_finc = t.getDate_finc();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        String s=inputFormat.format(date_debc);
        String s1=inputFormat.format(date_finc);
        System.out.println(s);
        
        
        
       

String url="http://127.0.0.1:8000/"+"sponsor/addsponsor?duree_contrat=" + duree_contrat + "&date_debc=" + s + "&date_finc=" + s1+ "&intitule=" + t.getIntitule();
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
    //********** Affichage********************/
      
     public ArrayList<Sponsor> affichageSponsor()
    {

        ArrayList<Sponsor> result = new ArrayList<>();
    
    String url="http://127.0.0.1:8000/"+"sponsor/allsponsors";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try 
                {
                    Map<String,Object>mapR = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> ListOfMaps = (List<Map<String,Object>>) mapR.get("root");
                    System.out.println(mapR);
                    for(Map<String, Object> obj : ListOfMaps)
                    {
                        System.out.println(obj);
                       Sponsor c = new Sponsor();
                       
                       // float id = Float.parseFloat(obj.get("id_sponsor").toString());

                        String intitule =obj.get("intitule").toString();
                         c.setIntitule(intitule);
                        
                        // float id = Float.parseFloat(obj.get("id_sponsor").toString());
                        // float Durecontrat = Float.parseFloat(obj.get("duree_contrat").toString());
                         // c.setDurecontrat(Durecontrat); 
                        

                     //  c.setId((int)id);
                       
                      
                        float Durecontrat = Float.parseFloat(obj.get("duree_contrat").toString());
                        int durecontrat = (int) Durecontrat;
                          c.setDurecontrat(durecontrat);

                                         

                        
                        result.add(c);
                        System.out.println(c.getIntitule()+c.getDurecontrat());
                    }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;
    
    }    

    
     /*********************************************update***************************************************/    
    public boolean modifierSponsor(Sponsor t) {
           String intitule = t.getIntitule();
             int  duree_contrat=t.getDurecontrat();
             int id = t.getId();

        Date date_debc = t.getDate_debc();
        Date date_finc = t.getDate_finc();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        String s=inputFormat.format(date_debc);
        String s1=inputFormat.format(date_finc);
        System.out.println(s);
        
        
        
       
//        String url= "http://127.0.0.1:8000/"+"destination/updatedestjson/"+t.getId()+"?ville="+t.getVille()+"&pays="+t.getPays();

String url="http://127.0.0.1:8000/"+"sponsor/updatesponsor/"+t.getId()+"?duree_contrat=" + duree_contrat + "&date_debc=" + s + "&date_finc=" + s1+ "&intitule=" + t.getIntitule();
      
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy 
    return resultOK;
        
    }
    //////////////////////////////////delete///////////////////////////////////////
   public boolean deleteSponsor(int id ) {
       //        String url = "http://127.0.0.1:8000/"+"destination/deletedestjson/"+id;

        String url = "http://127.0.0.1:8000/"+"sponsor/deletejson/"+id;
                //String url = "http://127.0.0.1:8000/"+"destination/deletedestjson/"+id;

    
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOK;
    }
    
   /* public void Supprimer(int id) {
        ConnectionRequest con = new ConnectionRequest();
        //con.setUrl(BASE_URL+"/deleteProduitApi/"+id);
        String url = "http://localhost/ace2/public/index.php/"+"eya/deletejson/"+id;
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }*/
   
  public boolean pdf() {
    String url="http://127.0.0.1:8000/"+"sponsor/pdfjson";
    String fileName = "sponsor.pdf";
    String home = FileSystemStorage.getInstance().getAppHomePath();
    String filePath = home + fileName;

    ConnectionRequest req = new ConnectionRequest();
    req.setPost(false);
    req.setUrl(url);
    req.setDestinationFile(filePath); // save response to file

    req.addResponseListener(e -> {
        if (e.getResponseCode() == 200) { // check if response is successful
            Display.getInstance().execute(filePath);
            resultOK = true;
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}
   
}

    
   

