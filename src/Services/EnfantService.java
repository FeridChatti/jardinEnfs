package Services;

import Entities.Enfant;
import Entities.Jardin;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static esprit.tn.MyApplication.authenticated;

public class EnfantService {

public static EnfantService instance=null;
private ConnectionRequest req;
public boolean resultOk;
public ArrayList <Enfant> enfants;
    public Enfant enf;
    public ArrayList <Jardin> montant;

private EnfantService(){
    req=new ConnectionRequest();
}
public static EnfantService getInstance(){
    if(instance==null){
        instance=new EnfantService();
    }
    return instance;
}
public Boolean AjouterEnfant(Enfant e){
    String url="http://127.0.0.1:8000/webservices/ajoutenf/"+authenticated.getId()+"/"+e.getNom()+"/"+e.getPrenom()+"/"+e.getSexe()+"/"+e.getDatenaiss();
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
    public Boolean SupprimerEnfant(String id){
        String url="http://127.0.0.1:8000/webservices/deleteenf/"+id;
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



    public Boolean ModifierEnfant(Enfant e){
        String url="http://127.0.0.1:8000/webservices/modifenf/"+e.getId()+"/"+e.getNom()+"/"+e.getPrenom()+"/"+e.getSexe()+"/"+e.getDatenaiss();
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






    public Enfant getEnfants (String idp){
        String Url="http://127.0.0.1:8000/webservices/getenfa/"+idp;
        req.setUrl(Url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                enf=ParseEnfant(new String (req.getResponseData())).get(0);
                req.removeResponseListener(this);


            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);


        return enf;
    }






public ArrayList<Enfant> ListEnfants (String idp){
    String Url="http://127.0.0.1:8000/webservices/listeenf/"+idp;
    req.setUrl(Url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
           enfants=ParseEnfant(new String (req.getResponseData()));
           req.removeResponseListener(this);


        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);


return enfants;
}


public ArrayList<Enfant> ParseEnfant(String json)  {
enfants=new ArrayList<>();
    JSONParser j= new JSONParser();
    Map<String,Object> ListEnfantJson= null;
    try {
        ListEnfantJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
    } catch (IOException e) {
        e.printStackTrace();
    }
    List<Map<String,Object>> list=(List <Map<String,Object>>) ListEnfantJson.get("root");
    for(Map<String,Object> obj:list){
        Enfant e =new Enfant();
       float t= Float.parseFloat(obj.get("id").toString());
        e.setId((int)t);
        e.setNom(obj.get("nom").toString());
        e.setPrenom(obj.get("prenom").toString());
        e.setSexe(obj.get("sexe").toString());
        Map<String,Object> m = (Map<String, Object>) obj.get("naiss");
        String str = m.get("date").toString();
        String g = str.substring(0,10);
        e.setDatenaiss(g);
        float tf=Float.parseFloat(obj.get("id").toString());
        e.setId((int)tf);
        enfants.add(e);

    }
return enfants;
}



    public ArrayList<Jardin> Montant (String idp){
        String Url="http://127.0.0.1:8000/webservices/montant/"+idp;
        req.setUrl(Url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                montant=ParseMontant(new String (req.getResponseData()));
                req.removeResponseListener(this);


            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);


        return montant;
    }
    public ArrayList<Jardin> ParseMontant(String json)  {
        montant=new ArrayList<>();
        JSONParser j= new JSONParser();
        Map<String,Object> MontantJson= null;
        try {
            MontantJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Map<String,Object>> list=(List <Map<String,Object>>) MontantJson.get("root");
        for(Map<String,Object> obj:list){
            Jardin jar =new Jardin();
            float t= Float.parseFloat(obj.get("tarif").toString());

            jar.setTarif(t);

            montant.add(jar);

        }
        return montant;
    }


}
