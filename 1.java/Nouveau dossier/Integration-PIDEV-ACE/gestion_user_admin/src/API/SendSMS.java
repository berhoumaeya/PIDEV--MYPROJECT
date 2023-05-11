/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import Entite.User;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author Lenovo
 */
public class SendSMS {public static final String ACCOUNT_SID = System.getenv("ACb9a8c651093739d767b11277d17c4ab0");
    public static final String AUTH_TOKEN = System.getenv("929f1bd02a3d02b6a200b1f8c7052b4e");

    public static void sendSMS(User u) {
        Twilio.init("ACb9a8c651093739d767b11277d17c4ab0", "929f1bd02a3d02b6a200b1f8c7052b4e");
        Message message = Message.creator(new PhoneNumber("+21628615917"),
                new PhoneNumber("+12766226384"),
                "user ajouté: nom: "+u.getNom()+" number: "+u.getTelephone()+" adresse: "+u.getAddress()).create();


        System.out.println(message.getSid());
    }
    
}
