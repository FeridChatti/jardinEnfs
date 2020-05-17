package Forms.Sami;

import Entities.Chauffeur;
import Entities.Tuteur;
import Services.ChauffeurService;
import Services.UserService;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import esprit.tn.MyApplication;

public class ModifierTuteur extends Form {

    public Container getCont(Label lb, TextField tf)
    {
        BoxLayout bx=new BoxLayout(BoxLayout.X_AXIS);
        Container cn=new Container();
        cn.setLayout(bx);
        cn.addAll(lb,tf);
        return  cn;
    }
    public ModifierTuteur(Form fo)
    {
        Tuteur ch= UserService.getInstance().getTuteur(MyApplication.authenticated.getId());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->fo.showBack());
        setTitle("Profil");
        setLayout(BoxLayout.y());

        TextField prenom_modif=new TextField(ch.getPrenom(),"Prenom");
        Container prenom= getCont(new Label("Prenom"),prenom_modif);
        TextField nom_modif=new TextField(ch.getNom(),"Nom");
        Container nom= getCont(new Label("Nom"),nom_modif);
        TextField email_modif=new TextField(ch.getEmail(),"Email");
        Container email= getCont(new Label("Email"),email_modif);
        TextField username_modif=new TextField(ch.getUsername(),"Username");
        Container username= getCont(new Label("Username"),username_modif);
        TextField password_modif=new TextField("","Mot de passe",20,TextField.PASSWORD);
        Container password= getCont(new Label("Mot de passe"),password_modif);

        Label info=new Label("Informations");
        Label ident=new Label("Identifiants");

        Button aj=new Button("Modifier");
           aj.setUIID("Confirmbtn");
        ComboBox<String> cb=new ComboBox<>();
        cb.addItem("Homme");
        cb.addItem("Femme");

        if(ch.getSexe().trim().equals("Homme"))
            cb.setSelectedIndex(0);
        else
            cb.setSelectedIndex(1);
        addAll(info,prenom,nom,email,cb,ident,username,password,aj);

        aj.addActionListener((event)->{
            String nom_mod=nom_modif.getText();
            String prenom_mod=prenom_modif.getText().toString();
            String email_mod=email_modif.getText();
            String username_mod=username_modif.getText();
            String password_mod=password_modif.getText();
            Tuteur tuteur=new Tuteur();
            tuteur.setId(MyApplication.authenticated.getId());
            tuteur.setEmail(email_mod);
            tuteur.setNom(nom_mod);
            tuteur.setSexe(cb.getSelectedItem());
            tuteur.setPrenom(prenom_mod);
            tuteur.setUsername(username_mod);
            tuteur.setPassword(password_mod);
            boolean response= UserService.getInstance().modifierTuteur(tuteur);
            if(response) {
                Dialog.show("Ajout éffectué", "Modifications enregistrées ", new Command("OK"));
            }
        });

    }
}
