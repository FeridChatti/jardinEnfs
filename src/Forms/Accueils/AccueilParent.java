package Forms.Accueils;

import Forms.*;
import Forms.Abonnements.AjouterAbonnement;

import Forms.ConsulterActivite;
import Forms.Enfants.AjouterEnfant;
import Forms.Enfants.ConsulterEnfant;
import Forms.Parent.Editprofile;
import Forms.Parent.SendReclam;
import Forms.ParticiperActivite;
import Forms.Remarques.ConsulterRemarques;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import esprit.tn.MyApplication;

public class AccueilParent extends Form {
Form fo;
public AccueilParent()
{fo=this;
setLayout(BoxLayout.y());

    Button b=new Button("ajouter enfant");
    b.addActionListener(e->new AjouterEnfant(fo).show());

        Button bs = new Button("ajouter abonnement");
        bs.addActionListener(s -> new AjouterAbonnement(fo).show());
        Button bse = new Button("consulter enfants");
        bse.addActionListener(s -> new ConsulterEnfant(fo).show());

    Button bts=new Button("Consulter Les Clubs");
    bts.addActionListener(s->new ConsulterClub(fo).show());
        Button bt = new Button("Consulter Activité");
        bt.addActionListener(s -> new ConsulterActivite(fo).show());
        Button btnmyrem = new Button("remarques des enfants");
        btnmyrem.addActionListener(s -> new ConsulterRemarques(fo).show());


    Button btnreclam = new Button("Envoyer une réclamation");
    btnreclam.addActionListener(s -> new SendReclam(fo).show());

    Button btnprofile = new Button("Modifier profile");
    btnprofile.addActionListener(s -> new Editprofile(fo).show());

    addAll(b, bs, bse, bts, bt, btnmyrem,btnreclam,btnprofile);

}

}
