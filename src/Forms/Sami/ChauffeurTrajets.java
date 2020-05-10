package Forms.Sami;

import Entities.Trajet;
import Forms.Accueils.AccueilResponsable;
import Services.TrajetService;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import esprit.tn.MyApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChauffeurTrajets {
    public ChauffeurTrajets(Form prev) {

        Form hi = new Form("Liste des trajets", BoxLayout.y());
        hi.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> prev.showBack());

        Button btmap=new Button("Voir map");
        btmap.addActionListener(e-> Dialog.show("Map","stay tuned","can't wait","can wait"));
        hi.addAll(btmap,new Label(""));

        ArrayList<Trajet> trajetList= TrajetService.getInstance().ListeTrajetsParChauffeur(MyApplication.authenticated.getId()+"");
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        for(Trajet tr :trajetList) {
            data.add(createListEntry(tr.getAdresse(), tr.getHeure(),tr));
        }
        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
        MultiList ml = new MultiList(model);
        hi.add(ml);
        hi.show();



    }

    private Map<String, Object> createListEntry(String adresse, String heure,Trajet trajet) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", "Adresse : "+adresse);
        entry.put("Line2", "Départ à "+heure);
        entry.put("Line3",trajet);
        return entry;
    }

}
