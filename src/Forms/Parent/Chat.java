package Forms.Parent;

import Entities.Jardin;
import Services.ChatService;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.io.Preferences;
import com.codename1.ui.*;
import com.codename1.ui.animations.MorphTransition;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.util.CaseInsensitiveOrder;
import esprit.tn.MyApplication;

import java.util.Arrays;
import java.util.List;

public class Chat extends Form {
    public Resources theme= MyApplication.theme;
    private String fullName;
    private String uniqueId;
    private String imageURL;
    private static EncodedImage userPlaceholder;
    private EncodedImage roundPlaceholder;
    private Image mask;
    private List<Jardin> contacts;


    public Chat(Form preb){

        Style iconFontStyle = UIManager.getInstance().getComponentStyle("LargeIconFont");
        iconFontStyle.setBgTransparency(255);
        FontImage fnt = FontImage.create(" \ue80f ", iconFontStyle);
        userPlaceholder = fnt.toEncodedImage();
        mask = theme.getImage("rounded-mask.png");
        roundPlaceholder =EncodedImage.createFromImage(userPlaceholder.scaled(mask.getWidth(), mask.getHeight()).applyMask(mask.createMask()), false);
        fullName = Preferences.get("fullName", null);
        uniqueId = Preferences.get("uniqueId", null);
        imageURL = Preferences.get("imageURL", null);



        Form contactsForm = new Form("Contacts");
        contactsForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        // the toolbar is created into a layer on the content pane. This allows us to render behind it and leave it semi transparent
        Toolbar tb = new Toolbar(true);

        // we want the title area to be transparent so it won't get in the way
        contactsForm.getTitleArea().setUIID("Container");

        // folds the toolbar automatically as we scroll down, shows it if we scroll back up
        tb.setScrollOffUponContentPane(true);
        contactsForm.setToolBar(tb);

        // we want the image behind the toolbar to stretch and fit the entire screen and leave no margin
        Label titleLabel = new Label(" ");
        Style titleLabelStyle = titleLabel.getUnselectedStyle();
        titleLabelStyle.setBgImage(theme.getImage("social-chat-tutorial-image-top.jpg"));
        titleLabelStyle.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        titleLabelStyle.setPadding(tb.getPreferredH(), tb.getPreferredH(), tb.getPreferredH(), tb.getPreferredH());
        titleLabelStyle.setPaddingUnit(Style.UNIT_TYPE_PIXELS, Style.UNIT_TYPE_PIXELS, Style.UNIT_TYPE_PIXELS, Style.UNIT_TYPE_PIXELS);
        titleLabelStyle.setMargin(0, 0, 0, 0);

        contactsForm.addComponent(titleLabel);

        // the behavior of the title is rather complex so we extract it to a separate method
        tb.setTitleComponent(createTitleComponent(contactsForm));

        InfiniteProgress ip = new InfiniteProgress();
        contactsForm.addComponent(ip);

        loadContacts(ChatService.getInstance().JardList(), ip, contactsForm.getContentPane());

        // creates the morph and other animations from the main form to the second form of the app
        createMorphEffect(titleLabel);

        contactsForm.show();
    }



    private MultiButton createContactComponent(Jardin d) {
        MultiButton mb = new MultiButton();
        mb.putClientProperty("uid", d.getId());
        mb.setTextLine1(d.getName());


        mb.setIcon(URLImage.createToStorage(userPlaceholder, "userPic" ,"alexandra.jpg" , URLImage.RESIZE_SCALE_TO_FILL));



      /*  mb.addActionListener((e) -> {
            showChatForm(d, mb);
        });*/
        return mb;
    }



