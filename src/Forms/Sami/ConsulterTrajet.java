package Forms.Sami;

import Entities.Trajet;
import Forms.Accueils.AccueilResponsable;
import Forms.ParticiperActivite;
import Services.ActiviteService;
import Services.ChauffeurService;
import Services.TrajetService;
import com.codename1.ui.*;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import esprit.tn.MyApplication;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConsulterTrajet extends Form {
    public ConsulterTrajet(Form prev) {

        Form hi = new Form("Liste des trajets", BoxLayout.y());
  hi.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> prev.showBack());

        Button aj=new Button("Ajouter un trajet");
        aj.addActionListener(e->new AjouterTrajet(hi).show());
        Button btmap=new Button("Voir map");
        btmap.addActionListener(e->Dialog.show("Map","stay tuned","can't wait","can wait"));

        hi.addAll(aj,btmap);

        ArrayList<Trajet> trajetList= TrajetService.getInstance().ListeTrajets(4+"");
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        for(Trajet tr :trajetList) {
            data.add(createListEntry(tr.getAdresse(), tr.getChauffeur().getNom(),tr));
        }
        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
        MultiList ml = new MultiList(model);
        hi.add(ml);
        hi.show();
        ml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Map<String, Object> t = (HashMap) ml.getSelectedItem();
                Trajet tr = (Trajet) t.get("Line3");
                if (Dialog.show("Attention!", "Voulez vous vraiment supprimer ce trajet ?", "Oui", "Non")) {
                    TrajetService.getInstance().supprimerTrajet(tr.getId() + "");
new ConsulterTrajet(AccueilResponsable.fo);
                }
            }});



    }

    private Map<String, Object> createListEntry(String adresse, String chauffeur,Trajet trajet) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", adresse);
        entry.put("Line2", chauffeur);
        entry.put("Line3",trajet);
        return entry;
    }

    }
