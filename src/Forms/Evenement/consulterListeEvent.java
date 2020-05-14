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
        ArrayList<Evenement> ev= EvenementService.getInstance().ListeEvenementJardin(UserService.getInstance().getJardin(MyApplication.authenticated.getId()+"").getId()+"");
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        for(Evenement event:ev){

            data.add(createListEntry(event.getTitre(),event.getId()));

        }

        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
        MultiList ml = new MultiList(model);
        ml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Evenement et=new Evenement();
                Map<String, Object> t = (HashMap) ml.getSelectedItem();
                et.setId((int) t.get("id"));
                new ConsulterEvenement(th,et).show();


            }
        });


        add(ml);


    }

    private Map<String, Object> createListEntry(String titre, int id ) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", titre);
        entry.put("id",id);

        return entry;



    }


    }

