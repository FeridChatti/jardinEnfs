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


    public Chat(Form prev){
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> prev.showBack());

    }



}
