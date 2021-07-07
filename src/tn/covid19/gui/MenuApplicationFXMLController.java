
package tn.covid19.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tn.covid19.entities.GMapCoordinates;
import tn.covid19.entities.Utilisateur;
import tn.covid19.service.impl.ServiceGmap;
import tn.covid19.service.impl.ServiceUtilisateur;


public class MenuApplicationFXMLController implements Initializable {

    @FXML
    private Button btnMonCompteNew,btnAgenLaboNew, btnAutorisationNew, btnDocteurNew ,btnCompteConfirmerModification,
                   btnDemandeoufairedonNew, btnAfficherCompteNew, btnModifierCompteNew, btnConnecterAgentLaboNew, 
                   btnConnecterDocteurNew, btnSeDeconnecterNew;
    @FXML
    private Pane paneA,paneMonCompte, paneAfficherMonCompte, paneModifierMonCompte, 
                 paneDocteurConnecter, paneAgentLaboConnecter;
      @FXML
    private TextField tfModifierCompteNom, tfModifierComptePrenom,tfModifierCompteCIN, tfModifierCompteAdresse,
                      tfModifierCompteAge, tfModifierCompteTel, tfModifierCompteEmail, tfModifierCompteLogin, tfModifierCompteMDP,
                      tfLoginAgentLaboNew, tfLoginAgentMDPNew,
                      tfLoginDocteurNew, tfMDPDocteurNew;
    @FXML
    private TextField tfAfficherCompteNom, tfAfficherComptePrenom, tfAfficherCompteCIN, tfAfficherCompteAdresse,
                      tfAfficherCompteVille, tfAfficherCompteAge, tfAfficherCompteTel, tfAfficherCompteEmail,
                      tfAfficherCompteLogin, tfAfficherCompteMDP;
    @FXML
    private Label labelLoginConnecter, labelAfficherErreurAgentLabo, labelErreurModification;
    @FXML
    private ComboBox<String> comboModifierCompteAdresse;
    
   //**************************************** Contole de saisie ***********************************************//
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
    //*********************************************************************************************************//

