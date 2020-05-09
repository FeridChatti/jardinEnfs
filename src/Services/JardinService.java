package Services;

import Entities.Enfant;
import Entities.Jardin;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JardinService {

    public static JardinService instance=null;
    private ConnectionRequest req;

    public boolean resultOk;
    public ArrayList <Jardin> jardin;

    private JardinService(){
        req=new ConnectionRequest();
    }
    public static JardinService getInstance(){
        if(instance==null){
            instance=new JardinService();
        }
        return instance;
    }

    public ArrayList<Jardin> ListJardin (){
        String Url = "http://127.0.0.1:8000/Apijar/listjardin";
        req.setUrl(Url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                jardin = ParseJardin(new String(req.getResponseData()));
                req.removeResponseListener(this);


            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);


        return jardin;
    }


    public ArrayList<Jardin> ParseJardin (String json){
        jardin = new ArrayList<>();
       JSONParser j= new JSONParser();
        Map<String, Object> ListJArdinJson = null;
        try {
            ListJArdinJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Map<String, Object>> list = (List<Map<String, Object>>) ListJArdinJson.get("root");;
        for (Map<String, Object> obj : list) {
            Jardin e = new Jardin();
            float t = Float.parseFloat(obj.get("id").toString());
            e.setId((int) t);
            e.setName(obj.get("name").toString());
            e.setDescription(obj.get("description").toString());
            e.setNumtel(obj.get("numtel").toString());
            float p = Float.parseFloat(obj.get("tarif").toString());
            e.setTarif((int) p);
            e.setAdresse(obj.get("adresse").toString());
            jardin.add(e);

        }
        return jardin;
    }
    public Boolean ModifierJardin(Jardin j){
        String url="http://127.0.0.1:8000/Apijar/modifjardin/"+j.getId()+"/"+j.getDescription()+"/"+j.getAdresse()+"/"+j.getTarif()+"/"+j.getNumtel()+"/"+j.getName();
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
