package Forms.Sami;

import Entities.Trajet;
import Forms.Accueils.AccueilResponsable;
import Services.MapService;
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

public class ChauffeurTrajets extends Form {
    public ChauffeurTrajets(Form prev) throws Exception {

        Form hi = new Form("Liste des trajets", BoxLayout.y());
        hi.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> prev.showBack());
        ArrayList<Trajet> trajetList= TrajetService.getInstance().ListeTrajetsParChauffeur(MyApplication.authenticated.getId()+"");

        Button btmap=new Button("Voir map");
        btmap.addActionListener(e->{new MapChauffeur(hi);});
btmap.setUIID("Confirmbtn");
        hi.addAll(btmap,new Label(""));
        int mm = Display.getInstance().convertToPixels(3);
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 4, 0), true);
        Image urlImage = URLImage.createToStorage(placeholder, "urlImage", "https://image.maps.ls.hereapi.com/mia/1.6/mapview?apiKey=CxxCHigH6e2itFdUuYEJdiNCKYOFT2wwtIF2QxxIjiw&co=tunisie&ci=tunis");
        urlImage.scaled(10,10);

        ArrayList<Map<String, Object>> data = new ArrayList<>();
        for(Trajet tr :trajetList) { data.add(createListEntry(tr.getAdresse(), tr.getHeure(),tr,urlImage));}

        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
        MultiList ml = new MultiList(model);

        hi.add(ml);
        hi.show();



    }

    private Map<String, Object> createListEntry(String adresse, String heure,Trajet trajet,Image urlImage) {

        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", "Adresse : "+adresse);
        entry.put("Line2", "Départ à "+heure);
        entry.put("Line3",trajet);
        entry.put("icon",urlImage);

        return entry;
    }

}
