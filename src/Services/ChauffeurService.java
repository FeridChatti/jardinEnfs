package Services;

import Entities.Chauffeur;
import Entities.Trajet;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChauffeurService {
    public ArrayList <Chauffeur> chauffeurs;
    public static ChauffeurService instance=null;
    private ConnectionRequest req;
    private String resultOk;
    private boolean result;

    public ChauffeurService(){
        req=new ConnectionRequest();
    }
    public static ChauffeurService getInstance(){
        if(instance==null){
            instance=new ChauffeurService();
        }
        return instance;
    }

    public ArrayList<Chauffeur> ListeChauffeursJardin (String idj) {
        String Url="http://127.0.0.1:8000/Sami/api/chauffeurs/"+idj;
        req.setUrl(Url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                chauffeurs=ParseChauffeurs(new String (req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);


        return chauffeurs;
    }

    public Chauffeur getChauffeur(int id) {chauffeurs=ListeChauffeursJardin(4+"");
     Chauffeur ch= (Chauffeur) chauffeurs.stream().filter(e->e.getId()==id).collect(Collectors.toList()).get(0);

     return ch;
    }
    public ArrayList<Chauffeur> ParseChauffeurs(String json)  {
        chauffeurs=new ArrayList<>();
        JSONParser j= new JSONParser();
        Map<String,Object> ListTrajetJson= null;
        try {
            ListTrajetJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Map<String,Object>> list=(List <Map<String,Object>>) ListTrajetJson.get("root");
        for(Map<String,Object> obj:list){
            Chauffeur e =new Chauffeur();
            float t= Float.parseFloat(obj.get("id").toString());
            e.setId((int)t);
            e.setCin(obj.get("cin").toString());
            e.setNom(obj.get("nom").toString());
            e.setSexe(obj.get("sexe").toString());
            e.setTel(obj.get("nom").toString());
            e.setEmail(obj.get("email").toString());
            e.setUsername(obj.get("username").toString());

            chauffeurs.add(e);
        }
        return chauffeurs;
    }

}
