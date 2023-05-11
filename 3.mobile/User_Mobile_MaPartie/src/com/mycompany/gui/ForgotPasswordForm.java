package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.services.servicesUser;
import java.io.IOException;

/**
 * The forgot password form
 *
 */
public class ForgotPasswordForm extends BaseForm {

    public ForgotPasswordForm(Resources res) {
        super(new BorderLayout());

        if (!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout) getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        add(BorderLayout.NORTH, new Label(res.getImage("Logo.png"), "LogoLabel"));

        TextField email = new TextField("", "Confirmer votre addresse e-mail ", 20, TextField.EMAILADDR);
        email.setSingleLineTextArea(false);
        Button resetPassword = new Button("Réinitialiser le mot de passe");
        Button login = new Button("S'identifier");

        login.addActionListener(e -> new SignInForm(res).show());
        login.setUIID("Link");
        Label dontRememberPassword = new Label("Vous avez un compte!");

        Container content = BoxLayout.encloseY(
                new FloatingHint(email),
                createLineSeparator(),
                resetPassword,
                FlowLayout.encloseCenter(dontRememberPassword,login)
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        resetPassword.requestFocus();
        resetPassword.addActionListener((e) -> {
            servicesUser.getInstance().forgotPassword(email, res);
        });
    }

}
