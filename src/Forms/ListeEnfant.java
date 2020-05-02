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

public class ListeEnfant extends Form {

    public ListeEnfant (Form prev){

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





        Button aj=new Button("Ajouter");
        aj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String text = datePicker.getValue().toString();

                Enfant e=new Enfant(50,nom.getText(),prenom.getText(),se,text);

                if(EnfantService.getInstance().AjouterEnfant(e)){
                    Dialog.show("succes",text,new Command("OK"));
                }

            }
        });
        addAll(nom,prenom,sexe,datePicker,aj);




    }


}
