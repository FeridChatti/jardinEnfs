package Services;

import Entities.Chauffeur;
import Entities.Enfant;

import Entities.Trajet;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrajetService {
    public ArrayList <Trajet> trajets;
    public static TrajetService instance=null;
    private ConnectionRequest req;
    private String resultOk;
    private boolean result;

    public TrajetService(){
        req=new ConnectionRequest();
    }
    public static TrajetService getInstance(){
        if(instance==null){
            instance=new TrajetService();
        }
        return instance;
    }

    public ArrayList<Trajet> ListeTrajetsParChauffeur (String idp) {
        String Url="http://127.0.0.1:8000/Sami/api/listpar/"+idp;
        req.setUrl(Url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                trajets=ParseTrajet(new String (req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);


        return trajets;
    }

    public ArrayList<Trajet> ListeTrajets (String idj) {
        String Url="http://127.0.0.1:8000/Sami/api/listtrajets/"+idj;
        req.setUrl(Url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                trajets=ParseTrajet(new String (req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);


        return trajets;
    }

    public String AjouterTrajet(Trajet trajet){
//String json=JSONParser.mapToJson(data);

        String url="http://127.0.0.1:8000/Sami/api/addTrajet";
        req.setUrl(url);
        req.setPost(false);
        req.setContentType("application/json");
        req.addArgument("id",14+"");
        req.addArgument("adresse",trajet.getAdresse());
        req.addArgument("heure",trajet.getHeure());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk=new String(req.getResponseData());

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }

    public boolean supprimerTrajet(String id) {
        String Url="http://127.0.0.1:8000/Sami/api/deleteTrajet/"+id;
        req.setUrl(Url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String res=new String (req.getResponseData());
                result=res.equals("true");
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }

    public ArrayList<Trajet> ParseTrajet(String json)  {
        trajets=new ArrayList<>();
        JSONParser j= new JSONParser();
        Map<String,Object> ListTrajetJson= null;
        try {
            ListTrajetJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Map<String,Object>> list=(List <Map<String,Object>>) ListTrajetJson.get("root");
        for(Map<String,Object> obj:list){
            Trajet e =new Trajet(0,"","");
            float t= Float.parseFloat(obj.get("id").toString());
            e.setId((int)t);
            e.setHeure(obj.get("heure").toString());
            e.setAdresse(obj.get("adresse").toString());
            if(obj.get("chauffeur_id").toString()!=null)
            {
                Chauffeur chauffeur=ChauffeurService.getInstance().getChauffeur((int)((double)obj.get("chauffeur_id")));
                e.setChauffeur(chauffeur);
                trajets.add(e);
        }
        }
        return trajets;
    }

}
