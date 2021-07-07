package tn.covid19.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tn.covid19.entities.Laboratoire;
import tn.covid19.entities.Utilisateur;
import tn.covid19.service.impl.ServiceLaboratoire;
import tn.covid19.service.impl.ServiceUtilisateur;



public class AdminFXMLController implements Initializable {

    @FXML
    private Button   btnMenu, btnGestUtilisateur, btnRim, btnOussama, btnHabib,btnAbdelAziz, btnPageApp,                        //Btn Menu
                     btnAffichUtilisateur,btnAffichResult, btnSuppUtilisateur,btnSuppResult,btnSuppConfirm, btnListeUtilisateur,//Btn Rochdi
                     btnAjouterLabo, btnSupprimerLabo, btnAfficherLabo,btnajouterlabo ,btnSupLabo;                                                        //Btn Oussama    
    @FXML
    private Pane     paneMenu, paneMenuVide,
                     paneGestUtilisateur,paneCINAff, paneAffichResult,paneCINSupp, paneSuppResult, paneListeUtilisateur,        //Pane Gestion utilisateur
                     paneRim,
                     paneOussama,paneAjouterLabo, paneSupprimerLabo, paneAfficherLabo,                                          //Pane Gestion laboratoire
                     paneHabib, 
                     paneAabdeAziz;    
    @FXML
    private TextField tfCINAfficher, tfCINSupp ,        //TextField Rochdi
                      tfnomlabo,tfnomlaboSup;                        //TextField Oussama
   
    @FXML
    private Label lbAfficheErreur, lbSuppErreur; 
    @FXML
    private Label lbNomAffich, lbPrenomAffich, lbAdresseAffich, lbVilleAffich, lbAgeAffich, lbTelephoneAffich, lbEmailAffich, lbLoginAffich, lbMotDePasseAffich,
                  labelNomAffich, labelPrenomAffich, labelAdresseAffich, labelVilleAffich, labelAgeAffich, labelTelephoneAffich, labelEmailAffich, labelLoginAffich, labelMotDePasseAffich;
    @FXML
    private Label  lbNomSupp, lbPrenomSupp, lbAdresseSupp, lbVilleSupp, lbAgeSupp, lbTelephoneSupp, lbEmailSupp, lbLoginSupp, lbMotDePasseSupp,
                   labelNomSupp, labelPrenomSupp, labelAdresseSupp, labelVilleSupp, labelAgeSupp, labelTelephoneSupp, labelEmailSupp, labelLoginSupp, labelMotDePasseSupp;

    // ------------------- Tableau affichage Utilisateur -------------------
   
    @FXML
    private TableView<Utilisateur> table3;
    @FXML
    private TableColumn<Utilisateur, String> cin3;   
    @FXML
    private TableColumn<Utilisateur, String> nom3;
    @FXML
    private TableColumn<Utilisateur, String> prenom3;
    @FXML
    private TableColumn<Utilisateur, String> adresse3;
    @FXML
    private TableColumn<Utilisateur, Integer> telephone3;
    @FXML
    private TableColumn<Utilisateur, String> email3;
    @FXML
    private TableColumn<Utilisateur, Integer> age3;
    
    
   
