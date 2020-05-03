package Services;


import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;


public class UserService {

    public static UserService instance;

    private ConnectionRequest req;
    public boolean resultOk;


    private UserService(){
        req=new ConnectionRequest();
    }

    public static UserService getInstance(){

        if(instance==null){
            instance=new UserService();
        }
        return instance;
    }



    public String login(String username,String password){
        String Url="http://localhost:8000/Api/login/" + username + "/" + password;

        req.setUrl(Url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {


                JSONParser j= new JSONParser();
                String json=new String(req.getResponseData());
                JSONParser p = new JSONParser();
                Map<String, Object> results = p.parseJSON(new CharArrayReader(json.toCharArray());


                System.out.println(json);
                return json;
                req.removeResponseListener(this);


            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);


        return "ERROR";
    }





}
