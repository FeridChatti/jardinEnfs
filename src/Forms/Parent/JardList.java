package Forms.Parent;

import Entities.Jardin;
import Entities.Parents;
import Forms.Responsable.Messagerie;
import Forms.User.BaseForm;
import Services.ChatService;
import Services.UserService;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import esprit.tn.MyApplication;

import java.util.List;

import static esprit.tn.MyApplication.authenticated;
import static esprit.tn.MyApplication.theme;

public class JardList extends BaseForm {


    Container cnt0 = new Container();

    Resources res= MyApplication.theme;
    public JardList(Form prev){

        initGuiBuilderComponents(theme);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->prev.showBack());

        //setTitle("InboxForm");


        getToolbar().addCommandToRightBar("", theme.getImage("toolbar-profile-pic.png"), e -> {});


        add(cnt0);



    }



    // <editor-fold defaultstate="collapsed" desc="Generated Code">


/*

    private void initGuiBuilderComponents(Resources resourceObjectInstance) {



        cnt0.setLayout(new BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));

        // setName("InboxForm");

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

            Label label_1=new Label();


            cnt0.add(firstcont);
            firstcont.setName("Container_1"+i);

            firstcont.add( BorderLayout.EAST, secondcont);
            secondcont.setName("Container_2"+i);


            firstcont.add(BorderLayout.WEST, cont4);
            cont4.setName("Container_4"+i);
            ((FlowLayout)cont4.getLayout()).setAlign(Component.CENTER);
            cont4.add(label_4);
            label_4.setUIID("Padding2");
            label_4.setName("Label_4"+i);
            label_4.setIcon(resourceObjectInstance.getImage("toolbar-profile-pic.png"));
            firstcont.addComponent(BorderLayout.CENTER, cont3);
            cont3.setName("Container_3"+i);
            cont3.addComponent(label_3);
            cont3.addComponent(label_2);


            label_3.setText(j.getName());
            label_3.setName("Label_3"+i);
            label_2.setText(j.getDescription());
            label_2.setUIID("RedLabel");
            label_2.setName("Label_2"+i);



            secondcont.add(label_1);

            label_1.setText(j.getDescription());
            label_1.setUIID("SmallFontLabel");
            label_1.setName("Label_1"+i);

            cont4.setName("Container_4"+i);
            ((FlowLayout)cont4.getLayout()).setAlign(Component.CENTER);
            cont3.setName("Container_3"+i);


            label_6.setText("");
            label_6.setUIID("Separator");
            label_6.setName("Label_6");

            cnt0.addComponent(label_6);



            label_3.addPointerReleasedListener(evt -> {
                new Chat(this,j.getId(),j.getName()) ;
            });
            firstcont.setLeadComponent(label_3);
            firstcont.setUIID("UserListCont");

            i++;


        } cnt0.setUIID("BackgroundForm");









    }

*/
private void initGuiBuilderComponents(Resources resourceObjectInstance) {



    cnt0.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

    // setName("InboxForm");

    List<Jardin> jarli=ChatService.getInstance().JardList();
  //  List<Parents> parlist= ChatService.getInstance().userlist(jardid.getId());

    getToolbar().setTitleComponent(
            FlowLayout.encloseCenterMiddle(
                    new Label("Inbox", "Title"),
                    new Label(String.valueOf(jarli.size()), "InboxNumber")
            )
    );
    int i=0;
    for(Jardin jar:jarli){



        Container firstcont=new Container(new BorderLayout());
        Container secondcont=new Container(new FlowLayout());
        Container cont3=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container cont4=new Container(new FlowLayout());


        Label label_4=new Label();
        Label label_2=new Label();
        Label label_3=new Label();
        Label label_6=new Label();

        Label label_1=new Label();


        cnt0.add(firstcont);
        firstcont.setName("Container_1"+i);

        firstcont.add( BorderLayout.EAST, secondcont);
        secondcont.setName("Container_2"+i);


        firstcont.add(BorderLayout.WEST, cont4);
        cont4.setName("Container_4"+i);
        ((FlowLayout)cont4.getLayout()).setAlign(Component.CENTER);
        cont4.add(label_4);
        label_4.setUIID("Padding2");
        label_4.setName("Label_4"+i);
        label_4.setIcon(resourceObjectInstance.getImage("toolbar-profile-pic.png"));
        firstcont.addComponent(BorderLayout.CENTER, cont3);
        cont3.setName("Container_3"+i);
        cont3.addComponent(label_3);
        cont3.addComponent(label_2);


        label_3.setText(jar.getName());
        label_3.setName("Label_3"+i);
        label_3.setUIID("paddingright");
        label_2.setText(jar.getDescription());
        label_2.setUIID("RedLabel");
        label_2.setName("Label_2"+i);


        secondcont.setName("Container_2"+i);
        secondcont.add(label_1);

        label_1.setText(jar.getDescription());
        label_1.setUIID("SmallFontLabel");
        label_1.setName("Label_1"+i);

        cont4.setName("Container_4"+i);
        ((FlowLayout)cont4.getLayout()).setAlign(Component.CENTER);
        cont3.setName("Container_3"+i);


        label_6.setText("");
        label_6.setUIID("Separator");
        label_6.setName("Label_6");

        cnt0.addComponent(label_6);



        label_3.addPointerReleasedListener(evt -> {
            new Chat(this,jar.getId(),jar.getName()) ; ;
        });
        firstcont.setLeadComponent(label_3);
        firstcont.setUIID("UserListCont");

        i++;
        firstcont.setPreferredW(this.getContentPane().getWidth());

    } cnt0.setUIID("BackgroundForm");









}
}
