package Forms.Remarques;

import Entities.Remarque;
import Forms.ParticiperActivite;
import Services.ActiviteService;
import Services.RemarqueService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import esprit.tn.MyApplication;

import java.util.ArrayList;

public class ConsulterRemarques extends Form {

    public ConsulterRemarques(Form form){
        setTitle("La Liste des remarques");
        form = this;



        Container detail = new Container(BoxLayout.y());
        add(detail);
        ArrayList<Remarque>  rmk= RemarqueService.getInstance().mesremarques();

        for(int i = 0; i<rmk.size(); i++){


            String id = String.valueOf(rmk.get(i).getId());
            Label lbID = new Label(id);
            lbID.isHidden(true);
            SpanLabel lbnom = new SpanLabel("Enfant  :"+rmk.get(i).getEnfant());


            SpanLabel lbtut = new SpanLabel("Tuteur  :"+rmk.get(i).getNomtut());


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
