package Services;

import Entities.*;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static esprit.tn.MyApplication.authenticated;

public class ChatService {
    public static ChatService instance;

    List<Jardin> jardins ;
    List<Parents> parents ;
    List<Messages> mess ;
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
            e.setDescription(obj.get("nom").toString());

            jardins.add(e);

        }
        return jardins;

    }



    public List<Messages> parsingmess(String json){

        mess=new ArrayList<>();
        JSONParser j= new JSONParser();
        Map<String,Object> ListmessJson= null;
        try {
            ListmessJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Map<String,Object>> list=(List <Map<String,Object>>) ListmessJson.get("root");
        for(Map<String,Object> obj:list){
            Messages e =new Messages();
            float t= Float.parseFloat(obj.get("mid").toString());
            e.setId((int)t);

            e.setJarname(obj.get("jardin").toString());
            e.setMsg(obj.get("msg").toString());
            e.setSendername(obj.get("parenom").toString()+" "+obj.get("pareprenom").toString());

            e.setDate(obj.get("date").toString());

            User sender=new User();
            float sendid= Float.parseFloat(obj.get("sid").toString());
            sender.setId((int)sendid);
            e.setSender(sender);

            Jardin jar=new Jardin();
            float jarid= Float.parseFloat(obj.get("jardid").toString());
            jar.setId((int)jarid);
            jar.setName(e.getJarname());
            e.setJardin(jar);


            mess.add(e);

        }
        return mess;
    }
    public  List<Messages> MessparList(String parid,String jard){
        String Url = "http://localhost:8000/Api/mymsg";

        req.setUrl(Url);
        req.setPost(false);
        req.addArgument("par", parid);
        req.addArgument("jar", jard);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {


                JSONParser j = new JSONParser();
                json = new String(req.getResponseData());
                mess=parsingmess(json);


                //return json;

                req.removeResponseListener(this);


            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return mess;

    }

    public  String sendmsg(String msg,int idjar,int senderid,int parid){
        String Url = "http://localhost:8000/Api/sendmsg";

        req.setUrl(Url);
        req.setPost(false);
        req.addArgument("par", String.valueOf(parid));
        req.addArgument("jard", String.valueOf(idjar));
        req.addArgument("sender", String.valueOf(senderid));
        req.addArgument("msg", msg);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {


                json = new String(req.getResponseData());




                //return json;

                req.removeResponseListener(this);


            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return json;

    }













    public  List<Parents> userlist(int jardid){
        String Url = "http://localhost:8000/Api/usermlist";

        req.setUrl(Url);
        req.setPost(false);
        req.addArgument("jar", String.valueOf(jardid));


        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {


                JSONParser j = new JSONParser();
                json = new String(req.getResponseData());
                parents=parsingusers(json);


                //return json;

                req.removeResponseListener(this);


            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return parents;

    }

    public List<Parents> parsingusers(String json){

        parents=new ArrayList<>();
        JSONParser j= new JSONParser();
        Map<String,Object> ListparentJson= null;
        try {
            ListparentJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Map<String,Object>> list=(List <Map<String,Object>>) ListparentJson.get("root");

        for(Map<String,Object> obj:list){
            Parents e =new Parents();
            float t= Float.parseFloat(obj.get("parid").toString());
            e.setId((int)t);
            e.setPrenom(obj.get("prenom").toString());
            e.setNom(obj.get("nom").toString());
            //used adresse as a holder for the last message
            e.setAdresse(obj.get("msg").toString());
            //used numtel to hold the date
            e.setNumtel(obj.get("mdate").toString());

            parents.add(e);

        }
        return parents;

    }






}
