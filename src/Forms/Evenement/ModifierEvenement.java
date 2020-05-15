package Forms.Evenement;

import Entities.Categorie;
import Entities.Evenement;
import Forms.Accueils.AccueilResponsable;
import Services.CategorieService;
import Services.EvenementService;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import esprit.tn.MyApplication;

import java.util.ArrayList;
import java.util.Date;

import static com.codename1.push.PushContent.setTitle;

public class ModifierEvenement extends Form{

    public ModifierEvenement(Form prev, int id){

        ArrayList<Categorie> lc = CategorieService.getInstance().getAllcategories();
       Evenement e= EvenementService.getInstance().AfficherEvent(id);

       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, et -> prev.showBack());
        setLayout(BoxLayout.y());
        setTitle("Modifier événement");


        Label t = new Label("Titre");
        TextField tit=new TextField(e.getTitre());
        Label d = new Label("Description");
        TextArea desc=new TextArea(e.getDescription());
        Label da = new Label("Date");

        PickerComponent dateE = PickerComponent.createDate(new Date()).label("Date");

        Label tp=new Label("catégorie:");

        ComboBox<Categorie> c = new ComboBox<>();
        for (Categorie ca: lc) {
            c.addItem(ca);
        }

        Button mod=new Button("Modifier");

        mod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Evenement ev=new Evenement();
                ev.setTitre(tit.getText());

                ev.setDescription(desc.getText());
                ev.setDate("16-05-2020");
                ev.setCategorie(c.getSelectedItem());
                ev.setId(id);
                EvenementService.getInstance().modifierEvenement(ev);
                Dialog.show("Succes","modification réussie!",new Command("OK"));
            }
        });



        addAll(t,tit,d,desc,da,dateE,tp,c,mod);
show();



    }


}
