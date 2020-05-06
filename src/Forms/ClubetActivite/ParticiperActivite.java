package Forms.ClubetActivite;

import Entities.Activite;
import Forms.ClubetActivite.ConsulterActivite;
import Services.ActiviteService;
import Services.EnfantService;
import com.codename1.components.ToastBar;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

import java.util.ArrayList;

public class ParticiperActivite extends Form {

    String ide ;
    Label lbName ;
    Label lbDescription ;
    Label date ;
    Label club ;
    int ida ;

    public ParticiperActivite(Form prev, String id){
        Form fo = this;
        setTitle("Participation");
        setLayout(BoxLayout.y());

        Label lbId = new Label(id);
        lbId.setHidden(true);
        add(lbId);



        ArrayList<Activite> l = ActiviteService.getInstance().getActivite(lbId.getText());

        for(int i = 0; i< l.size(); i++){

             lbName = new Label("nom :"+l.get(i).getTypeact());
             lbDescription = new Label("detail : " + l.get(i).getDetailles());
             date = new Label("Date :"+l.get(i).getDate());

             club = new Label("Club de l'activité : "+l.get(i).getClub().getName());
            add(lbName);
            add(lbDescription);
            add(date);
            add(club);
        }

        for(int i = 0; i< EnfantService.getInstance().ListEnfants("1").size(); i++) {

            ComboBox<String> lbenfant = new ComboBox<String>(EnfantService.getInstance().ListEnfants("1").get(i).getNom());

            add(lbenfant);
            ide = String.valueOf(EnfantService.getInstance().ListEnfants("1").get(i).getId());

            lbenfant.addActionListener(e -> ToastBar.showMessage("You picked " + lbenfant.getSelectedItem(), FontImage.MATERIAL_INFO));

        }

        Button participer=new Button("Participer");
        participer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                ida=Integer.parseInt(lbId.getText()) ;
                int idep = Integer.parseInt(ide);

                String datea = date.getText();

                if (ActiviteService.getInstance().AjouterParticiper(ida,idep,datea)) {
                    Dialog.show("Succes", "Ajout réussi", new Command("OK"));
                    new ConsulterActivite(fo).show();
                } else {
                    Dialog.show("Erreur", "erreuuur", new Command("OK"));
                }
            }

        });


add(participer);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());

    }


    }



