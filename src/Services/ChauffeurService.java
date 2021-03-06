package Services;

import Entities.Chauffeur;
import Entities.Jardin;
import Entities.Trajet;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import esprit.tn.MyApplication;

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

    public String modifierChauffeur(Chauffeur chauffeur) {
        String Url="http://127.0.0.1:8000/Sami/api/modifierChauffeur";
        req.setUrl(Url);
        req.setPost(false);
        req.setContentType("application/json");
        req.addArgument("id",chauffeur.getId()+"");
        req.addArgument("cin",chauffeur.getCin());
        req.addArgument("nom",chauffeur.getNom());
        req.addArgument("email",chauffeur.getEmail());
        req.addArgument("sexe",chauffeur.getSexe());
        req.addArgument("tel",chauffeur.getTel());
        req.addArgument("username",chauffeur.getUsername());
        req.addArgument("password",chauffeur.getPassword());

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
    Chauffeur chauffeur=new Chauffeur();
    public Chauffeur getChauffeur(int id) {

        String Url="http://127.0.0.1:8000/Sami/api/getchauffeur/"+id;
        req.setUrl(Url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                chauffeur=ParseChauffeur(new String (req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);


        return chauffeur;
    }

    public ArrayList<Chauffeur> ParseChauffeurs(String json) {
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
            e.setTel(obj.get("tel").toString());
            e.setEmail(obj.get("email").toString());
            e.setUsername(obj.get("username").toString());

            chauffeurs.add(e);
        }
        return chauffeurs;
    }
    public Chauffeur ParseChauffeur(String json) {
        chauffeurs=new ArrayList<>();
        JSONParser j= new JSONParser();
        Map<String,Object> ListTrajetJson= null;
        try {
            ListTrajetJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
            Chauffeur e =new Chauffeur();
            float t= Float.parseFloat(ListTrajetJson.get("id").toString());
            e.setId((int)t);
            e.setCin(ListTrajetJson.get("cin").toString());
            e.setNom(ListTrajetJson.get("nom").toString());
            e.setSexe(ListTrajetJson.get("sexe").toString());
            e.setTel(ListTrajetJson.get("tel").toString());
            e.setEmail(ListTrajetJson.get("email").toString());
            e.setUsername(ListTrajetJson.get("username").toString());


        return e;
    }

}
