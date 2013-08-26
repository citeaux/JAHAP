/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.gui;

/**
 *
 * @author russ
 */


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;


public class MainGuiFx implements Initializable {

    @FXML
    private TitledPane x1;

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private MenuItem newAddress;

 /*
    @FXML
    void newAddress(ActionEvent event) throws IOException {
        Stage second=new Stage();
      Parent root = FXMLLoader.load(getClass().getResource("AdressGuiFx.fxml"));
        
        Scene scene = new Scene(root);
        
        second.setScene(scene);
        second.show();
    }
*/
   

    @FXML
    private void newAddress(ActionEvent event) throws IOException {
     
        Stage stage = new Stage();
        String fxmlFile = "/fxml/AdressGuiFx.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
     

        
        stage.setScene(scene);
        stage.show();
        
    }

    public void initialize(URL url, ResourceBundle rb) {
       
    }

   
    
    
}
