package Services;

import Entities.Categorie;
import Entities.Evenement;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import esprit.tn.MyApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EvenementService {

    public static EvenementService instance = null;
    public ArrayList<Evenement> evenements;
    public boolean res;
    public Evenement ev;
    private ConnectionRequest req;
    private String resultOk;
    private boolean result;

    public EvenementService() {
        req = new ConnectionRequest();
    }

    public static EvenementService getInstance() {
        if (instance == null) {
            instance = new EvenementService();
        }
        return instance;

    }

    public ArrayList<Evenement> ListeEvenementJardin(String idj) {
        String Url = "http://127.0.0.1:8000/eveapi/Api/listeventsJar/" + idj;
        req.setUrl(Url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                evenements = ParseEvenements(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return evenements;
    }




    public ArrayList<Evenement> ParseEvenements(String json) {
        evenements = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> listeventsJson = null;
        try {
            listeventsJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Map<String, Object>> list = (List<Map<String, Object>>) listeventsJson.get("root");
        for (Map<String, Object> obj : list) {
            Evenement e = new Evenement();
            Categorie c = new Categorie();
            c.setLibelle(obj.get("libelle").toString());
            float t = Float.parseFloat(obj.get("id").toString());
            e.setId((int) t);
            e.setTitre(obj.get("titre").toString());
            e.setDescription(obj.get("description").toString());
            e.setCategorie(c);
            Map<String, Object> m = (Map<String, Object>) obj.get("date");
            String str = m.get("date").toString();
            String g = str.substring(0, 10);
            e.setDate(g);


            //e.setImage(obj.get("image").toString());

            evenements.add(e);
        }
        return evenements;
    }


    public Boolean AjouterEvenement(Evenement e) {
        String url = "http://127.0.0.1:8000/eveapi/Api/ajoutevent/2" + "/" + e.getTitre() + "/" + e.getDescription() + "/" + e.getDate() + "/" + e.getCategorie().getId();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                res = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return res;

    }


    public boolean supprimerEvenement(String id) {
        String Url = "http://127.0.0.1:8000/eveapi/Api/suppevent/" + id;
        req.setUrl(Url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String res = new String(req.getResponseData());
                result = res.equals("true");
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }

    public Evenement AfficherEvent(Evenement e) {

        ArrayList<Evenement> event = ListeEvenementJardin(UserService.getInstance().getJardin(MyApplication.authenticated.getId() + "").getId() + "");
    Evenement ev=event.stream().filter(a->a.getId()==e.getId()).collect(Collectors.toList()).get(0);

return ev;



    }

    public ArrayList <Evenement> AfficherEventPar(String idp) {
        String Url = "http://127.0.0.1:8000/eveapi/Api/event/" + idp;
        req.setUrl(Url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                evenements = ParseEvenements(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return evenements;

    }

//kima mtea sami

    public String modifierEvenement(Evenement e) {
        String Url="http://127.0.0.1:8000/eveapi/Api/modifierEvenement";
        req.setUrl(Url);
        req.setPost(false);
        req.setContentType("application/json");
        req.addArgument("titre",e.getTitre());
        req.addArgument("description",e.getDescription());
        req.addArgument("date",e.getDate());
        req.addArgument("categorie",e.getCategorie().toString());


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














}
