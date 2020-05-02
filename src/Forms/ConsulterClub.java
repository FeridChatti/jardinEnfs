package Forms;

import Services.ClubService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import esprit.tn.MyApplication;

public class ConsulterClub extends Form {

    public ConsulterClub(MyApplication prev){

        setTitle("Liste des clubs");
        SpanLabel sp =new SpanLabel();
        sp.setText(ClubService.getInstance().getAllclubs().toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.start());

    }
}
