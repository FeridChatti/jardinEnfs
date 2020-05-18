package Forms.Sami;

import Entities.Chauffeur;
import Entities.Trajet;
import Forms.Accueils.AccueilResponsable;
import Services.ChauffeurService;
import Services.TrajetService;
import Services.UserService;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.Validator;
import esprit.tn.MyApplication;

import javax.xml.soap.Text;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class AjouterTrajet extends Form {

    public AjouterTrajet(Form prev) {
        ArrayList<Chauffeur> lc = ChauffeurService.getInstance().ListeChauffeursJardin(UserService.getInstance().getJardin(MyApplication.authenticated.getId() + "").getId() + "");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> prev.showBack());
        setTitle("Ajouter un trajet");
        setLayout(BoxLayout.y());
        TextComponent adresse = new TextComponent().label("Adresse");
        TextComponent heure = new TextComponent().label("Heure");
        Validator val = new Validator();
        val.addConstraint(adresse, new LengthConstraint(1));
        val.addConstraint(heure, new LengthConstraint(1));
        Button aj = new Button("Ajouter");
        aj.setUIID("Confirmbtn");
        ComboBox<Chauffeur> cb = new ComboBox<>();
        for (Chauffeur c : lc) {
            cb.addItem(c);
        }

        add(adresse);
        add(heure);

        addAll(cb, aj);

        aj.addActionListener((event) -> {
            Chauffeur ch = cb.getSelectedItem();
            String ad = adresse.getText();
            String hr = heure.getText();
            Trajet tr = new Trajet(ch.getId(), ad, hr);

            String response = TrajetService.getInstance().AjouterTrajet(tr,String.valueOf(ch.getId()));
            if (response.trim().equals("true")) {
                Dialog.show("Ajout éffectué", "Vous avez ajouter un trajet pour le chauffeur : " + ch.getNom(), new Command("OK"));
                new ConsulterTrajet(AccueilResponsable.fo);
            }
        });
    }
}
