/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jahap.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import org.jahap.business.acc.accountsbean;
import org.jahap.business.acc.cscbean;
import org.jahap.entities.Csc;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class CscGuiFx implements Initializable {
    @FXML
    private TitledPane x1;
    @FXML
    private TableColumn<viewCSCpositionProperty, String> overnight_csc_tablefx;
    @FXML
    private TableColumn<viewCSCpositionProperty, String> from_csc_tablefx;
    @FXML
    private TableColumn<viewCSCpositionProperty, String> to_csc_tablefx;
    @FXML
    private TableColumn<viewCSCpositionProperty, String> amount_csc_tablefx;
    @FXML
    private TableColumn<viewCSCpositionProperty, String> service_csc_tablefx;
    @FXML
    private TableColumn<viewCSCpositionProperty, String> price_csc_tablefx;
    @FXML
    private TableColumn<viewCSCpositionProperty, String> total_csc_tablefx;
    @FXML
    private Button ok;
    @FXML
    private Button addRate;
    @FXML
    private Button editRate;
    @FXML
    private Button CancelRate;
    @FXML
    private Button printRate;

    private cscbean cscrates;
    private List<Csc> cscpos;
    private  accountsbean acc;
    final ObservableList<viewCSCpositionProperty> datam=FXCollections.observableArrayList();;
    @FXML
    private Tooltip balance_textbox_fxtooltip;
    private List<viewCSCpositionProperty> haku = new ArrayList<viewCSCpositionProperty>();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        acc=new accountsbean();
    }    
    
    void inittable(){
        Csc zw= new Csc();
        viewCSCpositionProperty kl;
        
        for(Iterator<Csc> iCscPos=acc.getCscCollection().iterator();iCscPos.hasNext();){
             kl = new viewCSCpositionProperty();
             zw= iCscPos.next();
             kl.setAmount(zw.getAmount());
             kl.setFrom(zw.getFromdate());
             kl.setTo(zw.getTodate());
             kl.setOvernight(zw.getRate().getOvernight());
             kl.setRateId(zw.getRate().getId());
             
        }
        
        
    }
    

    @FXML
    private void ok(ActionEvent event) {
    }

    @FXML
    private void addRate(ActionEvent event) {
    }

    @FXML
    private void editRate(ActionEvent event) {
    }

    @FXML
    private void CancelRate(ActionEvent event) {
    }

    @FXML
    private void printRate(ActionEvent event) {
    }
    
}
