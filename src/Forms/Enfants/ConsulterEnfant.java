package Forms.Enfants;

import Entities.Enfant;
import Services.EnfantService;
import com.codename1.l10n.ParseException;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

public class ConsulterEnfant extends Form {
    public ConsulterEnfant(Form prev){
        Form th=this;
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());
        setTitle("Consulter enfants");
        Container detail = new Container(BoxLayout.y());
        add(detail);


        for(int i = 0; i< EnfantService.getInstance().ListEnfants("4").size(); i++){
           Enfant enf=EnfantService.getInstance().ListEnfants("4").get(i);
            Label lbName = new Label(enf.getNom()+" "+enf.getPrenom());
           // Label lbprenom = new Label(EnfantService.getInstance().ListEnfants("4").get(i).getPrenom());
            Label date = new Label(enf.getDatenaiss());
            Label ids=new Label(String.valueOf(enf.getId()));
            ids.setHidden(true);
         //   lbName.setLeadComponent(lbName);

            lbName.addPointerPressedListener(e->{
                Dialog.show("Modifier cet Enfant?","Vous avez choisi :"+ids.getText(),"Oui","Non");
                try {
                    new ModifierEnfant(th,ids.getText()).show();
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            });

            detail.add(lbName);
           // detail.add(lbprenom);
            detail.add(date);
            detail.add(ids);
        }

    }


}
