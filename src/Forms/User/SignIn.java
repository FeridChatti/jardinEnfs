package Forms.User;

import Forms.Accueils.AccueilChauffeur;
import Forms.Accueils.AccueilParent;
import Forms.Accueils.AccueilResponsable;
import Forms.Accueils.AccueilTuteur;
import Services.UserService;
import com.codename1.ui.util.Resources;
import com.codename1.components.FloatingHint;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import esprit.tn.MyApplication;


public class SignIn extends BaseForm {
    String reponse = "";
    UserService su=new UserService();

    public SignIn(Resources res) {


        super(new BorderLayout());

        if (!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout) getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }




        getTitleArea().setUIID("Container");
        setUIID("SignIn");



        add(BorderLayout.NORTH, new Label(res.getImage("kgpng.png"), "LogoLabel"));

        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField pass = new TextField("", "Password", 20, TextField.PASSWORD);
       // username.setSingleLineTextArea(false);
        //password.setSingleLineTextArea(false);
        Button signIn = new Button("Sign In");
        signIn.setUIID("SignInBtn");
        // signUp.addActionListener(e -> new SignUpForm(res).show());

        ////////////


        ////////////
        Container content = BoxLayout.encloseY(
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(pass),
                createLineSeparator(),
                signIn
        );

        Label lb=new Label("login success");
        lb.setVisible(false);
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        signIn.requestFocus();

        signIn.addActionListener(new ActionListener() {

            @Override


                public void actionPerformed(ActionEvent evt) {
                    if (username.getText().length() == 0 || pass.getText().length() == 0) {
                        return;
                    }
                    else if(performsign(username.getText(),pass.getText())) {

                        lb.setVisible(true);
                        content.refreshTheme();
                        su.parseuser(username.getText());
                        //you should test the role of the authenticaed user and redirect them depending of their roles
                        switch (MyApplication.authenticated.getType()){
                            case "P": AccueilParent cc=new AccueilParent();
                                cc.show();
                                break;
                            case "R" :
                                AccueilResponsable ac=new AccueilResponsable();
                                ac.show();
                                break;
                            case "T" :
                                AccueilTuteur at=new AccueilTuteur();
                                at.show();
                                break;
                            case "C" :
                                AccueilChauffeur ach=new AccueilChauffeur();
                                ach.show();
                                break;
                        }







                        System.out.println("login success");
                    }else {
                        lb.setText("failed login");
                        lb.setVisible(true);
                        content.refreshTheme();
                    }
                }




        });
    }



    public boolean performsign(String username, String password){



        if(su.login(username,password).contains("Success"))
            return true;
        else return false;
    }

}
