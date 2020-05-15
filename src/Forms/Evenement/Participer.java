package Forms.Evenement;

import Entities.Enfant;
import Entities.Evenement;
import Forms.ClubetActivite.ConsulterActivite;
import Services.ActiviteService;
import Services.EnfantService;
import Services.EvenementService;
import com.codename1.components.ToastBar;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import esprit.tn.MyApplication;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Participer extends Form {



    public Participer(Form prev, int id){
        Form fo = this;
        setTitle("Participer à l'événement");
        setLayout(BoxLayout.y());

        Label lbId = new Label(id+"");
        lbId.setHidden(true);
        Evenement ev=EvenementService.getInstance().getEvent(id);
        Label lbNom=new Label(ev.getTitre());
        Label lbDesc=new Label(ev.getDescription());
        Label lbDate=new Label(ev.getDate());
        ArrayList<Enfant> af=EnfantService.getInstance().ListEnfants(MyApplication.authenticated.getId()+"");
        ComboBox<Enfant> enfants =new ComboBox<>();
        for(Enfant f:af)
            enfants.addItem(f);
        Button part=new Button("Participer");
        part.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
if (EvenementService.getInstance().AddParticiper(lbId.getText(),enfants.getSelectedItem().getId()))
{
    Dialog.show("Succés",enfants.getSelectedItem().getNom()+" participe à cet événement","ok",null);
}

            }
        });

        addAll(lbId,lbNom,lbDesc,lbDate,enfants,part);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());

    }





}

