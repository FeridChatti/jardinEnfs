package Forms.Accueils;

import Forms.AjouterAbonnement;
import Forms.AjouterEnfant;
import Forms.ConsulterActivite;
import Forms.ParticiperActivite;
import Forms.Remarques.ConsulterRemarques;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import esprit.tn.MyApplication;

public class AccueilParent extends Form {
    Form fo;

    public AccueilParent() {
        fo = this;
        setLayout(BoxLayout.y());

        Button b = new Button("ajouter enfant");
        b.addActionListener(e -> new AjouterEnfant(fo).show());

        Button bs = new Button("ajouter abonnement");
        bs.addActionListener(s -> new AjouterAbonnement(fo).show());

        Button bt = new Button("ajouter");
        bt.addActionListener(s -> new ConsulterActivite(fo).show());


        Button btnmyrem = new Button("remarques des enfants");
        btnmyrem.addActionListener(s -> new ConsulterRemarques(fo).show());
        addAll(b, bs, bt,btnmyrem);

    }

}
