package Forms.User;

import Forms.Abonnements.AjouterAbonnement;
import Forms.Abonnements.ConsulterAbonnement;
import Forms.Enfants.AjouterEnfant;
import Forms.Enfants.ConsulterEnfant;
import Forms.Parent.Editprofile;
import Forms.Remarques.ConsulterRemarques;
import Forms.raed.AfficheJArdin;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.*;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import  esprit.tn.MyApplication;
import java.io.IOException;

public class BaseForm extends Form {
    Image imgg;
    Image img1;
    EncodedImage enc;

    public BaseForm() {
    }

    public BaseForm(Layout contentPaneLayout) {

        super(contentPaneLayout);
    }

    public BaseForm(String title, Layout contentPaneLayout) {

        super(title, contentPaneLayout);
    }


    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    public Resources res= MyApplication.theme;


    protected void addSideMenu() {





            //String url2="http://localhost/fixitweb1/web/upload/aucun.png";
            img1=res.getImage("profile-pic.jpg");


        Toolbar tb = getToolbar();
        Image img = res.getImage("home2.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom(
                        new Label(img1, "PictureWhiteBackgrond"))
        ));

        Form fo=this;

        tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, s -> new Editprofile(fo).show());
        tb.addMaterialCommandToSideMenu("ajouter enfant", FontImage.MATERIAL_ADD_SHOPPING_CART,e -> new AjouterEnfant(fo).show());
        tb.addMaterialCommandToSideMenu("consulter jardin", FontImage.MATERIAL_SHOP,s -> new AfficheJArdin(fo).show());
        tb.addMaterialCommandToSideMenu("ajouter abonnement", FontImage.MATERIAL_SHOP,s -> new AjouterAbonnement(fo).show());
        tb.addMaterialCommandToSideMenu("consulter abonnement", FontImage.MATERIAL_SHOP, s -> new ConsulterAbonnement(fo).show());
         tb.addMaterialCommandToSideMenu("consulter enfants", FontImage.MATERIAL_ADD_TO_QUEUE, s -> new ConsulterEnfant(fo).show());
        tb.addMaterialCommandToSideMenu("remarques des enfants", FontImage.MATERIAL_EXIT_TO_APP, s -> new ConsulterRemarques(fo).show());
        /*
        Button b = new Button("ajouter enfant");
        b.addActionListener(e -> new AjouterEnfant(fo).show());

        Button bss = new Button("consulter jardin");
        bss.addActionListener(s -> new AfficheJArdin(fo).show());

        Button bs = new Button("ajouter abonnement");
        bs.addActionListener(s -> new AjouterAbonnement(fo).show());
        Button ca = new Button("consulter abonnement");
        ca.addActionListener(s -> new ConsulterAbonnement(fo).show());
        Button bse = new Button("consulter enfants");
        bse.addActionListener(s -> new ConsulterEnfant(fo).show());

        Button bts = new Button("Consulter Les Clubs");
        bts.addActionListener(s -> new ConsulterClub(fo).show());
        Button bt = new Button("Consulter Activité");
        bt.addActionListener(s -> new ConsulterActivite(fo).show());
        Button btnmyrem = new Button("remarques des enfants");
        btnmyrem.addActionListener(s -> new ConsulterRemarques(fo).show());


        Button btnreclam = new Button("Envoyer une réclamation");
        btnreclam.addActionListener(s -> new SendReclam(fo).show());

        Button btnprofile = new Button("Modifier profile");
        btnprofile.addActionListener(s -> new Editprofile(fo).show());
        Button btnchat = new Button("Contacter Jardin");
        btnchat.addActionListener(s -> new Chat(fo).show());
*/

    }



}