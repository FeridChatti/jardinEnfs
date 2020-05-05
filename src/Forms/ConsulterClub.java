package Forms;

import Entities.Club;
import Services.ClubService;
import com.codename1.components.*;
import com.codename1.components.SpanLabel;
import com.codename1.io.File;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import esprit.tn.MyApplication;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static com.codename1.ui.Image.createImage;


public class ConsulterClub extends Form {
    private Resources theme;


    public ConsulterClub(Form prev)  {

        Form fo= this;
        setTitle("Liste des clubs");

      Container holder = new Container(BoxLayout.x());
      add(holder);
        Container detail = new Container(BoxLayout.y());
        add(detail);

        ArrayList<Club> l = ClubService.getInstance().getAllclubs();
        for(int i=0; i<l.size(); i++){
            Label lbName = new Label(l.get(i).getName());
            Label lbDescription = new Label(l.get(i).getDescription());
       //    ImageViewer image = new ImageViewer(theme.getImage("17 Transformers 3.jpg"));



          //  add(image);
            //holder.add(image);
            detail.add(lbName);
            detail.add(lbDescription);




        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());

    }
}
