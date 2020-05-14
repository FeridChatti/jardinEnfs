package Forms.Parent;

import Entities.Jardin;
import Forms.User.BaseForm;
import Services.ChatService;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.*;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.*;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import esprit.tn.MyApplication;

import java.io.IOException;
import java.util.List;

import static esprit.tn.MyApplication.authenticated;
import static esprit.tn.MyApplication.theme;

public class JardList extends BaseForm {
    Image imgg;
    EncodedImage enc ;
    Container cnt = new Container();
    Container cnt0 = new Container();

    Resources res= MyApplication.theme;
    public JardList(Form prev){

        initGuiBuilderComponents(theme);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());




        getToolbar().addCommandToRightBar("", theme.getImage("toolbar-profile-pic.png"), e -> {});




    }



    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initGuiBuilderComponents(Resources resourceObjectInstance) {



        setLayout(new BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("InboxForm");
        setName("InboxForm");

        List<Jardin> jarli=ChatService.getInstance().JardList();
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Inbox", "Title"),
                        new Label(String.valueOf(jarli.size()), "InboxNumber")
                )
        );

int i=0;
        for(Jardin j:jarli){



            Container firstcont=new Container(new BorderLayout());
            Container secondcont=new Container(new FlowLayout());
            Container cont3=new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container cont4=new Container(new FlowLayout());


            Label label_4=new Label();
            Label label_2=new Label();
            Label label_3=new Label();
            Label label_6=new Label();



            add(firstcont);
            firstcont.setName("Container_1"+i);

            firstcont.add( BorderLayout.EAST, secondcont);
            secondcont.setName("Container_2"+i);


            firstcont.add(BorderLayout.WEST, cont4);
            cont4.setName("Container_4"+i);
        ((FlowLayout)cont4.getLayout()).setAlign(Component.CENTER);
            cont4.add(label_4);
            label_4.setUIID("Padding2");
            label_4.setName("Label_4"+i);
            label_4.setIcon(resourceObjectInstance.getImage("label_round.png"));
            firstcont.addComponent(BorderLayout.CENTER, cont3);
            cont3.setName("Container_3"+i);
            cont3.addComponent(label_3);
            cont3.addComponent(label_2);


            label_3.setText(j.getName());
            label_3.setName("Label_3"+i);
            label_2.setText(j.getDescription());
            label_2.setUIID("RedLabel");
            label_2.setName("Label_2"+i);


            secondcont.setName("Container_2"+i);
            cont4.setName("Container_4"+i);
        ((FlowLayout)cont4.getLayout()).setAlign(Component.CENTER);
            cont3.setName("Container_3"+i);


            label_6.setText("");
            label_6.setUIID("Separator");
            label_6.setName("Label_6");

        addComponent(label_6);



            label_3.addPointerReleasedListener(evt -> {
                new Chat(this,j.getId(),j.getName()).show();
            });
            firstcont.setLeadComponent(label_3);
            firstcont.setUIID("JardListCont");

        i++;


        }









    }





}