    @Override
    public void initialize(URL url, ResourceBundle rb) {
               Utilisateur ul = new Utilisateur();
               ServiceUtilisateur sut = new ServiceUtilisateur();
                try {
                    ul = sut.getUtilisateurByRole(1);
                } catch (SQLException ex) {
                    ex.getStackTrace();
                }
        labelLoginConnecter.setText(ul.getPrenom());
        
        btnMonCompteNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {  
             paneA.toFront();
             paneMonCompte.toFront(); 
            }
        });        
        btnAfficherCompteNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {    
             paneA.toFront();
             paneAfficherMonCompte.toFront(); 
             
              Utilisateur ut = new Utilisateur();
               ServiceUtilisateur sut = new ServiceUtilisateur();
                try {
                    ut = sut.getUtilisateurByRole(1);
                } catch (SQLException ex) {
                    ex.getStackTrace();
                }
                
                if(ut != null){
                    tfAfficherCompteNom.setText(ut.getNom());
                    tfAfficherCompteNom.setEditable(false);
                    tfAfficherComptePrenom.setText(ut.getPrenom());
                    tfAfficherComptePrenom.setEditable(false);
                    tfAfficherCompteCIN.setText(ut.getCin());
                    tfAfficherCompteCIN.setEditable(false);
                    tfAfficherCompteAdresse.setText(ut.getAdresse());
                    tfAfficherCompteAdresse.setEditable(false);
                    tfAfficherCompteVille.setText(ut.getVille());
                    tfAfficherCompteVille.setEditable(false);
                    tfAfficherCompteAge.setText(String.valueOf(ut.getAge()));
                    tfAfficherCompteAge.setEditable(false);
                    tfAfficherCompteTel.setText(String.valueOf(ut.getTel()));
                    tfAfficherCompteTel.setEditable(false);
                    tfAfficherCompteEmail.setText(ut.getMail());  
                    tfAfficherCompteEmail.setEditable(false);
                    tfAfficherCompteLogin.setText(ut.getLogin());
                    tfAfficherCompteLogin.setEditable(false);
                    tfAfficherCompteMDP.setText(ut.getMotDePasse()); 
                    tfAfficherCompteMDP.setEditable(false);
                }
                else{
                    System.out.println("ERREUR : Utilisateur n'existe pas !");
                }  
             
             
            }
        });  
        
        
        btnModifierCompteNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                paneA.toFront();
                paneModifierMonCompte.toFront();
                
            ServiceGmap serviceGmap= new ServiceGmap();
            ArrayList<GMapCoordinates> opt = new ArrayList<>();
            try {
                opt = serviceGmap.getVille();
            } catch (SQLException ex) {
               ex.getStackTrace();
              }
            for (int i = 0; i< opt.size(); i++ ){
                      comboModifierCompteAdresse.getItems().addAll( opt.get(i).getNomVille());
             }               
               Utilisateur ut = new Utilisateur();
               ServiceUtilisateur sut = new ServiceUtilisateur();
                try {
                    ut = sut.getUtilisateurByRole(1);
                } catch (SQLException ex) {
                    ex.getStackTrace();
                }
                
                if(ut != null){
                    tfModifierCompteNom.setText(ut.getNom());
                    tfModifierComptePrenom.setText(ut.getPrenom());
                    tfModifierCompteCIN.setText(ut.getCin());
                    tfModifierCompteCIN.setEditable(false);
                    tfModifierCompteAdresse.setText(ut.getAdresse());
                    comboModifierCompteAdresse.setValue(ut.getVille());
                    tfModifierCompteAge.setText(String.valueOf(ut.getAge()));
                    tfModifierCompteTel.setText(String.valueOf(ut.getTel()));
                    tfModifierCompteEmail.setText(ut.getMail());      
                    tfModifierCompteLogin.setText(ut.getLogin());
                    tfModifierCompteMDP.setText(ut.getMotDePasse()); 
                }
                else{
                    System.out.println("ERREUR : Utilisateur n'existe pas !");
                }  
            }
        });         
        btnCompteConfirmerModification.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {    
               
    
            Utilisateur u1 = new Utilisateur();
            ServiceUtilisateur su11 = new ServiceUtilisateur();

            String cin = tfModifierCompteCIN.getText();
                try {
                    u1= su11.getUtilisateurByCIN(cin);
                } catch (SQLException ex) {
                   ex.getStackTrace();
                }
                
              String nom = tfModifierCompteNom.getText();          
                if(isString(nom)){
                   tfModifierCompteNom.setStyle("-fx-border-color: #00ff00");              
                }else{  
                   tfModifierCompteNom.setStyle("-fx-border-color: #ff0000");  
                  // tfModifierCompteNom.setStyle("-fx-font-size: 16");
                }
              String prenom = tfModifierComptePrenom.getText();          
                if(isString(prenom)){
                   tfModifierComptePrenom.setStyle("-fx-border-color: #00ff00");              
                }else{  
                   tfModifierComptePrenom.setStyle("-fx-border-color: #ff0000");          
                }
              String cina = tfModifierCompteCIN.getText();
                if(isChiffre(cina)){
                    tfModifierCompteCIN.setStyle("-fx-border-color: #00ff00");               
                }else{
                   tfModifierCompteCIN.setStyle("-fx-border-color: #ff0000");
                }           
              String tel = tfModifierCompteTel.getText();
                if(isChiffre(tel)){
                   tfModifierCompteTel.setStyle("-fx-border-color: #00ff00");               
                }else{
                   tfModifierCompteTel.setStyle("-fx-border-color: #ff0000");
                   
                } 
              String age = tfModifierCompteAge.getText();
                if(isAge(age)){
                   tfModifierCompteAge.setStyle("-fx-border-color: #00ff00");               
                }else{
                   tfModifierCompteAge.setStyle("-fx-border-color: #ff0000");
                } 
              String comboVilleInsa = comboModifierCompteAdresse.getValue();
                if(comboVilleInsa== null){
                   comboModifierCompteAdresse.setStyle("-fx-border-color: #ff0000");  
                }else{
                   comboModifierCompteAdresse.setStyle("-fx-border-color: #00ff00");  
                }   
              String adresse = tfModifierCompteAdresse.getText();          
                if(isString(adresse)){
                  tfModifierCompteAdresse.setStyle("-fx-border-color: #00ff00");              
                }else{  
                  tfModifierCompteAdresse.setStyle("-fx-border-color: #ff0000");          
                }
              String mail = tfModifierCompteEmail.getText();
                if(ValiderEmail(mail)){
                  tfModifierCompteEmail.setStyle("-fx-border-color: #00ff00"); 
                }else{
                  tfModifierCompteEmail.setStyle("-fx-border-color: #ff0000");
                }
              String login = tfModifierCompteLogin.getText();          
                if(isString(login)){
                  tfModifierCompteLogin.setStyle("-fx-border-color: #00ff00");              
                }else{  
                  tfModifierCompteLogin.setStyle("-fx-border-color: #ff0000");          
                }
  
                
          int id= u1.getIdUtilisateur();
          String comboVilleMod = comboModifierCompteAdresse.getValue();
           Utilisateur u11 = new Utilisateur();
           u11.setIdUtilisateur(id);
           u11.setNom(tfModifierCompteNom.getText());
           u11.setPrenom(tfModifierComptePrenom.getText());
           u11.setCin(tfModifierCompteCIN.getText());
           u11.setAdresse(tfModifierCompteAdresse.getText());
           u11.setVille(comboVilleMod);
           u11.setTel(Integer.parseInt(tfModifierCompteTel.getText()));
           u11.setMail(tfModifierCompteEmail.getText());
           u11.setAge(Integer.parseInt(tfModifierCompteAge.getText()));
           u11.setRole(0);
           u11.setLogin(tfModifierCompteLogin.getText());
           u11.setMotDePasse(tfModifierCompteMDP.getText());
           
           if( (isString(nom)) && (isString(prenom)) && (isChiffre(cina)) &&  (isChiffre(tel)) && (isAge(age))
                                   && (comboVilleInsa != null) && (isString(adresse))&& (ValiderEmail(mail)) && (isString(login)) ){
              
              try {                            
                     su11.updateUtilisateur(u11); 
                      paneA.toFront();
               }  catch (SQLException ex) {
                   ex.getStackTrace();
                   System.out.println("Erreur : modification impossible !");
                }
            }else{
               labelErreurModification.setText("Erreur : champs rouges incorrecte !");
           }
 
            
           
         
                
                
                
                
            }
        });  
        
        
        btnSeDeconnecterNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { 
                try{
                    Stage stage1 = (Stage) btnSeDeconnecterNew.getScene().getWindow();                 
                    Parent parent1 = FXMLLoader.load(getClass().getResource("./ApplicationFXML.fxml"));
                    Scene scene1 = new Scene(parent1);
                    
                    stage1.setScene(scene1);
                    stage1.show();
                    
                }catch (IOException ex) {
                    System.out.println("Erreur : la page trois n'exixte pas");          
                }
             
            } 
            
        });
    
        btnAgenLaboNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { 
                paneA.toFront();
                paneAgentLaboConnecter.toFront(); 
            }
        });
        btnConnecterAgentLaboNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { 
            String login1 = tfLoginAgentLaboNew.getText();
            String pwd1 = tfLoginAgentMDPNew.getText();  
                 if((login1.equals("labo")) && (pwd1.equals("labo"))){
                     try{
                        Stage stage11 = (Stage) btnAutorisationNew.getScene().getWindow();                 
                        Parent parent11 = FXMLLoader.load(getClass().getResource("./AgentLaboFXML.fxml"));
                        Scene scene11 = new Scene(parent11);
                        stage11.setScene(scene11);
                        stage11.show();      
                     }catch (IOException ex) {
                           ex.getStackTrace();
                        }      
                  }else{
                      labelAfficherErreurAgentLabo.setText("Login ou mot de passe incorrecte !"); 
                    } 
            }
        });
        btnConnecterDocteurNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { 
            String login1 = tfLoginDocteurNew.getText();
               String pwd1 = tfMDPDocteurNew.getText();  
                 if((login1.equals("docteur")) && (pwd1.equals("docteur"))){
                     try{
                        Stage stage11 = (Stage) btnAutorisationNew.getScene().getWindow();                 
                        Parent parent11 = FXMLLoader.load(getClass().getResource("./DocteurFXML.fxml"));
                        Scene scene11 = new Scene(parent11);
                        stage11.setScene(scene11);
                        stage11.show();      
                     }catch (IOException ex) {
                           ex.getStackTrace();
                        }      
                  }else{
                      //labelAfficherErreurAgentLabo.setText("Login ou mot de passe incorrecte !"); 
                    } 
            }
        });
        
         
        
        btnDocteurNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { 
                paneA.toFront();
             paneDocteurConnecter.toFront(); 
            }
        });
        btnAutorisationNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { 
                try{
                    Stage stage22 = (Stage) btnAutorisationNew.getScene().getWindow();                 
                    Parent parent22 = FXMLLoader.load(getClass().getResource("./AutorisationNewFXML.fxml"));
                    Scene scene22 = new Scene(parent22);
                    
                    stage22.setScene(scene22);
                    stage22.show();
                    
                }catch (IOException ex) {
                    System.out.println("Erreur : la page trois n'exixte pas");          
                }
             
            } 
            
        });
        btnDemandeoufairedonNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { 
                try{
                    Stage stage22 = (Stage) btnDemandeoufairedonNew.getScene().getWindow();                 
                    Parent parent22 = FXMLLoader.load(getClass().getResource("./DemDonFXML.fxml"));
                    Scene scene22 = new Scene(parent22);
                    
                    stage22.setScene(scene22);
                    stage22.show();
                    
                }catch (IOException ex) {
                    System.out.println("Erreur : la page trois n'exixte pas");          
                }
             
            } 
            
        });
        
       
    }    
    
}
