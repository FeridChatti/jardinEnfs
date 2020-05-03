package Forms;

import Entities.Abonnement;
import Entities.Enfant;
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
import java.util.Date;

public class AjouterAbonnement extends Form {
    Form current;
    public AjouterAbonnement(MyApplication prev) {
        current=this;
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.start());
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
        for (int i=0;i< EnfantService.getInstance().ListEnfants("4").size();i++){
        enfant.addItem(EnfantService.getInstance().ListEnfants("4").get(i));
        }

        ComboBox types = new ComboBox();
        types.addItem("bus");
        types.addItem("normal");
        TextField montant= new TextField("","Montant");
        for (int i=0;i< EnfantService.getInstance().Montant("1").size();i++){
            montant.setText(String.valueOf(EnfantService.getInstance().Montant("1").get(0).getTarif()));
        }

        montant.setEnabled(false);
        Button bt=new Button("Ajouter");

        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Date lt = Date.from(Instant.now());
                String text = lt.toString();
                String se=types.getSelectedItem().toString();
                Abonnement abonne=new Abonnement(text,se,"attente",montant.getText(),enfant.getSelectedItem());

                if(AbonnementService.getInstance().AjouterAbonnement(abonne)){
                    Dialog.show("Succes","Ajout réussi",new Command("OK"));
                }

            }
        });


        Label vid=new Label(" ");
        addAll(enf,enfant,typ,types,mont,montant,vid,bt);
    }
}
