package Forms.Accueils;

import Forms.Sami.ChauffeurTrajets;
import Forms.Sami.ConsulterTrajet;
import Forms.Sami.MapsDemo;
import Forms.Sami.ProfilChauffeur;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

public class AccueilChauffeur extends Form {
    public static Form fo;

    public AccueilChauffeur()
    {fo=this;
        setLayout(BoxLayout.y());

        Button cs=new Button("Voir mes trajets");
        cs.addActionListener(e-> {
            try {
                new ChauffeurTrajets(fo);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });


        Button ch=new Button("Consulter Profil");
        ch.addActionListener(e->new ProfilChauffeur(fo).show());


        addAll(cs,ch);

    }
}
