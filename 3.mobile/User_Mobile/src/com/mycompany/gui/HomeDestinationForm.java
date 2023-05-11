/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.util.List;

/**
 *
 * @author Sondes
 */
public class HomeDestinationForm extends Form {

    private static final char ADD_ICON_NAME = FontImage.MATERIAL_ADD_CIRCLE;
    private static final char LIST_ICON_NAME = FontImage.MATERIAL_LIST;
    private static final String ADD_BUTTON_TEXT = "Ajouter Destination";
    private static final String LIST_BUTTON_TEXT = "Afficher Les Destinations";

    public HomeDestinationForm(Resources res, SessionManager se) {
        Toolbar tb = getToolbar();
        setTitle("Menu");
        setLayout(BoxLayout.yCenter());
        // add(new Label("Choose an option"));
//        Label chooseLabel = new Label("Choisir une option");
        add(new Label("Choisir une option"));/*

        Button addButton = new Button("");
        addButton.setIcon(FontImage.createMaterial(ADD_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
        addButton.setText(ADD_BUTTON_TEXT);
        addButton.addActionListener(e -> new addDestinationForm(this).show());

        Button listButton = new Button("");
        listButton.setIcon(FontImage.createMaterial(LIST_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
        listButton.setText(LIST_BUTTON_TEXT);
        listButton.addActionListener(e -> new ListDestination(this).show());*/

        //      Button addButton = new Button("");

//        Style addButtonStyle = addButton.getStyle();
//        addButtonStyle.setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
//        addButton.setIcon(FontImage.createMaterial(ADD_ICON_NAME, addButtonStyle));
//        addButton.setText(ADD_BUTTON_TEXT);
//        addButton.addActionListener(e -> new addDestinationForm(this).show());
        Button listButton = new Button("");
//        Style listButtonStyle = listButton.getStyle();
//        listButtonStyle.setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
//        listButton.setIcon(FontImage.createMaterial(LIST_ICON_NAME, listButtonStyle));
        listButton.setText(LIST_BUTTON_TEXT);
        listButton.addActionListener(e -> new ListDestination(this).show());

//        addAll(addButton, listButton);
        addAll(listButton);
        Style style = getStyle();
        style.setBgColor(0xD6DDDF);

        tb.addMaterialCommandToSideMenu("Mon profil", FontImage.MATERIAL_SETTINGS, e -> new ProfileForm(res, se).show());
        tb.addMaterialCommandToSideMenu("Destinations", FontImage.MATERIAL_LOCATION_CITY, e -> new HomeDestinationForm(res, se).show());
        tb.addMaterialCommandToSideMenu("Évènements", FontImage.MATERIAL_EVENT, e -> new HomeEvenementForm(res, se).show());
        tb.addMaterialCommandToSideMenu("Forum", FontImage.MATERIAL_COMMENT, e -> new ProfileForm(res, se).show());
        tb.addMaterialCommandToSideMenu("Réservations", FontImage.MATERIAL_BOOKMARK, e -> new ProfileForm(res, se).show());
        tb.addMaterialCommandToSideMenu("Sponsor", FontImage.MATERIAL_ATTACH_MONEY, e -> new HomeSponsorForm(res, se).show());

        tb.addMaterialCommandToSideMenu("Se déconnecter", FontImage.MATERIAL_EXIT_TO_APP, e -> {
            new SignInForm(res).show();
            SessionManager.pref.clearAll();
            Storage.getInstance().clearStorage();
            Storage.getInstance().clearCache();
        });
    }

}
