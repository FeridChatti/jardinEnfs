package Forms.Accueils;

import Entities.ChartModel;
import Entities.Evenement;
import Forms.ClubetActivite.AjouterActivite;

import Forms.ClubetActivite.ListeParticipation;

import Forms.AbonnementRespon.ConsulterAbonnement;
import Forms.AbonnementRespon.LocalNotificationTest;
import Forms.ClubetActivite.ListeParticipation;
import Forms.Enfants.ConsulterEnfant;
import Forms.Enfants.GestionEnfant;
import Forms.Evenement.AjouterEvenement;
import Forms.ClubetActivite.ListeParticipation;
import Forms.Evenement.ConsulterListeEventsParent;
import Forms.Evenement.consulterListeEvent;
import Forms.Parent.Editprofile;
import Forms.Parent.JardList;
import Forms.Parent.SendReclam;
import Forms.Remarques.ConsulterRemarques;
import Forms.Responsable.UserList;
import Forms.Sami.AjouterTrajet;
import Forms.Sami.ConsulterTrajet;
import Forms.Sami.MapParent;
import Forms.User.SignIn;
import Forms.raed.AfficheJArdin;
import Forms.raed.AfficheJardinRespo;
import Forms.raed.AffichePaiem;
import Forms.raed.EffectuerPaiement;
import Services.EvenementService;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.ScaleImageLabel;
import com.codename1.l10n.ParseException;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import esprit.tn.MyApplication;

import java.util.ArrayList;

import static esprit.tn.MyApplication.authenticated;

public class AccueilResponsable extends Form {
    public static Form fo;
    Image img1;
    public Resources res= MyApplication.theme;
    public AccueilResponsable() {
        fo = this;
        setLayout(BoxLayout.y());





        /*
        Button bj = new Button("Consulter Vos Jardin");

        bj.addActionListener(e -> {
            try {
                new AfficheJardinRespo(fo, authenticated.getId());
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });

        Button cs = new Button("Consulter les trajets");
        cs.addActionListener(e -> new ConsulterTrajet(fo));

        Button participer = new Button("Consulter les participations");
        participer.addActionListener(e -> new ListeParticipation(fo).show());

        Button ajouterAct = new Button("Ajouter une activité");
        ajouterAct.addActionListener(e -> new AjouterActivite(fo).show());

        Button abonnement = new Button("Consulter les abonnements");
        abonnement.addActionListener(e -> new ConsulterAbonnement(fo).show());

        Button ajev = new Button("Ajouter un événement");
        ajev.addActionListener(e -> new AjouterEvenement(fo).show());

        Button list = new Button("Consulter les événement");
        list.addActionListener(e -> new consulterListeEvent(fo).show());


        Button msg = new Button("Messages");
        msg.addActionListener(e -> new UserList(fo).show());
        Button Paiement = new Button("Paiement");
        Paiement.addActionListener(e -> new EffectuerPaiement(fo).show());

        Button logout = new Button("Se déconnecter");
        logout.addActionListener(s -> new SignIn(MyApplication.theme).show());


        addAll(cs, participer, ajouterAct, abonnement, ajev, list, bj, msg, logout, Paiement);
*/
        addSideMenu();
        setUIID("statbg");
        add(createPieChartForm());
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

        tb.addMaterialCommandToSideMenu("Consulter Votre Jardin", FontImage.MATERIAL_SETTINGS,s-> {
            try {
                new AfficheJardinRespo(fo, authenticated.getId());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        tb.addMaterialCommandToSideMenu("Consulter les trajets", FontImage.MATERIAL_ADD_LOCATION,e -> new ConsulterTrajet(fo));

        tb.addMaterialCommandToSideMenu("Consulter les participations", FontImage.MATERIAL_RADIO_BUTTON_CHECKED,e -> new ListeParticipation(fo).show());

        tb.addMaterialCommandToSideMenu("Consulter abonnements", FontImage.MATERIAL_LIST,e -> new ConsulterAbonnement(fo).show());

        tb.addMaterialCommandToSideMenu("Ajouter une activité", FontImage.MATERIAL_LOCAL_ACTIVITY, e -> new AjouterActivite(fo).show());

        tb.addMaterialCommandToSideMenu("Consulter les événement", FontImage.MATERIAL_EVENT, e -> new consulterListeEvent(fo).show());



        tb.addMaterialCommandToSideMenu("Messages", FontImage.MATERIAL_CHAT, e -> new UserList(fo).show());




        tb.addMaterialCommandToSideMenu("Effectuer Paiement", FontImage.MATERIAL_WALLET_GIFTCARD, e -> new EffectuerPaiement(fo).show());

        tb.addMaterialCommandToSideMenu("Paiements", FontImage.MATERIAL_WALLET_MEMBERSHIP, e -> new AffichePaiem(fo).show());


        tb.addMaterialCommandToSideMenu("Se deconnecter", FontImage.MATERIAL_LOGOUT, s ->new SignIn(MyApplication.theme).show());



        /*





*/



    }

    public Form createPieChartForm() {


        ArrayList<ChartModel> chm= EvenementService.getInstance().ListeParticipantEvenement();

        // Generate the values

        // Set up the renderer
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(200);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(4, ColorUtil.GREEN);
        r.setHighlighted(true);

        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("Participations au evenements", chm), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        // Create a form and show it.
        Form f = new Form(  new BorderLayout());
        f.getToolbar().hideToolbar();
        f.add(BorderLayout.CENTER, c);
        f.setUIID("statbg");
        return f;

    }

    protected CategorySeries buildCategoryDataset(String title, ArrayList<ChartModel>  values) {
        CategorySeries series = new CategorySeries(title);

        for (ChartModel value : values) {
            Evenement e=EvenementService.getInstance().getEvent(value.getId());

            series.add("Evenement : "+ e.getTitre()+" ", value.getNb());
        }

        return series;
    }

    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(35);
        renderer.setLegendTextSize(35);
        renderer.setMargins(new int[]{});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }
}
