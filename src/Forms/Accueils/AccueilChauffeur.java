package Forms.Accueils;

import Forms.Sami.ConsulterTrajet;
import Forms.Sami.ProfilChauffeur;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

public class AccueilChauffeur extends Form {
    public static Form fo;

    public AccueilChauffeur()
    {fo=this;
        setLayout(BoxLayout.y());

        Button cs=new Button("Consulter les trajets");
        cs.addActionListener(e->new ConsulterTrajet(fo));


        Button ch=new Button("Consulter Profil");
        ch.addActionListener(e->new ProfilChauffeur(fo).show());


        addAll(cs,ch);

    }
}
