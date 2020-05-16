package Forms.AbonnementRespon;

import Entities.Jardin;
import Forms.Accueils.AccueilParent;
import Forms.Accueils.AccueilResponsable;
import Services.AbonnementService;
import Services.EnfantService;
import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.codename1.io.URL;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;


import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;

public class ModifierAbonnement extends Form {
    Form fe;
    TextArea messag=new TextArea();
    TextField textField=new TextField();
    public ModifierAbonnement(Form fo,String id,String name,String typ,String idj,String numparent){
         fe=this;
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
        Button mess=new Button("Envoyer Sms");
        mess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                    InteractionDialog dlg = new InteractionDialog("Envoyer SMS");

                Label numt=new Label("Numero destinataire:");
              //  TextArea messag=new TextArea();
                messag.setRows(8);
               // messag.setHeight(70);
                Label tex=new Label("Corps du message:");
                Container cont=new Container();
                cont.addAll(numt,textField,tex,messag);
                cont.setLayout(BoxLayout.y());

                dlg.setLayout(new BorderLayout());
                dlg.addComponent(BorderLayout.CENTER, cont);
                textField.setText(numparent);
                textField.setEnabled(false);
                Button envoy=new Button("Envoyer");
                Container con=new Container();

              //  dlg.addComponent(BorderLayout.SOUTH, envoy);
                Button close = new Button("Close");
                con.addAll(envoy,close);

                con.setLayout(BoxLayout.y());
                close.addActionListener((ee) -> dlg.dispose());
                envoy.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (messag.getText().length()==0){
                            Dialog.show("Warning","Veuillez ecrire un message",new Command("Ok"));

                        }
                        else{
                            String sm=sendSms();
                            Dialog.show("Warning",sm,new Command("Ok"));

                           new ModifierAbonnement(new ConsulterAbonnement(new AccueilResponsable()),id,name,typ,idj,numparent).show();
                            dlg.dispose();

                        }
                    }
                });
                dlg.addComponent(BorderLayout.SOUTH, con);
                Dimension pre = dlg.getContentPane().getPreferredSize();


                dlg.show(0, 0, 0, 0);
            }
        });


        addAll(na,tp,types,montn,mon,esp,mod,mess,supp);




    }

    public String sendSms() {
        try {
            // Construct data
            String apiKey = "apikey=" + URLEncoder.encode("KsQJ77GDRJM-MFZBIssO8l3EWhNftVKQtkXXfaj3Lo", "UTF-8");
            String message = "&message=" + URLEncoder.encode(messag.getText(), "UTF-8");
            String sender = "&sender=" + URLEncoder.encode("JardinEnfan", "UTF-8");
            String numbers = "&numbers=" + URLEncoder.encode("17192860595", "UTF-8");

            // Send data
            String data = "http://api.txtlocal.com/send/?" + apiKey + numbers + message + sender;
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
