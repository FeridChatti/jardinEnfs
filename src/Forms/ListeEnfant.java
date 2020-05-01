package Forms;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;

public class ListeEnfant extends Form {
    Form form;
    public ListeEnfant (){
        form=this;
        setTitle("Ajouter Enfant");
        setLayout(BoxLayout.y());
        TextField nom= new TextField("","Nom");
        TextField prenom= new TextField("","Prenom");
        TextField sexe= new TextField("","Sexe");
        TextField date= new TextField("","Date");
       // Picker datePicker = new Picker();
        Button aj=new Button("Ajouter");
        aj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

            }
        });
        addAll(nom,prenom,sexe,date,aj);




    }


}
