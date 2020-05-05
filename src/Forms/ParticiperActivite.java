package Forms;

import Entities.Activite;
import Entities.Enfant;
import Services.ActiviteService;
import Services.EnfantService;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

import java.util.ArrayList;

public class ParticiperActivite extends Form {

    public ParticiperActivite(Form prev, String id){
        setTitle("Participation");

        Label lbId = new Label(id);
        lbId.setHidden(true);
        add(lbId);



        ArrayList<Activite> l = ActiviteService.getInstance().getActivite(lbId.getText());

        for(int i = 0; i< l.size(); i++){

            Label lbName = new Label(l.get(i).getTypeact());
            Label lbDescription = new Label(l.get(i).getDetailles());
            Label date = new Label(l.get(i).getDate());
            Label club = new Label(l.get(i).getClub().getName());
            add(lbName);
            add(lbDescription);
            add(date);
            add(club);
        }
        for(int i = 0; i< EnfantService.getInstance().ListEnfants("1").size(); i++) {

            ComboBox<String> lbenfant = new ComboBox<String>(EnfantService.getInstance().ListEnfants("1").get(i).getPrenom());
            add(lbenfant);
        }


        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());

    }


    }



