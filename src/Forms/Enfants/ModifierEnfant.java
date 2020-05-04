package Forms.Enfants;

import Services.EnfantService;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;

public class ModifierEnfant extends Form {
    public ModifierEnfant(Form prev,String id){
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());
        Label l =new Label("nom");
        TextField t=new TextField(EnfantService.getInstance().ListEnfants("4").get(0).getNom());
        TextField re=new TextField(EnfantService.getInstance().ListEnfants("4").get(0).getPrenom());
        add(l);


    }
}
