package Forms.raed;

import Forms.Abonnements.AjouterAbonnement;
import Forms.Accueils.AccueilParent;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

public class InfoJArdin  extends Form{
    Form th;
    public  InfoJArdin( String name , String adresse , String description,String tarif, Form f,String idj){
        th=this;
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
        Button AjouterAB=new Button("Ajouter un abonnement");
        AjouterAB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new AjouterAbonnement(th ,idj).show();
            }
        });

        addAll(l,la,le,lr,lee,lm,AjouterAB);

    }
}
