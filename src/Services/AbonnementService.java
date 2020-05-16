package Services;

import Entities.Abonnement;
import Entities.Enfant;
import Entities.Jardin;
import Entities.Parents;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AbonnementService {
    public ArrayList<Abonnement> abonnements;
    public static AbonnementService instance=null;
    private ConnectionRequest req;
    public boolean resultOk;
    private AbonnementService(){
        req=new ConnectionRequest();
    }
    public static AbonnementService getInstance(){
        if(instance==null){
            instance=new AbonnementService();
        }
        return instance;
    }



    public Boolean ModifierAbonnement(String id,String type,String montant){
        String url="http://127.0.0.1:8000/webservices/modifabo/"+id+"/"+type+"/"+montant;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk=req.getResponseCode()==200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;

    }
    public Boolean SupprimerAbonnement(String id){
        String url="http://127.0.0.1:8000/webservices/deleteabons/"+id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk=req.getResponseCode()==200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;

    }



    public Boolean AjouterAbonnement(Abonnement abo,String idj){
        String url="http://127.0.0.1:8000/webservices/ajoutabo/"+abo.getEnfant().getId()+"/"+idj+"/"+ abo.getType()+"/"+abo.getEtat()+"/"+abo.getDate()+"/"+abo.getMontant();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk=req.getResponseCode()==200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;

    }

    public ArrayList<Abonnement> ListAbonnement (String idp){
        String Url="http://127.0.0.1:8000/webservices/listeabo/"+idp;
        req.setUrl(Url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                abonnements=ParseAbonnement(new String (req.getResponseData()));
                req.removeResponseListener(this);


            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);


        return abonnements;
    }


    public ArrayList<Abonnement> ListAbonnementResp (String idp){
        String Url="http://127.0.0.1:8000/webservices/listeabojardin/"+idp;
        req.setUrl(Url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                abonnements=ParseAbonnementResp(new String (req.getResponseData()));
                req.removeResponseListener(this);


            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);


        return abonnements;
    }


    public ArrayList<Abonnement> ParseAbonnementResp(String json)  {
        abonnements=new ArrayList<>();
        JSONParser j= new JSONParser();
        Map<String,Object> ListEnfantJson= null;
        try {
            ListEnfantJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Map<String,Object>> list=(List <Map<String,Object>>) ListEnfantJson.get("root");
        for(Map<String,Object> obj:list){
            Abonnement e =new Abonnement();
            Enfant enf=new Enfant();
            Parents pa=new Parents();
            pa.setNumtel(obj.get("numtel").toString());
            enf.setNom(obj.get("nom").toString());
            enf.setParent(pa);
            enf.setPrenom(obj.get("prenom").toString());
            float t= Float.parseFloat(obj.get("id").toString());
            e.setId((int)t);
            e.setEnfant(enf);

            e.setType(obj.get("type").toString());
            e.setEtat(obj.get("etat").toString());
            Map<String,Object> m = (Map<String, Object>) obj.get("dateab");
            String str = m.get("date").toString();
            String g = str.substring(0,10);
            e.setDate(g);


            abonnements.add(e);

        }
        return abonnements;
    }



    public ArrayList<Abonnement> ParseAbonnement(String json)  {
        abonnements=new ArrayList<>();
        JSONParser j= new JSONParser();
        Map<String,Object> ListEnfantJson= null;
        try {
            ListEnfantJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Map<String,Object>> list=(List <Map<String,Object>>) ListEnfantJson.get("root");
        for(Map<String,Object> obj:list){
            Abonnement e =new Abonnement();
            Enfant enf=new Enfant();
            enf.setNom(obj.get("nom").toString());
            enf.setPrenom(obj.get("prenom").toString());
            Jardin jar=new Jardin();
            jar.setName(obj.get("name").toString());
            float idj= Float.parseFloat(obj.get("idj").toString());
            jar.setId((int)idj);
            float t= Float.parseFloat(obj.get("id").toString());
            e.setId((int)t);
            e.setEnfant(enf);
            e.setJardin(jar);
            e.setType(obj.get("type").toString());
            e.setEtat(obj.get("etat").toString());
            Map<String,Object> m = (Map<String, Object>) obj.get("dateab");
            String str = m.get("date").toString();
            String g = str.substring(0,10);
            e.setDate(g);


            abonnements.add(e);

        }
        return abonnements;
    }





}
