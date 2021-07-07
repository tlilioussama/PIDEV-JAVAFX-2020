package tn.covid19.gui;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import tn.covid19.entities.FicheMalade;
import tn.covid19.entities.GMapCoordinates;
import tn.covid19.entities.Laboratoire;
import static tn.covid19.gui.ApplicationFXMLController.ValiderEmail;
import tn.covid19.service.IserviceGmap;
import tn.covid19.service.impl.ServiceFicheMalade;
import tn.covid19.service.impl.ServiceGmap;
import tn.covid19.service.impl.ServiceLaboratoire;

public class AgentLaboFXMLController implements Initializable {

    @FXML
    private Button btnModifierfichemalade;
    @FXML
    private Button btnSupprimerfichemalade;
    @FXML
    private Button btnRetourAgentLabo;
    @FXML
    private Button btnAjouterfichemalade;
    @FXML
    private Pane paneModifierFiche;
    @FXML
    private Pane paneRechercheSupprimer;

    @FXML
    private Pane paneAjouterFiche;
    @FXML
    private Pane paneRechercheModifier;

    @FXML
    private Pane paneSupprimerFiche;
    @FXML
    private Pane paneVide;
    @FXML
    private TextField tfNomFicheMalade;
    @FXML
    private TextField tfPrenomFicheMalade;
    @FXML
    private TextField tfAgeFicheMalade;
    @FXML
    private TextField tfCinFIcheMalade;
    @FXML
    private ComboBox<String> comboChoixlabo;
    @FXML
    private ComboBox<String> comboVilleFIcheMalade;
    @FXML
    private CheckBox cbPositive;
    @FXML
    private CheckBox cbNegative;
    @FXML
    private Button btnAjouterFicheMalade;
    @FXML
    private TextField tfCinRechercherMOD;
    @FXML
    private Button btnRechercherModification;
    @FXML
    private Button btnModifierFicheConfirmer;
    @FXML
    private TextField tfNomMOD;
    @FXML
    private TextField tfPrenomMOD;
    @FXML
    private TextField tfAgeMOD;
    @FXML
    private TextField tfVilleMOD;
    @FXML
    private TextField tfEtatMOD;
    @FXML
    private TextField tfSupMaladeRech;
    @FXML
    private Button btnSupprimerFIcheMalade1;
    @FXML
    private Button btnSupFicheCO;
    @FXML
    private Label labelNomSupCO;
    @FXML
    private Label labelPrenomSupCO;
    @FXML
    private Label labelAgeSupCO;
    @FXML
    private Label labelVilleSupCO;
    @FXML
    private Label labelEtatSupCO;

    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();
    IserviceGmap MapService = new ServiceGmap();

    public int select() {
        int etat = 0;

        if (cbPositive.isSelected()) {
            etat = 1;
        }
        if (cbNegative.isSelected()) {
            etat = 0;
        }

        return etat;
    }

