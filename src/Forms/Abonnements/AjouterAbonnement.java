package Forms.Abonnements;

import Entities.Abonnement;
import Entities.Enfant;
import Entities.Jardin;
import Forms.Accueils.AccueilParent;
import Services.AbonnementService;
import Services.EnfantService;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import esprit.tn.MyApplication;

import java.awt.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import static esprit.tn.MyApplication.authenticated;

public class AjouterAbonnement extends Form {
    public Resources theme = MyApplication.theme;
    Form current;
    public AjouterAbonnement(Form prev,String idj) {
        current=this;
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, theme.getImage("ajouterabonnement.jpg"), spacer1, "  ", "", " ");
        addTab(swipe, theme.getImage("home2.jpg"), spacer2, " ", "", "");

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        add(swipe);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());
     /*   Button b=new Button("ajouter enfant");
        b.addActionListener(e->new AjouterEnfant(current).show());
        add(b);*/
        setTitle("Ajouter Abonnement");
        setLayout(BoxLayout.y());
        Label enf=new Label("Enfant:");
        enf.getAllStyles().setFgColor(ColorUtil.GRAY);
        Label typ=new Label("Type:");
        typ.getAllStyles().setFgColor(ColorUtil.GRAY);
        Label mont=new Label("Montant:");
        mont.getAllStyles().setFgColor(ColorUtil.GRAY);
        ComboBox<Enfant> enfant = new ComboBox();
        ArrayList<Enfant> enfan=EnfantService.getInstance().ListEnfants(String.valueOf(authenticated.getId()));
        for (int i=0;i< enfan.size();i++){
        enfant.addItem(enfan.get(i));
        }

        ComboBox types = new ComboBox();
        types.addItem("normal");
        types.addItem("bus");
        TextField montant= new TextField("","Montant");
        ArrayList<Jardin> jar=EnfantService.getInstance().Montant(idj);
        for (int i=0;i< jar.size();i++){
            montant.setText(String.valueOf(jar.get(0).getTarif()));}

         types.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                 if(types.getSelectedItem().toString().equals("normal")){
                     for (int i=0;i< jar.size();i++){
                         montant.setText(String.valueOf(jar.get(0).getTarif()));
                     }
                 }
                 else if (types.getSelectedItem().toString().equals("bus")){
                     montant.setText(String.valueOf(jar.get(0).getTarif()+50));
                 }
             }
         });

        montant.setEnabled(false);
        Button bt=new Button("Ajouter");
      //  String mount=String.valueOf(mnt);
        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Date lt = Date.from(Instant.now());
                String text = lt.toString();
                String se=types.getSelectedItem().toString();

                Abonnement abonne=new Abonnement(text,se,"attente",montant.getText(),enfant.getSelectedItem());

                if(AbonnementService.getInstance().AjouterAbonnement(abonne,idj)){
                    Dialog.show("Succes","Ajout réussi",new Command("OK"));
                    new ConsulterAbonnement(new AccueilParent()).show();
                }

            }
        });


        Label vid=new Label(" ");
        addAll(enf,enfant,typ,types,mont,montant,bt);
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
