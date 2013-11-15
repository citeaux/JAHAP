/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import net.sf.jasperreports.engine.JRException;
import org.jahap.business.base.addressbean;
import org.jahap.business.base.ratesbean;
import org.jahap.entities.Rates;
import static org.jahap.gui.AdressGuiFx.addresses;
import org.jahap.sreport.ratereports;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class RateGuiFx implements Initializable, RateSearchResultListener {
    @FXML
    private TextField ratecode_fxtextfield;
    @FXML
    private TextField ratename_fxtextfield;
    @FXML
    private TextField RateBasePrice_fxtextfield;
    @FXML
    private TextField ratevat_fxtextfield;
    @FXML
    private TextField RateRevAccount_fxtextfield;
    @FXML
    private Button firstRecord_fxbutton;
    @FXML
    private Button oneRecordBackward_fxbutton;
    @FXML
    private Button oneRecordForward_fxbutton;
    @FXML
    private Button lastRecord_fxbutton;
    @FXML
    private Button search;
    @FXML
    private Button printAdress;

    private static ratesbean rates;
    private List<TextField> textfields;
    private RateSearchResult searchresults;
    private long ratesid=0;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textfields=new ArrayList<TextField>();
         textfields.add(ratecode_fxtextfield);
         textfields.add(ratevat_fxtextfield);
         textfields.add(ratename_fxtextfield);
         textfields.add(RateRevAccount_fxtextfield);
         textfields.add(RateBasePrice_fxtextfield);
         
         rates= new ratesbean();
         searchresults = new RateSearchResult();
         searchresults.addIDListener(this);
         
    }    

    @FXML
    private void goFirstRecord(ActionEvent event) {
    }

    @FXML
    private void goOneRecordBackward(ActionEvent event) {
        rates.nextRecordBackward();
        FillWithSelectedData();
        
        
    }

    @FXML
    private void goOneRecordForward(ActionEvent event) {
        rates.nextRecordForeward();
        FillWithSelectedData();
    }

    @FXML
    private void goLastRecord(ActionEvent event) {
    }

    @FXML
    private void searchAdress(ActionEvent event) {
    }

    @FXML
    private void printAdress(ActionEvent event) throws JRException {
         List<Rates> adl= new ArrayList<Rates>();
       adl= rates.getCurrentRate();
        
        ratereports ARP = new ratereports();
        ARP.singleRateReport(adl);
        
    }
    
    
    public void init(long id){
        rates = new ratesbean();
        rates.setDataRecordId(id);
                      
              FillWithSelectedData();
        
        
        
    }

    public void idinfo(RateSearchResultEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void FillWithSelectedData() {
        ratename_fxtextfield.setText(rates.getName());
        ratecode_fxtextfield.setText(rates.getCode());
        RateRevAccount_fxtextfield.setText( String.valueOf(rates.getRevaccount()));
        RateBasePrice_fxtextfield.setText(String.valueOf(rates.getPrice()));
        
    }
    
}
