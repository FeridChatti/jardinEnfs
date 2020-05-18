package Forms.Evenement;


import Entities.Evenement;
import Forms.Accueils.AccueilResponsable;
import Services.EvenementService;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import esprit.tn.MyApplication;


public class DetailsEvenement extends Form {
    public Resources theme = MyApplication.theme;

    Form current;

    public DetailsEvenement(Form prev,int e ) {


        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, theme.getImage("event-2.jpg"), spacer1, "  ", "", " ");
        addTab(swipe, theme.getImage("event-2.jpg"), spacer2, " ", "", "");

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        add(swipe);







        Evenement ev = EvenementService.getInstance().getEvent(e);
        setLayout(BoxLayout.y());
        setTitle("Détails événement");

        current=this;

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, b -> prev.showBack());

        Label t = new Label("Titre");
        t.getAllStyles().setFgColor(ColorUtil.MAGENTA);

        Label tt = new Label(ev.getTitre());
        Label d = new Label("Description");
        d.getAllStyles().setFgColor(ColorUtil.MAGENTA);

        Label td = new Label(ev.getDescription());

        Label da = new Label("Date");
        da.getAllStyles().setFgColor(ColorUtil.MAGENTA);

        Label daa = new Label(ev.getDate());




        addAll(t, tt, d, td, da, daa);

        Button part = new Button("Participer");

        part.setUIID("Confirmbtn");
        add(part);


        part.addActionListener(new ActionListener() {
                                   @Override
                                   public void actionPerformed(ActionEvent evt) {


                                           new Participer(current,e).show();


                                       }


                               }

        );


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
                        com.codename1.ui.layouts.BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        // FlowLayout.encloseIn(likes, comments),
                                        spacer
                                )
                        )
                );

        swipe.addTab("", page1);
    }












}




