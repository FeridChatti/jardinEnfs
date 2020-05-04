package Forms.Enfants;

import Services.EnfantService;
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

            Label lbName = new Label(EnfantService.getInstance().ListEnfants("4").get(i).getNom()+" "+EnfantService.getInstance().ListEnfants("4").get(i).getPrenom());
           // Label lbprenom = new Label(EnfantService.getInstance().ListEnfants("4").get(i).getPrenom());
            Label date = new Label(EnfantService.getInstance().ListEnfants("4").get(i).getDatenaiss());
            Label ids=new Label(String.valueOf(EnfantService.getInstance().ListEnfants("4").get(i).getId()));
            ids.setHidden(true);
         //   lbName.setLeadComponent(lbName);

            lbName.addPointerPressedListener(e->{
                Dialog.show("Modifier cet Enfant?","Vous avez choisi :"+ids.getText(),"Oui","Non");
                new ModifierEnfant(th,ids.getText()).show();
            });

            detail.add(lbName);
           // detail.add(lbprenom);
            detail.add(date);
            detail.add(ids);
        }

    }


}
