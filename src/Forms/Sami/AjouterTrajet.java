package Forms.Sami;

import Entities.Chauffeur;
import Entities.Trajet;
import Services.ChauffeurService;
import Services.TrajetService;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import esprit.tn.MyApplication;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AjouterTrajet extends Form {

    public AjouterTrajet(Form prev) {
        ArrayList<Chauffeur> lc= ChauffeurService.getInstance().ListeChauffeursJardin(4+"");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());
        setTitle("Ajouter Trajet");
        setLayout(BoxLayout.y());
        TextField adresse= new TextField("","Adresse");
        TextField heure= new TextField("","Heure");
        Button aj=new Button("Ajouter");
        ComboBox<Chauffeur> cb=new ComboBox<>();
        for(Chauffeur c : lc)
        {cb.addItem(c);
        }
        addAll(adresse,heure,cb,aj);

  aj.addActionListener((event)->{
      Chauffeur ch=cb.getSelectedItem();
      String ad=adresse.getText().toString();
      String hr=heure.getText().toString();
      Trajet tr=new Trajet(ch.getId(),ad,hr);

      String response=TrajetService.getInstance().AjouterTrajet(tr);
if(response.trim().equals("true"))
      Dialog.show("Ajout éffectué","Vous avez ajouter un trajet pour le chauffeur : "+ ch.getNom(),new Command("OK"));

  });
    }
}
