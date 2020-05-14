package Forms.Evenement;

import Entities.Activite;
import Entities.Evenement;
import Forms.Accueils.AccueilResponsable;
import Forms.ClubetActivite.ParticiperActivite;
import Services.EvenementService;
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

public class DetailsEvenement extends Form {


    public DetailsEvenement(Form prev, Evenement e ) {
        setLayout(BoxLayout.y());

        Form fo = this;

        Evenement ev = EvenementService.getInstance().AfficherEvent(e);


        Label t = new Label("Titre");
        Label tt = new Label(ev.getTitre());
        Label d = new Label("Description");
        Label td = new Label(ev.getDescription());

        Label da = new Label("Date");
        Label daa = new Label(ev.getDate());

        Label c = new Label("Catégorie");
        Label cc = new Label(ev.getCategorie().getLibelle());
        Button part = new Button("Participer");

        addAll(t, tt, d, td, da, daa, c, cc, part);

        ArrayList<Evenement> le = EvenementService.getInstance().AfficherEventPar(MyApplication.authenticated.getId()+"");
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        for (Evenement evnt : le) {
            data.add(createListEntry(evnt.getTitre(), evnt.getDate(),evnt.getDescription(),String.valueOf(evnt.getId())));
        }
        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
        MultiList ml = new MultiList(model);
        fo.add(ml);
        fo.show();
        ml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Map<String, Object> t = (HashMap) ml.getSelectedItem();
                String tr = (String) t.get("Line1");
                if (Dialog.show("Participer à cet événement", "Vous avez choisi :" + t.get("Line1"), "Oui", "Non")) {
                    new Participer(fo, String.valueOf((String)t.get("id"))).show();
                }
            }
        });

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, b -> prev.showBack());


    }

    private Map<String, Object> createListEntry(String titre, String date,String description, String id) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", titre);
        entry.put("Line2", date);
        entry.put("Line3", description);
        entry.put("id",id);
        return entry;
    }

}
