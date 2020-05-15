package Forms.Evenement;

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

import java.util.ArrayList;

public class Participer extends Form {


    String ide ;
    Label lbTitre ;
    Label lbDescription ;
    Label date ;
    int ida ;
    Label categorie ;

    public Participer(Form prev, String id){
        Form fo = this;
        setTitle("Participer à l'événement");
        setLayout(BoxLayout.y());

        Label lbId = new Label(id);
        lbId.setHidden(true);
        add(lbId);



        ArrayList<Evenement> le = EvenementService.getInstance().getEvenement(lbId.getText());

        for(int i = 0; i< le.size(); i++){

            Label lbn = new Label("Nom de l'événement:");
            lbTitre = new Label(le.get(i).getTitre());
            Label lbd = new Label("Description :");
            lbDescription = new Label( le.get(i).getDescription());
            Label lbde = new Label("Date :");
            date = new Label(le.get(i).getDate());

            Label lbc = new Label("Catégorie :");
            categorie = new Label(le.get(i).getCategorie().getLibelle());
            addAll(lbn,lbTitre);
            addAll(lbd,lbDescription);
            addAll(lbde,date);
            addAll(lbc,categorie);
        }

        Label lbe = new Label("L'enfant qui va participer à l'événement:");
        for(int i = 0; i< EnfantService.getInstance().ListEnfants("1").size(); i++) {

            ComboBox<String> lbenfant = new ComboBox<String>(EnfantService.getInstance().ListEnfants("1").get(i).getNom());

            addAll(lbe,lbenfant);
            ide = String.valueOf(EnfantService.getInstance().ListEnfants("1").get(i).getId());

            lbenfant.addActionListener(e -> ToastBar.showMessage("vous avez choisi " + lbenfant.getSelectedItem(), FontImage.MATERIAL_INFO));

        }

        Button participer=new Button("Participer");
        participer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                ArrayList<Entities.Participer> l = EvenementService.getInstance().verification(ide,id);

                if(l.isEmpty()){

                    ida=Integer.parseInt(lbId.getText()) ;
                    int idep = Integer.parseInt(ide);


                    if (EvenementService.getInstance().AddParticiper(ida,idep)) {
                        Dialog.show("Succes", "Ajout réussi!", new Command("OK"));
                       // new ConsulterEvenement(fo).show();
                    } else {
                        Dialog.show("Erreur", "Erreur!", new Command("OK"));
                    }
                }else{
                    Dialog.show("Erreur", "L'enfant existe déjà dans la liste!", new Command("OK"));
                }
            }

        });


        add(participer);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());

    }





}

