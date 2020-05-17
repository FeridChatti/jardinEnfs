package Forms.Remarques;

import Entities.Remarque;
import Entities.Trajet;
import Services.RemarqueService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.util.Resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static esprit.tn.MyApplication.theme;

public class TuteurRemarques extends Form {
    Container form = new Container();
    public TuteurRemarques (Form prev){


        initGuiBuilderComponents(theme);
        //form.setTitle("La Liste des remarques");

       // Container detail = new Container(BoxLayout.y());
        //add(detail);

      //  form.setLayout(BoxLayout.y());
       // ArrayList<Remarque> rmk= RemarqueService.getInstance().tutremarques();
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());

        setTitle("Remarques des enfant");

        form.setUIID("BackgroundForm");
        //form.show();
        //form.getToolbar().setUIID("Container");

        add(form);
        show();







    }
    private void initGuiBuilderComponents(Resources resourceObjectInstance) {


        form.setLayout(new BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));

        //form.setName("remarques");


        ArrayList<Remarque> rmk= RemarqueService.getInstance().tutremarques();
        Collections.reverse(rmk);


        int i = 0;
        for (Remarque rem : rmk) {


            Container firstcont = new Container(new BorderLayout());
            Container secondcont = new Container(new FlowLayout());
            Container cont3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container cont4 = new Container(new FlowLayout());


            Label label_4 = new Label();
            Label label_2 = new Label();
            Label label_3 = new Label();
            Label label_6 = new Label();

            Label label_1 = new Label();

            TextArea text_area = new TextArea();
            text_area.setRows(3);
            text_area.setColumns(100);
            text_area.setEditable(false);


            form.add(firstcont);
            firstcont.setName("Container_1" + i);

            firstcont.add(BorderLayout.EAST, secondcont);
            secondcont.setName("Container_2" + i);


            firstcont.add(BorderLayout.WEST, cont4);
            cont4.setName("Container_4" + i);
            ((FlowLayout) cont4.getLayout()).setAlign(Component.CENTER);
            cont4.add(label_4);
            label_4.setUIID("Padding2");
            label_4.setName("Label_4" + i);
            label_4.setIcon(resourceObjectInstance.getImage("label_round-selected.png"));
            firstcont.addComponent(BorderLayout.CENTER, cont3);
            cont3.setName("Container_3" + i);
            cont3.addComponent(label_3);
            cont3.addComponent(label_2);


            label_3.setText(rem.getNomtut());
            label_3.setName("Label_3" + i);
            label_2.setText(rem.getEnfant());
            label_2.setUIID("RedLabel");
            label_2.setName("Label_2" + i);


            secondcont.setName("Container_2" + i);
            secondcont.add(label_1);
            label_1.setText(rem.getDate());
            label_1.setUIID("SmallFontLabel");
            label_1.setName("Label_1" + i);
            cont4.setName("Container_4" + i);
            ((FlowLayout) cont4.getLayout()).setAlign(Component.CENTER);
            cont3.setName("Container_3" + i);
            cont3.add(text_area);
            text_area.setText(rem.getDescription());
            text_area.setUIID("SmallFontLabel");

            label_6.setText("");
            label_6.setUIID("Separator");
            label_6.setName("Label_6");

            form. addComponent(label_6);


            firstcont.setUIID("UserListCont");

            i++;


        }


    }

}
