package Forms;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import esprit.tn.MyApplication;

public class AjouterAbonnement extends Form {
    Form current;
    public AjouterAbonnement(MyApplication prev) {
        current=this;
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.start());
     /*   Button b=new Button("ajouter enfant");
        b.addActionListener(e->new AjouterEnfant(current).show());
        add(b);*/
        setTitle("Ajouter Abonnement");
        setLayout(BoxLayout.y());
        ComboBox enfant = new ComboBox();
        enfant.addItem("Bus");
        enfant.addItem("Normal");
        
        ComboBox types = new ComboBox();
        types.addItem("Bus");
        types.addItem("Normal");
        TextField montant= new TextField("","Montant");
        addAll(enfant,types,montant);




    }
}
