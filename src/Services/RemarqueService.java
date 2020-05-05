package Services;

import Entities.Chauffeur;
import Entities.Enfant;
import Entities.Remarque;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static esprit.tn.MyApplication.authenticated;

public class RemarqueService {

    public static RemarqueService instance;
    public ArrayList <Remarque> remarques;

    private String json;
    private ConnectionRequest req;
    public boolean resultOk;


    public RemarqueService(){
        req=new ConnectionRequest();
    }

    public static RemarqueService getInstance(){

        if(instance==null){
            instance=new RemarqueService();
        }
        return instance;
    }

    public ArrayList<Remarque> mesremarques(){
        String Url="http://127.0.0.1:8000/Api/listrem/"+authenticated.getId();
        req.setUrl(Url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    remarques=ParseRemarques(new String (req.getResponseData()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);


        return remarques;
    }

    public ArrayList<Remarque> tutremarques(){
        String Url="http://127.0.0.1:8000/Api/listmyrem/"+authenticated.getId();
        req.setUrl(Url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    remarques=ParseMyRemarques(new String (req.getResponseData()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);


        return remarques;
    }



    public  ArrayList<Remarque> ParseRemarques(String json) throws ParseException {
        remarques=new ArrayList<>();
        JSONParser j= new JSONParser();
        Map<String,Object> ListremarktJson= null;
        try {
            ListremarktJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Map<String,Object>> list=(List <Map<String,Object>>) ListremarktJson.get("root");
        for(Map<String,Object> obj:list){
            Remarque e =new Remarque();
            float t= Float.parseFloat(obj.get("id").toString());
            e.setId((int)t);
            Map<String,Object> m = (Map<String, Object>) obj.get("date");

            String str = m.get("date").toString();


            float idab = Float.parseFloat((obj.get("ab_id").toString()));
            String g = str.substring(0,10);
            e.setDate(str);
            e.setAbid((int)idab);
            e.setDescription(obj.get("description").toString());
            e.setEnfant(obj.get("enfantnom").toString()+" "+obj.get("enfantprenom").toString());
            e.setNomtut(obj.get("tutnom").toString()+" "+obj.get("tutprenom").toString());



            remarques.add(e);
        }
        return remarques;
    }

    public  ArrayList<Remarque> ParseMyRemarques(String json) throws ParseException {
        remarques=new ArrayList<>();
        JSONParser j= new JSONParser();
        Map<String,Object> ListremarktJson= null;
        try {
            ListremarktJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Map<String,Object>> list=(List <Map<String,Object>>) ListremarktJson.get("root");
        for(Map<String,Object> obj:list){
            Remarque e =new Remarque();
            float t= Float.parseFloat(obj.get("id").toString());
            e.setId((int)t);
            Map<String,Object> m = (Map<String, Object>) obj.get("date");

            String str = m.get("date").toString();


            float idab = Float.parseFloat((obj.get("ab_id").toString()));
            String g = str.substring(0,10);
            e.setDate(str);
            e.setAbid((int)idab);
            e.setDescription(obj.get("description").toString());
            e.setEnfant(obj.get("enfantnom").toString()+" "+obj.get("enfantprenom").toString());
            e.setNomtut(obj.get("parnom").toString()+" "+obj.get("parprenom").toString());



            remarques.add(e);
        }
        return remarques;
    }




    public Boolean ajouterremarques(int tutid,int abid,String desc){
        String url="http://127.0.0.1:8000/Api/addrem";
        req.setUrl(url);
        req.addArgument("tut", String.valueOf(tutid));
        req.addArgument("abo", String.valueOf(abid));
        req.addArgument("descr", desc);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk=new String (req.getResponseData()).contains("success");
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }




    public ArrayList<Enfant> enfants=new ArrayList<>();

    public ArrayList<Enfant> ListEnfants (String idjar){



        String Url="http://127.0.0.1:8000/webservices/listeenfjardin/"+idjar;
        req.setUrl(Url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                enfants=EnfantService.getInstance().ParseEnfant(new String (req.getResponseData()));
                req.removeResponseListener(this);


            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);


        return enfants;
    }







}
