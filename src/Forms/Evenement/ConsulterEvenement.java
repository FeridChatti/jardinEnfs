package Forms.Evenement;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;


public class ConsulterEvenement extends Form {

public void DetailsEvent(Form prev, String titre, String description, String date, String categorie )
{
    setLayout(BoxLayout.y());
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());

    Label t= new Label("Titre");
    Label tt=new Label(titre);
    Label d= new Label("Description");
    Label td=new Label(description);

    Label da= new Label("Date");
    Label daa=new Label(date);

    Label c= new Label("Catégprie");
    Label cc=new Label(categorie);

    addAll(t,tt,d,td,da,daa,c,cc);

    Button mod=new Button("Modifier");
    Button supp=new Button("Supprimer");






}





}