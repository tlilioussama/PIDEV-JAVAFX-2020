package tn.covid19.gui;
import java.awt.Color;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tn.covid19.entities.Utilisateur;
import tn.covid19.service.impl.ServiceUtilisateur;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.media.MediaPlayer.Status.PLAYING;
import tn.covid19.entities.FicheMalade;
import tn.covid19.entities.GMapCoordinates;
import tn.covid19.entities.Laboratoire;
import tn.covid19.service.impl.ServiceFicheMalade;
import tn.covid19.service.impl.ServiceGmap;
import tn.covid19.service.impl.ServiceLaboratoire;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import tn.covid19.utils.MyDB;
import tn.covid19.utils.Session;


public class ApplicationFXMLController implements Initializable {
    private Connection cnx;

    @FXML
    private TextField tfNomInscrie, tfPrenomInscrie, tfCINInscrie, tfTelephoneInscrie, tfAdresseInscrie, 
                      tfAgeInscrie, tfEmailInscrie, tfLoginInscrie, tfMotDePasseInscrie,
                      tfConnetLogin, tfConnectMDP2, tfEmailEnvoyer, 
                      tfNomAfficher, tfPrenomAfficher, tfCINAfficher, tfAdresseAfficher, tfVilleAfficher, tfAgeAfficher, 
                      tfTelephoneAfficher, tfEmailAfficher, tfLoginAfficher, tfMDPAfficher,
                      tfLoginAgentLabo, tfMDPAgentLabo,nomMalade,prenommalade,agemalade,villemalade,etatmalade,tfCinMod,
                      tfNom, tfPrenom, tfAge, tfCin,tfCinSUPPPMalade, tfLoginDocteur, tfMDPDocteur;
    @FXML
    private Button    btnMonCompte,btnModifier,btnAfficher, btnAccueil, btnInscrire, btnConnecte, btnEnregistrerInscrie,
                      btnConnectConfirmer,btnLogMDPoublier, btnDemandeAide, btnFaireDon, btnAutorisation,
                      btnAgentLabo, btnDocteur, btnEnvoyerEmail, btnAide, btnModifierConfirmer, btnAjouterFicheMalade, 
                      btnModifierFicheMalade, btnSupprimerFicheMalade, btnConnecterAgentLabo,btnSupprimerMalade,btnRechercherFiche, 
                      btnSeDeconnecter, btnajouter,btnmodifier,btnSupressionFinale, btnVideo, btnConnecterDocteur,
                      btnAjouterPatient, btnModifierPatient, btnSupprimerPatient, btnAfficherListePatient, btnRechercherPatientMod,
                      btnPlayVideo, btnStopVideo, btnPauseVideo;         
    @FXML
    private Pane      panePrincipale, paneLogMDPoublier,paneCompteMenu, paneModifier,paneAfficher, paneMenu, paneMenuPrincipale,
                      paneAccueil, paneSinscrire, paneSeConnecter, paneFaireDon,paneDocteur, paneAgentLabo, paneAutorisation, 
                      paneDemandeAide, paneEmail, paneVideo, paneAide, paneFicheMaladi, paneSupprimerMalade, paneSupprimerMaladeConfirmer,
                      paneModifierMaladeConfirmer, paneAjouterMalade ,paneModifierMalade, panaMalade, paneAjouterPatient, paneRechercherPatientMod,
                      paneModifierPatientConfirmer;  
    @FXML
    private Label     labelAfficherNom, labelAfficherPrenom, labelAfficherCIN,labelAfficherAdresse, labelAfficherVille,
                      labelAfficherAge,labelAfficherTelephone, labelAfficherEmail, labelAfficherLogin, labelAfficherMDP,
                      labelErreurInscrie, labelConnectErreur, labelCompte, lbErreurEmailEnvoyer, labelErreurAgentLabo,labelNomSupMalade,
                      labelPrenomSupMalade,labelAgeSupMalade,labelVilleSupMalade,labelEtatSupMalade, labelErreurDocteur; 
    @FXML
    private MediaView mediaView;
    MediaPlayer mediaPlayer;    
    @FXML
    private Slider sliderVolume, sliderTempsVideo;
    @FXML
    private ComboBox<String> combichoixlabo,comboville, comboVilleInscrit;
    @FXML
    private CheckBox cbpos, cbneg; 
    
