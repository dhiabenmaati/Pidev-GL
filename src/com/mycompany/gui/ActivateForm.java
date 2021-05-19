

package com.mycompany.gui;

import com.Piart.Services.UserServices;
import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.sun.mail.smtp.SMTPTransport;
import java.util.Date;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
/**
 * Account activation UI
 *
 * @author Shai Almog
 */
public class ActivateForm extends BaseForm {
    
    TextField email ;

    public ActivateForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("Activate");
        

        
        email = new TextField("","Type Your Mail",20,TextField.ANY) ;
        email.setSingleLineTextArea(false);
        
        Button valider = new Button("Valider");
        Button SignIn = new Button("Back To Login") ;
        
        SignIn.addActionListener(e -> previous.showBack());
        SignIn.setUIID("CentelLink") ;
        
        Container content =BoxLayout.encloseY(
                new Label(res.getImage("smily.png"), "LogoLabel"),
                new FloatingHint(email),
                valider,
                FlowLayout.encloseCenter(SignIn)
        );
                
        content.setScrollableY(true);
        add(BorderLayout.CENTER,content) ;
        valider.requestFocus();
        
        valider.addActionListener(e ->{
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDialog = ip.showInfiniteBlocking() ;
            
            SendMail(res);
            ipDialog.dispose();
            Dialog.show("Password","We sent your password via mail", new Command("ok")) ;
            new SignInForm(res).show();
            refreshTheme();
        }) ;
    }
    
    
    public void SendMail(Resources res){
    // Recipient's email ID needs to be mentioned.
        String to = ""+email.getText().toString() ;

        // Sender's email ID needs to be mentioned
        String from = "pisquad.piart@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = new Properties();
        

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("pisquad.piart@gmail.com", "pisquad2021");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("You Asked For Password Reset!");

            // Now set the actual message
            message.setText("Hey Member, This Is Your Password  : "+ UserServices.getInstance().getPasswordByEmail(email.getText().toString(), res) );

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
    
}