    private Component createTitleComponent(Form parent) {
        // we want the toolbar to be completely transparent, since we created it on the layered pane (using the true
        // argument in the constructor) it will flow in the UI
        parent.getToolbar().setUIID("Container");

        // we create 3 layers within the title, the region contains all the layers, the encspsulate includes the "me image"
        // which we want to protrude under the title area layer
        Container titleRegion = new Container(new LayeredLayout());
        Container titleEncapsulate = new Container(new BorderLayout());
        Container titleArea = new Container(new BorderLayout());

        // since the Toolbar is now transparent we assign the title area UIID to one of the layers within and the look
        // is preserved, we make it translucent though so we can see what's underneath
        titleArea.setUIID("TitleArea");
        titleArea.getUnselectedStyle().setBgTransparency(128);

        // We customize the title completely using a component, the "title" is just a label with the Title UIID
        Label title = new Label(parent.getTitle());
        title.setUIID("Title");
        titleArea.addComponent(BorderLayout.CENTER, title);


        // we package everything in a container so we can replace the title area with a search button as needed
        Container cnt = new Container(new BoxLayout(BoxLayout.X_AXIS));
        titleArea.addComponent(BorderLayout.EAST, cnt);


        // this is the Me picture that protrudes downwards. We use a placeholder which is then replace by the URLImage
        // with the actual image. Notice that createMaskAdapter allows us to not just scale the image but also apply
        // a mask to it...
        Label me = new Label(URLImage.createToStorage(roundPlaceholder, "userImage", "alexandra.jpg", URLImage.createMaskAdapter(mask)));
        me.setUIID("UserImage");

        // the search icon and the "me" image are on two separate layers so we use a "dummy" component that we
        // place in the search container to space it to the side and leave room for the "me" image
        Label spacer = new Label(" ");
        Container.setSameWidth(spacer, me);
        cnt.addComponent(spacer);

        Container iconLayer = new Container(new BorderLayout());
        titleEncapsulate.addComponent(BorderLayout.NORTH, titleArea);

        titleRegion.addComponent(titleEncapsulate);
        titleRegion.addComponent(iconLayer);
        iconLayer.addComponent(BorderLayout.EAST, me);

        return titleRegion;
    }
    private void createMorphEffect(Label titleLabel) {
        // animate the components out of the previous form if we are coming in from the login form
        Form parent = Display.getInstance().getCurrent();
        if(parent.getUIID().equals("MainForm")) {
            for(Component cmp : parent.getContentPane()) {
                cmp.setX(parent.getWidth());
            }

            // moves all the components outside of the content pane to the right while fading them out over 400ms
            parent.getContentPane().animateUnlayoutAndWait(400, 0);
            parent.getContentPane().removeAll();

            // we can't mutate a form into a component in another form so we need to convert the background to an image and then morph that
            // this is especially easy since we already removed all the components
            Label dummy = new Label();
            dummy.setShowEvenIfBlank(true);
            dummy.setUIID("Container");
            dummy.setUnselectedStyle(new Style(parent.getUnselectedStyle()));
            parent.setUIID("Form");

            // special case to remove status bar on iOS 7
            parent.getTitleArea().removeAll();
            parent.setLayout(new BorderLayout());
            parent.addComponent(BorderLayout.CENTER, dummy);
            parent.revalidate();

            // animate the main panel to the new location at the top title area of the screen
            dummy.setName("fullScreen");
            titleLabel.setName("titleImage");
            parent.setTransitionOutAnimator(MorphTransition.create(1100).morph("fullScreen", "titleImage"));
        }
    }

    private void loadContacts(List<Jardin> data, InfiniteProgress ip, Container contactsContainer) {
        // we sort the contacts by name which is pretty concise code thanks to Java 8 lambdas
        Display.getInstance().scheduleBackgroundTask(() -> {
            contacts = data ;
            CaseInsensitiveOrder co = new CaseInsensitiveOrder();
            contacts.sort((Jardin o1, Jardin o2) -> {
                return co.compare(o1.getName(), o2.getName());
            });

            Display.getInstance().callSerially(() -> {


                contactsContainer.removeComponent(ip);

                for(Jardin d : contacts) {
                    contactsContainer.addComponent(createContactComponent(d));
                }
                contactsContainer.revalidate();
            });
        });
    }

}
