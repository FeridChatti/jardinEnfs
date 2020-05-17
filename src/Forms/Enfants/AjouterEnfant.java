package Forms.Enfants;

import Entities.Enfant;
import Forms.Accueils.AccueilParent;
import Services.EnfantService;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import esprit.tn.MyApplication;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class AjouterEnfant extends Form {
    public Resources theme = MyApplication.theme;
    public AjouterEnfant(Form prev){
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, theme.getImage("ajouterenfant.jpg"), spacer1, "  ", "", " ");
        addTab(swipe, theme.getImage("home2.jpg"), spacer2, " ", "", "");

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        add(swipe);
        getToolbar().hideToolbar();

        setLayout(BoxLayout.y());
        TextField nom= new TextField("","Nom");
        TextField prenom= new TextField("","Prenom");
        ComboBox sexe= new ComboBox();
        sexe.addItem("Homme");
        sexe.addItem("Femme");
        String se=sexe.getSelectedItem().toString();
        Picker datePicker = new Picker();
        Date lt = Date.from(Instant.now());
        Button aj=new Button("Ajouter");
        //aj.setUIID("ButtonAbonnement");
        aj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if((nom.getText().length()==0)||(prenom.getText().length()==0)){
                    Dialog.show("Erreur","Veuillez indiquer les champs",new Command("OK"));
                }

                else{
                    if(nom.getText().matches("[a-zA-Z]*")&& prenom.getText().matches("[a-zA-Z]*")){
                        if(lt.compareTo(datePicker.getDate())<0){
                            Dialog.show("Erreur","Date non valide",new Command("OK"));
                        }
                        else{
                String text = datePicker.getValue().toString();

                Enfant e=new Enfant(50,nom.getText(),prenom.getText(),se,text);

                if(EnfantService.getInstance().AjouterEnfant(e)){
                    Dialog.show("Succes","Ajout réussi",new Command("OK"));
                    new ConsulterEnfant(new AccueilParent()).show();
                }}}

                else{
                        Dialog.show("Erreur","Veuillez indiquez un nom ou prenom valide",new Command("OK"));
                    }
                }

            }
        });
        addAll(nom,prenom,sexe,datePicker,aj);




    }
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);




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


    public AjouterEnfant() {

    }
}
