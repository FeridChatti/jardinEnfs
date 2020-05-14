package Forms.raed;

import Entities.Enfant;
import Entities.Jardin;
import Entities.Paiement;
import Entities.User;
import Forms.Accueils.AccueilParent;
import Forms.Accueils.AccueilResponsable;
import Forms.Enfants.ConsulterEnfant;
import Forms.Enfants.ModifierEnfant;
import Services.EnfantService;
import Services.JardinService;
import Services.UserService;
import com.codename1.l10n.ParseException;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.spinner.Picker;
import esprit.tn.MyApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class AfficheJardinRespo  extends Form {
    String enl="";
    int count=0;

    public AfficheJardinRespo  (Form prev ,int idenf) throws ParseException {
        /*Form ts=this;

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());
        setTitle("Affiche Jardin Responsable");
        setLayout(BoxLayout.y());

        int mm = Display.getInstance().convertToPixels(3);
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 3, 0), false);
        Image icon1 = URLImage.createToStorage(placeholder, "icon1", "http://www.georgerrmartin.com/wp-content/uploads/2013/03/GOTMTI2.jpg");

        Image icon2 = URLImage.createToStorage(placeholder, "icon2", "http://www.vippng.com/png/detail/35-352335_baby-boy-icon-png-icone-enfant-png.png");

Jardin j= UserService.getInstance().getJardin(MyApplication.authenticated.getId()+"");

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

        Label tari = new Label("Tarif");
        TextField rse = new TextField(String.valueOf(j.getTarif()));
        rse.setEnabled(false);

        Button md=new Button("Modifier");




        md.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                md.setText("valider");
                t.setEnabled(true);
                re.setEnabled(true);
                ree.setEnabled(true);
                reee.setEnabled(true);
                rse.setEnabled(true);
                count=count+1;
                if (count==1){
                    md.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {

                            Dialog.show("Confirmation","ete vous sur de modifier votre jardin ? ","Oui","Non");


                            if((reee.getText().length()==0)||(t.getText().length()==0)||(re.getText().length()==0)||(ree.getText().length()==0)){
                                Dialog.show("Erreur","Veuillez indiquez les champs",new Command("OK"));
                            }

                            else {
                                if (t.getText().matches("[a-zA-Z]*") && re.getText().matches("[a-zA-Z]*") && ree.getText().matches("[a-zA-Z]*")) {
                                    if (reee.getText().matches("[a-zA-Z]*")) {
                                        Dialog.show("Erreur", "Num tel non valide", new Command("OK"));
                                    }
                                    if(rse.getText().matches("[a-zA-Z]*"))
                                    {
                                        Dialog.show("Erreur", "Tarif non valide", new Command("OK"));
                                    }

                                    else{

                                        Jardin je = new Jardin(   t.getText(), ree.getText(),
                                                reee.getText(), Float.parseFloat(rse.getText()), re.getText());



                                        boolean j = JardinService.getInstance().ModifierJardin(je);
                                        if (j) {
                                            Dialog.show("Succés", "jardin modifié avec succés", "Oui", null);
                                            try {
                                                new AfficheJardinRespo(prev,idenf).show();
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }
                                        } else {
                                            Dialog.show("Erreur", "Erreur", "Oui", null);
                                        }


                                    }


                                }}}
                    });

                }

            }
        });


        add(nom);
        add(t);
        addAll(add,re,desc,ree,nute,reee,tari,rse,md);
        ts.show();

*/


    }





}



