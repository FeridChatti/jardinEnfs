package Forms.Sami;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import esprit.tn.MyApplication;

public class ConsulterTrajet extends Form {
    public ConsulterTrajet(MyApplication prev) {
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> prev.start());
        setTitle("Liste des Trajet");


    }
    }
