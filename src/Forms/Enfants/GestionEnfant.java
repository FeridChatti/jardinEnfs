package Forms.Enfants;

import Forms.Accueils.AccueilParent;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;

public class GestionEnfant extends Form {
    public GestionEnfant(Form prev){
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK,e->prev.showBack());
        setTitle("Gestion Enfant");
        setLayout(BoxLayout.y());
        Tabs tabs= new Tabs();
        Style s = UIManager.getInstance().getComponentStyle("Tab");
        FontImage fo=FontImage.createMaterial(FontImage.MATERIAL_NATURE,s);
        FontImage fon=FontImage.createMaterial(FontImage.MATERIAL_ADD,s);
        tabs.addTab("Consultation",fo,new ConsulterEnfant(new AccueilParent()));
        tabs.addTab("Ajout",fon,new AjouterEnfant(new AccueilParent()));
        add(tabs);



    }
}
