package Forms.Sami;

import Entities.Chauffeur;
import Entities.Trajet;
import Forms.Accueils.AccueilResponsable;
import Services.ChauffeurService;
import Services.TrajetService;
import com.codename1.charts.compat.Paint;
import com.codename1.ui.*;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import esprit.tn.MyApplication;

import javax.swing.*;
import javax.xml.soap.Text;
import java.util.ArrayList;

public class ProfilChauffeur extends Form {
Label result=new Label("");
    public Container getCont(Label lb,TextField tf)
    {
        BoxLayout bx=new BoxLayout(BoxLayout.Y_AXIS);
        Container cn=new Container();
        cn.setLayout(bx);
        cn.addAll(tf);
 return  cn;
    }
private Validator saisie(TextField nom,TextField cin,TextField tel,TextField email)
{
    Validator v=new Validator();
  v.addConstraint(nom,new LengthConstraint(1,"*"),new RegexConstraint("^[a-zA-Z ]+$","*"));
 v.addConstraint(cin,new LengthConstraint(8,"*"),new RegexConstraint("^[0-9]+$","*"));
    v.addConstraint(cin,new LengthConstraint(8,"*"),new RegexConstraint("^[0-9]+$","*"));

    return  v;
}
    public ProfilChauffeur(Form fo)
{
    Chauffeur ch= ChauffeurService.getInstance().getChauffeur(MyApplication.authenticated.getId());
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->fo.showBack());
    setTitle("Profil");
    setLayout(BoxLayout.y());

    TextField cin_modif=new TextField(ch.getCin(),"CIN");
    Container cin= getCont(new Label("Cin"),cin_modif);
    TextField nom_modif=new TextField(ch.getNom(),"Nom et prenom");
    Container nom= getCont(new Label("Nom et prénom"),nom_modif);
    TextField tel_modif=new TextField(ch.getTel(),"Numéro de téléphone");
    Container tel= getCont(new Label("Numéro de téléphone"),tel_modif);
    TextField email_modif=new TextField(ch.getEmail(),"Email",40, TextField.EMAILADDR);
    Container email= getCont(new Label("Email"),email_modif);
    TextField username_modif=new TextField(ch.getUsername(),"Username");
    Container username= getCont(new Label("Username"),username_modif);
    TextField password_modif=new TextField(null,"Mot de passe",20,TextField.PASSWORD);
    Container password= getCont(new Label("Mot de passe"),password_modif);

    Label info=new Label("Informations");
    Label ident=new Label("Identifiants");
    Button aj=new Button("Modifier");
aj.setUIID("SignInBtn");
    ComboBox<String> cb=new ComboBox<>();
    cb.addItem("Homme");
    cb.addItem("Femme");

    if(ch.getSexe().trim().equals("Homme"))
    cb.setSelectedIndex(0);
    else
     cb.setSelectedIndex(1);
    info.setAlignment(Label.CENTER);
    ident.setAlignment(Label.CENTER);
    addAll(info,cin,nom,email,tel,cb,ident,username,password,aj,result);

    aj.addActionListener((event)->{

        if(saisie(nom_modif,cin_modif,tel_modif,email_modif).isValid()){
        String nom_mod=nom_modif.getText();
        String cin_mod=cin_modif.getText().toString();
       String tel_mod=tel_modif.getText();
       String email_mod=email_modif.getText();
       String username_mod=username_modif.getText();
       String password_mod=password_modif.getText();
       Chauffeur chauffeur=new Chauffeur();
       chauffeur.setId(MyApplication.authenticated.getId());
       chauffeur.setEmail(email_mod);
       chauffeur.setNom(nom_mod);
       chauffeur.setTel(tel_mod);
       chauffeur.setSexe(cb.getSelectedItem());
       chauffeur.setCin(cin_mod);
       chauffeur.setUsername(username_mod);
       chauffeur.setPassword(password_mod);
        String response= ChauffeurService.getInstance().modifierChauffeur(chauffeur);
        if(response.trim().equals("true")) {
            Dialog.show("Ajout éffectué", "Modifications enregistrées ", new Command("OK"));
        }
        }
    });

}
}
