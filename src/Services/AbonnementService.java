package Services;

import Entities.Abonnement;
import Entities.Enfant;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;

public class AbonnementService {
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

    public Boolean AjouterAbonnement(Abonnement abo){
        String url="http://127.0.0.1:8000/webservices/ajoutabo/"+abo.getEnfant().getId()+"/"+"1"+"/"+ abo.getType()+"/"+abo.getEtat()+"/"+abo.getDate()+"/"+abo.getMontant();
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
