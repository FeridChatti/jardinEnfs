package Forms.Parent;

import Entities.Enfant;
import Services.ReclamService;
import Services.RemarqueService;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

import static esprit.tn.MyApplication.authenticated;

public class SendReclam extends Form {

    public SendReclam(Form prev){
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());
        setTitle("Envoyer une reclamation");
        Form form = this;
        form.getContentPane().setUIID("BackgroundForm");

        setLayout(BoxLayout.y());

        Label titlbl=new Label("Titre");
        TextField titre=new TextField();
        Label desclbl=new Label("Explication");
        TextArea descr=new TextArea();
        Button aj=new Button("Envoyer");

        form.addAll(titlbl,titre,desclbl,descr,aj);

        aj.addActionListener(e->{
            if((descr.getText().length()==0)||(titre.getText().length()==0)){
                Dialog.show("Erreur","Veuillez remplir tout les champs",new Command("OK"));
            }

            else{





                if(ReclamService.getInstance().addReclam(titre.getText(),descr.getText())){
                    Dialog.show("Succes","Ajout réussi",new Command("OK"));
                    prev.showBack();
                }}





        });

        form.show();
    }
}
