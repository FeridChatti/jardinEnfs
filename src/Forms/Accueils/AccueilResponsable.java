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
        Button participer=new Button("Consulter les participations");
        participer.addActionListener(e->new ListeParticipation(fo));
        Button abonnement=new Button("Consulter les abonnements");
        abonnement.addActionListener(e->new ConsulterAbonnement(fo).show());
        Button lc=new Button("Clock");
        lc.addActionListener(e->new LocalNotificationTest().start());

        Button ajev=new Button("Ajouter evenement");
        ajev.addActionListener(e->new AjouterEvenement(fo).show());



        addAll(cs,participer,abonnement,lc);


        addAll(cs,participer,ajev);

    }

}
