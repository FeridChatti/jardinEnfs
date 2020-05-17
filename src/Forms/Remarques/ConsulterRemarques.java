package Forms.Remarques;

import Entities.Jardin;
import Entities.Parents;
import Entities.Remarque;
import Forms.Parent.Chat;
import Forms.Responsable.Messagerie;
import Services.ChatService;
import Services.RemarqueService;
import Services.UserService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import esprit.tn.MyApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static esprit.tn.MyApplication.authenticated;
import static esprit.tn.MyApplication.theme;

public class ConsulterRemarques extends Form {
    Resources res = MyApplication.theme;
    Container form = new Container();
    public ConsulterRemarques(Form prev) {
        setTitle("La Liste des remarques");


        form.setUIID("BackgroundForm");

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> prev.showBack());

        initGuiBuilderComponents(theme);
        add(form);
        show();

    }


    private void initGuiBuilderComponents(Resources resourceObjectInstance) {


        form.setLayout(new BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));



        ArrayList<Remarque> rmk = RemarqueService.getInstance().mesremarques();
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

            firstcont. addComponent(BorderLayout.SOUTH,label_6);


            firstcont.setUIID("UserListCont");

            i++;


        }


    }

}
