package Forms.raed;

import Entities.Jardin;
import Entities.Paiement;
import Services.UserService;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import esprit.tn.MyApplication;

import java.text.Normalizer;

public class EffectuerPaiement extends Form {
    public  EffectuerPaiement(Form prev)
    {


        Form th = this;

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> prev.showBack());
        setTitle("Affiche Paiement");
        setLayout(BoxLayout.y());

        int mm = Display.getInstance().convertToPixels(3);
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 3, 0), false);
        Image icon1 = URLImage.createToStorage(placeholder, "icon1", "http://www.georgerrmartin.com/wp-content/uploads/2013/03/GOTMTI2.jpg");

        Image icon2 = URLImage.createToStorage(placeholder, "icon2", "http://www.vippng.com/png/detail/35-352335_baby-boy-icon-png-icone-enfant-png.png");
        Jardin j= UserService.getInstance().getJardin(MyApplication.authenticated.getId()+"");
        setLayout(BoxLayout.y());
        Label nom = new Label("Nom:");
        TextField t = new TextField(j.getName());
        t.setEnabled(false);
        Label add = new Label("Adresse");
        TextField re = new TextField(j.getAdresse());
        re.setEnabled(false);



        Label desc = new Label("Description");
        TextField ree = new TextField(j.getDescription());
        ree.setEnabled(false);



        Label nute = new Label("Numero Telephone ");
        TextField reee = new TextField(j.getNumtel());
        reee.setEnabled(false);

        Label tari = new Label("Tarif");
        TextField rse = new TextField(String.valueOf(j.getTarif()));
        rse.setEnabled(false);
        Paiement paiement=new Paiement();
        Label Mont=new Label("montant a payer");
        TextField rr= new TextField(250);

    }
}
