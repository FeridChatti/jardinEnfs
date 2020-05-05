package Forms.Remarques;

import Entities.Remarque;
import Services.RemarqueService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

import java.util.ArrayList;

public class TuteurRemarques extends Form {

    public TuteurRemarques (Form prev){
        setTitle("La Liste des remarques");
        Form form = this;



        Container detail = new Container(BoxLayout.y());
        add(detail);
        ArrayList<Remarque> rmk= RemarqueService.getInstance().tutremarques();
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());

        for(int i = 0; i<rmk.size(); i++){


            String id = String.valueOf(rmk.get(i).getId());
            Label lbID = new Label(id);
            lbID.isHidden(true);
            SpanLabel lbnom = new SpanLabel("Enfant  :"+rmk.get(i).getEnfant());


            SpanLabel lbtut = new SpanLabel("Parent  :"+rmk.get(i).getNomtut());


            SpanLabel lbdate = new SpanLabel("Date :"+rmk.get(i).getDate());
            SpanLabel lbDescription=new SpanLabel("description: "+rmk.get(i).getDescription());



            detail.add(lbnom);


            detail.add(lbtut);

            detail.add(lbDescription);
            detail.add(lbdate);



            // detail.setLeadComponent(lbName);



        }







    }
}
