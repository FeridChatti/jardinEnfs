package Forms.Accueils;

import Forms.Remarques.AjouterRemarques;
import Forms.Remarques.TuteurRemarques;
import Forms.Sami.ModifierTuteur;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

public class AccueilTuteur extends Form {
    Form fo;


    public AccueilTuteur(){
        fo = this;
        setLayout(BoxLayout.y());
        Button btnmyrem = new Button("remarques des enfants");
        btnmyrem.addActionListener(s -> new TuteurRemarques(fo).show());
        Button addrmk = new Button("ajouter une remarque");
        addrmk.addActionListener(s -> new AjouterRemarques(fo).show());
        Button profil = new Button("Modifier profil");
        profil.addActionListener(s -> new ModifierTuteur(fo).show());

        addAll(btnmyrem,addrmk,profil);
    }

}
