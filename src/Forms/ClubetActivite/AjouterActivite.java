package Forms.ClubetActivite;

import Entities.Categorie;
import Entities.Club;
import Entities.PartActivite;
import Services.ActiviteService;
import Services.CategorieService;
import Services.ClubService;
import Services.EnfantService;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class AjouterActivite extends Form {



    String ide;

    public AjouterActivite(Form prev) {


        Form fo = this;
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> prev.showBack());


        setTitle("Ajouter une Activité");
        setLayout(BoxLayout.y());

        TextField Tnom = new TextField();
        Tnom.setHint("le nom de l'activité");
        add(Tnom);

        TextField Tdet = new TextField();
        Tdet.setHint("Description");
        add(Tdet);

        Picker datePicker = new Picker();
        Date lt = Date.from(Instant.now());
        add(datePicker);

        ArrayList<Club> le = ClubService.getInstance().getAllclubs();

        ComboBox<String> c = new ComboBox<>();
        for(int i = 0; i< le.size(); i++) {

            c.addItem(le.get(i).getName());
            ide = String.valueOf(le.get(i).getId());
        }


        add(c);

        Button ajouter=new Button("Ajouter");
        add(ajouter);
        ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {


                String text = datePicker.getValue().toString();
                if ((Tnom.getText().length() == 0) || (Tdet.getText().length() == 0)) {
                    Dialog.show("Erreur", "entrer nom/description ", new Command("OK"));
                }
                else {
                    if (Tnom.getText().matches("[a-zA-Z\\s]*") && Tdet.getText().matches("[a-zA-Z\\s]*")) {
                        if (lt.compareTo(datePicker.getDate()) >= 0) {
                            Dialog.show("Erreur", "Date non valide", new Command("OK"));
                        }
                        else {
                         
                            if (ActiviteService.getInstance().AjouterAct(Integer.parseInt(ide), Tnom.getText(), Tdet.getText(), text)) {
                                Dialog.show("Succes", "Activité ajouté", new Command("OK"));
                                //new ConsulterActivite(current).show();
                            }
                            else {
                                Dialog.show("Erreur", "erreuuur", new Command("OK"));
                            }
                        }
                    }
                    }


                }

            });






}
    }