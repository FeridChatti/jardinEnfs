package Forms.Parent;

import Entities.Parents;
import Services.ParentService;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

import static esprit.tn.MyApplication.authenticated;

public class Editprofile extends Form {
    Parents p = new Parents();
    Boolean fl = true;

    public Editprofile(Form prev) {
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> prev.showBack());
        setTitle("Modifier Votre Profile");
        setLayout(BoxLayout.y());


        p = ParentService.getInstance().getparent(authenticated.getId());

        Label nomlbl = new Label("Nom");
        TextField nomtf = new TextField(p.getNom());


        Label prenomlbl = new Label("Prenom");
        TextField prenomtf = new TextField(p.getPrenom());


        Label usernamelbl = new Label("username");
        TextField usernametf = new TextField(p.getUsername());


        Label emaillbl = new Label("email");
        TextField emailtf = new TextField(p.getEmail());


        Label numtellbl = new Label("NumTel");
        TextField numteltf = new TextField(p.getNumtel());


        Label adresselbl = new Label("Adresse");
        TextField adressetf = new TextField(p.getAdresse());


        Label passwordlbl = new Label("Password");
        TextField passwordtf = new TextField();
        passwordtf.setConstraint(TextField.PASSWORD);
        Button modifier = new Button("Modifier");

        modifier.addActionListener(e -> {

        while (true){


            if (nomtf.getText().matches("[a-zA-Z]*") && nomtf.getText().length() != 0) {

                p.setNom(nomtf.getText());


            } else {
                Dialog.show("Erreur", "le champ nom invalide", new Command("OK"));
                fl = false;
                break;
            }
            if (prenomtf.getText().matches("[a-zA-Z]*") && prenomtf.getText().length() != 0) {

                p.setPrenom(prenomtf.getText());


            } else {
                Dialog.show("Erreur", "le champ prenom invalide", new Command("OK"));
                fl = false;break;
            }

            if (!usernametf.getText().equals(authenticated.getUsername()) || !emailtf.getText().equals(authenticated.getEmail())) {


                if (!usernametf.getText().equals(authenticated.getUsername())) {

                    if (usernametf.getText().matches("[a-zA-Z0-9]*") && usernametf.getText().length() != 0) {

                        if (!ParentService.getInstance().checkusername(usernametf.getText())) {
                            p.setUsername(usernametf.getText());

                        } else {
                            Dialog.show("Erreur", "username  exit", new Command("OK"));
                            fl = false;break;

                        }


                    } else {
                        Dialog.show("Erreur", "le champ username invalide", new Command("OK"));
                        fl = false;break;
                    }
                }

                if (!emailtf.getText().equals(authenticated.getEmail())) {


                    if (emailtf.getText().matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}")
                            && emailtf.getText().length() != 0) {

                        if (!ParentService.getInstance().checkuseremail(emailtf.getText())) {
                            p.setEmail(emailtf.getText());


                        } else {
                            Dialog.show("Erreur", "email  exit", new Command("OK"));
                            fl = false;break;

                        }
                    } else {
                        Dialog.show("Erreur", "le champ email invalide", new Command("OK"));
                        fl = false;break;
                    }


                }
            }

            if (numteltf.getText().matches("[0-9]*") && numteltf.getText().length() == 8) {

                p.setNumtel(numteltf.getText());


            } else {
                Dialog.show("Erreur", "le champ numtel invalide", new Command("OK"));
                fl = false;break;
            }
            if (adressetf.getText().matches("^[a-zA-Z0-9]+$") && adressetf.getText().length() != 0) {

                p.setAdresse(adressetf.getText());


            } else {
                Dialog.show("Erreur", "le champ adresse invalide", new Command("OK"));
                fl = false;break;
            }

            if (passwordtf.getText().matches("^[a-zA-Z0-9]+$") && passwordtf.getText().length() >= 5) {

                p.setPassword(passwordtf.getText());


            } else if (passwordtf.getText().length() > 0) {
                Dialog.show("Erreur", "le champ password invalide doit etre >=5 caractere", new Command("OK"));
                fl = false;break;
            }

            if (fl) {
                Boolean flag = ParentService.getInstance().Updateparent(p.getUsername(), p.getEmail(), p.getPassword(), p.getNom(), p.getPrenom(), p.getNumtel(), p.getAdresse());
                if (flag) {
                    Dialog.show("Succes", "Votre profile a été modfifié avec succés", new Command("OK"));

                    prev.showBack();
                    break;
                }
            }else{break;}
        }
        });


        addAll(nomlbl, nomtf, prenomlbl, prenomtf, usernamelbl, usernametf, emaillbl, emailtf, numtellbl, numteltf, adresselbl, adressetf, passwordlbl, passwordtf, modifier);


    }


}
