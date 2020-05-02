package Forms;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;

public class AjouterAbonnement extends Form {
    Form current;
    public AjouterAbonnement() {
        current=this;

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
