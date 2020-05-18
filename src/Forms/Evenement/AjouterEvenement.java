package Forms.Evenement;

import Entities.*;
import Forms.Accueils.AccueilResponsable;

import Services.CategorieService;
import Services.EvenementService;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import esprit.tn.MyApplication;

import java.text.Normalizer;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class AjouterEvenement extends Form {

    public Resources theme = MyApplication.theme;

    public AjouterEvenement(Form prev) {

        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, theme.getImage("event-1.jpg"), spacer1, "  ", "", " ");
        addTab(swipe, theme.getImage("event-1.jpg"), spacer2, " ", "", "");

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        add(swipe);



        ArrayList<Categorie> le = CategorieService.getInstance().getAllcategories();
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> prev.showBack());
        setTitle("Ajouter Evenement");
        setLayout(BoxLayout.y());
        TextField titre = new TextField("", "Titre");
        TextField description = new TextField("", "Description");
        ComboBox<Categorie> c = new ComboBox<>();
        for (Categorie ca: le){
          c.addItem(ca);
        }
        Picker datePicker = new Picker();
        Date lt = Date.from(Instant.now());
        //ImageViewer image = new ImageViewer();
        Button aj = new Button("Ajouter");
        aj.setUIID("Confirmbtn");


        aj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if((titre.getText().length()==0)||(description.getText().length()==0)){
                    Dialog.show("Erreur","Veuillez indiquez les champs!!!",new Command("OK"));
                }

                else{
                    if(titre.getText().matches("[a-zA-Z]*")){
                        if(lt.compareTo(datePicker.getDate())<0){
                            Dialog.show("Erreur","Date non valide!",new Command("OK"));
                        }
                        else{
                            String text = datePicker.getDate().toString();

                            Evenement e=new Evenement(8,titre.getText(),text ,description.getText(),c.getSelectedItem());

                            if(EvenementService.getInstance().AjouterEvenement(e)){
                                Dialog.show("Succes","Ajout réussi!",new Command("OK"));
                                new consulterListeEvent(AccueilResponsable.fo).show();

                            }}}

                    else{
                        Dialog.show("Erreur","Veuillez indiquez un nom ou prenom valide",new Command("OK"));
                    }
                }

            }

        });
        addAll(titre,description,c,datePicker,aj);




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
