package Forms.Evenement;

import Entities.Evenement;
import Services.EvenementService;
import Services.UserService;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import esprit.tn.MyApplication;
import Forms.Accueils.AccueilResponsable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConsulterListeEventsParent extends Form {

    Form th;

    public ConsulterListeEventsParent(Form prev) {
        th = this;
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> prev.showBack());
        setTitle("Consulter la liste des événements");
        setLayout(BoxLayout.y());

        int mm = Display.getInstance().convertToPixels(3);
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 3, 0), false);

        Image icon = URLImage.createToStorage(placeholder, "icon6", "http://thumbs.dreamstime.com/b/eskisehir-turquie-mai-enfants-pr%C3%A9scolaires-s-occupant-un-%C3%A9v%C3%A9nement-animal-de-jour-dans-le-jardin-d-enfants-92046527.jpg");








        ArrayList<Evenement> ev= EvenementService.getInstance().AfficherEventPar(MyApplication.authenticated.getId()+"");
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        for(Evenement event:ev){

            data.add(createListEntry(event.getTitre(),event.getId(),event.getDate(),icon));

        }

        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
        MultiList ml = new MultiList(model);
        ml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Map<String, Object> t = (HashMap) ml.getSelectedItem();
                int id=(int) t.get("id");
                new DetailsEvenement(th,id).show();


            }
        });


        add(ml);


    }

    private Map<String, Object> createListEntry(String titre, int id, String date, Image icon ) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", titre);
        entry.put("Line2", date);
        entry.put("id",id);
        entry.put("icon",icon);
        return entry;



    }



}
