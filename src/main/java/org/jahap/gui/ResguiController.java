/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.jahap.business.base.addressbean;
import org.jahap.business.res.resbean;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class ResguiController implements Initializable, InterResSearchResultListener {
    @FXML
    private Label DASH_ResOrderer_txt;
    @FXML
    private Font x1;
    @FXML
    private Label DASH_ResNo_fxtxt;
    @FXML
    private Label DASH_ResArrival_fxtxt;
    @FXML
    private Font x2;
    @FXML
    private Label DASH_ResState_fxtxt;
    @FXML
    private Label DASH_ResPax_fxtxt;
    @FXML
    private Label DASH_ResDeparture_fxtxt;
    @FXML
    private TextField Orderer_Name_fxtxtfield;
    @FXML
    private TextField Orderer_FirstName_fxtxtfield;
    @FXML
    private TextField Orderer_Street_fxtxtfield;
    @FXML
    private TextField Orderer_ZipCode_fxtxtfield;
    @FXML
    private TextField Orderer_City_fxtxtfield;
    @FXML
    private TextField Orderer_Country_fxtxtfield;
    @FXML
    private Button Orderer_Seach_fxbuton;
    @FXML
    private Button Orderer_Details_fxbutton;
    @FXML
    private TextField Guest_firstName_fxtxtfield;
    @FXML
    private TextField Guest_Country_fxtxtfield;
    @FXML
    private TextField Guest_City_fxtxtfield;
    @FXML
    private TextField Guest_Street_fxtxtfield;
    @FXML
    private TextField Guest_ZipCode_fxtxtfield;
    @FXML
    private TextField Guest_Name_fxtxtfield;
    @FXML
    private Button Guest_Search_fxbutton;
    @FXML
    private Button Guest_Details_fxbutton;
    @FXML
    private TextField Room_Code_fxtxtfield;
    @FXML
    private Button Room_Search_fxbutton;
    @FXML
    private TextField ACC_No_fxtxtfield;
    @FXML
    private TextField ACC_Balance_fxtxtfield;
    @FXML
    private Button ACC_OpenAcc_fxbutton;
    @FXML
    private Button Save_fxbutton;
    @FXML
    private Button Exit_fxbutton;
    @FXML
    private Button Print_fxbutton;
    @FXML
    private Button Search_fxbutton;
    @FXML
    private Button Toolbox_FirstRecord_fxButton;
    @FXML
    private Button Toolbox_BackwardRecord_fxbutton;
    @FXML
    private ChoiceBox<?> Toolbox_Sorting_fxchoicebox;
    @FXML
    private Button Toolbox_ForewardRecord_fxbutton;
    @FXML
    private Button Toolbox_LastRecord_fxbutton;
    
    private InterResSearchResult ressearchresult;
    private resbean res;    
    private addressbean address;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Search_Orderer(ActionEvent event) throws IOException {
         Stage stage = new Stage();
        String fxmlFile = "/fxml/AddressList.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
       

        
        stage.setScene(scene);
        ListDialogAddressController controller= loader.<ListDialogAddressController>getController();
       controller.init(ressearchresult,this,"orderaddress");
       
        
        stage.showAndWait();
        
        
    }

    @FXML
    private void Details_Orderer(ActionEvent event) {
    }

    @FXML
    private void Search_Guest(ActionEvent event) {
    }

    @FXML
    private void Details_guest(ActionEvent event) {
    }

    @FXML
    private void Search_room(ActionEvent event) {
    }

    @FXML
    private void Open_Account(ActionEvent event) {
    }

    @FXML
    private void Save(ActionEvent event) {
    }

    @FXML
    private void Exit(ActionEvent event) {
    }

    @FXML
    private void Print(ActionEvent event) {
    }

    @FXML
    private void Search(ActionEvent event) {
    }

    @FXML
    private void Toolbox_FirstRecord(ActionEvent event) {
    }

    @FXML
    private void Toolbox_BackwardRecord(ActionEvent event) {
        res.nextRecordBackward();
        FillWithSelectedData();
    }

    @FXML
    private void Toolbox_ForewardRecord(ActionEvent event) {
        res.nextRecordForeward();
        FillWithSelectedData();
    }

    @FXML
    private void Toolbox_LastRecord(ActionEvent event) {
    }
    
    public void init(long id){
        res = new resbean();
        res.setDataRecordId(id);
                      ressearchresult.addIDListener(this);
              FillWithSelectedData();
        
        
        
    }

    private void FillWithSelectedData() {
        // init DASH Board
        DASH_ResArrival_fxtxt.setText(res.getArrivaldate());
        DASH_ResDeparture_fxtxt.setText(res.getDeparturedate());
        DASH_ResNo_fxtxt.setText(res.getResno());
        DASH_ResOrderer_txt.setText(res.getAddresses().getName());
        DASH_ResPax_fxtxt.setText("1"); //DEV : Paximplenetion
        DASH_ResState_fxtxt.setText("Definitv"); //DEV : State implemnetation
        
        // init Order infos
        fillOrderer();
        
        //DEV :Init Guest infos
       
        fillGuest();
        //Res
        
        Room_Code_fxtxtfield.setText("xxx");
        
        // ACC
        ACC_Balance_fxtxtfield.setText("677");
        ACC_No_fxtxtfield.setText("009080");
        
    }

    private void fillOrderer(){
         Orderer_Name_fxtxtfield.setText(res.getAddresses().getName());
        Orderer_FirstName_fxtxtfield.setText(res.getAddresses().getChristianname());
        Orderer_Street_fxtxtfield.setText(res.getAddresses().getStreet());
        Orderer_ZipCode_fxtxtfield.setText(res.getAddresses().getZipcode());
        Orderer_City_fxtxtfield.setText(res.getAddresses().getCity());
    }
    
    private void fillOrderer(long addressid){
        
         Orderer_Name_fxtxtfield.setText(address.getDataRecord(addressid).getName());
        Orderer_FirstName_fxtxtfield.setText(address.getDataRecord(addressid).getChristianname());
        Orderer_Street_fxtxtfield.setText(address.getDataRecord(addressid).getStreet());
        Orderer_ZipCode_fxtxtfield.setText(address.getDataRecord(addressid).getZipcode());
        Orderer_City_fxtxtfield.setText(address.getDataRecord(addressid).getCity());
        
    }
    
    
    private void fillGuest(){
         Guest_Name_fxtxtfield.setText("xxxxx");
        Guest_firstName_fxtxtfield.setText("xxx");
        Guest_Street_fxtxtfield.setText("xxx");
        Guest_ZipCode_fxtxtfield.setText("xxx");
        Guest_City_fxtxtfield.setText("xxx");
    }
    
    private void fillGuest(long addressid){
         Guest_Name_fxtxtfield.setText(address.getDataRecord(addressid).getName());
        Guest_firstName_fxtxtfield.setText(address.getDataRecord(addressid).getChristianname());
        Guest_Street_fxtxtfield.setText(address.getDataRecord(addressid).getStreet());
        Guest_ZipCode_fxtxtfield.setText(address.getDataRecord(addressid).getZipcode());
        Guest_City_fxtxtfield.setText(address.getDataRecord(addressid).getCity());
    }
    
    
    public void idinfo(InterResSearchResultEvent e) {
     if(e.getTableNameofSource()=="orderaddress"){
         fillOrderer(e.getDbRecordId());
     }   
     
     if(e.getTableNameofSource()=="guestaddress"){
           fillGuest(e.getDbRecordId());
     }
    }
    
}
