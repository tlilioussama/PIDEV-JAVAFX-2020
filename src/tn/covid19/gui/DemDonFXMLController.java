
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

public class DemDonFXMLController implements Initializable {

    @FXML
    private Button btnFaireDon;
    @FXML
    private Button btnAfficherDon;
    @FXML
    private Button btnDemandeAide;
    @FXML
    private Button btnAfficherDemande;
    @FXML
    private Button btnRetourDemDon;
    @FXML
    private Pane paneFaireDon, paneVideDon;
    @FXML
    private Pane paneDemandeAide;
    @FXML
    private Pane paneAfficherDemande;
    @FXML
    private Pane paneAfficherDon;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnFaireDon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               paneVideDon.toFront();
               paneFaireDon.toFront();
            }
        });
        btnAfficherDon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               paneVideDon.toFront();
               paneAfficherDon.toFront();
            }
        });
        btnDemandeAide.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               paneVideDon.toFront();
               paneDemandeAide.toFront();
            }
        });
        btnAfficherDemande.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               paneVideDon.toFront();
               paneAfficherDemande.toFront();
            }
        });
        
        btnRetourDemDon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    Stage stage10 = (Stage) btnRetourDemDon.getScene().getWindow();                 
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
