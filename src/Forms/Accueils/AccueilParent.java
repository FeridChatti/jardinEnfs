package Forms.Accueils;

import Forms.*;
import Forms.Abonnements.AjouterAbonnement;

import Forms.ClubetActivite.ConsulterActivite;
import Forms.ClubetActivite.ConsulterClub;
import Forms.Abonnements.ConsulterAbonnement;

import Forms.Enfants.AjouterEnfant;
import Forms.Enfants.ConsulterEnfant;
import Forms.Enfants.ConsulterListeenf;
import Forms.Evenement.ConsulterEvenement;
import Forms.Evenement.consulterListeEvent;
import Forms.Parent.Chat;
import Forms.Parent.Editprofile;
import Forms.Parent.JardList;
import Forms.Parent.SendReclam;

import Forms.Remarques.ConsulterRemarques;
import Forms.User.BaseForm;
import Forms.raed.AfficheJArdin;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import esprit.tn.MyApplication;

public class AccueilParent extends BaseForm {
    Form fo;

    public AccueilParent() {
        fo = this;
        setLayout(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Parent");
        getContentPane().setScrollVisible(false);

        super.addSideMenu();

        Button b = new Button("ajouter enfant");
        b.addActionListener(e -> new AjouterEnfant(fo).show());

        Button bss = new Button("consulter jardin");
        bss.addActionListener(s -> new AfficheJArdin(fo).show());

        Button bs = new Button("ajouter abonnement");
        bs.addActionListener(s -> new AjouterAbonnement(fo).show());
        Button ca = new Button("consulter abonnement");
        ca.addActionListener(s -> new ConsulterAbonnement(fo).show());
        Button bse = new Button("consulter enfants");
        bse.addActionListener(s -> new ConsulterEnfant(fo).show());

        Button bts = new Button("Consulter Les Clubs");
        bts.addActionListener(s -> new ConsulterClub(fo).show());
        Button bt = new Button("Consulter Activité");
        bt.addActionListener(s -> new ConsulterActivite(fo).show());
        Button btnmyrem = new Button("remarques des enfants");
        btnmyrem.addActionListener(s -> new ConsulterRemarques(fo).show());


        Button btnreclam = new Button("Envoyer une réclamation");
        btnreclam.addActionListener(s -> new SendReclam(fo).show());

        Button btnprofile = new Button("Modifier profile");
        btnprofile.addActionListener(s -> new Editprofile(fo).show());
        Button btnchat = new Button("Contacter Jardin");
        btnchat.addActionListener(s -> new JardList(fo).show());

        Button btList = new Button("Liste des événements");
        btList.addActionListener(s -> new consulterListeEvent(fo).show());



        addAll(b, bs, bse, bts, bt, btnmyrem, btnreclam, btnprofile, btnchat, bss,btList);



    }

}