    //**************************************** Contole de saisie *******************************************************//
    @FXML
    public boolean alertVide() {
        if(tfConnetLogin.getText().isEmpty() | tfConnectMDP2.getText().isEmpty()){
                       Alert alert = new Alert(Alert.AlertType.ERROR);
                       alert.setTitle("Login ou mot de passe vide");
                       String text = "ERREUR: \n   Veullez entrer votre login et mot de passe !";
                       alert.setHeaderText(text);
                       alert.show();
          return false;
    }
        return true; 
    }
    public boolean alertInscriptionVide() {
      if(tfNomInscrie.getText().isEmpty() | tfPrenomInscrie.getText().isEmpty() | tfCINInscrie.getText().isEmpty()  | tfTelephoneInscrie.getText().isEmpty() 
            | tfLoginInscrie.getText().isEmpty() | tfAdresseInscrie.getText().isEmpty()  | comboVilleInscrit.getItems().isEmpty()  | tfAgeInscrie.getText().isEmpty() 
            | tfEmailInscrie.getText().isEmpty() | tfMotDePasseInscrie.getText().isEmpty()){
                       Alert alert1 = new Alert(Alert.AlertType.ERROR);
                       alert1.setTitle("Champ vide");
                       String text1 = "ERREUR: \n   Veullez entrer toutes les information !";
                       alert1.setHeaderText(text1);
                       alert1.show();
          return false;
    }
        return true;
 
    }
    public int select(){
        int etat=0;
        
        if (cbpos.isSelected())
            etat=1;
        if (cbneg.isSelected())
            etat=0;
        
        return etat;    
    }
    public boolean isString(String texte) {        
            if( texte.matches("^[a-zA-Z]+$")){ 
                return true;
            }else{               
                return false;
            }
        }
    public static boolean ValiderEmail(String email) { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    }
    public boolean isChiffre(String texte) {
        if (texte.matches("^[0-9]+$") && (texte.length()==8)) {
            return true;
        } else {
            return false;
        }

    }
    public boolean isAge(String age) {     
        if (age.matches("^[0-9]+$") && (age.length()==2)) {
            return true;
        } else {
            return false;
        }
    }
 


    
    
    
    
    
    //***********************************************************************************************************************//

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //---------------- Partie API MediaPlayer ------------------------------
        String vUrl = "file:/D:/covidVedio.mp4";
        Media media = new Media(vUrl);
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);       
        sliderVolume.setValue(mediaPlayer.getVolume() * 100);
        sliderVolume.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(sliderVolume.getValue()/100);
            }
        });
        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
             sliderTempsVideo.setValue(newValue.toSeconds());
            }
        });
        sliderTempsVideo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
              mediaPlayer.seek(Duration.seconds(sliderTempsVideo.getValue()));
            }
        });
        btnVideo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               paneAccueil.toFront();
               paneVideo.toFront();
            }
        });

  // ***************************************** Partie Rochdi les interfaces ! ***************************************** //
        // ----------------- Partie Accueil -------------------- //
        btnAccueil.setOnMouseEntered((event) -> {  btnAccueil.setStyle("-fx-background-color: #2196f3");});
        btnAccueil.setOnMouseExited((event)  -> { btnAccueil.setStyle("-fx-background-color: #EEE8AA"); }); 
        btnAccueil.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               paneAccueil.toFront();
               paneCompteMenu.toBack();

               btnInscrire.setStyle("-fx-background-color: #87CEEB");            
               btnConnecte.setStyle("-fx-background-color: #87CEEB"); 
               btnFaireDon.setStyle("-fx-background-color: #EEE8AA");
               btnDemandeAide.setStyle("-fx-background-color:   #EEE8AA");
               btnAutorisation.setStyle("-fx-background-color:  #EEE8AA");
               btnAgentLabo.setStyle("-fx-background-color:  #EEE8AA");  
               btnDocteur.setStyle("-fx-background-color:  #EEE8AA");   
            }
        });
        btnAide.setOnMouseEntered((event) -> { paneAide.toFront();});
        btnAide.setOnMouseExited((event)  -> { paneAide.toBack(); }); 
              
 // ----------------- Partie inscription -------------------- //
        final Tooltip lettre = new Tooltip();
        lettre.setText("Accepter que les lettres");
        tfNomInscrie.setTooltip(lettre);
        tfPrenomInscrie.setTooltip(lettre);
        tfLoginInscrie.setTooltip(lettre);
        
        final Tooltip chiffre = new Tooltip();
        chiffre.setText("Accepter que 8 nombres");
        tfCINInscrie.setTooltip(chiffre);
        tfTelephoneInscrie.setTooltip(chiffre);
        
        btnInscrire.setOnMouseEntered((event) -> { btnInscrire.setStyle("-fx-background-color: #2196f3"); });
        btnInscrire.setOnMouseExited((event)  -> { btnInscrire.setStyle("-fx-background-color: #87CEEB"); }); 
        btnInscrire.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
            btnInscrire.setStyle("-fx-background-color: #0000ff");            
            btnConnecte.setStyle("-fx-background-color: #87CEEB");        
            paneAccueil.toFront();
            paneSinscrire.toFront(); 
                     
              tfNomInscrie.setText("");
              tfNomInscrie.setStyle("-fx-border-color: #dcdcdc");
              tfPrenomInscrie.setText("");
              tfPrenomInscrie.setStyle("-fx-border-color: #dcdcdc");
              tfCINInscrie.setText("");
              tfCINInscrie.setStyle("-fx-border-color: #dcdcdc");
              tfAdresseInscrie.setText("");
              tfAdresseInscrie.setStyle("-fx-border-color: #dcdcdc");
              comboVilleInscrit.setValue(null);
              comboVilleInscrit.setStyle("-fx-border-color: #dcdcdc");
              tfTelephoneInscrie.setText("");
              tfTelephoneInscrie.setStyle("-fx-border-color: #dcdcdc");
              tfEmailInscrie.setText("");
              tfEmailInscrie.setStyle("-fx-border-color: #dcdcdc");
              tfAgeInscrie.setText("");
              tfAgeInscrie.setStyle("-fx-border-color: #dcdcdc");
              tfLoginInscrie.setText("");
              tfLoginInscrie.setStyle("-fx-border-color: #dcdcdc");
              tfMotDePasseInscrie.setText("");
              labelErreurInscrie.setText("");
              btnEnregistrerInscrie.setVisible(false);
             
            }
        }); 
        
        tfMotDePasseInscrie.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                  if(tfMotDePasseInscrie.getText().isEmpty() | tfMotDePasseInscrie.getText().length()== 0 ){
                       btnEnregistrerInscrie.setVisible(false);
                  }else{
                      btnEnregistrerInscrie.setVisible(true); 
                    }  
            }
        });
         
        btnEnregistrerInscrie.setOnMouseEntered((event) -> { btnEnregistrerInscrie.setStyle("-fx-background-color: #0000ff"); });
        btnEnregistrerInscrie.setOnMouseExited((event)  -> { btnEnregistrerInscrie.setStyle("-fx-background-color: #87CEEB"); });
        btnEnregistrerInscrie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            if(alertInscriptionVide()){                 
              String nom = tfNomInscrie.getText();          
                if(isString(nom)){
                   tfNomInscrie.setStyle("-fx-border-color: #00ff00");      
                }else{  
                   tfNomInscrie.setStyle("-fx-border-color: #ff0000");    
                   //tfNomInscrie.setStyle("-fx-font-size: 16");
                }
              String prenom = tfPrenomInscrie.getText();          
                if(isString(prenom)){
                   tfPrenomInscrie.setStyle("-fx-border-color: #00ff00");              
                }else{  
                   tfPrenomInscrie.setStyle("-fx-border-color: #ff0000");          
                }
              String cin = tfCINInscrie.getText();
                if(isChiffre(cin)){
                    tfCINInscrie.setStyle("-fx-border-color: #00ff00");               
                }else{
                   tfCINInscrie.setStyle("-fx-border-color: #ff0000");
                }           
              String tel = tfTelephoneInscrie.getText();
                if(isChiffre(tel)){
                   tfTelephoneInscrie.setStyle("-fx-border-color: #00ff00");               
                }else{
                   tfTelephoneInscrie.setStyle("-fx-border-color: #ff0000");
                } 
              String age = tfAgeInscrie.getText();
                if(isAge(age)){
                   tfAgeInscrie.setStyle("-fx-border-color: #00ff00");               
                }else{
                   tfAgeInscrie.setStyle("-fx-border-color: #ff0000");
                } 
              String comboVilleIns = comboVilleInscrit.getValue();
                if(comboVilleIns== null){
                   comboVilleInscrit.setStyle("-fx-border-color: #ff0000");  
                }else{
                   comboVilleInscrit.setStyle("-fx-border-color: #00ff00");  
                }   
              String adresse = tfAdresseInscrie.getText();          
                if(isString(adresse)){
                  tfAdresseInscrie.setStyle("-fx-border-color: #00ff00");              
                }else{  
                  tfAdresseInscrie.setStyle("-fx-border-color: #ff0000");          
                }
              String mail = tfEmailInscrie.getText();
                if(ValiderEmail(mail)){
                  tfEmailInscrie.setStyle("-fx-border-color: #00ff00"); 
                }else{
                  tfEmailInscrie.setStyle("-fx-border-color: #ff0000");
                }
              String login = tfLoginInscrie.getText();          
                if(isString(login)){
                  tfLoginInscrie.setStyle("-fx-border-color: #00ff00");              
                }else{  
                  tfLoginInscrie.setStyle("-fx-border-color: #ff0000");          
                }
               ServiceUtilisateur su11 = new ServiceUtilisateur();
               Utilisateur u11 = new Utilisateur();
               u11.setNom(tfNomInscrie.getText());
               u11.setPrenom(tfPrenomInscrie.getText());            
               u11.setCin(tfCINInscrie.getText());
               u11.setAdresse(tfAdresseInscrie.getText());
               u11.setVille(comboVilleIns);
               u11.setTel(Integer.parseInt(tfTelephoneInscrie.getText()));
               u11.setMail(tfEmailInscrie.getText());
               u11.setAge(Integer.parseInt(tfAgeInscrie.getText()));
               u11.setLogin(tfLoginInscrie.getText());
               u11.setMotDePasse(tfMotDePasseInscrie.getText());
              tfCINInscrie.setOnMouseEntered((event22) -> { tfCINInscrie.setStyle("-fx-text-fill: red; -fx-font-size: 18px;"); });
              tfCINInscrie.setOnMouseExited ((event21) -> { 
                  tfCINInscrie.setStyle("-fx-text-fill: black; -fx-font-size: 12px;"); 
                  if(isChiffre(cin)){
                    tfCINInscrie.setStyle("-fx-border-color: #00ff00");               
                }else{
                   tfCINInscrie.setStyle("-fx-border-color: #ff0000");
                }   
              });
               Utilisateur util = new Utilisateur();
               try {
                   util = su11.getUtilisateurByCIN(tfCINInscrie.getText());
                } catch (SQLException ex) {
                    ex.getStackTrace();
                }
                if(util==null){
                       if( (isString(nom)) && (isString(prenom)) && (isChiffre(cin)) &&  (isChiffre(tel)) && (isAge(age))
                           && (comboVilleIns != null) && (isString(adresse))&& (ValiderEmail(mail)) && (isString(login)) ){
                           try {                            
                               su11.ajouterUtilisateur(u11);                           
                           }catch (SQLException ex) { ex.getStackTrace(); }
                
                         paneAccueil.toFront(); 
                         paneLogMDPoublier.toFront();
                         paneSeConnecter.toFront();        
                        }else { 
                               labelErreurInscrie.setText("Erreur : les champs rouges sont incorrecte !");
                            }
                }else{   
                   // tfCINInscrie.setStyle("-fx-text-fill: red; -fx-font-size: 18px;");
                    labelErreurInscrie.setText("Erreur : Vous ètes déjà connectés !"); 
  
                }  
             }
           }
        });
     
        
        
        
 // ----------------- Partie Connexion -------------------- //
        btnConnecte.setOnMouseEntered((event) -> { btnConnecte.setStyle("-fx-background-color: #2196f3"); });
        btnConnecte.setOnMouseExited((event)  -> { btnConnecte.setStyle("-fx-background-color: #87CEEB"); }); 
        btnConnecte.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                  btnConnecte.setStyle("-fx-background-color: #2196f3");            
                  btnInscrire.setStyle("-fx-background-color: #87CEEB");        
                  
             paneSeConnecter.toFront();
             paneSinscrire.toBack();;
             paneLogMDPoublier.toBack();
             paneEmail.toBack();
             paneVideo.toBack();
                labelConnectErreur.setText("");
                tfConnetLogin.setText("");                 
                tfConnectMDP2.setText("");
             
            }
        });
        
        btnConnectConfirmer.setOnMouseEntered((event) -> { btnConnectConfirmer.setStyle("-fx-background-color: #0000ff"); });
        btnConnectConfirmer.setOnMouseExited((event)  -> { btnConnectConfirmer.setStyle("-fx-background-color: #87CEEB"); });
        btnConnectConfirmer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
                ServiceUtilisateur su = new ServiceUtilisateur();
                Utilisateur userConnected = new Utilisateur();
               
                String login = tfConnetLogin.getText();
                String pwd = tfConnectMDP2.getText();               
                System.out.println("Login : "+login+"\nMot de passe : "+pwd);
        
                try {
                    userConnected = su.getUtilisateurByLognAndPWD(login, pwd);
                } catch (SQLException ex) {
                    ex.getStackTrace();                   
                    System.out.println("***************************\nLogin ou Mot de passe incorrecte");
                   
                }
             if(alertVide()){
               if (userConnected != null){
            
                     try{
                    Stage stage2 = (Stage) btnConnectConfirmer.getScene().getWindow();                 
                    Parent parent2 = FXMLLoader.load(getClass().getResource("./MenuApplicationFXML.fxml"));
                    Scene scene2 = new Scene(parent2);
                    
                    stage2.setScene(scene2);
                    stage2.show();
                    
                }catch (IOException ex) {
                    System.out.println("Erreur : connection impossible pour le momment !");          
               }          

            String request= "UPDATE `utilisateur` SET `role`=1 WHERE login= '"+login+"' AND motDePasse='"+pwd+"' ";
            cnx= MyDB.getInstance().getConnection();
                   try {
                       PreparedStatement pst = cnx.prepareStatement(request);
                       pst.execute();
                   } catch (SQLException ex) {
                      
                   }
                   
            
                } else if((login.equals("admin")) && (pwd.equals("admin"))){
                    try{                    
                         Stage stage11 = (Stage) btnConnectConfirmer.getScene().getWindow();

                          Parent parent11 = FXMLLoader.load(getClass().getResource("./AdminFXML.fxml"));                       
                           Scene scene11 = new Scene(parent11);                    
                         stage11.setScene(scene11);
                         stage11.show(); 
                         System.out.println("***************************\nLogin et Mot de passe correcte, vous etes connecter");
                        }catch (IOException ex) {
                            ex.getMessage();
                             System.out.println("Erreur : partie administrateur");                   
                            }
                }
                else{                  
                       System.out.println("***************************\nLogin ou Mot de passe incorrecte");
                       labelConnectErreur.setText("Login ou mot de passe incorrecte !");
                       
                    }  
             
            }
            }
        }); 
        btnLogMDPoublier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                paneEmail.toFront();
            }
        });
      
 
        
        
        
        
        
        
        
        

  // ***************************************** Partie Oussama : Agent labo ***************************************** // 
       
        ServiceLaboratoire serviceLab = new ServiceLaboratoire();
        ArrayList<Laboratoire> options = new ArrayList<>();
        try {
            options = serviceLab.getLaboratoires();
        } catch (SQLException ex) {
            System.out.println("Erreur");
        }
        for (int i = 0; i< options.size(); i++ ){
        //System.out.println(options.get(i).getNom_labo());
          combichoixlabo.getItems().addAll(    
          options.get(i).getId_labo() +"_"+ options.get(i).getNom_labo());
        }
        ServiceGmap serviceGmap= new ServiceGmap();
        ArrayList<GMapCoordinates> opt = new ArrayList<>();
        try {
            opt = serviceGmap.getVille();
        } catch (SQLException ex) {
            System.out.println("Erreur");
        }
        for (int i = 0; i< opt.size(); i++ ){
           // System.out.println(opt.get(i).getId());
            comboville.getItems().addAll( opt.get(i).getNomVille());
            comboVilleInscrit.getItems().addAll( opt.get(i).getNomVille());
        }
        btnAgentLabo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                  btnAgentLabo.setStyle("-fx-background-color: #f5f5f5");
             
                  btnFaireDon.setStyle("-fx-background-color: #EEE8AA");
                  btnDemandeAide.setStyle("-fx-background-color: #EEE8AA");
                  btnAutorisation.setStyle("-fx-background-color:  #EEE8AA");  
                  btnDocteur.setStyle("-fx-background-color:  #EEE8AA");                  
             paneAccueil.toFront();
             paneAgentLabo.toFront(); 
              tfLoginAgentLabo.setText("");
              tfMDPAgentLabo.setText("");
            
            }
        });
        btnConnecterAgentLabo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { 
               String login1 = tfLoginAgentLabo.getText();
               String pwd1 = tfMDPAgentLabo.getText();  
                 if((login1.equals("labo")) && (pwd1.equals("labo"))){
                       paneAccueil.toFront();
                       paneFicheMaladi.toFront();
                    }
                       else{
                       labelErreurAgentLabo.setText("Login ou mot de passe incorrecte !");  
                    }  
            }
        });
        btnAjouterFicheMalade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               paneAccueil.toFront();
               paneFicheMaladi.toFront();
               paneAjouterMalade.toFront();
            }
        });
        btnajouter.setOnAction(new EventHandler<ActionEvent>() {
              ServiceLaboratoire serviceLab = new ServiceLaboratoire();
              ServiceGmap serviceGmap = new ServiceGmap();
              Laboratoire lab = new Laboratoire();
              GMapCoordinates gmap = new GMapCoordinates();
 
              @Override
           public void handle(ActionEvent t) {
               
               String nameLab= combichoixlabo.getValue();
               String combVille = comboville.getValue();
               
                  
               int index = nameLab.indexOf("_");
               String idString = nameLab.substring(0, index);
              
               System.out.println("+++++++++++++++++++++++++"+idString);
               int id = Integer.parseInt(idString);
               System.out.println("+++++++*******++++++++++++++++"+id);
                  try {
                      lab = serviceLab.getLabobyId(id);
                  } catch (SQLException ex) {
                      //Logger.getLogger(Test2Controller.class.getName()).log(Level.SEVERE, null, ex);
                  }
                 
                  FicheMalade f = new FicheMalade();
                  
                f.setNom_malade(tfNom.getText());
                f.setPrenom_malade(tfPrenom.getText());
                f.setAge(Integer.parseInt(tfAge.getText()));
                f.setCin(tfCin.getText());
                f.setVille(combVille);
                f.setEtat_analyse(select());
                f.setIdLabo(lab.getId_labo());
                  System.out.println("fiche*/*/"+ f.getVille());
                ServiceFicheMalade sf = new ServiceFicheMalade();
                  System.out.println("loadFiche"+f.toString());
                  try {
                      gmap = serviceGmap.getGmapByNom(f.getVille());
                    sf.addFicheMalade(f);
                    
                    tfNom.setText("");
                    tfPrenom.setText("");
                    tfAge.setText("");
                    tfCin.setText("");
                    //comboville.setText("");
                   
                     if (f.getIdLabo()== lab.getId_labo() && f.getEtat_analyse()== 1 ){
                        int nbrTestPositif=lab.getNbr_test_positif();
                        int nbrAnalyse = lab.getNbrs_des_anaylses();
                    lab.setNbr_test_positif(nbrTestPositif+1);
                     
                    lab.setNbrs_des_anaylses(nbrAnalyse+1);
                    
                    serviceLab.updateLaboratoire(lab, lab.getId_labo());
                    
                    }
                     else {
                        int nbrAnalyse = lab.getNbrs_des_anaylses();
                         lab.setNbrs_des_anaylses(nbrAnalyse+1);
                         
                         serviceLab.updateLaboratoire(lab, lab.getId_labo());
                         

                     }
                     
                     if (f.getVille().equalsIgnoreCase(gmap.getNomVille())&& f.getEtat_analyse()== 1){
                        int nbrTestPositif=gmap.getNbr_test_positif();
                        int nbrAnalyse = gmap.getNbrs_des_anaylses();
                    
                    gmap.setNbrs_des_anaylses(nbrAnalyse+1);
                    gmap.setNbr_test_positif(nbrTestPositif+1);
                    
                    serviceGmap.updateVille(gmap, gmap.getId());
                    }
                     else if (f.getVille().equalsIgnoreCase(gmap.getNomVille())&& f.getEtat_analyse()== 0) {
                        int nbrAnalyse = gmap.getNbrs_des_anaylses();
                         
                         gmap.setNbrs_des_anaylses(nbrAnalyse+1);
                        
                         serviceGmap.updateVille(gmap, gmap.getId());

                     }
                     
                    
                    
                } catch (SQLException ex) {
                    System.out.println("Erreur de la base");
                }
            }
        });

        btnSupprimerFicheMalade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               paneAccueil.toFront();
               paneFicheMaladi.toFront();
               paneSupprimerMalade.toFront();
               
               
            }
        });
        btnSupprimerMalade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                paneSupprimerMaladeConfirmer.toFront();
                
                FicheMalade ut = new FicheMalade();
                ServiceFicheMalade sut = new ServiceFicheMalade();
                String cinSUp = tfCinSUPPPMalade.getText();
                
               
                
                    
                try {
                    ut = sut.getFichebyCIN(cinSUp);
                } catch (SQLException ex) {
                    ex.getStackTrace();
                }
                
                System.out.println(ut);
                
                if(ut != null){
                    labelNomSupMalade.setText(ut.getNom_malade());
                    labelPrenomSupMalade.setText(ut.getPrenom_malade());
                    labelAgeSupMalade.setText(String.valueOf(ut.getAge()));
                    labelVilleSupMalade.setText(ut.getVille());
                    if (ut.getEtat_analyse()==0)
                    labelEtatSupMalade.setText("Négative");
                    else 
                        labelEtatSupMalade.setText("Positive");
                    

                }
                else{
                    System.out.println("ERREUR : Utilisateur n'existe pas !");
                }

            }
        });
        btnSupressionFinale.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               paneAccueil.toFront();
               paneFicheMaladi.toFront();
               paneSupprimerMalade.toFront();
               
                FicheMalade f = new FicheMalade();
                
                f.setCin(tfCinSUPPPMalade.getText());
                
                
              
                ServiceFicheMalade sf = new ServiceFicheMalade();
                  try {
                    sf.deleteFicheMalade(f);
                    tfCinSUPPPMalade.setText("");                   
                    
                } catch (SQLException ex) {
                    System.out.println("Erreur de la base");
                }
               
            }
        });
        btnModifierFicheMalade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               paneAccueil.toFront();
               paneFicheMaladi.toFront();
               paneModifierMalade.toFront();
            }
        });
        btnRechercherFiche.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                paneModifierMaladeConfirmer.toFront();
                 FicheMalade f = new FicheMalade();
                
                f.setCin(tfCinMod.getText());
              
                ServiceFicheMalade sf = new ServiceFicheMalade();
                  try {
                   FicheMalade f2=  sf.getFichebyCIN(f.getCin());
                   
                    System.out.println(f2.getId_fiche());
                   System.out.println(f2.toString());
                    nomMalade.setText(f2.getNom_malade());
                    prenommalade.setText(f2.getPrenom_malade());
                    agemalade.setText(String.valueOf(f2.getAge()));
                    tfCinMod.setText(f2.getCin());
                    villemalade.setText(f2.getVille());
                    etatmalade.setText(String.valueOf(f2.getEtat_analyse()));
                    // à completer champs malade et age 
        
                    
          btnmodifier.setOnAction(new EventHandler<ActionEvent>() {
            
           public void handle(ActionEvent t) {
                
              
                ServiceFicheMalade sf = new ServiceFicheMalade();
                
                  try {
                       
                       System.out.println(f2.getId_fiche());
                      
                      f2.setNom_malade(nomMalade.getText());
                      f2.setPrenom_malade(prenommalade.getText());
                      f2.setCin(tfCinMod.getText());
                      f2.setAge(Integer.parseInt(agemalade.getText()));
                      f2.setVille(villemalade.getText());
                      f2.setEtat_analyse(Integer.parseInt(etatmalade.getText()));
                      
                       
                   
                      
                   sf.updateFicheMalade(f2, f2.getId_fiche());
                   
                  
                    
                } catch (SQLException ex) {
                    System.out.println("Erreur de la base");
                }
            }
           
           
        });
                } catch (SQLException ex) {
                    System.out.println("Erreur de la base");
                }
                
            }
        });
        
        

           
    }  
  // ***************************************** Partie API MediaPlayer ***************************************** //
    @FXML
    void onCilickBtnPlay(ActionEvent event) {
      if(mediaPlayer.getStatus()==PLAYING){
        mediaPlayer.stop();
        mediaPlayer.play();
      }else{
         mediaPlayer.play();  
      }    
    }
    @FXML
    void onCilickBtnStop(ActionEvent event) {
      mediaPlayer.stop();
    }    
    @FXML
    void onCilickBtnPause(ActionEvent event) {
      mediaPlayer.pause();
    }
    
    
}

