package tn.covid19;

import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tn.covid19.service.impl.ServiceUtilisateur;




public class NewFXMain extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/tn/covid19/gui/ApplicationFXML.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        ServiceUtilisateur su =new ServiceUtilisateur();
        try {
            su.updateUtilisateurDeconnecter();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