    public ObservableList<Utilisateur> getObservableList(){
        ServiceUtilisateur su = new ServiceUtilisateur();
        ArrayList<Utilisateur> liste2 = new ArrayList<>();
        try {
            liste2 = su.getUtilisateurList();
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return FXCollections.observableArrayList(liste2);
    }
    // --------------------------- Afichage LAB ---------------------
    @FXML
    private TableView<Laboratoire> tableAfficher;
    @FXML
    private TableColumn<Laboratoire, String> colid;
    @FXML
    private TableColumn<Laboratoire, String> colnom;
    @FXML
    private TableColumn<Laboratoire, String> colNbrtotal;
    @FXML
    private TableColumn<Laboratoire, String> colpos;
    
   ObservableList<Laboratoire> laboratoireList = FXCollections.observableArrayList();
   
   
    @FXML
    public boolean alertAffichageVide() {
        if(tfCINAfficher.getText().isEmpty()){
                       Alert alert = new Alert(Alert.AlertType.ERROR);
                       alert.setTitle("CIN vide");
                       String text = "ERREUR: \n >>>> Veullez entrer numéro CIN !";
                       alert.setHeaderText(text);
                       alert.show();
          return false;
    }
        return true; 
    }
    @FXML
    public boolean alertSupprissionVide() {
        if(tfCINSupp.getText().isEmpty()){
                       Alert alert = new Alert(Alert.AlertType.ERROR);
                       alert.setTitle("CIN vide");
                       String text = "ERREUR: \n >>>> Veullez entrer numéro CIN !";
                       alert.setHeaderText(text);
                       alert.show();
          return false;
    }
        return true; 
    }
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        btnMenu.setOnMouseEntered((event) -> { paneMenu.toFront();});
        paneMenu.setOnMouseExited((event) -> { paneMenu.toBack();});
        
       /******************************** PARTIE GESTION D'UTILISATEUR *********************************/
           
        btnGestUtilisateur.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             paneMenuVide.toFront();
             paneGestUtilisateur.toFront();
              
             btnAffichUtilisateur.setStyle("-fx-background-color: #4169E1");
             btnSuppUtilisateur.setStyle("-fx-background-color: #4169E1");
             btnListeUtilisateur.setStyle("-fx-background-color: #4169E1");       
            }
        });       
        btnAffichUtilisateur.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             paneCINAff.toFront();
             
             paneCINSupp.toBack();
             paneSuppResult.toBack();
             paneListeUtilisateur.toBack();
             paneAffichResult.toBack();
             paneMenu.toBack();
            
             tfCINAfficher.setText("");
             lbAfficheErreur.setText("");
             btnAffichUtilisateur.setStyle("-fx-background-color: #000080");
             btnSuppUtilisateur.setStyle("-fx-background-color: #4169E1");
             btnListeUtilisateur.setStyle("-fx-background-color: #4169E1");           
            }
        });
        btnAffichResult.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                 lbAfficheErreur.setText(""); 
                 lbSuppErreur.setText("");                
                
                 Utilisateur u = new Utilisateur();
                 ServiceUtilisateur su = new ServiceUtilisateur();
                String cin = tfCINAfficher.getText(); 
              if(alertAffichageVide()){
                try {
                    u = su.getUtilisateurByCIN(cin);
                } catch (SQLException ex) {
                   ex.getSQLState();
                }
             
                if(u != null){
                     paneAffichResult.toFront();
                    
                    lbNomAffich.setText(u.getNom());
                    lbPrenomAffich.setText(u.getPrenom());
                    lbAdresseAffich.setText(u.getAdresse());
                    lbVilleAffich.setText(u.getVille());
                    lbAgeAffich.setText(String.valueOf(u.getAge()));
                    lbTelephoneAffich.setText(String.valueOf(u.getTel()));
                    lbEmailAffich.setText(u.getMail());      
                    lbLoginAffich.setText(u.getLogin());
                    lbMotDePasseAffich.setText(u.getMotDePasse());
                    
                    labelNomAffich.setText("Nom :");
                    labelPrenomAffich.setText("Prenom :");
                    labelAdresseAffich.setText("Adresse :");
                    labelVilleAffich.setText("Ville :");
                    labelAgeAffich.setText("Age :");
                    labelTelephoneAffich.setText("Téléphone :");
                    labelEmailAffich.setText("Email :");      
                    labelLoginAffich.setText("Login :");
                    labelMotDePasseAffich.setText("Mot de passe :");                         
                }
                else{
                    lbAfficheErreur.setText("Utilisateur n'existe pas !");  
                }               
            } 
          }
        });                              
        btnSuppUtilisateur.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             btnSuppUtilisateur.setStyle("-fx-background-color: #000080	");
             btnAffichUtilisateur.setStyle("-fx-background-color: #4169E1");
             btnListeUtilisateur.setStyle("-fx-background-color: #4169E1");
             paneCINSupp.toFront();
             paneListeUtilisateur.toBack();
             paneAffichResult.toBack();
             paneSuppResult.toBack();
             paneMenu.toBack();
             tfCINSupp.setText("");
             lbSuppErreur.setText("");             
            }           
        }); 
        btnSuppResult.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                Utilisateur u2 = new Utilisateur();
                ServiceUtilisateur su2 = new ServiceUtilisateur();
                String cin = tfCINSupp.getText();
              if(alertSupprissionVide()){
                try {
                    u2 = su2.getUtilisateurByCIN(cin);
                } catch (SQLException ex) {
                   ex.getSQLState();
                }
               
                if(u2 != null){
                    paneSuppResult.toFront();
                    lbSuppErreur.setText("");
                    labelNomSupp.setText("Nom :");
                    labelPrenomSupp.setText("Prenom :");
                    labelAdresseSupp.setText("Adresse :");
                    labelVilleSupp.setText("Ville :");
                    labelAgeSupp.setText("Age :");
                    labelTelephoneSupp.setText("Téléphone :");
                    labelEmailSupp.setText("Email :"); 
                    labelLoginSupp.setText("Login :");
                    labelMotDePasseSupp.setText("Mot de passe :");
                    
                    lbNomSupp.setText(u2.getNom());
                    lbPrenomSupp.setText(u2.getPrenom());
                    lbAdresseSupp.setText(u2.getAdresse());
                    lbVilleSupp.setText(u2.getVille());
                    lbAgeSupp.setText(String.valueOf(u2.getAge()));
                    lbTelephoneSupp.setText(String.valueOf(u2.getTel()));
                    lbEmailSupp.setText(u2.getMail()); 
                    lbLoginSupp.setText(u2.getLogin());
                    lbMotDePasseSupp.setText(u2.getMotDePasse());  
                }
                else{
                    lbSuppErreur.setText("Utilisateur n'existe pas !"); 
     
                }                
            }
         }
            
        });
        btnSuppConfirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                       alert.setTitle("Confirmation de supprission");
                       String text = " Veullez vous vraiment supprimer l'utilisateur ?";
                       alert.setHeaderText(text);
                       Optional <ButtonType> action = alert.showAndWait();
                       if(action.get() == ButtonType.OK){
                            String cin = tfCINSupp.getText();
                            ServiceUtilisateur su3 = new ServiceUtilisateur();
                          try {
                            su3.deleteUtilisateur(cin);
                            //labeSuppConfirme.setText("Effectué, l'utilisateur est supprimer ");
                            lbSuppErreur.setText("Effectué, l'utilisateur est supprimer");
                            paneCINSupp.toFront();
                           } catch (SQLException ex) {
                               ex.getMessage();
                           }   
                       }
            }
        });       
        btnListeUtilisateur.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnListeUtilisateur.setStyle("-fx-background-color: #000080");
                btnAffichUtilisateur.setStyle("-fx-background-color:  #4169E1");
                btnSuppUtilisateur.setStyle("-fx-background-color:  #4169E1");
                paneListeUtilisateur.toFront();
              
              paneCINAff.toBack();
              paneCINSupp.toBack();
              paneMenu.toBack();
              paneAffichResult.toBack();
              paneSuppResult.toBack();
              
                nom3.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("nom"));
                prenom3.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("prenom"));
                adresse3.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("adresse"));
                telephone3.setCellValueFactory(new PropertyValueFactory<Utilisateur,Integer>("tel"));
                email3.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("mail"));
                age3.setCellValueFactory(new PropertyValueFactory<Utilisateur,Integer>("age"));
                cin3.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("cin"));
                 
              table3.setItems(getObservableList());                
            }
        });
                 
     
      /******************************** PARTIE RIM DHIFALLAH *********************************/  
       
        btnRim.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            paneMenuVide.toFront();
             paneRim.toFront();            
            }
        });
        
        
      /******************************** PARTIE GESTION DES LABORATOIRES *********************************/   
         
        btnOussama.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             paneMenuVide.toFront();  
             paneOussama.toFront();
          
             btnAjouterLabo.setStyle("-fx-background-color: #4169E1");
             btnSupprimerLabo.setStyle("-fx-background-color: #4169E1");
             btnAfficherLabo.setStyle("-fx-background-color: #4169E1");
            }
        });
        btnAjouterLabo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                paneMenuVide.toFront();
                paneOussama.toFront();
                paneAjouterLabo.toFront();
                
                btnAjouterLabo.setStyle("-fx-background-color: #000080");
                btnSupprimerLabo.setStyle("-fx-background-color: #4169E1");
                btnAfficherLabo.setStyle("-fx-background-color: #4169E1"); 
            }
        });
        btnajouterlabo.setOnAction(new EventHandler<ActionEvent>() {
            
           @Override
           public void handle(ActionEvent t) {
                Laboratoire lab = new Laboratoire();
                lab.setNom_labo(tfnomlabo.getText());
                //lab.setNbrs_des_anaylses(Integer.parseInt(tfnbrana.getText()));
                //lab.setNbr_test_positif(Integer.parseInt(tfnbrPOs.getText()));
                
              
                ServiceLaboratoire sf = new ServiceLaboratoire();
                  try {
                    sf.addLaboratoire(lab);
                    tfnomlabo.setText("");
                    //tfnbrana.setText("0");
                    //tfnbrPOs.setText("0");
                    
                } catch (SQLException ex) {
                    System.out.println("Erreur de la base");
                }
            }
        });
        btnSupprimerLabo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                paneMenuVide.toFront();
                paneOussama.toFront();
                paneSupprimerLabo.toFront();
                
                btnSupprimerLabo.setStyle("-fx-background-color: #000080");
                btnAjouterLabo.setStyle("-fx-background-color: #4169E1");
                btnAfficherLabo.setStyle("-fx-background-color: #4169E1"); 
            }
        });
        btnSupLabo.setOnAction(new EventHandler<ActionEvent>() {
            
           @Override
           public void handle(ActionEvent t) {
                Laboratoire lab = new Laboratoire();
                
                lab.setNom_labo(tfnomlaboSup.getText());
                
                 System.out.println("text filed issssss   " + tfnomlaboSup.getText());
              
                ServiceLaboratoire s = new ServiceLaboratoire();
                  try {
                    s.deleteLaabo(lab);
                    tfnomlaboSup.setText("");                 
                     System.out.println("sucees");
                } catch (SQLException ex) {
                    System.out.println("Erreur de la base");
                }
            }
        });
        btnAfficherLabo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                paneMenuVide.toFront();
                paneOussama.toFront();
                paneAfficherLabo.toFront();
                
                btnAfficherLabo.setStyle("-fx-background-color: #000080");
                btnAjouterLabo.setStyle("-fx-background-color: #4169E1");
                btnSupprimerLabo.setStyle("-fx-background-color: #4169E1"); 
            }
        });
        ServiceLaboratoire sf = new ServiceLaboratoire();
        List <Laboratoire> lab = new ArrayList<Laboratoire>();
        try {
            lab=sf.getLaboratoires();
        } catch (SQLException ex) {
            System.out.println("Rien à afficher");
        }
        for(int i=0; i<lab.size();i++){
        laboratoireList.add(new Laboratoire(lab.get(i).getId_labo(), 
                lab.get(i).getNom_labo(), lab.get(i).getNbrs_des_anaylses(), lab.get(i).getNbr_test_positif()));
        
        }
        
        colid.setCellValueFactory(new PropertyValueFactory<>("id_labo"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom_labo"));
        colNbrtotal.setCellValueFactory(new PropertyValueFactory<>("nbrs_des_anaylses"));
        colpos.setCellValueFactory(new PropertyValueFactory<>("nbr_test_positif"));
        tableAfficher.setItems(laboratoireList);
       /******************************** PARTIE HABIB JERBI *********************************/
      
        btnHabib.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             paneMenuVide.toFront();
             paneHabib.toFront();
             
            }
        });
        
       /******************************** PARTIE ABDELAZIZ *********************************/
        btnAbdelAziz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              paneMenuVide.toFront(); 
             paneAabdeAziz.toFront();
          
            }
        });
        
      /******************************** PARTIE RETOUR A LA APPLICATION *********************************/
        btnPageApp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                  
                try{
                    Stage stage2 = (Stage) btnPageApp.getScene().getWindow();                 
                    Parent parent2 = FXMLLoader.load(getClass().getResource("./ApplicationFXML.fxml"));
                    Scene scene2 = new Scene(parent2);
                    
                    stage2.setScene(scene2);
                    stage2.show();
                    
                }catch (IOException ex) {
                    System.out.println("Erreur : la page trois n'exixte pas");          
                }
            }
        });        
    }    
    
}
