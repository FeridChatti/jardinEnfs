package Forms.Remarques;

import Entities.Enfant;
import Services.EnfantService;
import Services.RemarqueService;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import static esprit.tn.MyApplication.authenticated;

public class AjouterRemarques extends Form {

    public AjouterRemarques(Form prev){



        Form form=new Form();

        form.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());
        form.setTitle("Ajouter Remarques");
        form.setLayout(new BorderLayout());
        Container cont=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        TextArea descr= new TextArea();
        descr.setHint("Votre remarque");
        descr.setRows(2);

        ComboBox<Enfant> enfant = new ComboBox<Enfant>();


        ArrayList<Enfant> enflist=RemarqueService.getInstance().ListEnfants(authenticated.getId());
        for (int i = 0; i< enflist.size(); i++){
            enfant.addItem(enflist.get(i));
        }










        Button aj=new Button("Ajouter");
        aj.setUIID("Confirmbtn");
        aj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if((descr.getText().length()==0)){
                    Dialog.show("Erreur","Veuillez indiquez la remarque",new Command("OK"));
                }

                else{



                            Enfant e=enfant.getSelectedItem();

                            if(RemarqueService.getInstance().ajouterremarques(authenticated.getId(),e.getIdabo(),descr.getText(),String.valueOf(e.getId()))){
                                Dialog.show("Succes","Ajout réussi",new Command("OK"));
                                prev.showBack();
                            }}}




            }
        );



form.getContentPane().setUIID("BackgroundForm");

        cont.add(descr);
         cont.add(enfant);
          cont.add(aj);
        form.add(BorderLayout.CENTER,cont);

        form.show();

    }


}
