/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.components.ImageViewer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;

import com.codename1.ui.Form;
import com.codename1.ui.Image;
import java.io.IOException;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.html.DocumentInfo;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.util.Base64;
import com.mycompany.myapp.entities.Destination;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
public class ServiceDestination {
    
    

    public static ServiceDestination instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<Destination> listCategory=new ArrayList<>();

    public ServiceDestination() {
        req = new ConnectionRequest();
    }

    public static ServiceDestination getInstance() {
        if (instance == null) {
            instance = new ServiceDestination();
        }
        return instance;
    }
//ajout
    public boolean addDestination(Destination t) {
        
        String ville = t.getVille();
        String pays = t.getPays();
        
             

    
        String url= "http://127.0.0.1:8000/"+"destination/newdestjson?ville="+t.getVille()+"&pays="+t.getPays();
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
    /********** Affichage********************/
      
     public ArrayList<Destination> affichageDestination()
    {

        ArrayList<Destination> result = new ArrayList<>();
        String url ="http://127.0.0.1:8000/"+"destination/displaydestinationjson";
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
                       Destination c = new Destination();
                       
                         float id = Float.parseFloat(obj.get("id").toString());
                         String ville = obj.get("ville").toString();
                         String pays = obj.get("pays").toString();
                       

                         c.setId((int)id);
                        c.setVille(ville);
                        
                        c.setPays(pays); 
                       
                        
                        result.add(c);
                         System.out.println(c.getPays()+c.getVille());
                        
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
    public boolean modifierDestination(Destination t) {
        String ville = t.getVille();
        String pays = t.getPays();
        int id = t.getId();
        
//"http://127.0.0.1:8000/"+"destination/updatedestjson/id="+t.getId()+"?ville="+t.getVille()+"&pays="+t.getPays();
        String url= "http://127.0.0.1:8000/"+"destination/updatedestjson/"+t.getId()+"?ville="+t.getVille()+"&pays="+t.getPays();
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
    public boolean deleteDestination(int id ) {
        
        //"http://127.0.0.1:8000/"+"destination/deletedestjson/id"+id;
        String url = "http://127.0.0.1:8000/"+"destination/deletedestjson/"+id;
        
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
    
    
    public boolean exportToExcel() {
        String url = "http://127.0.0.1:8000/destination/exportjson";
    String fileName = "destinationsmobile.xls";
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
    
    
    /*
 public boolean qrcode(int id) {
    String url = "http://127.0.0.1:8000/destination/qrjson/" + id;
    
    String fileName = "qrcode.png";
    String home = FileSystemStorage.getInstance().getAppHomePath();
    String filePath = home + fileName;

    ConnectionRequest req = new ConnectionRequest();
    req.setPost(false);
    req.setUrl(url);
    req.setDestinationFile(filePath); // save response to file

    req.addResponseListener(e -> {
        if (e.getResponseCode() == 200) { // check if response is successful
            // Create a new form to display the QR code
            Form form = new Form("QR Code");

            // Load the image from the saved file
            Image image = null;
            try {
                image = Image.createImage(filePath);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            // Create an ImageViewer with the loaded image and set its size
            ImageViewer viewer = new ImageViewer(image.scaled(500, 500));
            viewer.setPreferredSize(new Dimension(500, 500));

            // Add the ImageViewer to the center of the form using BorderLayout
            Container cnt = new Container(new BorderLayout());
            cnt.add(BorderLayout.CENTER, viewer);
            form.add(cnt);

            // Show the form
            form.show();
            


            resultOK = true;
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}*/

  
  
  public boolean qrcode(int id) {
    String url = "http://127.0.0.1:8000/destination/qrjson/" + id;
    
     String fileName = "qrcode.png";
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
