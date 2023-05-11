/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

/**
 *
 * @author farah
 */
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

public class addEvenementForm extends Form {

    public addEvenementForm(Form previous) {
        setTitle("Ajouter un nouvel événement");
        setLayout(BoxLayout.y());

        TextField tfDuree = new TextField("", "Durée");
        tfDuree.setUIID("TextFieldBlack");

        TextField tfPrix = new TextField("", "Prix");
        tfPrix.setUIID("TextFieldBlack");

        TextField tfNomEvent = new TextField("", "Nom de l'événement");
        tfNomEvent.setUIID("TextFieldBlack");

        Picker tfDateDeb = new Picker();
        tfDateDeb.setType(Display.PICKER_TYPE_DATE);
        //addsponsorform
        Picker tfDateFin = new Picker();
        tfDateFin.setType(Display.PICKER_TYPE_DATE);

        Button btnAdd = new Button("Ajouter");

        // Add action listener to button
        btnAdd.addActionListener(evt -> {
            if (tfDuree.getText().length() == 0 || tfPrix.getText().length() == 0
                    || tfNomEvent.getText().length() == 0 || tfDateDeb.getText().length() == 0
                    || tfDateFin.getText().length() == 0) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                try {
                    // Create new reclamation object and add it to the server
                    evenement ev = new evenement(Float.parseFloat(tfDuree.getText()), Float.parseFloat(tfPrix.getText()),
                            tfNomEvent.getText(), tfDateDeb.getDate(), tfDateFin.getDate());

                    if (ServiceEvenement.getInstance().addEvenement(ev)) {
                        Dialog.show("Success", "Congrats", new Command("OK"));
                    } else {
                        Dialog.show("Error", "Server error", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("Error", "Invalid input", new Command("OK"));
                }
            }
        });

        // Add input fields and button to the form
        addAll(tfDuree, tfPrix, tfNomEvent, tfDateDeb, tfDateFin, btnAdd);

        // Add back button to the toolbar
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> previous.showBack());
    }

}
