/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.jahap.business.acc.accountsbean;
import org.jahap.business.base.ratesbean;
import org.jahap.entities.AccountPosition;
import org.jahap.entities.Accounts;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class SimpelAccountingController implements Initializable, InterAccSearchResultListener {
    @FXML
    private TableView Account_tablefx;
        private TableColumn<viewAccountPositons, String> id_Account_tablefx;
    @FXML
    private TableColumn<viewAccountPositons, String> date_Account_tablefx;
    @FXML
    private TableColumn<viewAccountPositons, String> cService_Account_tablefxColumn;
    @FXML
    private TableColumn<viewAccountPositons, String> cAmount_Account_tablefxColumn;
    @FXML
    private TableColumn<viewAccountPositons, String> dService_Account_tablefxColumn;
    @FXML
    private TableColumn<viewAccountPositons, String> dAmount_Account_tablefxColumn;
    @FXML
    private TextField balance_fxtextbox;
    @FXML
    private Button addArticle;
    @FXML
    private Button cancleArticle;
    @FXML
    private Button editArticle;
    @FXML
    private Button editRates;
    @FXML
    private Button addPayment;
    @FXML
    private Button printOverview;
    @FXML
    private Button createInvoice;
    @FXML
    private Button closeAccount;
    @FXML
    private Button AdvancedChargeRates;
    private List<viewAccountPositons> accview;
    private InterAccSearchResult accsearchresult;
    private  accountsbean acc;
    private ratesbean rates;
    private List<AccountPosition> accpos;
    private long rateid=0;
    @FXML
    private Tooltip balance_textbox_fxtooltip;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    void init(long id){
        acc=new accountsbean();
        rates= new ratesbean();
        acc.getDataRecord(id);
        initTable();
        double t=acc.getBalance();
        balance_fxtextbox.setText(String.valueOf(acc.getBalance()));
        balance_textbox_fxtooltip.setText("Total Credits: " + String.valueOf(acc.getSumofCreditsPos()) + "\n" + "Total Debits: " + 
                 String.valueOf(acc.getSumofDebitsPos()));
        
    }
    

    private void initTable(){
        // (id) -- date -- cService  -- cAmount -- dService -- dAmount
    
    //accpos=new ArrayList<AccountPosition>();
    accview=new ArrayList<viewAccountPositons>();
   
    AccountPosition zw=new AccountPosition();
    viewAccountPositons bz;
                    for(Iterator<AccountPosition> iAccPos=acc.getAccountPositionCollection().iterator();iAccPos.hasNext();){
                        
                            bz = new viewAccountPositons();
                            zw=iAccPos.next();
                            bz.setRatedate(zw.getRatedate());                            
                            bz.setDebit(zw.getDebit());
                            
                     
                      

                            // ############### Split Credit Row  #####################      
                           if(zw.getDebit()==false){
                                  bz.setcAmount(zw.getAmount());
                                  bz.setcPositionname(zw.getPositionname());
                                  bz.setcRateid(zw.getRate().getId());

                           } 

                            if(zw.getDebit()==true){
                                  bz.setdAmount(zw.getAmount());
                                  bz.setdPositionname(zw.getPositionname());
                                  bz.setdRateid(zw.getRate().getId());

                           } 
                        accview.add(bz);
                    }
                    
                    ObservableList<viewAccountPositons> data= FXCollections.observableList(accview);
        
             // #################  ID       
             id_Account_tablefx  = new TableColumn<viewAccountPositons, String>("id");
             id_Account_tablefx.setCellValueFactory(new Callback<CellDataFeatures<viewAccountPositons, String>, ObservableValue<String>>() {
             public ObservableValue<String> call(CellDataFeatures<viewAccountPositons, String> p) {
                 return new ReadOnlyObjectWrapper(p.getValue().getId());
             }       
             });

             //Account_tablefx.getColumns().add(id_Account_tablefx);
     
     
             // ############### Date
             
             //date_Account_tablefx  = new TableColumn<viewAccountPositons, String>("Date");
             date_Account_tablefx.setCellValueFactory(new Callback<CellDataFeatures<viewAccountPositons, String>, ObservableValue<String>>() {
             public ObservableValue<String> call(CellDataFeatures<viewAccountPositons, String> p) {
                 return new ReadOnlyObjectWrapper(p.getValue().getRateDateString());
             }       
             });
             
             //Account_tablefx.getColumns().add(date_Account_tablefx);
             
             // ################ cPosname
             
             // cService_Account_tablefxColumn  = new TableColumn<viewAccountPositons, String>("cPosition");
             cService_Account_tablefxColumn.setCellValueFactory(new Callback<CellDataFeatures<viewAccountPositons, String>, ObservableValue<String>>() {
             public ObservableValue<String> call(CellDataFeatures<viewAccountPositons, String> p) {
                 return new ReadOnlyObjectWrapper(p.getValue().getcPositionname());
             }       
             });
              
             cService_Account_tablefxColumn.setStyle("-fx-background-color:red");
             
             //Account_tablefx.getColumns().add(date_Account_tablefx);
             
             // #############cPrice
             cAmount_Account_tablefxColumn.setCellValueFactory(new Callback<CellDataFeatures<viewAccountPositons, String>, ObservableValue<String>>() {
             public ObservableValue<String> call(CellDataFeatures<viewAccountPositons, String> p) {
                 return new ReadOnlyObjectWrapper(p.getValue().getcAmountString());
             }       
             });
              
             cAmount_Account_tablefxColumn.setStyle("-fx-background-color:red");
             //Account_tablefx.getColumns().add(date_Account_tablefx);
             
             // ##############dPosname
              dService_Account_tablefxColumn.setCellValueFactory(new Callback<CellDataFeatures<viewAccountPositons, String>, ObservableValue<String>>() {
             public ObservableValue<String> call(CellDataFeatures<viewAccountPositons, String> p) {
                 return new ReadOnlyObjectWrapper(p.getValue().getdPositionname());
             }       
             });
              
             
             //Account_tablefx.getColumns().add(date_Account_tablefx);
             
             // #################dAmount 
              dAmount_Account_tablefxColumn.setCellValueFactory(new Callback<CellDataFeatures<viewAccountPositons, String>, ObservableValue<String>>() {
             public ObservableValue<String> call(CellDataFeatures<viewAccountPositons, String> p) {
                 return new ReadOnlyObjectWrapper(p.getValue().getdAmountString());
             }       
             });
              
             
             //Account_tablefx.getColumns().add(date_Account_tablefx);
              
              Account_tablefx.setItems(data);
     
     
    }
    @FXML
    private void addArticle(ActionEvent event) throws IOException {
         Stage stage = new Stage();
        String fxmlFile = "/fxml/RatesList.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
       

        
        stage.setScene(scene);
        RateListController controller= loader.<RateListController>getController();
       controller.init(accsearchresult,this,"rate");
       
        
        stage.showAndWait();
        
        
    }

    @FXML
    private void cancleArticle(ActionEvent event) {
    }

    @FXML
    private void editArticle(ActionEvent event) {
    }

    @FXML
    private void editRates(ActionEvent event) {
    }

    @FXML
    private void addPayment(ActionEvent event) {
    }

    @FXML
    private void printOverview(ActionEvent event) {
    }

    @FXML
    private void createInvoice(ActionEvent event) {
    }

    @FXML
    private void closeAccount(ActionEvent event) {
    }

    @FXML
    private void AdvancedChargeRates(ActionEvent event) {
    }

    public void idinfo(InterAccSearchResultEvent e) {
         if(e.getTableNameofSource()=="rate"){
           System.out.println("Rate" + String.valueOf(e.getDbRecordId()));
           
           viewAccountPositons ml=new viewAccountPositons();
           ml.setdRateid(e.getDbRecordId());
           ml.setcAmount(1);
           ml.setcAmountString("1");
           ml.setcPositionname(rates.getDataRecord(e.getDbRecordId()).getName());
           
     }
    }
}
