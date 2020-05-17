package Forms.Accueils;

import Forms.Remarques.AjouterRemarques;
import Forms.Remarques.TuteurRemarques;
import Forms.Sami.ModifierTuteur;
import Forms.User.Login;
import Forms.User.SignIn;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import esprit.tn.MyApplication;

import static esprit.tn.MyApplication.authenticated;

public class AccueilTuteur extends Form {
    Form fo;


    public AccueilTuteur(){
        fo = this;
        setLayout(BoxLayout.y());
        Button btnmyrem = new Button("remarques des enfants");
        btnmyrem.addActionListener(s -> new TuteurRemarques(fo) );
        Button addrmk = new Button("ajouter une remarque");
        addrmk.addActionListener(s -> new AjouterRemarques(fo) );
        Button profil = new Button("Modifier profil");
        profil.addActionListener(s -> new ModifierTuteur(fo).show());

        Button logout = new Button("Se déconnecter");
        logout.addActionListener(s ->new SignIn(MyApplication.theme).show());

        addAll(btnmyrem,addrmk,profil,logout);
    }

}
