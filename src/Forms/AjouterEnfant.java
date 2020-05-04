package Forms;

import Entities.Enfant;
import Services.EnfantService;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import esprit.tn.MyApplication;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class AjouterEnfant extends Form {

    public AjouterEnfant(Form prev){

        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK,e->prev.showBack());
        setTitle("Ajouter Enfant");
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
        aj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if((nom.getText().length()==0)||(prenom.getText().length()==0)){
                    Dialog.show("Erreur","Veuillez indiquez les champs",new Command("OK"));
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
                }}}

                else{
                        Dialog.show("Erreur","Veuillez indiquez un nom ou prenom valide",new Command("OK"));
                    }
                }

            }
        });
        addAll(nom,prenom,sexe,datePicker,aj);




    }



    public AjouterEnfant() {

    }
}
