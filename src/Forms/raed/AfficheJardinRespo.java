package Forms.raed;

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



public class AfficheJardinRespo  extends Form {
    String enl="";
    public AfficheJardinRespo  (Form prev){
        Form th=this;

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());
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

            data.add(createListEntry(jard.getName()+" "+jard.getDescription(),jard.getNumtel(),jard.getId(),jard.getAdresse(),jard.getTarif(),icon2));

        }

        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
        MultiList ml = new MultiList(model);
        ml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Map<String, Object> t = (HashMap) ml.getSelectedItem();
                enl=(String)t.get("id");
                Dialog.show("Modifier cet Jardin?","Veuillez selectionez un choix","Oui","Non");

                try {
                    new ModifierEnfant(th,enl).show();
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        });

        add(ml);

    }

    private Map<String, Object> createListEntry(String s, String numtel, int id, String adresse, float tarif, Image icon2) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", s);
        entry.put("Line3", numtel);
        entry.put("Line4",String.valueOf(id));
        entry.put("Line5", adresse);
        entry.put("Line6",String.valueOf(tarif));
        entry.put("icon",icon2);
        return entry;
    }





}



