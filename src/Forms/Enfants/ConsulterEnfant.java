package Forms.Enfants;

import Entities.Enfant;
import Entities.Trajet;
import Services.EnfantService;
import com.codename1.l10n.ParseException;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static esprit.tn.MyApplication.authenticated;

public class ConsulterEnfant extends Form {
    String enl="";
    public ConsulterEnfant(Form prev){
        Form th=this;

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());
        setTitle("Consulter enfants");
        setLayout(BoxLayout.y());

        int mm = Display.getInstance().convertToPixels(3);
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 3, 0), false);
        Image icon1 = URLImage.createToStorage(placeholder, "icon1", "http://www.georgerrmartin.com/wp-content/uploads/2013/03/GOTMTI2.jpg");
       // Container detail = new Container(BoxLayout.y());
        Image icon2 = URLImage.createToStorage(placeholder, "icon2", "http://www.vippng.com/png/detail/35-352335_baby-boy-icon-png-icone-enfant-png.png");


        //add(detail);
        ArrayList<Enfant>enfa=EnfantService.getInstance().ListEnfants(String.valueOf(authenticated.getId()));
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        for(Enfant enf:enfa){

            data.add(createListEntry(enf.getNom()+" "+enf.getPrenom(),enf.getDatenaiss(),enf.getId(),icon2));

        }

        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
        MultiList ml = new MultiList(model);
        ml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Map<String, Object> t = (HashMap) ml.getSelectedItem();
                enl=(String)t.get("id");
                Dialog.show("Modifier cet Enfant?","Veuillez selectionez un choix","Oui","Non");

                try {
                    new ModifierEnfant(th,enl).show();
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        });

        add(ml);

    }
    private Map<String, Object> createListEntry(String nom, String date,int idi,Image icon1 ) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", nom);
        entry.put("Line2", date);
        entry.put("id",String.valueOf(idi));
        entry.put("icon",icon1);
        // idenf=String.valueOf(idi);

        return entry;
    }


}
