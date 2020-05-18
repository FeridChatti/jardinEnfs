package Forms.Accueils;

import Forms.Remarques.AjouterRemarques;
import Forms.Remarques.TuteurRemarques;
import Forms.Sami.ChauffeurTrajets;
import Forms.Sami.ModifierTuteur;
import Forms.Sami.ProfilChauffeur;
import Forms.User.Login;
import Forms.User.SignIn;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
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


        setLayout(BoxLayout.y());

        Container form=new Container();

        fo = this;
        setLayout(BoxLayout.y());

        // getToolbar().hideToolbar();

        setTitle("Espace Tuteur");


        getContentPane().setScrollVisible(false);

        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("swipe menu.jpg"), spacer1, "  ", "", " ");
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        form.add(swipe);


        Label schoolbellabel=new Label("\nKinderG Introduction","schoolbell");
        form.add(schoolbellabel);


        SpanLabel titlelavel=new SpanLabel();
        titlelavel.setText("Welcome to Kindergarten's Guides");
        titlelavel.setTextUIID("TitleLabel");
        form.add(titlelavel);

        SpanLabel contentlbl=new SpanLabel();
        contentlbl.setText("KinderG vous permet de trouver le jardin ideal pour votre enfant.\n" +
                "trouver le jardin d'enfants le plus proche depend a vos besoins sans aucun effort.");
        contentlbl.setTextUIID("txtlbl");
        form. add(contentlbl);


        form.setUIID("BackgroundForm");
        form.setPreferredH(getPreferredH());
        form.setPreferredW(getPreferredW());

        add(form);










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


    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {



        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());

        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }

        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");

        Container page1 =
                LayeredLayout.encloseIn(
                        image,
                        overlay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),

                                        spacer
                                )
                        )
                );

        swipe.addTab("", page1);




    }

}
