package Forms.Enfants;

import Entities.Enfant;
import Forms.Accueils.AccueilParent;
import Services.EnfantService;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.*;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;

import java.awt.*;
import java.time.Instant;
import java.util.Date;

public class ModifierEnfant extends Form {
    int count=0;
    public ModifierEnfant(Form prev,String id) throws ParseException {

        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());
              Enfant th=EnfantService.getInstance().getEnfants(id);
            Label l = new Label("Nom:");
            TextField t = new TextField(th.getNom());
            t.setEnabled(false);
            Label le = new Label("Prenom:");
            TextField re = new TextField(th.getPrenom());
            re.setEnabled(false);
            Label de = new Label("Date de Naissance:");
            Picker dt = new Picker();
            @Deprecated
            Date lt = new SimpleDateFormat("yyyy-MM-dd").parse(th.getDatenaiss());

            dt.setDate(lt);
           //TextField dt = new TextField(th.getDatenaiss());
           dt.setEnabled(false);
            ComboBox sexe= new ComboBox();
            sexe.addItem("Homme");
            sexe.addItem("Femme");
            sexe.setSelectedItem(th.getSexe());
            sexe.setEnabled(false);
           Button md=new Button("Modifier");
        Button supp=new Button("Supprimer");
        Date dte = Date.from(Instant.now());

        String se=sexe.getSelectedItem().toString();
        String text = dt.getValue().toString();

            md.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    md.setText("valider");
                    t.setEnabled(true);
                    re.setEnabled(true);
                    dt.setEnabled(true);
                    sexe.setEnabled(true);
                    count=count+1;
                    if (count==1){
                    md.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {

                                if(Dialog.show("Confirmation","Modifier cet Enfant?","Oui","Non")){

                                int idenf=Integer.valueOf(id);
                            if((t.getText().length()==0)||(re.getText().length()==0)){
                                Dialog.show("Erreur","Veuillez indiquez les champs",new Command("OK"));
                            }

                              else {
                                if (t.getText().matches("[a-zA-Z]*") && re.getText().matches("[a-zA-Z]*")) {
                                    if (dte.compareTo(dt.getDate()) < 0) {
                                        Dialog.show("Erreur", "Date non valide", new Command("OK"));
                                    }
                                    else{

                                    Enfant e = new Enfant(idenf, t.getText(), re.getText(), se, text);

                                    boolean f = EnfantService.getInstance().ModifierEnfant(e);
                                    if (f) {
                                        Dialog.show("Succés", "Enfant modifié avec succés", new Command("OK"));
                                        new GestionEnfant(new AccueilParent()).show();
                                    } else {
                                        Dialog.show("Erreur", "Erreur", new Command("OK"));
                                    }


                                }


                            }}}



                        }
                    });

                    }

                }
            });

        supp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Dialog.show("Alert","Voulez vous supprimer cet enfant?","Oui","Non");
                EnfantService.getInstance().SupprimerEnfant(id);
                new GestionEnfant(new AccueilParent()).show();
            }
        });



            addAll(l,t,le,re,de,dt,sexe,md,supp);


    }
}
