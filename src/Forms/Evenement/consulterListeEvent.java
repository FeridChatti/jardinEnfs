package Forms.Evenement;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

public class consulterListeEvent extends Form {

    public void ConsulterEvents(Form prev) {

        Form hi = new Form("Liste des trajets", BoxLayout.y());
        hi.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> prev.showBack());

        Button aj = new Button("Ajouter un trajet");
        aj.addActionListener(e -> new AjouterEvenement(hi).show());

        hi.addAll(aj);



    }
}
