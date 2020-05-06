package Forms.Sami;

import Entities.Chauffeur;
import Entities.Trajet;
import Forms.Accueils.AccueilResponsable;
import Services.ChauffeurService;
import Services.TrajetService;
import com.codename1.charts.compat.Paint;
import com.codename1.ui.*;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import esprit.tn.MyApplication;

import java.util.ArrayList;

public class ProfilChauffeur extends Form {

    public Container getCont(Label lb,TextField tf)
    {
        BoxLayout bx=new BoxLayout(BoxLayout.X_AXIS);
        Container cn=new Container();
        cn.setLayout(bx);
        cn.addAll(lb,tf);
 return  cn;
    }
public ProfilChauffeur(Form fo)
{
    Chauffeur ch= ChauffeurService.getInstance().getChauffeur(MyApplication.authenticated.getId());
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->fo.showBack());
    setTitle("Profil");
    setLayout(BoxLayout.y());
    TextField cin_modif=new TextField(ch.getCin(),"CIN");
    Container cin= getCont(new Label("Cin"),cin_modif);
    TextField nom_modif=new TextField(ch.getNom(),"Nom et prénom");
    Container nom= getCont(new Label("Nom et prénom"),nom_modif);
    TextField tel_modif=new TextField(ch.getNom(),"Numéro de téléphone");
    Container tel= getCont(new Label("Numéro de téléphone"),tel_modif);
    TextField email_modif=new TextField(ch.getNom(),"Email");
    Container email= getCont(new Label("Email"),email_modif);

    Button aj=new Button("Modifier");
    ComboBox<String> cb=new ComboBox<>();
    cb.addItem("Homme");
    cb.addItem("Femme");
    if(ch.getSexe().trim().equals("Homme"))
    cb.setSelectedIndex(0);
   else
     cb.setSelectedIndex(1);
    addAll(nom,cin,tel,email,cb,aj);

    aj.addActionListener((event)->{
        String nom_mod=nom_modif.getText();
        String cin_mod=cin_modif.getText().toString();
       String tel_mod=tel_modif.getText();
       String email_mod=email_modif.getText();
       Chauffeur chauffeur=new Chauffeur();
       chauffeur.setEmail(email_mod);
       chauffeur.setNom(nom_mod);
       chauffeur.setTel(tel_mod);
       chauffeur.setSexe(cb.getSelectedItem());
       chauffeur.setCin(cin_mod);
        String response= ChauffeurService.getInstance().modifierChauffeur(chauffeur);
        if(response.trim().equals("true")) {
            Dialog.show("Ajout éffectué", "Vous avez ajouter un trajet pour le chauffeur : " + ch.getNom(), new Command("OK"));
            new ConsulterTrajet(AccueilResponsable.fo);
        }
    });

}
}
