package Services;


import Entities.Categorie;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategorieService {


    public static CategorieService instance=null;
    private ConnectionRequest req;
    public boolean resultOk;
    public ArrayList<Categorie> categories;

    private CategorieService(){
        req=new ConnectionRequest();
    }
    public static CategorieService getInstance(){
        if(instance==null){
            instance=new CategorieService();
        }
        return instance;
    }

    public ArrayList<Categorie> Parsecategorie(String jsontext) throws IOException {
        categories = new ArrayList();
        JSONParser j = new JSONParser();
        Map<String,Object> categorielisteJson = j.parseJSON(new CharArrayReader(jsontext.toCharArray()));
        List<Map<String,Object>> list = (List<Map<String, Object>>)categorielisteJson.get("root");

        for (Map<String,Object> obj : list){
            Categorie c = new Categorie();
            float id = Float.parseFloat((obj.get("id").toString()));
            c.setId((int)id);
            c.setLibelle(obj.get("libelle").toString());
            categories.add(c);

        }

        return categories;

    }

    public ArrayList<Categorie> getAllcategories(){
        String url="http://127.0.0.1:8000/eveapi/Api/categories";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    categories = Parsecategorie(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categories;
    }
}
