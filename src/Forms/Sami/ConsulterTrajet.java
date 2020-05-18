package Forms.Sami;

import Entities.Trajet;
import Forms.Accueils.AccueilResponsable;
import Services.TrajetService;
import Services.UserService;
import com.codename1.ui.*;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import esprit.tn.MyApplication;


import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConsulterTrajet extends Form {
    public ConsulterTrajet(Form prev) {

        ArrayList<Trajet> trajetList= TrajetService.getInstance().ListeTrajets(UserService.getInstance().getJardin(MyApplication.authenticated.getId()+"").getId() +"");

        Form hi = new Form("Liste des trajets", BoxLayout.y());
  hi.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> prev.showBack());

        Button aj=new Button("Ajouter un trajet");
        aj.setUIID("Confirmbtn");
        aj.addActionListener(e->new AjouterTrajet(hi).show());
        Button btmap=new Button("Voir map");
        btmap.setUIID("Confirmbtn");
        btmap.addActionListener(e->new MapsDemo(trajetList,hi));
        hi.addAll(aj,btmap);

        ArrayList<Map<String, Object>> data = new ArrayList<>();
        int mm = Display.getInstance().convertToPixels(3);
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 4, 0), true);
        Image urlImage = URLImage.createToStorage(placeholder, "urlImage", "https://image.maps.ls.hereapi.com/mia/1.6/mapview?apiKey=CxxCHigH6e2itFdUuYEJdiNCKYOFT2wwtIF2QxxIjiw&co=tunisie&ci=tunis");
        urlImage.scaled(10,10);


        for(Trajet tr :trajetList) {
            data.add(createListEntry(tr.getAdresse(), tr.getChauffeur().getNom(),tr,urlImage));
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

    private Map<String, Object> createListEntry(String adresse, String chauffeur,Trajet trajet,Image im) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", adresse);
        entry.put("Line2", chauffeur);
        entry.put("Line3",trajet);
        entry.put("icon",im);
        return entry;
    }

    }
