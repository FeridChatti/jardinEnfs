package Forms;

import Services.ActiviteService;
import Services.ClubService;
import com.codename1.components.ToastBar;
import com.codename1.l10n.DateFormat;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import esprit.tn.MyApplication;

import java.util.Date;

public class ConsulterActivite extends Form {
     Form fo ;



    public ConsulterActivite(Form prev){
        setTitle("La Liste des Activités");
        fo = this;

        Label choix  = new Label("veuillez choisir l'activité");
        add(choix);

        Container detail = new Container(BoxLayout.y());
        add(detail);
        for(int i = 0; i< ActiviteService.getInstance().getAllActivites().size(); i++){


            String id = String.valueOf(ActiviteService.getInstance().getAllActivites().get(i).getId());
            Label lbID = new Label(id);
            lbID.isHidden(true);
            Label lbnom = new Label("Nom :");
            Label lbName = new Label(ActiviteService.getInstance().getAllActivites().get(i).getTypeact());
            //Label lbDescription = new Label(ActiviteService.getInstance().getAllActivites().get(i).getDetailles());

            Label lbdate = new Label("Date :");
            Label date = new Label(ActiviteService.getInstance().getAllActivites().get(i).getDate());

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
