package Forms.Sami;

import Entities.Emplacement;
import Entities.Trajet;
import Services.MapService;
import Services.TrajetService;
import com.codename1.maps.Coord;
import com.codename1.maps.MapComponent;
import com.codename1.maps.layers.PointLayer;
import com.codename1.maps.layers.PointsLayer;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import esprit.tn.MyApplication;

import java.io.IOException;
import java.util.ArrayList;

public class MapParent extends Form {
    private Coord lastLocation;

    public MapParent(Form fo)
    {  showMeOnMap(fo);


    }
    private void showMeOnMap(Form fo) {
        Form map = new Form("Les Trajets Disponibles");
        map.setLayout(new BorderLayout());
        map.setScrollable(false);
        final MapComponent mc = new MapComponent();
        map.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> fo.showBack());
        putTrajetsOnMap(mc);
        mc.zoomToLayers();
        map.addComponent(BorderLayout.CENTER, mc);
        map.show();
    }

    private void putTrajetsOnMap(MapComponent map) {
        ArrayList<Trajet> tr= TrajetService.getInstance().ListeTrajetsParent(MyApplication.authenticated.getId());

        ArrayList<Emplacement> emplacements=new ArrayList<>();
        for(Trajet t:tr)
            emplacements.add(MapService.getInstance().getEmplacements(t));

        for (Emplacement em : emplacements) {
            try{
                String description="Adresse : "+em.getTrajet().getAdresse()+"\n\nHeure départ : "+em.getTrajet().getHeure()+"\n";
                lastLocation = new Coord(em.getLatitude(), em.getLongitude());
                Image i = Image.createImage("/pin.png");

                PointsLayer pointsLayer = new PointsLayer();
                PointLayer pL = new PointLayer(lastLocation, description, i);

                pL.setDisplayName(false);
                pointsLayer.addPoint(pL);
                pointsLayer.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent evt) {
                        PointLayer p = (PointLayer) evt.getSource();
                        Dialog.show("Details", p.getName(), "Ok", null);
                    }
                });

                map.addLayer(pointsLayer);
            } catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }

}
