/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Piart.Services;

import com.Piart.entity.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.List;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.ProfileForm;
import com.mycompany.gui.SessionManager;
import com.mycompany.gui.SignInForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;



/**
 *
 * @author alabe
 */
public class UserServices {
    
    
    
    //singleton
    
   public static UserServices instance = null ;
   public static boolean ResultOk = true ;
   public ArrayList<User> Users;
   public boolean resultOk;
   String json ;
   
   // init conncect request 
   private ConnectionRequest req ;
   
   public static UserServices getInstance(){
       if(instance == null)
           instance = new UserServices() ;
       return instance ;
   }
   
   public  UserServices(){
       req = new ConnectionRequest() ;
   }
   
   public void SignUp(TextField email, TextField password, TextField name, TextField surname, Resources res){
       
       String url = "http://127.0.0.1:8000/SignUpRest?email="+email.getText().toString()+"&password="+password.getText().toString()+
               "&name="+name.getText().toString()+"&surname="+surname.getText().toString() ;
       
       req.setUrl(url) ;
       // vrify form if needed --- here ---
       
       req.addResponseListener((e)-> {
       
       byte[]data = (byte[]) e.getMetaData();
       String responseData = new String(data);
       System.out.print("data ===>"+responseData);
       });
       NetworkManager.getInstance().addToQueueAndWait(req);
              Dialog.show("Account Created Successfully ","", new Command("ok"));
   }
   
   public void SignIn(TextField email, TextField password, Resources rs){
       
       String url = "http://127.0.0.1:8000/SignInRest?email="+email.getText().toString()+"&password="+password.getText().toString() ;
       
       req.setUrl(url) ;
       
       // vrify form if needed --- here ---
       
       req.addResponseListener((e)-> {
           
           JSONParser j = new JSONParser() ;
           String json = new String(req.getResponseData()) + "";
          
           try {
                if (json.equals("fail")) { Dialog.show("No User Found With This ","Email", new Command("ok")); 
                    System.out.print("no user ");}
                
                else {
                    System.out.print("data ===> "+json);
              
                   Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray())) ;
                   
                    //Session  Guard
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                
                SessionManager.setPassowrd(user.get("password").toString());
                SessionManager.setEmail(user.get("email").toString());
                //photo to use later
                if(user.get("photo") != null)
                    SessionManager.setPhoto(user.get("photo").toString());
                
               System.out.println("Current user ->" + SessionManager.getEmail());
                
                // end Session  Guard
                   
                   if (user.size() > 0 )  
                       new ProfileForm(rs).show() ;
               } 
                } catch (Exception ex) {
                    ex.printStackTrace();
               }
       });
       
       NetworkManager.getInstance().addToQueueAndWait(req);
   }
   
    public User UserDetails() {
        
        User user = new User() ; 
        int id = SessionManager.getId();   // get id saved in SessionManager
        String url = "http://127.0.0.1:8000/UserDetailsRest?id="+id;  
        req.setUrl(url) ;
        
        String str = new String (req.getResponseData());
        req.addResponseListener((evt)-> {
        JSONParser jsonp = new JSONParser() ;
        try {
                   Map<String,Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray())) ;
                   user.setEmail(obj.get("email").toString());
                   user.setPassword(obj.get("Password").toString());
                   user.setName(obj.get("Name").toString());
                   user.setSurname(obj.get("Surname").toString());
        }   catch (IOException ex) {
            System.out.println("catch error"); 
            }
        System.out.println("Data 2 ===>"+str);
     });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return user ;
}
    
    
    public void EditUser(TextField email, TextField password, TextField name, TextField surname, Resources res){
       int id = SessionManager.getId();
       String url = "http://127.0.0.1:8000/EditUserRest?id="+id+"&email="+email.getText().toString()+"&password="+password.getText().toString()+
               "&name="+name.getText().toString()+"&surname="+surname.getText().toString() ;
       
       req.setUrl(url) ;
       // vrify form if needed --- here ---
       
       req.addResponseListener((e)-> {
       
       byte[]data = (byte[]) e.getMetaData();
       String responseData = new String(data);
       System.out.print("data ===>"+responseData);
       });
       
       NetworkManager.getInstance().addToQueueAndWait(req);
       new ProfileForm(res).show() ;
   }
    
    
    public boolean DeleteAccount(Resources rs){
        int id = SessionManager.getId();   // get id saved in SessionManager
        String url = "http://127.0.0.1:8000/deleteUserRest?id="+id;  
        req.setUrl(url) ;
        
        req.addResponseListener(new ActionListener<NetworkEvent>(){
                public void actionPerformed(NetworkEvent evt){
                    req.removeResponseCodeListener(this);
                }});
        
            NetworkManager.getInstance().addToQueueAndWait(req);
            System.out.println("deleted yayy");
            new SignInForm(rs).show() ;
            return resultOk ;
    }
    
    public String getPasswordByEmail(String email, Resources rs){

        String url = "http://127.0.0.1:8000/getPasswordByEmailRest?email="+email;  
        req = new ConnectionRequest(url, false);
        req.setUrl(url) ;    
        
        req.addResponseListener((NetworkEvent e) -> {
                
                JSONParser j = new JSONParser();
                json = new String(req.getResponseData()) + "" ;
                
                try{
                    System.out.println("Data ===> "+json);
                    Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    }catch(Exception c){
                        c.getStackTrace() ;
                            }}
        );
             NetworkManager.getInstance().addToQueueAndWait(req);               
        
             return json ;
    }
    
}
