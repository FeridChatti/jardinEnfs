package Forms.Enfants;

import Entities.Enfant;
import Entities.Trajet;
import Forms.AbonnementRespon.ConsulterAbonnement;
import Forms.Accueils.AccueilParent;
import Forms.Accueils.AccueilResponsable;
import Services.EnfantService;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.ParseException;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import esprit.tn.MyApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static esprit.tn.MyApplication.authenticated;

public class ConsulterEnfant extends Form {
    public Resources theme = MyApplication.theme;
    String enl="";
    public ConsulterEnfant(Form prev){
        Form th=this;
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, theme.getImage("enfants.png"), spacer1, "  ", "", " ");
        addTab(swipe, theme.getImage("home2.jpg"), spacer2, " ", "", "");

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
       add(swipe);


        setLayout(BoxLayout.y());


        int mm = Display.getInstance().convertToPixels(3);
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 3, 0), false);

        Image icon2 = URLImage.createToStorage(placeholder, "icon2", "http://i.f1g.fr/media/madame/300x300_crop/sites/default/files/img/2020/03/confinement-enfants-activites.jpg");
        Image icon3 = URLImage.createToStorage(placeholder, "icon3", "http://i.f1g.fr/media/madame/300x300_crop/sites/default/files/img/2020/03/confinement-enfants-activites.jpg");

        //add(detail);
        ArrayList<Enfant>enfa=EnfantService.getInstance().ListEnfants(String.valueOf(authenticated.getId()));
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        for(Enfant enf:enfa){

            data.add(createListEntry(enf.getNom()+" "+enf.getPrenom(),enf.getDatenaiss(),enf.getId(),icon3));

        }

        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
        MultiList ml = new MultiList(model);

        ml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Map<String, Object> t = (HashMap) ml.getSelectedItem();
                enl=(String)t.get("id");

                if(Dialog.show("Modifier cet Enfant?","Veuillez selectionez un choix","Oui","Non")){
                try {
                    new ModifierEnfant(new GestionEnfant(new AccueilParent()),enl).show();
                } catch (ParseException e) {
                    e.printStackTrace();
                }}


            }
        });

        add(ml);


    }
    private Map<String, Object> createListEntry(String nom, String date,int idi,Image icon1 ) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", nom);
        entry.put("Line2", date);
        entry.put("id",String.valueOf(idi));
        entry.put("icon",icon1);
        // idenf=String.valueOf(idi);

        return entry;
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
                                        FlowLayout.encloseIn(likes, comments),
                                        spacer
                                )
                        )
                );

        swipe.addTab("", page1);
    }

}
