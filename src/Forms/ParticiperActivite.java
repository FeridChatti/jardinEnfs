package Forms;

import Entities.Activite;
import Entities.Enfant;
import Entities.PartActivite;
import Services.ActiviteService;
import Services.EnfantService;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

import java.util.ArrayList;

public class ParticiperActivite extends Form {

    String ide ;
    Label lbName = new Label();
    Label lbDescription = new Label();
    Label date = new Label();
    Label club = new Label();

    public ParticiperActivite(Form prev, String id){
        setTitle("Participation");

        Label lbId = new Label(id);
        lbId.setHidden(true);
        add(lbId);



        ArrayList<Activite> l = ActiviteService.getInstance().getActivite(lbId.getText());

        for(int i = 0; i< l.size(); i++){

             lbName = new Label(l.get(i).getTypeact());
             lbDescription = new Label(l.get(i).getDetailles());
             date = new Label(l.get(i).getDate());
             club = new Label(l.get(i).getClub().getName());
            add(lbName);
            add(lbDescription);
            add(date);
            add(club);
        }
        for(int i = 0; i< EnfantService.getInstance().ListEnfants("1").size(); i++) {

            ComboBox<String> lbenfant = new ComboBox<String>(EnfantService.getInstance().ListEnfants("1").get(i).getNom());
            add(lbenfant);
            ide = String.valueOf(EnfantService.getInstance().ListEnfants("1").get(i).getId());
        }

        Button Participer=new Button("Participer");
        Participer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {




                Activite ac = new Activite();
                ac.setId(Integer.parseInt(lbId.getText()));

                Enfant e = new Enfant();
                e.setId(Integer.parseInt(ide));

                PartActivite p = new PartActivite();
                p.setDate(date.getText());
                p.setActivite(ac);
                p.setEnfant(e);

                if (ActiviteService.getInstance().AjouterParticiper(p)) {
                    Dialog.show("Succes", "Ajout réussi", new Command("OK"));
                } else {
                    Dialog.show("Erreur", "erreuuur", new Command("OK"));
                }
            }

        });


        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());

    }


    }



