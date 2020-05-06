package Forms.Abonnements;

import Entities.Abonnement;
import Entities.Jardin;
import Forms.Accueils.AccueilParent;
import Services.AbonnementService;
import Services.EnfantService;
import com.codename1.charts.compat.Paint;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

import java.util.ArrayList;

public class ModifierAbonnement extends Form {

    public ModifierAbonnement(Form fo,String id,String name,String typ,String idj){

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->fo.showBack());
        setTitle("Modifier Abonnement");
        setLayout(BoxLayout.y());
        TextArea na=new TextArea(name);
        na.setAlignment(TextArea.CENTER);
        na.setEnabled(false);

        Label tp=new Label("Type:");
        ComboBox types = new ComboBox();
        types.addItem("normal");
        types.addItem("bus");
        types.setSelectedItem(typ);
        Label montn= new Label ("Montant:");
        Label esp=new Label(" ");

        TextField mon=new TextField();
        ArrayList<Jardin> jar= EnfantService.getInstance().Montant(idj);

        if(types.getSelectedItem().toString().equals("normal")){

                mon.setText(String.valueOf(jar.get(0).getTarif()));

        }
        else if (types.getSelectedItem().toString().equals("bus")){

            mon.setText(String.valueOf(jar.get(0).getTarif()+50));
        }



        types.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(types.getSelectedItem().toString().equals("normal")){
                    for (int i=0;i< jar.size();i++){
                        mon.setText(String.valueOf(jar.get(0).getTarif()));
                    }
                }
                else if (types.getSelectedItem().toString().equals("bus")){

                    mon.setText(String.valueOf(jar.get(0).getTarif()+50));
                }
                else{
                    mon.setText(String.valueOf(jar.get(0).getTarif()+50));
                }
            }
        });
         Button mod=new Button("Modifier");
         mod.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                 AbonnementService.getInstance().ModifierAbonnement(id,types.getSelectedItem().toString(),mon.getText());
                 Dialog.show("Succes","modification réussie",new Command("OK"));
                 new ConsulterAbonnement(new AccueilParent()).show();
             }
         });
        Button supp=new Button("Supprimer");
        supp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println(id);
                AbonnementService.getInstance().SupprimerAbonnement(id);
                Dialog.show("Succes","Suppression réussie",new Command("OK"));
                new ConsulterAbonnement(new AccueilParent()).show();
            }
        });


        addAll(na,tp,types,montn,mon,esp,mod,supp);




    }

}
