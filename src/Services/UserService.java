package Services;


import Entities.Enfant;
import Entities.Jardin;
import Entities.User;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import static esprit.tn.MyApplication.authenticated;


public class UserService {

    public static UserService instance;
    public  Jardin jardin=new Jardin();

    private String json;
    private ConnectionRequest req;
    public boolean resultOk;


    public UserService(){
        req=new ConnectionRequest();
    }

    public static UserService getInstance(){

        if(instance==null){
            instance=new UserService();
        }
        return instance;
    }




    public void parseuser(String username){

        String Url="http://127.0.0.1:8000/Api/usercred";

        req.setUrl(Url);
        req.setPost(false);
        req.addArgument("username",username);


        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {


                JSONParser j= new JSONParser();
                json=new String(req.getResponseData());
                parsing(json);




                //return json;

                req.removeResponseListener(this);


            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);



    }




    public String login(String username,String password){
        String Url="http://127.0.0.1:8000/Api/login/" + username + "/" + password;

        req.setUrl(Url);
        req.setPost(false);


        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {


                JSONParser j= new JSONParser();
                 json=new String(req.getResponseData());



               //return json;

                req.removeResponseListener(this);


            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return json;
    }



    public void parsing(String json){
        User e=new User();
        JSONParser j= new JSONParser();
        Map<String,Object> usrjson= null;
        try {
            usrjson = j.parseJSON(new CharArrayReader(json.toCharArray()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        List<Map<String,Object>> list=(List <Map<String,Object>>) usrjson.get("root");
        for(Map<String,Object> obj:list){

            float t= Float.parseFloat(obj.get("id").toString());
            authenticated.setId((int)t);
            authenticated.setEmail(obj.get("email").toString());
            authenticated.setUsername(obj.get("username").toString());
            authenticated.setRole(obj.get("roles").toString());
            authenticated.setType(obj.get("type").toString());



        }


    }

    public Jardin getJardin(String id)
    {String Url="http://127.0.0.1:8000/Api/jardin/"+id;
        req.setUrl(Url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                jardin=parseJardin(new String (req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);


        return jardin;

    }

    public Jardin parseJardin(String json){

        JSONParser j= new JSONParser();
        Map<String,Object> obj= null;
        try {
            obj= j.parseJSON(new CharArrayReader(json.toCharArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
                   Jardin e =new Jardin();
        float id= Float.parseFloat(obj.get("id").toString());
        float tarif= Float.parseFloat(obj.get("tarif").toString());

        e.setId((int)id);
            e.setName(obj.get("name").toString());
            e.setAdresse(obj.get("adresse").toString());
            e.setTarif((int)tarif);
            e.setDescription(obj.get("description").toString());
            e.setEtat(obj.get("etat").toString());
            e.setNumtel(obj.get("numtel").toString());

        return e;


    }

}
