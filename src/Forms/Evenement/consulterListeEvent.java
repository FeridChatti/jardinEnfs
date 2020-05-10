package Forms.Evenement;

import Entities.Evenement;
import Services.EvenementService;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class consulterListeEvent extends Form {

    String nmepre="";
    Form th;

    public consulterListeEvent(Form prev) {
        th = this;
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> prev.showBack());
        setTitle("Consulter Evenements");
        setLayout(BoxLayout.y());



        ArrayList<Evenement> ev= EvenementService.getInstance().ListeEvenementJardin("2");
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        for(Evenement event:ev){

            data.add(createListEntry(event.getTitre(),event.getDescription(),event.getDate()));

        }

        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
        MultiList ml = new MultiList(model);
        ml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Map<String, Object> t = (HashMap) ml.getSelectedItem();
                nmepre=(String)t.get("Line1");

                Dialog.show("Détails de l'événement",t.get("Line1").toString()+t.get("Description")+t.get("Date"),null,"Cancel");


            }
        });


        add(ml);


    }

    private Map<String, Object> createListEntry(String titre, String description,String Date ) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", titre);
        entry.put("Description",description);
        entry.put("Date", Date);

        return entry;



    }


    }

