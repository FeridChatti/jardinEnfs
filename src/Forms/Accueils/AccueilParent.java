package Forms.Accueils;

import Forms.*;
import Forms.Abonnements.AjouterAbonnement;

import Forms.ClubetActivite.ConsulterActivite;
import Forms.ClubetActivite.ConsulterClub;
import Forms.Abonnements.ConsulterAbonnement;

import Forms.Enfants.AjouterEnfant;
import Forms.Enfants.ConsulterEnfant;

import Forms.Enfants.GestionEnfant;
import Forms.Evenement.ConsulterEvenement;
import Forms.Evenement.ConsulterListeEventsParent;
import Forms.Evenement.DetailsEvenement;
import Forms.Evenement.consulterListeEvent;
import Forms.Parent.Chat;
import Forms.Parent.Editprofile;
import Forms.Parent.JardList;
import Forms.Parent.SendReclam;

import Forms.Remarques.ConsulterRemarques;
import Forms.Sami.MapParent;
import Forms.User.BaseForm;
import Forms.User.SignIn;
import Forms.raed.AfficheJArdin;
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

public class AccueilParent extends BaseForm {
    Form fo;

    public Resources theme = MyApplication.theme;

    public AccueilParent() {


        Container form=new Container();

        fo = this;
        setLayout(BoxLayout.y());

       // getToolbar().hideToolbar();

        setTitle("Espace Parent");


        getContentPane().setScrollVisible(false);

       Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, theme.getImage("swipe menu.jpg"), spacer1, "  ", "", " ");
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
        super.addSideMenu();

        form.setUIID("BackgroundForm");
        form.setPreferredH(getPreferredH());
        form.setPreferredW(getPreferredW());

        add(form);



/*

        Button b = new Button("ajouter enfant");
        b.addActionListener(e -> new AjouterEnfant(fo).show());
        Button gestion = new Button("ajouter enfant");
        gestion.addActionListener(e -> new GestionEnfant(fo).show());

        Button bss = new Button("consulter jardin");
        bss.addActionListener(s -> new AfficheJArdin(fo).show());

        Button bs = new Button("ajouter abonnement");
        bs.addActionListener(s -> new AjouterAbonnement(fo,"2").show());
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
        btnreclam.addActionListener(s -> new SendReclam(fo));

        Button btnprofile = new Button("Modifier profile");
        btnprofile.addActionListener(s -> new Editprofile(fo));
        Button btnchat = new Button("Contacter Jardin");
        btnchat.addActionListener(s -> new JardList(fo).show());

        Button btList = new Button("Liste des événements");
        btList.addActionListener(s -> new ConsulterListeEventsParent(fo).show());


        Button trajets = new Button("Les trajets disponibles");
        trajets.addActionListener(s -> new MapParent(fo));


        Button logout = new Button("Se déconnecter");
        logout.addActionListener(s ->new SignIn(MyApplication.theme).show());


        addAll(b, bs, bse, bts, bt, btnmyrem, btnreclam, btnprofile, btnchat, bss,btList,trajets,logout,gestion);
*/


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
