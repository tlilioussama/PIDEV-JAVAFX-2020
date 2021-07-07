
package tn.covid19.gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tn.covid19.entities.Autorisation;
import tn.covid19.entities.Utilisateur;
import tn.covid19.service.impl.ServiceAutorisation;
import tn.covid19.service.impl.ServiceUtilisateur;

public class AutorisationNewFXMLController implements Initializable {

    @FXML
    private Button btnDemanderAuto, btnGestionAuto, btnRetourAuto, btnModifierAutorisation1;  
    @FXML
    private Pane paneDemanderAuto, paneGestionAuto, paneVideAuto, paneModificationAuto;
    @FXML
    private TextField textfieldCin1;
    @FXML
    private TextField textfieldNom1;
    @FXML
    private TextField textfieldAdresse1;
    @FXML
    private DatePicker datepickerDebut1;
    @FXML
    private DatePicker datepickerFin1;
    @FXML
    private Button btnenregistrer1;
    @FXML
    private CheckBox checkbox11;
    @FXML
    private CheckBox checkbox21;
    @FXML
    private CheckBox checkbox31;
    @FXML
    private CheckBox checkbox41;
    @FXML
    private CheckBox checkbox51;
    @FXML
    private TextField textfieldPrenom1;
    @FXML
    private TextField textfieldVille1;
    @FXML
    private Button btnrechercherautorisation1;
    @FXML
    private TableView<Autorisation> tableliste2;
    @FXML
    private TableColumn<Autorisation, LocalDate> tabdatedebut1;
    @FXML
    private TableColumn<Autorisation, LocalDate> tabdatefin1;
    @FXML
    private TableColumn<Autorisation, String> tabmotif1;
    
