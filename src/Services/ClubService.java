package Services;

import Entities.Club;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClubService {

    public static ClubService instance=null;
    private ConnectionRequest req;
    public boolean resultOk;
    public ArrayList<Club> clubs;

    private ClubService(){
        req=new ConnectionRequest();
    }
    public static ClubService getInstance(){
        if(instance==null){
            instance=new ClubService();
        }
        return instance;
    }

    public ArrayList<Club> Parseclub(String jsontext) throws IOException {
        clubs = new ArrayList<Club>();
        JSONParser j = new JSONParser();
        Map<String,Object> clubListJson = j.parseJSON(new CharArrayReader(jsontext.toCharArray()));
        List<Map<String,Object>> list = (List<Map<String, Object>>)clubListJson.get("root");

        for (Map<String,Object> obj : list){
            Club c = new Club();
           float id = Float.parseFloat((obj.get("id").toString()));
            c.setId((int)id);
            c.setName(obj.get("name").toString());
            c.setDescription(obj.get("description").toString());
            clubs.add(c);

        }

        return clubs;

    }

    public ArrayList<Club> getAllclubs(){
        String url="http://127.0.0.1:8000/dorra/webS/listeclub";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    clubs = Parseclub(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
return clubs;
    }

    public Boolean AddRank(int id,int rank, int idp, String comment){
        String url="http://127.0.0.1:8000/dorra/webS/addrank/"+id+"/"+rank+"/"+idp+"/"+comment;
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