    public boolean isString(String texte) {
        if (texte.matches("^[a-zA-Z]+$")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isChiffre(String texte) {
        if (texte.matches("^[0-9]+$") && (texte.length() == 8)) {
            return true;
        } else {
            return false;
        }

    }

    public boolean isAge(String age) {
        if (age.matches("^[0-9]+$") && (age.length() == 2)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ServiceLaboratoire serviceLab = new ServiceLaboratoire();
        ArrayList<Laboratoire> options = new ArrayList<>();
        try {
            options = serviceLab.getLaboratoires();
        } catch (SQLException ex) {
            System.out.println("Erreur");
        }
        for (int i = 0; i < options.size(); i++) {
            //System.out.println(options.get(i).getNom_labo());
            comboChoixlabo.getItems().addAll(
                    options.get(i).getId_labo() + "_" + options.get(i).getNom_labo());
        }
        ServiceGmap serviceGmap = new ServiceGmap();
        ArrayList<GMapCoordinates> opt = new ArrayList<>();
        try {
            opt = serviceGmap.getVille();
        } catch (SQLException ex) {
            System.out.println("Erreur");
        }
        for (int i = 0; i < opt.size(); i++) {
            // System.out.println(opt.get(i).getId());
            comboVilleFIcheMalade.getItems().addAll(opt.get(i).getNomVille());

        }
        btnAjouterfichemalade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                paneVide.toFront();
                paneAjouterFiche.toFront();

            }
        });
        btnAjouterFicheMalade.setOnAction(new EventHandler<ActionEvent>() {
            ServiceLaboratoire serviceLab = new ServiceLaboratoire();
            ServiceGmap serviceGmap = new ServiceGmap();
            Laboratoire lab = new Laboratoire();
            GMapCoordinates gmap = new GMapCoordinates();

            @Override
            public void handle(ActionEvent t) {

                String nom = tfNomFicheMalade.getText();
                if (isString(nom)) {
                    tfNomFicheMalade.setStyle("-fx-border-color: #00ff00");
                } else {
                    tfNomFicheMalade.setStyle("-fx-border-color: #ff0000");
                    //tfNomInscrie.setStyle("-fx-font-size: 16");
                }
                String prenom = tfPrenomFicheMalade.getText();
                if (isString(prenom)) {
                    tfPrenomFicheMalade.setStyle("-fx-border-color: #00ff00");
                } else {
                    tfPrenomFicheMalade.setStyle("-fx-border-color: #ff0000");
                }
                String cin = tfCinFIcheMalade.getText();
                if (isChiffre(cin)) {
                    tfCinFIcheMalade.setStyle("-fx-border-color: #00ff00");
                } else {
                    tfCinFIcheMalade.setStyle("-fx-border-color: #ff0000");
                }

                String age = tfAgeFicheMalade.getText();
                if (isAge(age)) {
                    tfAgeFicheMalade.setStyle("-fx-border-color: #00ff00");
                } else {
                    tfAgeFicheMalade.setStyle("-fx-border-color: #ff0000");
                }
                String comboVilleIns = comboVilleFIcheMalade.getValue();
                if (comboVilleIns == null) {
                    comboVilleFIcheMalade.setStyle("-fx-border-color: #ff0000");
                } else {
                    comboVilleFIcheMalade.setStyle("-fx-border-color: #00ff00");
                }
                String comboLab = comboChoixlabo.getValue();
                if (comboLab == null) {
                    comboChoixlabo.setStyle("-fx-border-color: #ff0000");
                } else {
                    comboChoixlabo.setStyle("-fx-border-color: #00ff00");
                }
                String nameLab = comboChoixlabo.getValue();
                String combVille = comboVilleFIcheMalade.getValue();

                int index = nameLab.indexOf("_");
                String idString = nameLab.substring(0, index);

                System.out.println("+++++++++++++++++++++++++" + idString);
                int id = Integer.parseInt(idString);
                System.out.println("+++++++*******++++++++++++++++" + id);
                try {
                    lab = serviceLab.getLabobyId(id);
                } catch (SQLException ex) {
                    //Logger.getLogger(Test2Controller.class.getName()).log(Level.SEVERE, null, ex);
                }

                FicheMalade f = new FicheMalade();

                f.setNom_malade(tfNomFicheMalade.getText());
                f.setPrenom_malade(tfPrenomFicheMalade.getText());
                f.setAge(Integer.parseInt(tfAgeFicheMalade.getText()));
                f.setCin(tfCinFIcheMalade.getText());
                f.setVille(combVille);
                f.setEtat_analyse(select());
                f.setIdLabo(lab.getId_labo());
                System.out.println("fiche*/*/" + f.getVille());
                ServiceFicheMalade sf = new ServiceFicheMalade();
                System.out.println("loadFiche" + f.toString());
                if ((isString(nom)) && (isString(prenom)) && (isChiffre(cin)) && (isAge(age))
                        && (comboVilleIns != null) && (comboLab != null)) {

                    try {
                        gmap = serviceGmap.getGmapByNom(f.getVille());
                        sf.addFicheMalade(f);
                        JOptionPane.showMessageDialog(null, "Fiche malade ajoutée avec succée");
                        paneVide.toFront();

                        tfNomFicheMalade.setText("");
                        tfPrenomFicheMalade.setText("");
                        tfAgeFicheMalade.setText("");
                        tfCinFIcheMalade.setText("");
                        comboVilleFIcheMalade.setValue(null);
                        comboChoixlabo.setValue(null);
                        cbNegative.setSelected(false);
                        cbPositive.setSelected(false);
                        //comboville.setText("");

                        if (f.getIdLabo() == lab.getId_labo() && f.getEtat_analyse() == 1) {
                            int nbrTestPositif = lab.getNbr_test_positif();
                            int nbrAnalyse = lab.getNbrs_des_anaylses();
                            lab.setNbr_test_positif(nbrTestPositif + 1);

                            lab.setNbrs_des_anaylses(nbrAnalyse + 1);

                            serviceLab.updateLaboratoire(lab, lab.getId_labo());

                        } else {
                            int nbrAnalyse = lab.getNbrs_des_anaylses();
                            lab.setNbrs_des_anaylses(nbrAnalyse + 1);

                            serviceLab.updateLaboratoire(lab, lab.getId_labo());

                        }

                        if (f.getVille().equalsIgnoreCase(gmap.getNomVille()) && f.getEtat_analyse() == 1) {
                            int nbrTestPositif = gmap.getNbr_test_positif();
                            int nbrAnalyse = gmap.getNbrs_des_anaylses();

                            gmap.setNbrs_des_anaylses(nbrAnalyse + 1);
                            gmap.setNbr_test_positif(nbrTestPositif + 1);

                            serviceGmap.updateVille(gmap, gmap.getId());
                        } else if (f.getVille().equalsIgnoreCase(gmap.getNomVille()) && f.getEtat_analyse() == 0) {
                            int nbrAnalyse = gmap.getNbrs_des_anaylses();

                            gmap.setNbrs_des_anaylses(nbrAnalyse + 1);

                            serviceGmap.updateVille(gmap, gmap.getId());

                        }

                    } catch (SQLException ex) {
                        System.out.println("Erreur de la base");
                    }

                }

            }
        });

        btnModifierfichemalade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                paneVide.toFront();
                paneRechercheModifier.toFront();

            }
        });
        btnSupprimerfichemalade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                paneVide.toFront();
                paneRechercheSupprimer.toFront();

            }
        });
        btnSupprimerFIcheMalade1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                paneVide.toFront();
                paneRechercheSupprimer.toFront();
                paneSupprimerFiche.toFront();
                FicheMalade ut = new FicheMalade();
                ServiceFicheMalade sut = new ServiceFicheMalade();
                String cinSUp = tfSupMaladeRech.getText();

                try {
                    ut = sut.getFichebyCIN(cinSUp);
                } catch (SQLException ex) {
                    ex.getStackTrace();
                }

                System.out.println(ut);

                if (ut != null) {
                    labelNomSupCO.setText(ut.getNom_malade());
                    labelPrenomSupCO.setText(ut.getPrenom_malade());
                    labelAgeSupCO.setText(String.valueOf(ut.getAge()));
                    labelVilleSupCO.setText(ut.getVille());
                    if (ut.getEtat_analyse() == 0) {
                        labelEtatSupCO.setText("Négative");
                    } else {
                        labelEtatSupCO.setText("Positive");
                    }

                } else {
                    System.out.println("ERREUR : Utilisateur n'existe pas !");
                }
            }

        });
        btnSupFicheCO.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                paneVide.toFront();
                paneRechercheSupprimer.toFront();
                paneSupprimerFiche.toFront();

                FicheMalade f = new FicheMalade();

                f.setCin(tfSupMaladeRech.getText());

                ServiceFicheMalade sf = new ServiceFicheMalade();
                try {
                    sf.deleteFicheMalade(f);
                    JOptionPane.showMessageDialog(null, "Fiche malade supprimée avec succée");
                    paneVide.toFront();
                    tfSupMaladeRech.setText("");
                    paneVide.toFront();

                } catch (SQLException ex) {
                    System.out.println("Erreur de la base");
                }

            }
        });
        btnRechercherModification.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                paneVide.toFront();
                paneRechercheModifier.toFront();
                paneModifierFiche.toFront();
                FicheMalade f = new FicheMalade();

                f.setCin(tfCinRechercherMOD.getText());

                ServiceFicheMalade sf = new ServiceFicheMalade();
                try {
                    FicheMalade f2 = sf.getFichebyCIN(f.getCin());

                    System.out.println(f2.getId_fiche());
                    System.out.println(f2.toString());
                    tfNomMOD.setText(f2.getNom_malade());
                    tfPrenomMOD.setText(f2.getPrenom_malade());
                    tfAgeMOD.setText(String.valueOf(f2.getAge()));
                    tfCinRechercherMOD.setText(f2.getCin());
                    tfVilleMOD.setText(f2.getVille());
                    tfEtatMOD.setText(String.valueOf(f2.getEtat_analyse()));
                    // à completer champs malade et age 

                    btnModifierFicheConfirmer.setOnAction(new EventHandler<ActionEvent>() {

                        public void handle(ActionEvent t) {

                            ServiceFicheMalade sf = new ServiceFicheMalade();

                            try {

                                System.out.println(f2.getId_fiche());

                                f2.setNom_malade(tfNomMOD.getText());
                                f2.setPrenom_malade(tfPrenomMOD.getText());
                                f2.setCin(tfCinRechercherMOD.getText());
                                f2.setAge(Integer.parseInt(tfAgeMOD.getText()));
                                f2.setVille(tfVilleMOD.getText());
                                f2.setEtat_analyse(Integer.parseInt(tfEtatMOD.getText()));

                                sf.updateFicheMalade(f2, f2.getId_fiche());
                                JOptionPane.showMessageDialog(null, "Fiche malade modifiée avec succée");
                                paneVide.toFront();

                                tfNomMOD.setText("");
                                tfPrenomMOD.setText("");
                                tfAgeMOD.setText("");
                                tfCinRechercherMOD.setText("");
                                tfVilleMOD.setText("");
                                tfEtatMOD.setText("");

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

        btnRetourAgentLabo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Stage stage10 = (Stage) btnRetourAgentLabo.getScene().getWindow();
                    Parent parent10 = FXMLLoader.load(getClass().getResource("./MenuApplicationFXML.fxml"));
                    Scene scene10 = new Scene(parent10);

                    stage10.setScene(scene10);
                    stage10.show();

                } catch (IOException ex) {
                    System.out.println("Erreur : la page trois n'exixte pas");
                }

            }
        });

        List<GMapCoordinates> coordinates = MapService.getAll();

        System.out.println("*/*/*/*/" + coordinates);

        String coordinatesToJson = new Gson().toJson(coordinates);
        System.out.println(coordinatesToJson);
        webEngine.load("file:///C:/googlemaps/googlemaps.html?q=" + coordinatesToJson);

        paneVide.getChildren().add(webView);

    }

}
