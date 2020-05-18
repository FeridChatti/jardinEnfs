package Services;

import Entities.Jardin;
import Entities.Paiement;
import com.codename1.io.*;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PaimentService {

 public ArrayList<Paiement> paim;
    public static PaimentService instance=null;
    private ConnectionRequest req;
    public String json;

    public boolean resultOk;


    private PaimentService(){
        req=new ConnectionRequest();
    }
    public static PaimentService getInstance(){
        if(instance==null){
            instance=new PaimentService();
        }
        return instance;
    }

    public ArrayList<Paiement> ListPaim (String id)
    {
        String Url="http://127.0.0.1:8000/Apijar/listpaiement/"+id;

        req.setUrl(Url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                paim=parsePaiement(new String (req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);


        return paim;

    }

    public ArrayList<Paiement> parsePaiement (String json){
        paim = new ArrayList<>();
        JSONParser p= new JSONParser();
        Map<String, Object> Paimjson = null;
        try {
            Paimjson  = p.parseJSON(new CharArrayReader(json.toCharArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Map<String, Object>> list = (List<Map<String, Object>>)  Paimjson.get("root");;
        for (Map<String, Object> obj : list) {

            float t = Float.parseFloat(obj.get("id").toString());

            float s = Float.parseFloat(obj.get("montant").toString());

            Map<String,Object> m = (Map<String, Object>) obj.get("date");
            String str = m.get("date").toString();
            String g = str.substring(0,10);

            Paiement pam = new Paiement((int)t,s,g);
            paim.add(pam);


        }
        return paim;
    }




    public Boolean effectuerpaim(String id){
        String Url="http://127.0.0.1:8000/Apijar/paiement1";

        req.setUrl(Url);
        req.setPost(false);
        req.addArgument("id", String.valueOf(id));

        req.addResponseListener(evt ->
                json=new String(req.getResponseData())
        );

        NetworkManager.getInstance().addToQueueAndWait(req);
        return json.contains("true");
    }


    }

