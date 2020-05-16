package Forms.Abonnements;

import Entities.Abonnement;
import Entities.Enfant;
import Forms.Enfants.ModifierEnfant;
import Services.AbonnementService;
import Services.EnfantService;
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

import static esprit.tn.MyApplication.authenticated;

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
    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 9, 0), false);
    Image icon1 = URLImage.createToStorage(placeholder, "icon1", "http://www.georgerrmartin.com/wp-content/uploads/2013/03/GOTMTI2.jpg");
    // Container detail = new Container(BoxLayout.y());
    Image icon3 = URLImage.createToStorage(placeholder, "icon3", "http://i.f1g.fr/media/madame/300x300_crop/sites/default/files/img/2020/03/confinement-enfants-activites.jpg");


    //add(detail);
    ArrayList<Abonnement> abonnem= AbonnementService.getInstance().ListAbonnement(String.valueOf(authenticated.getId()));
    ArrayList<Map<String, Object>> data = new ArrayList<>();
    Label label_6=new Label();

    for(Abonnement abon:abonnem){
        label_6.setText(" ");
        label_6.setUIID("Separator");
        label_6.setName("Label_6");
        label_6.setPreferredH(5);

        data.add(createListEntry(abon.getEnfant().getPrenom()+" "+abon.getEnfant().getNom(),abon.getJardin().getName(),abon.getEtat(),abon.getDate(),abon.getId(),icon3,abon.getType(),abon.getJardin().getId(),label_6));

    }

    DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
    MultiList ml = new MultiList(model);
    ml.addItem(label_6);
    ml.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Map<String, Object> t = (HashMap) ml.getSelectedItem();
            enl=(String)t.get("id");
            nmepre=(String)t.get("Line1");
           if (Dialog.show("Modifier cet Enfant?","Veuillez selectionez un choix","Oui","Non")) {

               new ModifierAbonnement(th, enl, nmepre, (String) t.get("type"), (String) t.get("jid")).show();


           }
        }
    });


    add(ml);







}

    private Map<String, Object> createListEntry(String nom,String jardin, String etat,String date,int idi,Image icon1,String type,int jid,Label lab ) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", nom);
        entry.put("Line4", jardin);
        entry.put("Line2", etat);
        entry.put("Line3", date);
        entry.put("id",String.valueOf(idi));
        entry.put("type",type);
        entry.put("icon",icon1);
        entry.put("jid",String.valueOf(jid));
        entry.put("label",lab);
        // idenf=String.valueOf(idi);

        return entry;
    }

}
