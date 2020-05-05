package Forms.Accueils;

import Forms.Sami.AjouterTrajet;
import Forms.Sami.ConsulterTrajet;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

public class AccueilResponsable extends Form {
 public static   Form fo;

    public AccueilResponsable()
    {fo=this;
        setLayout(BoxLayout.y());

        Button cs=new Button("Consulter les trajets");
        cs.addActionListener(e->new ConsulterTrajet(fo));


        addAll(cs);

    }

}
