package Forms.Remarques;

import Entities.Remarque;
import Entities.Trajet;
import Services.RemarqueService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TuteurRemarques extends Form {

    public TuteurRemarques (Form prev){
        setTitle("La Liste des remarques");
        Form form = this;



       // Container detail = new Container(BoxLayout.y());
        //add(detail);

        setLayout(BoxLayout.y());
        ArrayList<Remarque> rmk= RemarqueService.getInstance().tutremarques();
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());


        ArrayList<Map<String, Object>> data = new ArrayList<>();
        for (Remarque rm : rmk) {
            data.add(createListEntry(rm.getEnfant(), rm.getNomtut(),rm.getDescription(),rm.getDate()));
        }
        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
        MultiList ml = new MultiList(model);




      /*  for(int i = 0; i<rmk.size(); i++){


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



        }*/

        add(ml);





    }
    private Map<String, Object> createListEntry(String enfant, String parent, String descr,String date) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line2", enfant);
        entry.put("Line1", parent);
        entry.put("Line3", descr);
        entry.put("Line4", date);
        return entry;
    }
}
