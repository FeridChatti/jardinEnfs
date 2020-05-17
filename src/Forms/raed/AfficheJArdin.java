package Forms.raed;

import Entities.Enfant;
import Entities.Jardin;
import Forms.Enfants.ModifierEnfant;

import Services.JardinService;
import com.codename1.l10n.ParseException;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AfficheJArdin extends Form {
    String jrid="";
    String jrn="";
    String jrad="";
    String jrd="";
    String jart="";
    public AfficheJArdin (Form prev){
        Form th=this;

        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK,e->prev.showBack());
        setTitle("Affiche Jardin");
        setLayout(BoxLayout.y());

        int mm = Display.getInstance().convertToPixels(3);
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 3, 0), false);
        Image icon1 = URLImage.createToStorage(placeholder, "icon1", "http://www.georgerrmartin.com/wp-content/uploads/2013/03/GOTMTI2.jpg");

        Image icon2 = URLImage.createToStorage(placeholder, "icon2", "http://www.vippng.com/png/detail/35-352335_baby-boy-icon-png-icone-enfant-png.png");


        //add(detail);
        ArrayList<Jardin> jar= JardinService.getInstance().ListJardin();
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        for(Jardin jard:jar){

            data.add(createListEntry(jard.getName(),jard.getId(),jard.getAdresse(),jard.getDescription(),jard.getTarif(),icon2));

        }

        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
        MultiList ml = new MultiList(model);
        ml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Map<String, Object> t = (HashMap) ml.getSelectedItem();

                jrn=(String)t.get("Line1");
                jrad=(String)t.get("Line2");
                jrd=(String)t.get("description");
                jart=(String)t.get("tarif");
                String idj=(String)t.get("id");

                 new InfoJArdin(jrn,jrad,jrd,jart,th,idj).show();
            }
        });

        add(ml);

    }

    private Map<String, Object> createListEntry(String s, int id, String adresse,String description,double tarif, Image icon2) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", s);
        entry.put("id",String.valueOf(id));
        entry.put("Line2", adresse);
        entry.put("icon",icon2);
        entry.put("description",description);

        entry.put("tarif",String.valueOf(tarif));
        return entry;
    }





}


