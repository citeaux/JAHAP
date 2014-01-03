/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.gui;

import com.google.common.eventbus.EventBus;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.jahap.business.base.ratesbean;
import org.jahap.entities.Rates;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class EditPositionFx implements Initializable {
    @FXML
    private TextField amount_fxtextfield;
    @FXML
    private TextField Price_fxtextfield;
    @FXML
    private Button firstRecord_fxbutton;
    @FXML
    private Button oneRecordBackward_fxbutton;
    @FXML
    private Button oneRecordForward_fxbutton;
    @FXML
    private Button lastRecord_fxbutton;
    @FXML
    private Button ok;
    @FXML
    private Button cancel;
    private InterAccSearchResult accm;
    @FXML
    private TextField PositionName_fxtextfield;
    private ratesbean rbean;
    @FXML
    private TextField rate_fxtextbox;
    @FXML
    private Button search_fxbutton;
    private viewAccountPositions viewPosition;
   private  EventBus eventbus;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    void init(EventBus eventbus, viewAccountPositions jm){
         this.eventbus=eventbus;
         
         
         
         rbean=new ratesbean();
         viewPosition=jm;
         if(jm.isDebit()!=true){
         Price_fxtextfield.setText(jm.getcPriceString());
         amount_fxtextfield.setText(jm.getcAmountString());
         PositionName_fxtextfield.setText(jm.getcPositionname());
         rate_fxtextbox.setText(jm.getcPositionname());
         }
         
         if(jm.isDebit()!=false){
         Price_fxtextfield.setText(jm.getdPriceString());
         amount_fxtextfield.setText(jm.getdAmountString());
         PositionName_fxtextfield.setText(jm.getdPositionname());
         rate_fxtextbox.setText(jm.getdPositionname());
         }
         
         
    }
    

    @FXML
    private void goFirstRecord(ActionEvent event) {
    }

    @FXML
    private void goOneRecordBackward(ActionEvent event) {
    }

    @FXML
    private void goOneRecordForward(ActionEvent event) {
    }

    @FXML
    private void goLastRecord(ActionEvent event) {
    }

    @FXML
    private void OK(ActionEvent event) {
       
        
        eventbus.post(viewPosition);
        
    }

    @FXML
    private void Cancel(ActionEvent event) {
    }

    @FXML
    private void searchforrate(MouseEvent event) {
    }

    @FXML
    private void detailsofrate(MouseEvent event) {
    }
}
