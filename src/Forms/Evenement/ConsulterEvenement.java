package Forms.Evenement;
import Entities.Evenement;
import Forms.Accueils.AccueilResponsable;
import Services.EvenementService;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
public class ConsulterEvenement extends Form {
Form current;

public ConsulterEvenement(Form prev,int e ) {
    Evenement ev = EvenementService.getInstance().AfficherEvent(e);
    setLayout(BoxLayout.y());
current=this;

    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, b -> prev.showBack());

    Label t = new Label("Titre");
    t.getAllStyles().setFgColor(ColorUtil.MAGENTA);

    Label tt = new Label(ev.getTitre());
    Label d = new Label("Description");
    d.getAllStyles().setFgColor(ColorUtil.MAGENTA);

    Label td = new Label(ev.getDescription());

    Label da = new Label("Date");
    da.getAllStyles().setFgColor(ColorUtil.MAGENTA);

    Label daa = new Label(ev.getDate());

    Label c = new Label("Catégorie");
    c.getAllStyles().setFgColor(ColorUtil.MAGENTA);

    Label cc = new Label(ev.getCategorie().getLibelle());

    addAll(t, tt, d, td, da, daa, c, cc);

    Button mod = new Button("Modifier");
    mod.setUIID("Confirmbtn");

    Button supp = new Button("Supprimer");
    supp.setUIID("Confirmbtn");

    addAll(mod, supp);


    supp.addActionListener(new ActionListener() {
                               @Override
                               public void actionPerformed(ActionEvent evt) {
                                   if (Dialog.show("Attention!", "Voulez vous vraiment supprimer cet événement ?", "Oui", "Non")) {
                                       EvenementService.getInstance().supprimerEvenement(ev.getId() + "");

                                       new consulterListeEvent(AccueilResponsable.fo).show();


                                   }

                               }
                           }

    );
    mod.addActionListener(new ActionListener() {
                               @Override
                               public void actionPerformed(ActionEvent evt) {
                             new ModifierEvenement(current,e);


                               }
                           }

    );




}}








