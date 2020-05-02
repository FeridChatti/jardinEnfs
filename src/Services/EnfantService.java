package Services;

import Entities.Enfant;
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
         //   enfants=Parse
        }
    });

return null;
}
public ArrayList<Enfant> ParseEnfant(String json) throws IOException {
enfants=new ArrayList<>();
    JSONParser j= new JSONParser();
    Map<String,Object> ListEnfantJson=j.parseJSON(new CharArrayReader(json.toCharArray()));
    List<Map<String,Object>> list=(List <Map<String,Object>>) ListEnfantJson.get("root");
    for(Map<String,Object> obj:list){
        Enfant e =new Enfant();
        e.setId(Integer.parseInt(obj.get("id").toString()));
        e.setNom(obj.get("nom").toString());
        e.setPrenom(obj.get("prenom").toString());
        enfants.add(e);

    }
return enfants;
}

}