    int index=-1;
    private Connection cnx;
    private ServiceAutorisation serviceAutorisation;
    
    
    public ObservableList<Autorisation> getObservableList(){
         ServiceAutorisation sa =new ServiceAutorisation();
        ServiceUtilisateur sv = new ServiceUtilisateur();
        Utilisateur u = new Utilisateur();
        try {
            u= sv.getUtilisateurByRole(1);
        
        } catch (SQLException ex) {
            System.out.println("tn.covid19.gui.AutorisationFXMLController.UpdateTable()");
           ex.getStackTrace();
        }
 
        ArrayList<Autorisation> liste2 = new ArrayList<>();
        try {
            liste2 = sa.rechercherAutorisationparCin(u.getCin());
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return FXCollections.observableArrayList(liste2);
    }
    
     public String Select(){
        String motif="";
        
        if (checkbox11.isSelected())
            motif+=checkbox11.getText() +"\n";
        if (checkbox21.isSelected())
            motif+=checkbox21.getText() +"\n";
        if (checkbox31.isSelected())
            motif+=checkbox31.getText() +"\n";
        if (checkbox41.isSelected())
            motif+=checkbox41.getText() +"\n";
        if (checkbox51.isSelected())
            motif+=checkbox51.getText() +"\n";
        
        return motif;    
    }
     public void UpdateTable(){
                
        ServiceAutorisation sa =new ServiceAutorisation();
        ServiceUtilisateur sv = new ServiceUtilisateur();
        Utilisateur u = new Utilisateur();
        try {
            u= sv.getUtilisateurByRole(1);
        
        } catch (SQLException ex) {
            System.out.println("tn.covid19.gui.AutorisationFXMLController.UpdateTable()");
           ex.getStackTrace();
        }
        System.out.println("cin*******"+u.getCin());  

        tabdatedebut1.setCellValueFactory(new PropertyValueFactory<>("dateAutorisation"));
        tabdatefin1.setCellValueFactory(new PropertyValueFactory<>("finAutorisation"));
        tabmotif1.setCellValueFactory(new PropertyValueFactory<>("motifsAutorisation"));
        tableliste2.setItems(getObservableList());
        
         System.out.println("Liste 2  : \n"+ tableliste2 );
                
            }

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      btnDemanderAuto.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               paneVideAuto.toFront();
               paneDemanderAuto.toFront();
               
               Utilisateur u = new Utilisateur();
               ServiceUtilisateur su = new ServiceUtilisateur();
                try {
                    u=su.getUtilisateurByRole(1);
                    System.out.println(u);
                } catch (SQLException ex) {
                    ex.getStackTrace();
                }
        
        
        //Session.onligneuser= new Utilisateur(100, "jerbi", "habib", "08720776", "7 rue abdelhamid bel kadhi","ariana",2083, 98423248, "jerb.habib@mail.com", 0, 0, "habib","habib");
        textfieldNom1.setText(u.getNom());
        textfieldNom1.setEditable(false);
        textfieldPrenom1.setText(u.getPrenom());
        textfieldPrenom1.setEditable(false);
        textfieldCin1.setText(String.valueOf(u.getCin()));
        textfieldCin1.setEditable(false);
        textfieldAdresse1.setText(u.getAdresse());
        textfieldAdresse1.setEditable(false);
        textfieldVille1.setText(u.getVille());
        textfieldVille1.setEditable(false);
        
        Autorisation a = new Autorisation();
        ServiceAutorisation sa = new ServiceAutorisation();
        
        btnenregistrer1.setOnAction((ActionEvent event2) -> {
            Utilisateur u1=new Utilisateur();
            
            try {
                u1=su.getUtilisateurByRole(1);
                
                LocalDate date1= LocalDate.now();
                date1 = datepickerDebut1.getValue();
                String formattedDate1 = date1.format(DateTimeFormatter.ofPattern("dd/MMM/yyyy"));
                
                LocalDate date2=LocalDate.now();
                date2=datepickerFin1.getValue();
                String formattedDate2 = date2.format(DateTimeFormatter.ofPattern("dd/MMM/yyyy"));
                
                a.setDateAutorisation(date1);
                a.setFinAutorisation(date2);
               
                if (date1.compareTo(date2)>0) {
                        Alert diaAlert = new Alert(Alert.AlertType.INFORMATION);
                        diaAlert.setTitle("Autorisation ");
                        String g="selectionne une valide date";
                        diaAlert.setHeaderText(g);
                        diaAlert.show();
                    
                }
                else{
                a.setMotifsAutorisation(Select());
                    if (Select().isEmpty()) {
                        Alert diaAlert = new Alert(Alert.AlertType.INFORMATION);
                       diaAlert.setTitle("Autorisation ");
                       String g="selectionne votre motif";
                       diaAlert.setHeaderText(g);
                       diaAlert.show();
                    
                    }
                    else{
                        sa.AjouterAutorisation(a);
                        Document doc = new Document();
                try {
                            
                    
                            try {
                                PdfWriter.getInstance(doc, new FileOutputStream("D:\\Autorisation."));
                            } catch (DocumentException ex) {
                                Logger.getLogger(AutorisationNewFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                           
                    doc.open();
                    Image img = Image.getInstance("D:\\covid.png");
                    img.scaleAbsoluteWidth(400);
                    img.scaleAbsoluteHeight(92);
                    img.setAlignment(img.ALIGN_CENTER);
                    doc.add(img);
                    
                    Paragraph paragtitle = new Paragraph("Autorisation de sortie",FontFactory.getFont("Times New Roman",20));
                    paragtitle.setAlignment(Element.ALIGN_CENTER);
                    doc.add(paragtitle);                    
                    Paragraph paragvide1 = new Paragraph("\n");
                    doc.add(paragvide1);
                    
                    Paragraph paragnom = new Paragraph("Nom : "+u1.getNom(),FontFactory.getFont("Times New Roman",14));
                    paragnom.setAlignment(Element.ALIGN_LEFT);
                    doc.add(paragnom);
                    Paragraph paragvide2 = new Paragraph("\n");
                    doc.add(paragvide2);
                    
                    Paragraph paragprenom = new Paragraph("Prenom : "+u1.getPrenom(),FontFactory.getFont("Times New Roman",14));
                    paragprenom.setAlignment(Element.ALIGN_LEFT);
                    doc.add(paragprenom);
                    Paragraph paragvide3 = new Paragraph("\n");
                    doc.add(paragvide3);
                    
                    Paragraph paragadresse= new Paragraph("Adresse : "+u1.getAdresse(),FontFactory.getFont("Times New Roman",14));
                    paragadresse.setAlignment(Element.ALIGN_LEFT);
                    doc.add(paragadresse);
                    Paragraph paragvide4 = new Paragraph("\n");
                    doc.add(paragvide4);
                    
                    Paragraph paragville= new Paragraph("Ville : "+u1.getVille(),FontFactory.getFont("Times New Roman",14));
                    paragville.setAlignment(Element.ALIGN_LEFT);
                    doc.add(paragville);
                    Paragraph paragvide5 = new Paragraph("\n");
                    doc.add(paragvide5);                                
                                         
                    Paragraph paragdatedebut = new Paragraph("Date debut autorisation: "+formattedDate1.toString(),FontFactory.getFont("Times New Roman",14));
                    paragdatedebut.setAlignment(Element.ALIGN_LEFT);
                    doc.add(paragdatedebut);
                    Paragraph paragvide6 = new Paragraph("\n");
                    doc.add(paragvide6);
                    
                    Paragraph paragdatefin = new Paragraph("Date fin autorisation: "+formattedDate2.toString(),FontFactory.getFont("Times New Roman",14));
                    paragdatefin.setAlignment(Element.ALIGN_LEFT);
                    doc.add(paragdatefin);
                    Paragraph paragvide7 = new Paragraph("\n");
                    doc.add(paragvide7);
                    
                    Paragraph paragmotif = new Paragraph("Motif: \n"+Select().toString(),FontFactory.getFont("Times New Roman",14));
                    paragmotif.setAlignment(Element.ALIGN_LEFT);
                    doc.add(paragmotif);
                    Paragraph paragvide8 = new Paragraph("\n");
                    doc.add(paragvide8);                
                    doc.close();
                    Desktop.getDesktop().open(new File("D:\\Autorisation."));
                    
                            
                    
                    
                } catch (FileNotFoundException ex) {
                    System.out.println("pdf non cree");
                } catch (DocumentException ex) {
                    Logger.getLogger(AutorisationNewFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(AutorisationNewFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                        Alert diaAlert = new Alert(Alert.AlertType.INFORMATION);
                        diaAlert.setTitle("Autorisation Ajouter");
                        String g="Autorisation crée avec succès";
                        diaAlert.setHeaderText(g);
                        diaAlert.show();
                    }
                
                }
                
                
                
                
            }
            catch (SQLException ex) {
                ex.printStackTrace();
               
                Alert diaAlert = new Alert(Alert.AlertType.ERROR);
                diaAlert.setTitle("Autorisation Ajouter");
                diaAlert.setAlertType(Alert.AlertType.ERROR);
                diaAlert.setContentText("Autorisation non crée");
                diaAlert.show();
                     
                System.out.println("autorisation non ajouter");
                
            }
        });
             
            }
        }); 
    
      btnGestionAuto.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               paneVideAuto.toFront();
               paneGestionAuto.toFront();
          
          btnrechercherautorisation1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {               
                UpdateTable();   
            }
          });  
            }
        });
      btnModifierAutorisation1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               paneVideAuto.toFront();
               paneModificationAuto.toFront();
            }
        });
      
      btnRetourAuto.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    Stage stage10 = (Stage) btnRetourAuto.getScene().getWindow();                 
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

