package Forms.Sami;

import Entities.Emplacement;
import Entities.Trajet;
import Services.MapService;
import Services.TrajetService;
import Services.UserService;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.maps.Coord;
import com.codename1.maps.MapComponent;
import com.codename1.maps.layers.LinesLayer;
import com.codename1.maps.layers.PointLayer;
import com.codename1.maps.layers.PointsLayer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import esprit.tn.MyApplication;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * This is a simple demo that demonstrates how to use the MapComponent and how
 * to show POI on a map using google location API's Make sure to get a key from
 * https://developers.google.com/maps/documentation/places/ to run the 'Find
 * Resturants' sub demo
 *
 * IMPORTANT - This demo uses the simple tiling map component to display a Map.
 * In order to display a Native Map on the device please refer to this:
 * http://www.codenameone.com/blog/mapping-natively.html
 *
 *
 * @author Chen
 */
public class MapsDemo {

    private Coord lastLocation;

    public void init(Object context) {
        Resources theme = UIManager.initFirstTheme("/theme");
        //Enable Toolbar to all Forms by default
        Toolbar.setGlobalToolbar(true);
        // Pro only feature, uncomment if you have a pro subscription
        //Log.bindCrashProtection(true);
    }

    public MapsDemo(List<Trajet> trajets,Form fo) {
                showMeOnMap(trajets,fo);


    }

    private void showMeOnMap(List<Trajet> trajets,Form fo) {
        Form map = new Form("Map Trajets");
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
     ArrayList<Trajet> tr= TrajetService.getInstance().ListeTrajets(UserService.getInstance().getJardin(MyApplication.authenticated.getId()+"").getId()+"");

       ArrayList<Emplacement> emplacements=new ArrayList<>();
       for(Trajet t:tr)
           emplacements.add(MapService.getInstance().getEmplacements(t));

            for (Emplacement em : emplacements) {
               try{
                   String description="Adresse : "+em.getTrajet().getAdresse()+"\nHeure départ : "+em.getTrajet().getHeure()+"\nChauffeur affecté : "+em.getTrajet().getChauffeur().getNom();
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

    public void stop() {
        System.out.println("stopped");
    }

    public void destroy() {
        System.out.println("destroyed");

    }



}