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
    public Resources theme= MyApplication.theme;
    private Image roundedMeImage;
    private final WeakHashMap<String, EncodedImage> roundedImagesOfFriends = new WeakHashMap<>();

    public String parname;


    private List<Jardin> contacts;


    public Chat(Form prev,int id){

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> prev.showBack());
        Form chatForm = new Form();

        // this identifies the person we are chatting with, so an incoming message will know if this is the right person...
        chatForm.putClientProperty("cid", id);
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

        // Provides the ability to swipe the screen to go back to the previous form
        SwipeBackSupport.bindBack(chatForm, (args) -> {
            return prev.getComponentForm();
        });

        // Gets a rounded version of our friends picture and caches it


        // load the stored messages and add them to the form
        List<Messages> messages = ChatService.getInstance().MessparList(String.valueOf(id));
        if(messages != null) {
            for(Messages m : messages) {
                parname=m.getSendername();
                if(m.getSender().getId()==authenticated.getId()) {
                   Container c=new Container(new BoxLayout(BoxLayout.X_AXIS));
                   SpanLabel ms=new SpanLabel(BorderLayout.EAST);
                   ms.setUIID("BubbleThem");
                   ms.setText(m.getMsg());
                   c.add(ms);
                    add(c);
                } else {
                    Container c=new Container(new BoxLayout(BoxLayout.X_AXIS));
                    SpanLabel ms=new SpanLabel(BorderLayout.WEST);
                    ms.setUIID("BubbleMe");
                    ms.setText(m.getMsg());
                    c.add(ms);
                    add(c);

                }

            }
        }


    }




}
