package Forms.ClubetActivite;

import Forms.Accueils.AccueilParent;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;


public class gestionParent extends Form {

    public gestionParent() {

        Form fo = this;
        setLayout(BoxLayout.y());
        Tabs tabs = new Tabs();
        Style s = UIManager.getInstance().getComponentStyle("Tab");
        FontImage fof = FontImage.createMaterial(FontImage.MATERIAL_NATURE, s);
        FontImage fon = FontImage.createMaterial(FontImage.MATERIAL_ADD, s);
        tabs.addTab("participants", fof, new ListeParticipation(fo));
        tabs.addTab("activité", fon, new AjouterActivite(new AccueilParent()));
        add(tabs);
    }
}
