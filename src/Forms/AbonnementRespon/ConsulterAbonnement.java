package Forms.AbonnementRespon;

import Entities.Abonnement;
import Services.AbonnementService;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConsulterAbonnement extends Form {

    String enl="";
    String nmepre="";
    Form th;
    public ConsulterAbonnement(Form prev){
        th=this;
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());
        setTitle("Consulter Abonnements");
        setLayout(BoxLayout.y());
        int mm = Display.getInstance().convertToPixels(3);
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 3, 0), false);
        Image icon1 = URLImage.createToStorage(placeholder, "icon1", "http://www.georgerrmartin.com/wp-content/uploads/2013/03/GOTMTI2.jpg");
        // Container detail = new Container(BoxLayout.y());
        Image icon2 = URLImage.createToStorage(placeholder, "icon2", "http://www.vippng.com/png/detail/35-352335_baby-boy-icon-png-icone-enfant-png.png");


        //add(detail);
        ArrayList<Abonnement> abonnem= AbonnementService.getInstance().ListAbonnementResp("2");
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        for(Abonnement abon:abonnem){

            data.add(createListEntry(abon.getEnfant().getPrenom()+" "+abon.getEnfant().getNom(),abon.getEtat(),abon.getDate(),abon.getId(),icon2,abon.getType()));

        }

        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
        MultiList ml = new MultiList(model);
        ml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Map<String, Object> t = (HashMap) ml.getSelectedItem();
                enl=(String)t.get("id");
                nmepre=(String)t.get("Line1");
                Dialog.show("Modifier cet Enfant?","Veuillez selectionez un choix","Oui","Non");

                new ModifierAbonnement(th,enl,nmepre,(String)t.get("type"),"2").show();



            }
        });


        add(ml);







    }

    private Map<String, Object> createListEntry(String nom, String etat,String date,int idi,Image icon1,String type ) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", nom);
        entry.put("Line4", type);
        entry.put("Line2", etat);
        entry.put("Line3", date);
        entry.put("id",String.valueOf(idi));
        entry.put("type",type);
        entry.put("icon",icon1);

        // idenf=String.valueOf(idi);

        return entry;
    }



}
