package Forms.Accueils;

import Forms.Remarques.AjouterRemarques;
import Forms.Remarques.TuteurRemarques;
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
        addAll(btnmyrem,addrmk);
    }

}
