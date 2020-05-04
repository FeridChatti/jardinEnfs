package Forms;

import Entities.Activite;
import Services.ActiviteService;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

import java.util.ArrayList;

public class ParticiperActivite extends Form {

    public ParticiperActivite(Form prev, String id){
        setTitle("Participation");

        Label lbId = new Label(id);
        lbId.isHidden(true);
        add(lbId);

        ArrayList<Activite> l = ActiviteService.getInstance().getActivite(lbId.getText());

        for(int i = 0; i< l.size(); i++){

            Label lbName = new Label(l.get(i).getTypeact());
            Label lbDescription = new Label(l.get(i).getDetailles());
          //  Label date = new Label(l.get(i).getDate());



            add(lbName);
            add(lbDescription);
          //  add(date);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());

    }


    }



