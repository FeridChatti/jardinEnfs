package Forms;

import Entities.Club;
import Services.ClubService;
import com.codename1.components.*;
import com.codename1.components.SpanLabel;
import com.codename1.io.File;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import esprit.tn.MyApplication;

import java.io.IOException;
import java.net.URISyntaxException;


public class ConsulterClub extends Form {

    public ConsulterClub(MyApplication prev) throws URISyntaxException, IOException {

        setTitle("Liste des clubs");

      Container holder = new Container(BoxLayout.x());
      add(holder);
        Container detail = new Container(BoxLayout.y());
        add(detail);

        for(int i=0; i<ClubService.getInstance().getAllclubs().size(); i++){
            Label lbName = new Label(ClubService.getInstance().getAllclubs().get(i).getName());
            Label lbDescription = new Label(ClubService.getInstance().getAllclubs().get(i).getDescription());

            Image im = Image.createImage(ClubService.getInstance().getAllclubs().get(i).getPhoto());
            ImageViewer image = new ImageViewer(im);



            add(image);
            holder.add(image);
            detail.add(lbName);
            detail.add(lbDescription);



        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.start());

    }
}
