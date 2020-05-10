package Forms.raed;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

public class InfoJArdin  extends Form{
    public  InfoJArdin( String name , String adresse , String description,String tarif, Form f){
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->f.showBack());
        setLayout(BoxLayout.y());
        Label lp = new Label("Nom:");
        Label ls=new Label(name);
        Label l = new Label("Adresse:");
        Label la=new Label(adresse);
        Label le = new Label("Description:");
        Label lr=new Label(description);
        Label lee = new Label("Tarif:");
        Label lm=new Label(tarif);

add(lp);
add(ls);

        addAll(l,la,le,lr,lee,lm);

    }
}
