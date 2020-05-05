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
           /* Map<String,Object> m = (Map<String, Object>) obj.get("Date");
            String str = m.get("date").toString();
            String g = str.substring(0,10);
            c.setDate(g);*/
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

    public Boolean AjouterParticiper(PartActivite p){
        String url="http://127.0.0.1:8000/dorra/webS/participer/"+p.getId()+"/"+p.getEnfant().getId()+"/"+p.getDate();
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
}
