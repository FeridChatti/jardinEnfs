package Forms.User;

import Forms.Accueils.AccueilParent;
import Forms.Accueils.AccueilResponsable;
import Services.UserService;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import esprit.tn.MyApplication;

public class Login extends Form {
    private String name;
    private String imageURL;
    private Form loginForm;
    UserService su=new UserService();

    public Login (MyApplication prev,Resources theme){
        loginForm = new Form("Sign in ");
        loginForm.setLayout(new BorderLayout());
        Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        center.setUIID("ContainerWithPadding");

        Image logo = theme.getImage("kgpng.png");
        Label l = new Label(logo);
        Container flow = new Container(new FlowLayout(Component.CENTER));
        flow.addComponent(l);
        center.addComponent(flow);

        final TextField username = new TextField();
        username.setHint("Username");
        final TextField pass = new TextField();
        pass.setHint("Password");
        pass.setConstraint(TextField.PASSWORD);

        center.addComponent(username);
        center.addComponent(pass);
        Label lb=new Label("login success");
            lb.setVisible(false);

        center.addComponent(lb);

        Button signIn = new Button("Sign In");
        signIn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                if (username.getText().length() == 0 || pass.getText().length() == 0) {
                    return;
                }
                else if(performsign(username.getText(),pass.getText())) {

                    lb.setVisible(true);
                    center.refreshTheme();
                    su.parseuser(username.getText());
                    //you should test the role of the authenticaed user and redirect them depending of their roles
                   switch (MyApplication.authenticated.getType()){
                       case "P": AccueilParent cc=new AccueilParent();
                                 cc.show();
                                  break;
                       case "R" :
                           AccueilResponsable ac=new AccueilResponsable();
                           ac.show();
                   }







                    System.out.println("login success");
                }else {
                    lb.setText("failed login");
                    lb.setVisible(true);
                    center.refreshTheme();
                }
            }
        });
        center.addComponent(signIn);
        loginForm.addComponent(BorderLayout.CENTER, center);

        Container bottom = new Container(new BoxLayout(BoxLayout.Y_AXIS));



        loginForm.addComponent(BorderLayout.SOUTH, bottom);

        loginForm.show();
    }

public boolean performsign(String username, String password){



        if(su.login(username,password).contains("Success"))
        return true;
        else return false;
}

}
