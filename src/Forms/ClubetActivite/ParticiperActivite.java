package Forms.ClubetActivite;

import Entities.Activite;
import Entities.PartActivite;
import Forms.ClubetActivite.ConsulterActivite;
import Services.ActiviteService;
import Services.EnfantService;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ToastBar;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

import java.util.ArrayList;

public class ParticiperActivite extends Form {

    String ide ;
    Label lbName ;
    Label lbDescription ;
    Label date ;
    Label club ;
    int ida ;

    public ParticiperActivite(Form prev, String id){
        Form fo = this;
        setTitle("Participation");
        setLayout(BoxLayout.y());


        Label lbId = new Label(id);
        lbId.setHidden(true);
        add(lbId);



        ArrayList<Activite> l = ActiviteService.getInstance().getActivite(lbId.getText());

        for(int i = 0; i< l.size(); i++){

            Label lbn = new Label("Nom de l'activité :");

            lbn.getAllStyles().setFgColor(ColorUtil.MAGENTA);
             lbName = new Label(l.get(i).getTypeact());
             Label lbd = new Label("Détail :");

            lbd.getAllStyles().setFgColor(ColorUtil.MAGENTA);
             lbDescription = new Label( l.get(i).getDetailles());
             Label lbde = new Label("Date :");

            lbde.getAllStyles().setFgColor(ColorUtil.MAGENTA);
             date = new Label(l.get(i).getDate());

             Label lbc = new Label("Club de l'activité :");

            lbc.getAllStyles().setFgColor(ColorUtil.MAGENTA);
             club = new Label(l.get(i).getClub().getName());
            addAll(lbn,lbName);
            addAll(lbd,lbDescription);
            addAll(lbde,date);
            addAll(lbc,club);
        }

        Label lbe = new Label("L'enfant qui va participer :");

        lbe.getAllStyles().setFgColor(ColorUtil.MAGENTA);
        for(int i = 0; i< EnfantService.getInstance().ListEnfants("1").size(); i++) {

            ComboBox<String> lbenfant = new ComboBox<String>(EnfantService.getInstance().ListEnfants("1").get(i).getNom());

            addAll(lbe,lbenfant);
            ide = String.valueOf(EnfantService.getInstance().ListEnfants("1").get(i).getId());

            lbenfant.addActionListener(e -> ToastBar.showMessage("You picked " + lbenfant.getSelectedItem(), FontImage.MATERIAL_INFO));

        }

        Button participer=new Button("Participer");
        participer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

               ArrayList<PartActivite> v = ActiviteService.getInstance().verification(ide,id);

                if(v.isEmpty()){

                ida=Integer.parseInt(lbId.getText()) ;
                int idep = Integer.parseInt(ide);

                String datea = date.getText();

                if (ActiviteService.getInstance().AjouterParticiper(ida,idep,datea)) {
                    Dialog.show("Succes", "ajouté à la liste des participants", new Command("OK"));
                    new ConsulterActivite(fo).show();
                } else {
                    Dialog.show("Erreur", "erreuuur", new Command("OK"));
                }
            }else{
                    Dialog.show("existant", "existe déjà dans la liste", new Command("OK"));
                }
            }

        });


add(participer);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());

    }


    }



