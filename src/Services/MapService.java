package Services;

import Entities.Chauffeur;
import Entities.Emplacement;
import Entities.Trajet;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapService {
    private static MapService instance;
    private ArrayList<Emplacement> emplacements = new ArrayList<Emplacement>();
    private ConnectionRequest req;
    public MapService(){
        req=new ConnectionRequest();
    }

    public static MapService getInstance() {
        if (instance == null) {
            instance = new MapService();
        }
        return instance;
    }

    private Emplacement em;

    public Emplacement getEmplacements (Trajet t) {
        String Url="";

           Url = "https://api.mapbox.com/geocoding/v5/mapbox.places/" + t.getAdresse() + ".json?access_token=pk.eyJ1Ijoic2FtaWtyIiwiYSI6ImNrOHRieWk3dDBuaTQzbGxvZDh2ZGJrZjgifQ.ZXvwJ489e09-HnnWfWpWtA&limit=1";
           req.setUrl(Url);
           req.setPost(false);
           req.addResponseListener(new ActionListener<NetworkEvent>() {
                                       @Override
                                       public void actionPerformed(NetworkEvent evt) {
                                           em = ParseMap(new String(req.getResponseData()));
                                           em.setTrajet(t);
                                       }
                                   }
           );
       NetworkManager.getInstance().addToQueueAndWait(req);
        return em;
    }

    public Emplacement ParseMap(String json) {
Emplacement e=new Emplacement();
        JSONParser j= new JSONParser();
        Map<String,Object> ListTrajetJson= null;
        try {
            ListTrajetJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        List<Map<String,Object>> list=(List <Map<String,Object>>) ListTrajetJson.get("features");
        for(Map<String,Object> obj:list){
         ArrayList<Double> center=(ArrayList<Double>) obj.get("center");
       e.setLatitude(center.get(1));
       e.setLongitude(center.get(0));
          }
        return e;
    }
}