package Services;

import Entities.*;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActiviteService {

    public static ActiviteService instance=null;
    private ConnectionRequest req;
    public boolean resultOk;
    public ArrayList<Activite> activites;
    public ArrayList<Activite> activite;
    public ArrayList<PartActivite> participants;

    public ArrayList<PartActivite> verif;

    private ActiviteService(){
        req=new ConnectionRequest();
    }
    public static ActiviteService getInstance(){
        if(instance==null){
            instance=new ActiviteService();
        }
        return instance;
    }


    public ArrayList<Activite> ParseActivite(String jsontext) throws IOException {
        activites = new ArrayList<Activite>();
        JSONParser j = new JSONParser();
        Map<String,Object> actListJson = j.parseJSON(new CharArrayReader(jsontext.toCharArray()));
        List<Map<String,Object>> list = (List<Map<String, Object>>)actListJson.get("root");

        for (Map<String,Object> obj : list){
            Activite c = new Activite();
            float id = Float.parseFloat((obj.get("id").toString()));
            c.setId((int)id);
            c.setTypeact(obj.get("typeact").toString());
            c.setDetailles(obj.get("detailles").toString());
            Map<String,Object> m = (Map<String, Object>) obj.get("Date");

            String str = m.get("date").toString();
            String g = str.substring(0,10);
            c.setDate(g);
            activites.add(c);

        }

        return activites;

    }

    public ArrayList<Activite> getAllActivites(){
        String url="http://127.0.0.1:8000/dorra/webS/listeact";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    activites = ParseActivite(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return activites;
    }

    public ArrayList<Activite> ParseAct(String jsontext) throws IOException {
        activite = new ArrayList<Activite>();
        JSONParser j = new JSONParser();
        Map<String,Object> actListJson = j.parseJSON(new CharArrayReader(jsontext.toCharArray()));
        List<Map<String,Object>> list = (List<Map<String, Object>>)actListJson.get("root");

        for (Map<String,Object> obj : list){
            Activite c = new Activite();
            float id = Float.parseFloat((obj.get("id").toString()));
            c.setId((int)id);
            c.setTypeact(obj.get("typeact").toString());
            c.setDetailles(obj.get("detailles").toString());
            Club a = new Club();
            a.setName(obj.get("name").toString());
            c.setClub(a);
            Map<String,Object> m = (Map<String, Object>) obj.get("Date");
            String str = m.get("date").toString();
            String g = str.substring(0,10);
            c.setDate(g);
            activite.add(c);
        }
        return activite;

    }

    public ArrayList<Activite> getActivite(String id){
        String url="http://127.0.0.1:8000/dorra/webS/showA/"+id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    activite = ParseAct(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return activite;
    }

    public Boolean AjouterParticiper(int ida, int ide, String date){
        String url="http://127.0.0.1:8000/dorra/webS/partact/"+ida+"/"+ide+"/"+date;
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

    public Boolean AjouterAct(int idc, String type, String det,String date){
        String url="http://127.0.0.1:8000/dorra/webS/addact/"+idc+"/"+type+"/"+det+"/"+date;
        req.setUrl(url);
        req.setPost(false);
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


    public ArrayList<PartActivite> ParsePArt(String jsontext) throws IOException {
        participants = new ArrayList<PartActivite>();
        JSONParser j = new JSONParser();
        Map<String,Object> actListJson = j.parseJSON(new CharArrayReader(jsontext.toCharArray()));
        List<Map<String,Object>> list = (List<Map<String, Object>>)actListJson.get("root");

        for (Map<String,Object> obj : list){
            PartActivite c = new PartActivite();
//            float id = Float.parseFloat((obj.get("id").toString()));
  //          c.setId((int)id);
            Enfant e = new Enfant();
            e.setNom(obj.get("nom").toString());
            c.setEnfant(e);
            Activite a = new Activite();
            a.setTypeact(obj.get("typeact").toString());
            c.setActivite(a);
            Map<String,Object> m = (Map<String, Object>) obj.get("date");
            String str = m.get("date").toString();
            String g = str.substring(0,10);
            c.setDate(g);
            participants.add(c);

        }

        return participants;

    }

    public ArrayList<PartActivite> ListeParticipants(){
        String url="http://127.0.0.1:8000/dorra/webS/listePart";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    participants = ParsePArt(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return participants;
    }

    public ArrayList<PartActivite> ParseVerif(String jsontext) throws IOException {
        verif = new ArrayList<PartActivite>();
        JSONParser j = new JSONParser();
        Map<String,Object> actListJson = j.parseJSON(new CharArrayReader(jsontext.toCharArray()));
        List<Map<String,Object>> list = (List<Map<String, Object>>)actListJson.get("root");

        for (Map<String,Object> obj : list){
            PartActivite c = new PartActivite();
//            float id = Float.parseFloat((obj.get("id").toString()));
            //          c.setId((int)id);
            Enfant e = new Enfant();
            float id = Float.parseFloat((obj.get("d").toString()));
            e.setId((int) id);
            c.setEnfant(e);
            Activite a = new Activite();
            float ida = Float.parseFloat((obj.get("id").toString()));
            a.setId((int)ida);
            c.setActivite(a);
            Map<String,Object> m = (Map<String, Object>) obj.get("date");
            String str = m.get("date").toString();
            String g = str.substring(0,10);
            c.setDate(g);
            verif.add(c);

        }

        return verif;

    }
    public ArrayList<PartActivite> verification(String id, String ida){
        String url="http://127.0.0.1:8000/dorra/webS/verif/"+id+"/"+ida;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    verif = ParseVerif(new String(req.getResponseData()));
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
