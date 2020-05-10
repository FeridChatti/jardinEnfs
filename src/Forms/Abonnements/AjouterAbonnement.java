package Forms.Abonnements;

import Entities.Abonnement;
import Entities.Enfant;
import Entities.Jardin;
import Forms.Accueils.AccueilParent;
import Services.AbonnementService;
import Services.EnfantService;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import esprit.tn.MyApplication;

import java.awt.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import static esprit.tn.MyApplication.authenticated;

public class AjouterAbonnement extends Form {
    Form current;
    public AjouterAbonnement(Form prev) {
        current=this;
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());
     /*   Button b=new Button("ajouter enfant");
        b.addActionListener(e->new AjouterEnfant(current).show());
        add(b);*/
        setTitle("Ajouter Abonnement");
        setLayout(BoxLayout.y());
        Label enf=new Label("Enfant:");
        enf.getAllStyles().setFgColor(ColorUtil.GRAY);
        Label typ=new Label("Type:");
        typ.getAllStyles().setFgColor(ColorUtil.GRAY);
        Label mont=new Label("Montant:");
        mont.getAllStyles().setFgColor(ColorUtil.GRAY);
        ComboBox<Enfant> enfant = new ComboBox();
        ArrayList<Enfant> enfan=EnfantService.getInstance().ListEnfants(String.valueOf(authenticated.getId()));
        for (int i=0;i< enfan.size();i++){
        enfant.addItem(enfan.get(i));
        }

        ComboBox types = new ComboBox();
        types.addItem("normal");
        types.addItem("bus");
        TextField montant= new TextField("","Montant");
        ArrayList<Jardin> jar=EnfantService.getInstance().Montant("1");
        for (int i=0;i< jar.size();i++){
            montant.setText(String.valueOf(jar.get(0).getTarif()));}

         types.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                 if(types.getSelectedItem().toString().equals("normal")){
                     for (int i=0;i< jar.size();i++){
                         montant.setText(String.valueOf(jar.get(0).getTarif()));
                     }
                 }
                 else if (types.getSelectedItem().toString().equals("bus")){
                     montant.setText(String.valueOf(jar.get(0).getTarif()+50));
                 }
             }
         });

        montant.setEnabled(false);
        Button bt=new Button("Ajouter");
      //  String mount=String.valueOf(mnt);
        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Date lt = Date.from(Instant.now());
                String text = lt.toString();
                String se=types.getSelectedItem().toString();

                Abonnement abonne=new Abonnement(text,se,"attente",montant.getText(),enfant.getSelectedItem());

                if(AbonnementService.getInstance().AjouterAbonnement(abonne)){
                    Dialog.show("Succes","Ajout réussi",new Command("OK"));
                    new ConsulterAbonnement(new AccueilParent()).show();
                }

            }
        });


        Label vid=new Label(" ");
        addAll(enf,enfant,typ,types,mont,montant,vid,bt);
    }
}
