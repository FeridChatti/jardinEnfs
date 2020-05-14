package Forms.Evenement;

import Entities.Categorie;
import Entities.Chauffeur;
import Entities.Evenement;
import Forms.Abonnements.ConsulterAbonnement;
import Forms.Accueils.AccueilParent;
import Forms.Accueils.AccueilResponsable;
import Services.CategorieService;
import Services.ChauffeurService;
import Services.EvenementService;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import esprit.tn.MyApplication;

import java.util.ArrayList;
import java.util.Date;

import static com.codename1.push.PushContent.setTitle;

public class ModifierEvenement {

    public ModifierEvenement(Form prev, String id, String titre, String description, String date,String libelle){

        ArrayList<Categorie> lc = CategorieService.getInstance().getAllcategories();
       // Evenement e= EvenementService.getInstance().AfficherEvent(MyApplication.authenticated.getId());

        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> prev.showBack());
        //setLayout(BoxLayout.y());
        setTitle("Modifier événement");


        TextArea tit=new TextArea(titre);
        tit.setAlignment(TextArea.CENTER);
        tit.setEnabled(false);
        TextArea desc=new TextArea(description);
        PickerComponent dateE = PickerComponent.createDate(new Date()).label("Date");
        Label tp=new Label("catégorie:");
        ComboBox<Categorie> c = new ComboBox<>();
        for (Categorie ca: lc) {
            c.addItem(ca);
        }

        Button mod=new Button("Modifier");

        mod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               // EvenementService.getInstance().modifierEvenement();
                Dialog.show("Succes","modification réussie",new Command("OK"));
                new ConsulterAbonnement(new AccueilParent()).show();
            }
        });
        Button supp=new Button("Supprimer");
        supp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println(id);
                EvenementService.getInstance().supprimerEvenement(id);
                Dialog.show("Succes","Suppression réussie!",new Command("OK"));
                new consulterListeEvent(new AccueilResponsable()).show();
            }
        });


        prev.addAll(tit,desc,dateE,c,mod,supp);




    }


}
