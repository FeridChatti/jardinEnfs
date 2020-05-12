package Forms.Evenement;
import Entities.Evenement;
import Forms.Accueils.AccueilResponsable;
import Services.EvenementService;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
public class ConsulterEvenement extends Form {

public ConsulterEvenement(Form prev,Evenement e ) {
    Evenement ev = EvenementService.getInstance().AfficherEvent(e);
    setLayout(BoxLayout.y());

    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, b -> prev.showBack());

    Label t = new Label("Titre");
    Label tt = new Label(ev.getTitre());
    Label d = new Label("Description");
    Label td = new Label(ev.getDescription());

    Label da = new Label("Date");
    Label daa = new Label(ev.getDate());

    Label c = new Label("Catégorie");
    Label cc = new Label(ev.getCategorie().getLibelle());

    addAll(t, tt, d, td, da, daa, c, cc);

    Button mod = new Button("Modifier");
    Button supp = new Button("Supprimer");
    addAll(mod, supp);


 /*
supp.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        Evenement ev = new Evenement();
        if (Dialog.show("Attention!", "Voulez vous vraiment supprimer cet événement ?", "Oui", "Non")) {
            EvenementService.getInstance().supprimerEvenement(ev.getId() + "");
            new ConsulterEvenement(AccueilResponsable.fo);
        }


    }

}}


}
  */
}}