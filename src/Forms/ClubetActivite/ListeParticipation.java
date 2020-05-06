package Forms.ClubetActivite;

import Entities.PartActivite;
import Services.ActiviteService;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListeParticipation extends Form {

    public ListeParticipation(Form prev){
        Form fo = this;
        setLayout(BoxLayout.y());
        setTitle("Liste des Participants");


        ArrayList<PartActivite> lc = ActiviteService.getInstance().ListeParticipants();
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        for (PartActivite pa : lc) {
            data.add(createListEntry(pa.getActivite().getTypeact(), pa.getEnfant().getNom(),pa.getDate() ));
        }
        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
        MultiList ml = new MultiList(model);
        fo.add(ml);
        fo.show();



        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());

    }

    private Map<String, Object> createListEntry(String name, String nomenfant, String date) {
        int mm = Display.getInstance().convertToPixels(3);

        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", name);
        entry.put("Line2", nomenfant);
        entry.put("Line3",date);
        return entry;
    }


    }




