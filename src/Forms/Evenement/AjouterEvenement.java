package Forms.Evenement;

import Entities.*;
import Forms.Accueils.AccueilResponsable;

import Services.CategorieService;
import Services.EvenementService;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;

import java.text.Normalizer;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class AjouterEvenement extends Form {


    public AjouterEvenement(Form prev) {

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

        aj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if((titre.getText().length()==0)||(description.getText().length()==0)){
                    Dialog.show("Erreur","Veuillez indiquez les champs",new Command("OK"));
                }

                else{
                    if(titre.getText().matches("[a-zA-Z]*")&& description.getText().matches("[a-zA-Z]*")){
                        if(lt.compareTo(datePicker.getDate())<0){
                            Dialog.show("Erreur","Date non valide",new Command("OK"));
                        }
                        else{
                            String text = datePicker.getValue().toString();

                            Evenement e=new Evenement(8,titre.getText(),text,description.getText(),c.getSelectedItem());

                            if(EvenementService.getInstance().AjouterEvenement(e)){
                                Dialog.show("Succes","Ajout réussi",new Command("OK"));
                            }}}

                    else{
                        Dialog.show("Erreur","Veuillez indiquez un nom ou prenom valide",new Command("OK"));
                    }
                }

            }

        });
        addAll(titre,description,c,datePicker,aj);




    }
}
