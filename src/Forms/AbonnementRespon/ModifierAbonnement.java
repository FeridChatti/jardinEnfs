package Forms.AbonnementRespon;

import Entities.Jardin;
import Forms.Accueils.AccueilParent;
import Forms.Accueils.AccueilResponsable;
import Services.AbonnementService;
import Services.EnfantService;
import com.codename1.io.URL;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;

public class ModifierAbonnement extends Form {

    public ModifierAbonnement(Form fo,String id,String name,String typ,String idj){

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->fo.showBack());
        setTitle("Modifier Abonnement");
        setLayout(BoxLayout.y());
        TextArea na=new TextArea(name);
        na.setAlignment(TextArea.CENTER);
        na.setEnabled(false);

        Label tp=new Label("Type:");
        ComboBox types = new ComboBox();
        types.addItem("normal");
        types.addItem("bus");
        types.setSelectedItem(typ);
        Label montn= new Label ("Montant:");
        Label esp=new Label(" ");

        TextField mon=new TextField();
        ArrayList<Jardin> jar= EnfantService.getInstance().Montant(idj);

        if(types.getSelectedItem().toString().equals("normal")){

            mon.setText(String.valueOf(jar.get(0).getTarif()));

        }
        else if (types.getSelectedItem().toString().equals("bus")){

            mon.setText(String.valueOf(jar.get(0).getTarif()+50));
        }



        types.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(types.getSelectedItem().toString().equals("normal")){
                    for (int i=0;i< jar.size();i++){
                        mon.setText(String.valueOf(jar.get(0).getTarif()));
                    }
                }
                else if (types.getSelectedItem().toString().equals("bus")){

                    mon.setText(String.valueOf(jar.get(0).getTarif()+50));
                }
                else{
                    mon.setText(String.valueOf(jar.get(0).getTarif()+50));
                }
            }
        });
        Button mod=new Button("Modifier");
        mod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                AbonnementService.getInstance().ModifierAbonnement(id,types.getSelectedItem().toString(),mon.getText());
                Dialog.show("Succes","modification réussie",new Command("OK"));
                new Forms.AbonnementRespon.ConsulterAbonnement(new AccueilParent()).show();
            }
        });
        Button supp=new Button("Supprimer");
        supp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println(id);
                AbonnementService.getInstance().SupprimerAbonnement(id);
                Dialog.show("Succes","Suppression réussie",new Command("OK"));
                new ConsulterAbonnement(new AccueilResponsable()).show();
            }
        });


        addAll(na,tp,types,montn,mon,esp,mod,supp);




    }
    public String sendSms() {
        try {
            // Construct data
            String apiKey = "apikey=" + URLEncoder.encode("9mVey+4gZKw-fBiZPJTKI4fHw53uWcz3WcytFHzCNu", "UTF-8");
            String message = "&message=" + URLEncoder.encode("This is your message", "UTF-8");
            String sender = "&sender=" + URLEncoder.encode("Jardin", "UTF-8");
            String numbers = "&numbers=" + URLEncoder.encode("021654005783", "UTF-8");

            // Send data
            String data = "https://api.txtlocal.com/send/?" + apiKey + numbers + message + sender;
            URL url = new URL(data);
            URL.URLConnection conn = url.openConnection();
            conn.setDoOutput(true);

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            String sResult="";
            while ((line = rd.readLine()) != null) {
                // Process line...
                sResult=sResult+line+" ";
            }
            rd.close();

            return sResult;
        } catch (Exception e) {
            System.out.println("Error SMS "+e);
            return "Error "+e;
        }
    }


}
