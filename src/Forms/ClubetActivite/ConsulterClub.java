package Forms.ClubetActivite;

import Entities.Club;
import Entities.Trajet;
import Forms.Accueils.AccueilResponsable;
import Forms.Sami.ConsulterTrajet;
import Services.ClubService;
import Services.TrajetService;
import com.codename1.components.*;
import com.codename1.components.SpanLabel;
import com.codename1.io.File;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.util.Resources;
import com.sun.javafx.collections.SetListenerHelper;
import esprit.tn.MyApplication;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.codename1.ui.Image.createImage;


public class ConsulterClub extends Form {
    private Resources theme;


    public ConsulterClub(Form prev) {

        Form fo = this;
        setLayout(BoxLayout.y());
        setTitle("Liste des Clubs");




      /*  Container holder = new Container(BoxLayout.x());
        add(holder);
        Container detail = new Container(BoxLayout.y());
        add(detail);

        ArrayList<Club> l = ClubService.getInstance().getAllclubs();
        for (int i = 0; i < l.size(); i++) {
            Label lbName = new Label(l.get(i).getName());
            Label lbDescription = new Label(l.get(i).getDescription());
            //    ImageViewer image = new ImageViewer(theme.getImage("17 Transformers 3.jpg"));


            //  add(image);
            //holder.add(image);
            detail.add(lbName);
            detail.add(lbDescription);


        }*/


        ArrayList<Club> lc = ClubService.getInstance().getAllclubs();
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        for (Club cl : lc) {
            data.add(createListEntry(cl.getName(), cl.getDescription(),cl.getPhoto() ));
        }
        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
        MultiList ml = new MultiList(model);
        fo.add(ml);
        fo.show();



        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());

    }

    private Map<String, Object> createListEntry(String name, String description, String photo) {
        int mm = Display.getInstance().convertToPixels(3);
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 4, 0), false);
        Image icon1 = URLImage.createToStorage(placeholder, "icon1", "photo");

        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", name);
        entry.put("Line2", description);
        entry.put("icon",icon1);
        return entry;
    }



}
