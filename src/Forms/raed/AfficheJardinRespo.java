package Forms.raed;

import Entities.Enfant;
import Entities.Jardin;
import Entities.User;
import Forms.Enfants.ModifierEnfant;
import Services.EnfantService;
import Services.JardinService;
import Services.UserService;
import com.codename1.l10n.ParseException;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.spinner.Picker;
import esprit.tn.MyApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class AfficheJardinRespo  extends Form {
    String enl="";
    public AfficheJardinRespo  (Form prev){
        Form th=this;

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());
        setTitle("Affiche Jardin Responsable");
        setLayout(BoxLayout.y());

        int mm = Display.getInstance().convertToPixels(3);
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 3, 0), false);
        Image icon1 = URLImage.createToStorage(placeholder, "icon1", "http://www.georgerrmartin.com/wp-content/uploads/2013/03/GOTMTI2.jpg");

        Image icon2 = URLImage.createToStorage(placeholder, "icon2", "http://www.vippng.com/png/detail/35-352335_baby-boy-icon-png-icone-enfant-png.png");

Jardin j= UserService.getInstance().getJardin(MyApplication.authenticated.getId()+"");


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
        TextField rse = new TextField((int) j.getTarif());
        rse.setEnabled(false);







    }






}



