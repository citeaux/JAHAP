/*
 * The MIT License
 *
 * Copyright 2014 Sebastian Russ <citeaux at https://github.com/citeaux/JAHAP>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */


package org.jahap.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import net.sf.jasperreports.engine.JRException;
import org.jahap.CurrentUser;
import org.jahap.business.acc.vatbean;
import org.jahap.business.base.addressbean;
import org.jahap.business.base.ratesbean;
import org.jahap.business.base.vattypesbean;
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
    private Button search;
    @FXML
    private Button printAdress;

    private static ratesbean rates;
    private List<TextField> textfields;
    private RateSearchResult searchresults;
    private long ratesid=0;
    @FXML
        private CheckBox OvernightRate_fxCheckBox;
    @FXML
    private Button firstRecord_fxbutton;
    @FXML
    private Button oneRecordBackward_fxbutton;
    @FXML
    private Button oneRecordForward_fxbutton;
    @FXML
    private Button lastRecord_fxbutton;
    private CurrentUser cuser;
    @FXML
    private ChoiceBox<String> revAccount;
    @FXML
    private ChoiceBox<String> vat;
    @FXML
    private Button newRate;
    @FXML
    private Button saveRate;
    
    
    private vattypesbean vbean;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vbean=new vattypesbean();
        textfields=new ArrayList<TextField>();
         textfields.add(ratecode_fxtextfield);
         
         
         
         textfields.add(ratename_fxtextfield);
         
         textfields.add(RateBasePrice_fxtextfield);
         
         rates= new ratesbean();
         searchresults = new RateSearchResult();
         searchresults.addIDListener(this);
         cuser= CurrentUser.getCurrentUser();
         if(!cuser.isIsAdmin())saveRate.setVisible(false);
         
        ObservableList<String> data= FXCollections.observableList(vbean.SearchForRateString(null));
         
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
        OvernightRate_fxCheckBox.setSelected(rates.getOvernight());
        
    }

    @FXML
    private void goFirstRecord(ActionEvent event) {
    }

    @FXML
    private void goLastRecord(ActionEvent event) {
    }

    @FXML
    private void newRate(ActionEvent event) {
    }

    @FXML
    private void saveRate(ActionEvent event) {
    }
    
}
