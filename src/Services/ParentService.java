package Services;

import Entities.Parents;
import Entities.User;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static esprit.tn.MyApplication.authenticated;

public class ParentService {
    public static ParentService instance;

    public Parents p;
    public boolean resultOk;
    private String json;
    private ConnectionRequest req;


    public ParentService() {
        req = new ConnectionRequest();
    }

    public static ParentService getInstance() {

        if (instance == null) {
            instance = new ParentService();
        }
        return instance;
    }


    //get the parent to edit
    public Parents getparent(int id) {
        String Url = "http://127.0.0.1:8000/Api/getparent";

        req.setUrl(Url);
        req.setPost(false);
        req.addArgument("par", String.valueOf(id));


        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {


                JSONParser j = new JSONParser();
                json = new String(req.getResponseData());
                p=parsing(json);


                //return json;

                req.removeResponseListener(this);


            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return p;
    }


    public Boolean checkusername(String username) {
        String Url = "http://127.0.0.1:8000/Api/testusername";

        req.setUrl(Url);
        req.setPost(false);
        req.addArgument("username", username);




        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {



                json = new String(req.getResponseData());


                //return json;

                req.removeResponseListener(this);


            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        //return p;
        return !json.contains("Exist");

    }


    public Boolean checkuseremail(String email) {
        String Url = "http://127.0.0.1:8000/Api/testemail";

        req.setUrl(Url);
        req.setPost(false);
        req.addArgument("email", email);




        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {



                json = new String(req.getResponseData());


                //return json;

                req.removeResponseListener(this);


            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        //return p;
        return !json.contains("Exist");

    }



    public Boolean Updateparent(String username ,String email , String password, String nom , String prenom ,String numtel ,String adresse){
        String Url = "http://127.0.0.1:8000/Api/editparent";

        req.setUrl(Url);
        req.setPost(false);
        req.addArgument("username", username);
        req.addArgument("email", email);
        req.addArgument("par",String.valueOf(authenticated.getId()));
        req.addArgument("password",password);
        req.addArgument("nom",nom);
        req.addArgument("prenom",prenom);
        req.addArgument("numtel",numtel);
        req.addArgument("adresse",adresse);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {



                resultOk=req.getResponseCode()==200;


                //return json;

                req.removeResponseListener(this);


            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        //return p;
        return resultOk;



    }



    public Parents parsing(String json) {
        Parents p = new Parents();
        JSONParser j = new JSONParser();
        Map<String, Object> usrjson = null;
        try {
            usrjson = j.parseJSON(new CharArrayReader(json.toCharArray()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        List<Map<String, Object>> list = (List<Map<String, Object>>) usrjson.get("root");
        for (Map<String, Object> obj : list) {

            //p.nom,p.prenom,p.numtel,p.adresse

            p.setId(authenticated.getId());
            p.setEmail(authenticated.getEmail());
            p.setUsername(authenticated.getUsername());
            p.setNom(obj.get("nom").toString());
            p.setPrenom(obj.get("prenom").toString());
            p.setNumtel(obj.get("numtel").toString());
            p.setAdresse(obj.get("adresse").toString());


        }
        return p;
    }

}
