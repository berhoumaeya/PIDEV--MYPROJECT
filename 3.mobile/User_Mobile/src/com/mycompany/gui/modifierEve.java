/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.evenement;
import com.mycompany.services.ServiceEvenement;
import java.util.Date;

/**
 *
 * @author farah
 */
public class modifierEve extends Form {

    public modifierEve(Form previous) {
        setTitle("Modifier Evenement");
        setLayout(BoxLayout.y());
        TextField idField = new TextField("", "ID");
        idField.setUIID("TextFieldBlack");

        TextField tfDuree = new TextField("", "Durée");
        tfDuree.setUIID("TextFieldBlack");

        TextField tfPrix = new TextField("", "Prix");
        tfPrix.setUIID("TextFieldBlack");

        TextField tfNomEvent = new TextField("", "Nom de l'événement");
        tfNomEvent.setUIID("TextFieldBlack");

        Picker tfDateDeb = new Picker();
        tfDateDeb.setType(Display.PICKER_TYPE_DATE);
        tfDateDeb.setDate(new Date());
        Picker tfDateFin = new Picker();
        tfDateFin.setType(Display.PICKER_TYPE_DATE);
        tfDateFin.setDate(new Date());

        Button btnValider = new Button("Update ");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNomEvent.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        float id = Float.parseFloat(idField.getText().toString());
                        evenement t;
                        //    public evenement(int id_event, float duree, float prix, int id, String nom_event, Date date_deb, Date date_fin) {

                        t = new evenement((int) id, Float.parseFloat(tfDuree.getText()), Float.parseFloat(tfPrix.getText()), tfNomEvent.getText(), tfDateDeb.getDate(), tfDateFin.getDate());
                        if (ServiceEvenement.getInstance().modifierEvemenent(t)) {
                            Dialog.show("Success", "Congrats!!", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });

        addAll(idField, tfDuree, tfPrix, tfNomEvent, tfDateDeb, tfDateFin, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
