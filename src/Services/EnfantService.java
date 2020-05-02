package Services;

import Entities.Enfant;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;

public class EnfantService {

public static EnfantService instance=null;
private ConnectionRequest req;
public boolean resultOk;

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

}
