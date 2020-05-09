package Services;

import Entities.Enfant;
import Entities.Jardin;
import Entities.Messages;
import Entities.Parents;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static esprit.tn.MyApplication.authenticated;

public class ChatService {
    public static ChatService instance;

    List<Jardin> jardins ;
    public Messages p;
    public boolean resultOk;
    private String json;
    private ConnectionRequest req;


    public ChatService() {
        req = new ConnectionRequest();
    }

    public static ChatService getInstance() {

        if (instance == null) {
            instance = new ChatService();
        }
        return instance;
    }



    public  List<Jardin> JardList(){
        String Url = "http://localhost:8000/Api/jardmess";

        req.setUrl(Url);
        req.setPost(false);
        req.addArgument("par", String.valueOf(authenticated.getId()));


        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {


                JSONParser j = new JSONParser();
                json = new String(req.getResponseData());
                jardins=parsing(json);


                //return json;

                req.removeResponseListener(this);


            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return jardins;

    }

    public List<Jardin> parsing(String json){

        jardins=new ArrayList<>();
        JSONParser j= new JSONParser();
        Map<String,Object> ListjardintJson= null;
        try {
            ListjardintJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Map<String,Object>> list=(List <Map<String,Object>>) ListjardintJson.get("root");
        for(Map<String,Object> obj:list){
            Jardin e =new Jardin();
            float t= Float.parseFloat(obj.get("id").toString());
            e.setId((int)t);
            e.setName(obj.get("name").toString());

            jardins.add(e);

        }
        return jardins;

    }

}
