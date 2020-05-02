package Services;

import Entities.Enfant;
import Entities.Jardin;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EnfantService {

public static EnfantService instance=null;
private ConnectionRequest req;
public boolean resultOk;
public ArrayList <Enfant> enfants;
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
    String url="http://127.0.0.1:8000/webservices/ajoutenf/4"+"/"+e.getNom()+"/"+e.getPrenom()+"/"+e.getSexe()+"/"+e.getDatenaiss();
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
