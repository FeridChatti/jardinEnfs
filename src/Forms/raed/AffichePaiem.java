package Forms.raed;

import Entities.Jardin;
import Entities.Paiement;
import Services.JardinService;
import Services.PaimentService;
import Services.UserService;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import esprit.tn.MyApplication;

import java.sql.Date;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static esprit.tn.MyApplication.authenticated;

public class AffichePaiem extends Form {
    public AffichePaiem (Form prev) {
        Form th = this;

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> prev.showBack());
        setTitle("Affiche Paiement");
        setLayout(BoxLayout.y());

        int mm = Display.getInstance().convertToPixels(3);
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 3, 0), false);
        Image icon1 = URLImage.createToStorage(placeholder, "icon1", "http://www.georgerrmartin.com/wp-content/uploads/2013/03/GOTMTI2.jpg");

        Image icon2 = URLImage.createToStorage(placeholder, "icon2", "http://www.vippng.com/png/detail/35-352335_baby-boy-icon-png-icone-enfant-png.png");
        Jardin j= UserService.getInstance().getJardin(MyApplication.authenticated.getId()+"");

        ArrayList<Paiement> Paim= PaimentService.getInstance().ListPaim(String.valueOf(j.getId()));
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        for(Paiement Pee:Paim) {

            data.add(createListEntry(Pee.getId(),Pee.getDate(),Pee.getMontant(),icon2));

        }
        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
        MultiList ml = new MultiList(model);
        ml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Map<String, Object> t = (HashMap) ml.getSelectedItem();

            }
        });

        add(ml);

    }

    private Map<String, Object> createListEntry(int id, String date, float montant, Image icon2) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("id",String.valueOf(id));
        entry.put("Line1",String.valueOf(montant));
        entry.put("Line2",String.valueOf(date));
        entry.put("icon",icon2);
        return entry;
    }


    }

