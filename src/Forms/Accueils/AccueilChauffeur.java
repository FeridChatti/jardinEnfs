package Forms.Accueils;

import Forms.AbonnementRespon.ConsulterAbonnement;
import Forms.ClubetActivite.AjouterActivite;
import Forms.ClubetActivite.ListeParticipation;
import Forms.Evenement.consulterListeEvent;
import Forms.Responsable.UserList;
import Forms.Sami.ChauffeurTrajets;
import Forms.Sami.ConsulterTrajet;
import Forms.Sami.MapsDemo;
import Forms.Sami.ProfilChauffeur;
import Forms.User.SignIn;
import Forms.raed.AfficheJardinRespo;
import Forms.raed.EffectuerPaiement;
import com.codename1.components.ScaleImageLabel;
import com.codename1.l10n.ParseException;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import esprit.tn.MyApplication;

import static esprit.tn.MyApplication.authenticated;

public class AccueilChauffeur extends Form {
    public static Form fo;
    Image img1;
    public Resources res= MyApplication.theme;
    public AccueilChauffeur()
    {fo=this;
        setLayout(BoxLayout.y());

        Button cs=new Button("Voir mes trajets");
        cs.addActionListener(e-> {
            try {
                new ChauffeurTrajets(fo);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });


     /*   Button ch=new Button("Consulter Profil");
        ch.addActionListener(e->new ProfilChauffeur(fo).show());

        Button logout = new Button("Se déconnecter");
        logout.addActionListener(s ->new SignIn(MyApplication.theme).show());

        addAll(cs,ch,logout);
*/
     addSideMenu();
    }
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



        tb.addMaterialCommandToSideMenu("Voir mes trajets", FontImage.MATERIAL_ADD_LOCATION,e -> {
            try {
                new ChauffeurTrajets(fo);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        tb.addMaterialCommandToSideMenu("Consulter Profil", FontImage.MATERIAL_SETTINGS,e -> new ProfilChauffeur(fo).show());


        tb.addMaterialCommandToSideMenu("Se deconnecter", FontImage.MATERIAL_LOGOUT, s ->new SignIn(MyApplication.theme).show());



        /*





         */



    }

}
