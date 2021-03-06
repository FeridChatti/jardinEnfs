package Forms.AbonnementRespon;

import Entities.Abonnement;
import Entities.Jardin;
import Services.AbonnementService;
import Services.UserService;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static esprit.tn.MyApplication.authenticated;

public class ConsulterAbonnement extends Form {

    String enl="";
    String nmepre="";
    String numparent="";
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
        Image icon4 = URLImage.createToStorage(placeholder, "icon4", "http://www.nessma.tv/uploads/news/d31715327e0aa9d1256c2a492caf465727.jpg");


        //add(detail);

        Jardin ja= UserService.getInstance().getJardin(String.valueOf(authenticated.getId()));
        String idjar=String.valueOf(ja.getId());
        ArrayList<Abonnement> abonnem= AbonnementService.getInstance().ListAbonnementResp(idjar);
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        for(Abonnement abon:abonnem){

            data.add(createListEntry(abon.getEnfant().getPrenom()+" "+abon.getEnfant().getNom(),abon.getEtat(),abon.getDate(),abon.getId(),icon4,abon.getType(),abon.getEnfant().getParent().getNumtel()));

        }

        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
        MultiList ml = new MultiList(model);
        ml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Map<String, Object> t = (HashMap) ml.getSelectedItem();
                enl=(String)t.get("id");
                nmepre=(String)t.get("Line1");
                numparent=(String)t.get("numtel");

               if( Dialog.show("Modifier cet Enfant?","Veuillez selectionez un choix","Oui","Non")) {

                   new ModifierAbonnement(th, enl, nmepre, (String) t.get("type"), idjar, numparent).show();

               }

            }
        });


        add(ml);







    }

    private Map<String, Object> createListEntry(String nom, String etat,String date,int idi,Image icon1,String type,String numtel ) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", nom);
        entry.put("Line4", type);
        entry.put("Line2", etat);
        entry.put("Line3", date);
        entry.put("id",String.valueOf(idi));
        entry.put("type",type);
        entry.put("icon",icon1);
        entry.put("numtel",numtel);

        // idenf=String.valueOf(idi);

        return entry;
    }



}
