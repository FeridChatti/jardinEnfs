package Forms.Evenement;


import Entities.Evenement;
import Forms.Accueils.AccueilResponsable;
import Services.EvenementService;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;


public class DetailsEvenement extends Form {
    Form current;

    public DetailsEvenement(Form prev,int e ) {
        Evenement ev = EvenementService.getInstance().getEvent(e);
        setLayout(BoxLayout.y());
        current=this;

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, b -> prev.showBack());

        Label t = new Label("Titre");
        Label tt = new Label(ev.getTitre());
        Label d = new Label("Description");
        Label td = new Label(ev.getDescription());

        Label da = new Label("Date");
        Label daa = new Label(ev.getDate());




        addAll(t, tt, d, td, da, daa);

        Button part = new Button("Participer");
        add(part);


        part.addActionListener(new ActionListener() {
                                   @Override
                                   public void actionPerformed(ActionEvent evt) {


                                           new Participer(current,e).show();


                                       }


                               }

        );


    }}




