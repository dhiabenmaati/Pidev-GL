

package com.mycompany.gui;

import com.Piart.Services.UserServices;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 * SignUp UI
 *
 * @author ALABENMAATI
 */
public class SignUpForm extends BaseForm {

    public SignUpForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                

        TextField email = new TextField("", "Mail", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField confirmPassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
        TextField name = new TextField("", "Name", 20, TextField.ANY);
        TextField surname = new TextField("", "SurName", 20, TextField.ANY);

        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        confirmPassword.setSingleLineTextArea(false);
        name.setSingleLineTextArea(false);
        surname.setSingleLineTextArea(false);
        
        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.addActionListener( e -> new SignInForm(res).show());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(name),
                createLineSeparator(),
                new FloatingHint(surname),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(confirmPassword),
                createLineSeparator()

        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener((e) -> {
        UserServices.getInstance().SignUp(email, password, name, surname, res);
        new SignInForm(res).show();
        });
    }
    
}
