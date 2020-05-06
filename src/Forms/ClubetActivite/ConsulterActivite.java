package Forms.ClubetActivite;

import Entities.Activite;
import Services.ActiviteService;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConsulterActivite extends Form {




    public ConsulterActivite(Form prev) {

        Form fo = this;
        setLayout(BoxLayout.y());
        setTitle("Liste des Activités");

/*
        Label choix  = new Label("veuillez choisir l'activité");


        Container detail = new Container(BoxLayout.y());
        add(detail);
        detail.add(choix);
*/
        ArrayList<Activite> l = ActiviteService.getInstance().getAllActivites();
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        for (Activite act : l) {
            data.add(createListEntry(act.getTypeact(), act.getDate(),String.valueOf(act.getId())));
        }
        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
        MultiList ml = new MultiList(model);
        fo.add(ml);
        fo.show();
        ml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Map<String, Object> t = (HashMap) ml.getSelectedItem();
                String tr = (String) t.get("Line1");
                if (Dialog.show("Participer à cette activité", "Vous avez choisi :" + t.get("Line1"), "Oui", "Non")) {
                    new ParticiperActivite(fo, String.valueOf((String)t.get("id"))).show();
                }
            }
        });

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> prev.showBack());

       /* for(int i = 0; i< l.size(); i++){


            String id = String.valueOf(l.get(i).getId());
            Label lbID = new Label(id);
            lbID.setHidden(true);
            Label lbnom = new Label("Nom :");
            Label lbName = new Label(l.get(i).getTypeact());
            //Label lbDescription = new Label(ActiviteService.getInstance().getAllActivites().get(i).getDetailles());

            Label lbdate = new Label("Date :");
            Label date = new Label(l.get(i).getDate());

            lbName.addPointerReleasedListener(e->{
              if(  Dialog.show("Participer à cette activité","Vous avez choisi :"+lbName.getText(),"Oui","Non")){
                   new ParticiperActivite(fo,id).show();
              }
                });

           detail.add(lbnom);

            detail.add(lbName);
            //detail.add(lbDescription);
            detail.add(lbdate);
            detail.add(date);

           // detail.setLeadComponent(lbName);

*/
    }


    private Map<String, Object> createListEntry(String typeact, String date, String id) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", typeact);
        entry.put("Line2", date);
        entry.put("id",id);
        return entry;
    }










}
