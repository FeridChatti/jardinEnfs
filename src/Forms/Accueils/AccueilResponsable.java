package Forms.Accueils;

import Forms.ClubetActivite.ListeParticipation;
import Forms.AbonnementRespon.ConsulterAbonnement;
import Forms.AbonnementRespon.LocalNotificationTest;
import Forms.ClubetActivite.ListeParticipation;
import Forms.Evenement.AjouterEvenement;
import Forms.ClubetActivite.ListeParticipation;
import Forms.Evenement.ChartDemo;
import Forms.Evenement.consulterListeEvent;
import Forms.Responsable.UserList;
import Forms.Sami.AjouterTrajet;
import Forms.Sami.ConsulterTrajet;
import Forms.User.SignIn;
import Forms.raed.AfficheJardinRespo;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import esprit.tn.MyApplication;

import static esprit.tn.MyApplication.authenticated;

public class AccueilResponsable extends Form {
 public static   Form fo;

    public AccueilResponsable()
    { fo=this;
        setLayout(BoxLayout.y());
        Button bj=new Button("Consulter Vos Jardin");
        bj.addActionListener(e-> {
            try {
                new AfficheJardinRespo(fo,authenticated.getId());
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });

        Button cs=new Button("Consulter les trajets");
        cs.addActionListener(e->new ConsulterTrajet(fo));

        Button participer=new Button("Consulter les participations");
        participer.addActionListener(e->new ListeParticipation(fo));

        Button abonnement=new Button("Consulter les abonnements");
        abonnement.addActionListener(e->new ConsulterAbonnement(fo).show());

        Button ajev=new Button("Ajouter un événement");
        ajev.addActionListener(e->new AjouterEvenement(fo).show());

        Button list=new Button("Consulter les événement");
        list.addActionListener(e->new consulterListeEvent(fo).show());


        Button msg=new Button("Messages");
        msg.addActionListener(e->new UserList(fo).show());

        Button stat = new Button("Statistiques");
        stat.addActionListener(e ->new ChartDemo().show());

        Button logout = new Button("Se déconnecter");
        logout.addActionListener(s ->new SignIn(MyApplication.theme).show());


        addAll(cs,participer,abonnement,ajev,list,bj,msg,stat,logout);




    }

}
