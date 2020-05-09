package Services;

import Entities.Enfant;
import Entities.Remarque;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;

import java.util.ArrayList;

import static esprit.tn.MyApplication.authenticated;

public class ReclamService {

    public static ReclamService instance;


    public boolean resultOk;


    private String json;
    private ConnectionRequest req;

    public ReclamService() {
        req = new ConnectionRequest();
    }

    public static ReclamService getInstance() {

        if (instance == null) {
            instance = new ReclamService();
        }
        return instance;
    }





    public Boolean addReclam(String titre,String descr){

        String url = "http://127.0.0.1:8000/Api/addreclam";
        req.setUrl(url);
        req.addArgument("titre", String.valueOf(titre));
        req.addArgument("description", String.valueOf(descr));
        req.addArgument("par", String.valueOf(authenticated.getId()));
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = new String(req.getResponseData()).contains("success");
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;

    }

}
