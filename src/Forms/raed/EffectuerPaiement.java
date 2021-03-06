package Forms.raed;

import Entities.Jardin;
import Entities.Paiement;
import Services.PaimentService;
import Services.UserService;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormatPatterns;
import com.codename1.l10n.ParseException;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import esprit.tn.MyApplication;

import java.text.Normalizer;
import java.time.Instant;
import java.util.Date;

public class EffectuerPaiement extends Form {
    private ConnectionRequest req;
    public boolean resultOk;



    public  EffectuerPaiement(Form prev)
    {


        Form th = this;

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> prev.showBack());
        setTitle("Affiche Paiement");
        setLayout(BoxLayout.y());

        int mm = Display.getInstance().convertToPixels(3);
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 3, 0), false);
        Image icon1 = URLImage.createToStorage(placeholder, "icon1", "http://www.georgerrmartin.com/wp-content/uploads/2013/03/GOTMTI2.jpg");

        Image icon2 = URLImage.createToStorage(placeholder, "icon2", "http://www.vippng.com/png/detail/35-352335_baby-boy-icon-png-icone-enfant-png.png");
        Jardin j= UserService.getInstance().getJardin(MyApplication.authenticated.getId()+"");
        int id1 = j.getId();
        setLayout(BoxLayout.y());
        Label nom = new Label("Nom:");
        TextField t = new TextField(j.getName());
        t.setEnabled(false);
        Label add = new Label("Adresse");
        TextField re = new TextField(j.getAdresse());
        re.setEnabled(false);
        Label desc = new Label("Description");
        TextField ree = new TextField(j.getDescription());
        ree.setEnabled(false);
        Label nute = new Label("Numero Telephone ");
        TextField reee = new TextField(j.getNumtel());
        reee.setEnabled(false);
        Label Mont=new Label("montant a payer");
       float m= (float) 250.0;
        TextField rr= new TextField();
        rr.setText(String.valueOf(m));
        rr.setEnabled(false);
        TextField id= new TextField(j.getId());
        id.setVisible(false);
        id.setEnabled(false);
        Label num=new Label("Num carte");
        TextField nume= new TextField();
        Label cod=new Label("Code VV2");
        TextField code= new TextField();
        code.setHint("Password");
        code.setConstraint(TextField.PASSWORD);
        Label da=new Label("Date expiration");
        Picker datePicke= new Picker();
        Button md=new Button("Effectuer");
        md.setUIID("Confirmbtn");


        md.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Dialog.show("Confirmation", "est ce que tous les informations sont juste ", "Oui", "Non");
                String t = String.valueOf(code.getText());
                String tt = String.valueOf(nume.getText());
                if (tt.length() != 14) {
                    Dialog.show("Erreur", "Veuillez verifier votre numero de carte ", new Command("OK"));

                } else if (t.length() != 4) {
                    Dialog.show("Erreur", "Veuillez verifier votre password ", new Command("OK"));

                }
                else {

                    if (PaimentService.getInstance().effectuerpaim(String.valueOf(id1))) {

                        Dialog.show("Succés", "paiement effectuer avec succés", "Oui", null);

                    } else {
                        Dialog.show("Erreur", "Erreur", "Oui", null);
                    }

                }
            }


        });










        add(nom);
        add(t);
        addAll(add,re,desc,ree,nute,reee,Mont,rr,num,nume,cod,code,da,datePicke,md);
        th.show();



    }
}
