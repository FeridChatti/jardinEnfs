package Forms.Accueils;

import Forms.*;
import Forms.Abonnements.AjouterAbonnement;
import Forms.Enfants.AjouterEnfant;
import Forms.Enfants.ConsulterEnfant;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

public class AccueilParent extends Form {
Form fo;
public AccueilParent()
{fo=this;
setLayout(BoxLayout.y());

    Button b=new Button("ajouter enfant");
    b.addActionListener(e->new AjouterEnfant(fo).show());

    Button bs=new Button("ajouter abonnement");
    bs.addActionListener(s->new AjouterAbonnement(fo).show());
    Button bse=new Button("consulter enfants");
    bse.addActionListener(s->new ConsulterEnfant(fo).show());
    Button bts=new Button("Consulter Les Clubs");
    bts.addActionListener(s->new ConsulterClub(fo).show());
    Button bt=new Button("Consulter Activité");
    bt.addActionListener(s->new ConsulterActivite(fo).show());




    addAll(b,bs,bse,bts,bt);

}

}
