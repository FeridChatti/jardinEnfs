package Forms.User;

import Forms.Abonnements.AjouterAbonnement;
import Forms.Abonnements.ConsulterAbonnement;
import Forms.ClubetActivite.ConsulterActivite;
import Forms.ClubetActivite.ConsulterClub;
import Forms.Enfants.AjouterEnfant;
import Forms.Enfants.ConsulterEnfant;
import Forms.Enfants.GestionEnfant;
import Forms.Evenement.ConsulterListeEventsParent;
import Forms.Evenement.consulterListeEvent;
import Forms.Parent.Chat;
import Forms.Parent.Editprofile;
import Forms.Parent.JardList;
import Forms.Parent.SendReclam;
import Forms.Remarques.ConsulterRemarques;
import Forms.Sami.MapParent;
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



    public Resources res= MyApplication.theme;


    protected void addSideMenu() {





            //String url2="http://localhost/fixitweb1/web/upload/aucun.png";
            img1=res.getImage("profile-pic.jpg");


        Toolbar tb = getToolbar();
        Image img = res.getImage("sidemenu.jpg");
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

        tb.addMaterialCommandToSideMenu("Gerer enfants", FontImage.MATERIAL_CREATE,e -> new GestionEnfant(fo).show());
        tb.addMaterialCommandToSideMenu("consulter jardins", FontImage.MATERIAL_SHOP,s -> new AfficheJArdin(fo).show());
        tb.addMaterialCommandToSideMenu("Consulter abonnements", FontImage.MATERIAL_LIST,s -> new ConsulterAbonnement(fo).show());
        tb.addMaterialCommandToSideMenu("Messages", FontImage.MATERIAL_CHAT, s -> new JardList(fo).show());
        tb.addMaterialCommandToSideMenu("Clubs", FontImage.MATERIAL_GAMES, s -> new ConsulterClub(fo).show());
        tb.addMaterialCommandToSideMenu("Activités", FontImage.MATERIAL_LOCAL_ACTIVITY, s -> new ConsulterActivite(fo).show());


        tb.addMaterialCommandToSideMenu("Remarques", FontImage.MATERIAL_NOTE, s -> new ConsulterRemarques(fo).show());
        tb.addMaterialCommandToSideMenu("Envoyer une réclamation", FontImage.MATERIAL_WARNING, s -> new SendReclam(fo));
        tb.addMaterialCommandToSideMenu("Evénements", FontImage.MATERIAL_EVENT, s -> new ConsulterListeEventsParent(fo).show());
        tb.addMaterialCommandToSideMenu("Trajets", FontImage.MATERIAL_LOCATION_SEARCHING, s -> new MapParent(fo));

        tb.addMaterialCommandToSideMenu("Se deconnecter", FontImage.MATERIAL_LOGOUT, s ->new SignIn(MyApplication.theme).show());



        /*
        Button btnmyrem = new Button("remarques des enfants");
        btnmyrem.addActionListener(s -> new ConsulterRemarques(fo).show());


        Button btnreclam = new Button("Envoyer une réclamation");
        btnreclam.addActionListener(s -> new SendReclam(fo));

        Button btnprofile = new Button("Modifier profile");
        btnprofile.addActionListener(s -> new Editprofile(fo));
        Button btnchat = new Button("Contacter Jardin");
        btnchat.addActionListener(s -> new JardList(fo).show());



        Button btList = new Button("Liste des événements");
        btList.addActionListener(s -> new ConsulterListeEventsParent(fo).show());
        btList.addActionListener(s -> new consulterListeEvent(fo).show());
        Button trajets = new Button("Les trajets disponibles");
        trajets.addActionListener(s -> new MapParent(fo));
*/



    }



}