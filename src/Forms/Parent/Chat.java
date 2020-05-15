package Forms.Parent;

import Entities.Jardin;
import Entities.Messages;
import Services.ChatService;
import com.codename1.components.FloatingActionButton;
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
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.SwipeBackSupport;
import com.codename1.ui.util.WeakHashMap;
import com.codename1.util.CaseInsensitiveOrder;
import esprit.tn.MyApplication;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static esprit.tn.MyApplication.authenticated;

public class Chat extends Form {
    private final WeakHashMap<String, EncodedImage> roundedImagesOfFriends = new WeakHashMap<>();
    public Resources theme = MyApplication.theme;
    public String parname;
    private Image roundedMeImage;
    private List<Jardin> contacts;


    public Chat(Form prev, int idjar, String jarname) {


        Form chatForm = new Form(jarname);

        // this identifies the person we are chatting with, so an incoming message will know if this is the right person...
        chatForm.putClientProperty("cid", jarname);
        chatForm.setLayout(new BorderLayout());
        Toolbar tb = new Toolbar();
        final Container chatArea = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        chatArea.setScrollableY(true);
        chatArea.setName("ChatArea");
        chatForm.setToolBar(tb);

        chatForm.setBackCommand(new Command("Contacts") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                prev.getComponentForm().showBack();
            }
        });
        SwipeBackSupport.bindBack(chatForm, (args) -> {
            return prev.getComponentForm();
        });
        // Gets a rounded version of our friends picture and caches it

       // Container cont = new Container(BoxLayout.y());
        // load the stored messages and add them to the form
        List<Messages> messages = ChatService.getInstance().MessparList(String.valueOf(authenticated.getId()),String.valueOf(idjar));
        if (messages != null) {
            for (Messages m : messages) {
                parname = m.getSendername();


                if (m.getSender().getId() == authenticated.getId()) {

                    Label dt = new Label(m.getDate());
                    dt.setUIID("SmallFontLabel");
                    chatArea.add(dt);
                    dt.setAlignment(Component.RIGHT);
                 //  cont.add(respondNoLayout(chatArea, m.getMsg())); cont.add(respondNoLayout(chatArea, m.getMsg())); cont.add(respondNoLayout(chatArea, m.getMsg()));
                    respondNoLayout(chatArea, m.getMsg());
                    //respondNoLayout(chatArea, m.getMsg());respondNoLayout(chatArea, m.getMsg());
                } else {

                    Label dt = new Label(m.getDate());
                    dt.setUIID("SmallFontLabel");
                    dt.setAlignment(Component.LEFT);

                    chatArea.add(dt);
                    sayNoLayout(chatArea, m.getMsg());
                    //sayNoLayout(chatArea, m.getMsg()); sayNoLayout(chatArea, m.getMsg());


                }

            }


        }
        TextField write = new TextField(30);
        write.setHint("Write to " + jarname);


        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_SEND);

        fab.setUIID("SendButton");



        Container cnt = BorderLayout.centerEastWest(write, fab, null);



        write.getAllStyles().setBorder(Border.createEmpty());



        Style msgStyle = write.getAllStyles();
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        msgStyle.setBorder(RoundBorder.create().
                rectangle(true).
                color(0xffffff).
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        msgStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        msgStyle.setMargin(Component.BOTTOM, 3);











        chatForm.addComponent(BorderLayout.CENTER, chatArea);
        chatForm.addComponent(BorderLayout.SOUTH, cnt);
        // the action listener for the text field creates a message object, converts it to JSON and publishes it to the listener queue
        fab.addActionListener((e) -> {

            String text = write.getText();
            if(!text.isEmpty()){
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                Label dt = new Label(dtf.format(now));
                dt.setUIID("SmallFontLabel");
                dt.setAlignment(Component.RIGHT);
                chatArea.add(dt);
                final Component t = respond(chatArea, text);

                // we make outgoing messages translucent to indicate that they weren't received yet
                t.getUnselectedStyle().setOpacity(120);
                write.setText("");

                //final Message messageObject = new Message(tokenPrefix + uniqueId, tokenPrefix + d.uniqueId, imageURL, fullName, text);
                // JSONObject obj = messageObject.toJSON();


                String flag=ChatService.getInstance().sendmsg(text,idjar,authenticated.getId(),authenticated.getId());

                if(flag.contains("true")) {

                    // a message was received, we make it opauqe and add it to the storage
                    t.getUnselectedStyle().setOpacity(255);
                    chatArea.revalidate();

                }else{
                    chatArea.removeComponent(t);
                    chatArea.revalidate();
                    Dialog.show("Error", "Connection error message wasn't sent", "OK", null);

                }
            }

            });








        chatForm.show();






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
        chatArea.addComponent(t);
        //chatArea.scrollComponentToVisible(t);
        return t;
    }

    private Component respond(Container chatArea, String text ) {
        Component answer = respondNoLayout(chatArea, text);
        answer.setX(chatArea.getWidth());
        answer.setWidth(chatArea.getWidth());
        answer.setHeight(40);

        chatArea.animateLayoutAndWait(300);
        chatArea.scrollComponentToVisible(answer);
        return answer;
    }

    private Component respondNoLayout(Container chatArea, String text) {
        SpanLabel answer = new SpanLabel(text);

        answer.setIconPosition(BorderLayout.EAST);
        answer.setTextUIID("BubbleThem");
        answer.setTextBlockAlign(Component.RIGHT);
        answer.setScrollableY(true);
       chatArea.addComponent(answer);
       // chatArea.scrollComponentToVisible(answer);
        return answer;
    }

}
