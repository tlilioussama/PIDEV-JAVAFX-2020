
package tn.covid19.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class DocteurFXMLController implements Initializable {

    @FXML
    private Button btnAjouterMalade, btnModifierMalade, btnSupprimerMalade, btnRetourDocteur, btnSupprimerResultatMalade,
                   btnModifierResultatMalade;  
    @FXML
    private Pane paneAjouterMalade, paneModifierMaladeConfirmer, paneRechercherModifier, paneRechercherSupprimer, paneSupprimerMalade, 
                 paneVideMalade;


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnAjouterMalade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             paneVideMalade.toFront();
             paneAjouterMalade.toFront();

            }
        }); 
              
        btnModifierMalade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             paneVideMalade.toFront();
             paneRechercherModifier.toFront(); 
            }
        }); 
        
        btnModifierResultatMalade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             paneVideMalade.toFront();
             paneRechercherModifier.toFront();
             paneModifierMaladeConfirmer.toFront();
            }
        });
                
        btnSupprimerMalade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             paneVideMalade.toFront();
             paneRechercherSupprimer.toFront();

            }
        }); 
        
        btnSupprimerResultatMalade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             paneVideMalade.toFront();
             paneRechercherSupprimer.toFront();
             paneSupprimerMalade.toFront();
            }
        }); 
               
        btnRetourDocteur.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    Stage stage10 = (Stage) btnRetourDocteur.getScene().getWindow();                 
                    Parent parent10 = FXMLLoader.load(getClass().getResource("./MenuApplicationFXML.fxml"));
                    Scene scene10 = new Scene(parent10);
                    
                    stage10.setScene(scene10);
                    stage10.show();
                    
                }catch (IOException ex) {
                    System.out.println("Erreur : la page trois n'exixte pas");          
                }
            
            }
        }); 
        
       
    }    
    
}
