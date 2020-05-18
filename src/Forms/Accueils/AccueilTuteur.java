package Forms.Accueils;

import Forms.Remarques.AjouterRemarques;
import Forms.Remarques.TuteurRemarques;
import Forms.Sami.ChauffeurTrajets;
import Forms.Sami.ModifierTuteur;
import Forms.Sami.ProfilChauffeur;
import Forms.User.Login;
import Forms.User.SignIn;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import esprit.tn.MyApplication;

import static esprit.tn.MyApplication.authenticated;

public class AccueilTuteur extends Form {
    public static Form fo;
    Image img1;
    public Resources res= MyApplication.theme;
    public AccueilTuteur(){
        fo = this;
       /* setLayout(BoxLayout.y());
        Button btnmyrem = new Button("remarques des enfants");
        btnmyrem.addActionListener(s -> new TuteurRemarques(fo) );
        Button addrmk = new Button("ajouter une remarque");
        addrmk.addActionListener(s -> new AjouterRemarques(fo) );
        Button profil = new Button("Modifier profil");
        profil.addActionListener(s -> new ModifierTuteur(fo).show());

        Button logout = new Button("Se déconnecter");
        logout.addActionListener(s ->new SignIn(MyApplication.theme).show());

        addAll(btnmyrem,addrmk,profil,logout);
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

        tb.addMaterialCommandToSideMenu("Profil", FontImage.MATERIAL_SETTINGS,e ->         new ModifierTuteur(fo).show());

        tb.addMaterialCommandToSideMenu("Ajouter une remarque", FontImage.MATERIAL_ADD ,e -> {

                new AjouterRemarques(fo)         ;
        });

        tb.addMaterialCommandToSideMenu("Remarques des enfants", FontImage.MATERIAL_LIST ,e-> new TuteurRemarques(fo));


        tb.addMaterialCommandToSideMenu("Se déconnecter", FontImage.MATERIAL_LOGOUT, s ->new SignIn(MyApplication.theme).show());


    }

}
