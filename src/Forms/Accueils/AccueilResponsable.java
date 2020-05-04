package Forms.Accueils;

import Forms.AjouterEnfant;
import Forms.Sami.AjouterTrajet;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import esprit.tn.MyApplication;

public class AccueilResponsable extends Form {
  Form fo;
    public AccueilResponsable()
    {fo=this;
        setLayout(BoxLayout.y());

        Button b=new Button("Ajouter un trajet");
        b.addActionListener(e->new AjouterTrajet(fo).show());

addAll(b);
    }

}
