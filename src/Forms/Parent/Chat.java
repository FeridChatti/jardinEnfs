package Forms.Parent;

import Entities.Jardin;
import Entities.Messages;
import Services.ChatService;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.Preferences;
import com.codename1.ui.*;
import com.codename1.ui.animations.MorphTransition;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.SwipeBackSupport;
import com.codename1.ui.util.WeakHashMap;
import com.codename1.util.CaseInsensitiveOrder;
import esprit.tn.MyApplication;

import java.util.Arrays;
import java.util.List;

import static esprit.tn.MyApplication.authenticated;

public class Chat extends Form {
    private final WeakHashMap<String, EncodedImage> roundedImagesOfFriends = new WeakHashMap<>();
    public Resources theme = MyApplication.theme;
    public String parname;
    private Image roundedMeImage;
    private List<Jardin> contacts;


    public Chat(Form prev, int idjar, String jarname) {


        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> prev.showBack());
        Form chatForm = new Form(new BorderLayout());


        // this identifies the person we are chatting with, so an incoming message will know if this is the right person...
        chatForm.putClientProperty("cid", authenticated.getId());
       // chatForm.setLayout();
        chatForm.setScrollableY(true);

        final Container chatArea = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        chatArea.setScrollableY(true);
        chatArea.setName("ChatArea");


        // Provides the ability to swipe the screen to go back to the previous form
        SwipeBackSupport.bindBack(chatForm, (args) -> {
            return prev.getComponentForm();
        });

        // Gets a rounded version of our friends picture and caches it

        Container cont = new Container(BoxLayout.y());
        // load the stored messages and add them to the form
        List<Messages> messages = ChatService.getInstance().MessparList(String.valueOf(idjar));
        if (messages != null) {
            for (Messages m : messages) {
                parname = m.getSendername();


                if (m.getSender().getId() == authenticated.getId()) {
                  /* Container c=new Container(new BoxLayout(BoxLayout.X_AXIS));
                   SpanLabel ms=new SpanLabel(BorderLayout.EAST);
                   ms.setUIID("BubbleThem");
                   ms.setText(m.getMsg());
                   c.add(ms);
                    add(c);*/
                    Label dt = new Label(m.getDate());
                    dt.setUIID("SmallFontLabel");
                    chatArea.add(dt);
                    dt.setAlignment(Component.RIGHT);
                   cont.add(respondNoLayout(chatArea, m.getMsg())); cont.add(respondNoLayout(chatArea, m.getMsg())); cont.add(respondNoLayout(chatArea, m.getMsg()));

                } else {

                    Label dt = new Label(m.getDate());
                    dt.setUIID("SmallFontLabel");
                    dt.setAlignment(Component.LEFT);

                    chatArea.add(dt);
                    cont.add(sayNoLayout(chatArea, m.getMsg())); cont.add(sayNoLayout(chatArea, m.getMsg())); cont.add(sayNoLayout(chatArea, m.getMsg()));
                  /*  Container c=new Container(new BoxLayout(BoxLayout.X_AXIS));
                    SpanLabel ms=new SpanLabel(BorderLayout.WEST);
                    ms.setUIID("BubbleMe");
                    ms.setText(m.getMsg());
                    c.add(ms);
                    add(c);*/

                }

            }


        }
        TextField write = new TextField(30);
        write.setHint("Write to " + jarname);

        //chatForm.addComponent(BorderLayout.CENTER, chatArea);
       // chatForm.addComponent(BorderLayout.SOUTH, write);


        // the action listener for the text field creates a message object, converts it to JSON and publishes it to the listener queue
        write.addActionListener((e) -> {
            String text = write.getText();
            final Component t = say(chatArea, text);

            // we make outgoing messages translucent to indicate that they weren't received yet
            t.getUnselectedStyle().setOpacity(120);

            write.setText("");
            String mschat = ChatService.getInstance().sendmsg(text, idjar, authenticated.getId(), authenticated.getId());

        });

       // cont.addComponent(BorderLayout.SOUTH,write);

        chatForm.add( BorderLayout.CENTER,cont);
        add(chatForm);
       //addComponent();









       // chatForm.add(BorderLayout.CENTER, ic);
        //add( ic);


    }

    private Component say(Container chatArea, String text) {
        Component t = sayNoLayout(chatArea, text);
        t.setY(chatArea.getHeight());
        t.setWidth(chatArea.getWidth());
        t.setHeight(40);


        chatArea.animateLayoutAndWait(300);
        chatArea.scrollComponentToVisible(t);
        return t;
    }

    private Component sayNoLayout(Container chatArea, String text) {
        SpanLabel t = new SpanLabel(text);
        t.setIcon(roundedMeImage);
        t.setTextBlockAlign(Component.LEFT);
        t.setTextUIID("BubbleMe");
        t.setScrollableY(true);
      //  chatArea.addComponent(t);
        //chatArea.scrollComponentToVisible(t);
        return t;
    }

    private void respond(Container chatArea, String text, Image roundedHimOrHerImage) {
        Component answer = respondNoLayout(chatArea, text);
        answer.setX(chatArea.getWidth());
        answer.setWidth(chatArea.getWidth());
        answer.setHeight(40);

        chatArea.animateLayoutAndWait(300);
        chatArea.scrollComponentToVisible(answer);
    }

    private Component respondNoLayout(Container chatArea, String text) {
        SpanLabel answer = new SpanLabel(text);

        answer.setIconPosition(BorderLayout.EAST);
        answer.setTextUIID("BubbleThem");
        answer.setTextBlockAlign(Component.RIGHT);
        answer.setScrollableY(true);
     //   chatArea.addComponent(answer);
       // chatArea.scrollComponentToVisible(answer);
        return answer;
    }

}
