package Forms;

import Entities.Activite;
import Services.ActiviteService;
import Services.ClubService;
import com.codename1.components.ToastBar;
import com.codename1.l10n.DateFormat;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import esprit.tn.MyApplication;

import java.util.ArrayList;
import java.util.Date;

public class ConsulterActivite extends Form {




    public ConsulterActivite(Form prev){
        setTitle("La Liste des Activités");
        Form fo = this;

        Label choix  = new Label("veuillez choisir l'activité");


        Container detail = new Container(BoxLayout.y());
        add(detail);
        detail.add(choix);

        ArrayList<Activite> l = ActiviteService.getInstance().getAllActivites();
        for(int i = 0; i< l.size(); i++){


            String id = String.valueOf(l.get(i).getId());
            Label lbID = new Label(id);
            lbID.isHidden(true);
            Label lbnom = new Label("Nom :");
            Label lbName = new Label(l.get(i).getTypeact());
            //Label lbDescription = new Label(ActiviteService.getInstance().getAllActivites().get(i).getDetailles());

            Label lbdate = new Label("Date :");
            Label date = new Label(l.get(i).getDate());

            lbName.addPointerReleasedListener(e->{
              if(  Dialog.show("Participer à cette activité","Vous avez choisi :"+lbName.getText(),"Oui","Non")){
                   new ParticiperActivite(fo,id).show();
              }
                });

           detail.add(lbnom);

            detail.add(lbName);
            //detail.add(lbDescription);
            detail.add(lbdate);
            detail.add(date);

           // detail.setLeadComponent(lbName);



        }





        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());



    }
}
