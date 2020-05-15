package Services;

import Entities.*;
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
    public Evenement event;
    private ConnectionRequest req;
    private String resultOk;
    public boolean resultOkk;
    public ArrayList<Participer> participants;
    public ArrayList<Participer> veriff;
    public ArrayList<Participer> verif;

    private boolean result;
    public ArrayList<Evenement> evenement;


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
            float t = Float.parseFloat(obj.get("id").toString());
            e.setId((int) t);
            e.setTitre(obj.get("titre").toString());
            e.setDescription(obj.get("description").toString());
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
//get event

    public Evenement getEvent(int id)
    {
        String Url = "http://127.0.0.1:8000/eveapi/Api/getevent/"+ id;
        req.setUrl(Url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                event = ParseEvenements(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return event;
    }













    public Evenement AfficherEvent(int e) {
        ArrayList<Evenement> event = ListeEvenementJardin(UserService.getInstance().getJardin(MyApplication.authenticated.getId() + "").getId() + "");
    Evenement ev=event.stream().filter(a->a.getId()==e).collect(Collectors.toList()).get(0);

return ev;



    }






    public ArrayList <Evenement> AfficherEventPar(String idp) {
        String Url = "http://127.0.0.1:8000/eveapi/Api/event/"+ idp;
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



    public String modifierEvenement(Evenement e) {
        String Url="http://127.0.0.1:8000/eveapi/Api/editevent/"+e.getId()+"/"+e.getTitre()+"/"+e.getDescription()+"/"+e.getDate()+"/"+e.getCategorie().getId();
        req.setUrl(Url);
        req.setPost(false);



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


    public ArrayList<Evenement> ParseEvent(String jsontext) throws IOException {
        evenement = new ArrayList<Evenement>();
        JSONParser j = new JSONParser();
        Map<String,Object> actListJson = j.parseJSON(new CharArrayReader(jsontext.toCharArray()));
        List<Map<String,Object>> list = (List<Map<String, Object>>)actListJson.get("root");

        for (Map<String,Object> obj : list){
            Evenement e = new Evenement();
            float id = Float.parseFloat((obj.get("id").toString()));
            e.setId((int)id);
            e.setTitre(obj.get("titre").toString());
            e.setDescription(obj.get("description").toString());
            Categorie c = new Categorie();
            c.setLibelle(obj.get("libelle").toString());
            e.setCategorie(c);
            Map<String,Object> m = (Map<String, Object>) obj.get("Date");
            String str = m.get("date").toString();
            String g = str.substring(0,10);
            e.setDate(g);
            evenement.add(e);
        }
        return evenement;

    }

    public ArrayList<Evenement> getEvenement(String id){
        String url="http://127.0.0.1:8000/eveapi/Api/affEv/"+id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    evenement = ParseEvent(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return evenement;
    }

    public Boolean AddParticiper(int ide, int iden){
        String url="http://127.0.0.1:8000/eveapi/Api/partEvent/"+ide+"/"+iden;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOkk=req.getResponseCode()==200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOkk;

    }


    public ArrayList<Participer> ParseParticiperEvent(String jsontext) throws IOException {
        participants = new ArrayList<Participer>();
        JSONParser j = new JSONParser();
        Map<String,Object> actListJson = j.parseJSON(new CharArrayReader(jsontext.toCharArray()));
        List<Map<String,Object>> list = (List<Map<String, Object>>)actListJson.get("root");

        for (Map<String,Object> obj : list){
            Participer c = new Participer();

            Enfant e = new Enfant();
            e.setNom(obj.get("nom").toString());
            c.setEnfant(e);
            Evenement ev = new Evenement();
            ev.setTitre(obj.get("titre").toString());
            c.setEvenement(ev);
            Map<String,Object> m = (Map<String, Object>) obj.get("date");

            participants.add(c);

        }

        return participants;

    }


    public ArrayList<Participer> ListeParticipants(){
        String url="http://127.0.0.1:8000/eveapi/Api/listePart";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    participants = ParseParticiperEvent(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return participants;
    }

    public ArrayList<Participer> ParseVeriff(String jsontext) throws IOException {
        veriff = new ArrayList<Participer>();
        JSONParser j = new JSONParser();
        Map<String, Object> actListJson = j.parseJSON(new CharArrayReader(jsontext.toCharArray()));
        List<Map<String, Object>> list = (List<Map<String, Object>>) actListJson.get("root");

        for (Map<String, Object> obj : list) {
            Participer p = new Participer();

            Enfant e = new Enfant();
            float id = Float.parseFloat((obj.get("d").toString()));
            e.setId((int) id);
            p.setEnfant(e);
            Evenement ev = new Evenement();
            float ide = Float.parseFloat((obj.get("id").toString()));
            e.setId((int) ide);
            p.setEvenement(ev);

            veriff.add(p);

        }

        return veriff;
    }

        public ArrayList<Participer> verification(String id,String ide)
        {

            String url="http://127.0.0.1:8000/eveapi/Api/verifierr/"+id+"/"+ide;
            req.setUrl(url);
            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    try {
                        verif = ParseVeriff(new String(req.getResponseData()));
                        req.removeResponseListener(this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(req);
            return verif;

        }
    }

