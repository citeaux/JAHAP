/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.gui;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
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
        private TableColumn<viewAccountPositions, String> id_Account_tablefx;
    @FXML
    private TableColumn<viewAccountPositions, String> date_Account_tablefx;
    @FXML
    private TableColumn<viewAccountPositions, String> cService_Account_tablefxColumn;
    @FXML
    private TableColumn<viewAccountPositions, String> cAmount_Account_tablefxColumn;
    @FXML
    private TableColumn<viewAccountPositions, String> dService_Account_tablefxColumn;
    @FXML
    private TableColumn<viewAccountPositions, String> dAmount_Account_tablefxColumn;
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
    private List<viewAccountPositions> accview;
    private InterAccSearchResult accsearchresult;
    private  accountsbean acc;
    private ratesbean rates;
    private List<AccountPosition> accpos;
    private long rateid=0;
    private ObservableList<viewAccountPositions> data;
    @FXML
    private Tooltip balance_textbox_fxtooltip;
    @FXML
    private TableColumn<viewAccountPositions, String> cPrice_Account_tablefxColumn;
    @FXML
    private TableColumn<viewAccountPositions, String> dPrice_Account_tablefxColumn;
    private EventBus eventbus;
    @FXML
    private TableColumn<viewAccountPositions, String> cTotal_Account_tablefxColumn;
    @FXML
    private TableColumn<viewAccountPositions, String> dTotal_Account_tablefxColumn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    void init(long id){
         eventbus=new EventBus("Position"); 
          eventbus.register(this);
        acc=new accountsbean();
        rates= new ratesbean();
        accsearchresult = new InterAccSearchResult();
        accsearchresult.addIDListener(this);
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
    accview=new ArrayList<viewAccountPositions>();
   
    AccountPosition zw=new AccountPosition();
    viewAccountPositions bz;
                    for(Iterator<AccountPosition> iAccPos=acc.getAccountPositionCollection().iterator();iAccPos.hasNext();){
                        
                            bz = new viewAccountPositions();
                            zw=iAccPos.next();
                            bz.setRatedate(zw.getRatedate());                            
                            bz.setDebit(zw.getDebit());
                            bz.setId(zw.getId());
                     
                      

                            // ############### Split Credit Row  #####################      
                           if(zw.getDebit()==false){
                                  bz.setcAmount(zw.getAmount());
                                  bz.setcPositionname(zw.getPositionname());
                                  bz.setcRateid(zw.getRate().getId());
                                  bz.setcPrice(zw.getPrice());
                                  

                           } 

                            if(zw.getDebit()==true){
                                  bz.setdAmount(zw.getAmount());
                                  bz.setdPositionname(zw.getPositionname());
                                  bz.setdRateid(zw.getRate().getId());
                                  bz.setdPrice(zw.getPrice());

                           } 
                        accview.add(bz);
                    }
                    
                     data= FXCollections.observableList(accview);
                  
             // #################  ID       
             id_Account_tablefx  = new TableColumn<viewAccountPositions, String>("id");
             id_Account_tablefx.setCellValueFactory(new Callback<CellDataFeatures<viewAccountPositions, String>, ObservableValue<String>>() {  
                             
                 public ObservableValue<String> call(CellDataFeatures<viewAccountPositions, String> p) {
                 
                 return new ReadOnlyObjectWrapper(p.getValue().getId());
             }       
             });
             
      

             //Account_tablefx.getColumns().add(id_Account_tablefx);
     
     
             // ############### Date
             
             //date_Account_tablefx  = new TableColumn<viewAccountPositons, String>("Date");
             
             
             
              // ############### Date
                
             //date_Account_tablefx  = new TableColumn<viewAccountPositions, String>("Date");
             
             
              date_Account_tablefx.setCellValueFactory(new Callback<CellDataFeatures<viewAccountPositions, String>, ObservableValue<String>>() {  
                             
                 public ObservableValue<String> call(CellDataFeatures<viewAccountPositions, String> p) {
                 
                 return new ReadOnlyObjectWrapper(p.getValue().getRatedate());
             }       
             });
            
           
          // Account_tablefx.getColumns().add(date_Account_tablefx);
 
             
              // ################ cTotal
             
             // cService_Account_tablefxColumn  = new TableColumn<viewAccountPositons, String>("cPosition");
             cTotal_Account_tablefxColumn.setCellValueFactory(new Callback<CellDataFeatures<viewAccountPositions, String>, ObservableValue<String>>() {
             public ObservableValue<String> call(CellDataFeatures<viewAccountPositions, String> p) {
                 return new ReadOnlyObjectWrapper(p.getValue().getcTotal());
             }       
             });
              
             cTotal_Account_tablefxColumn.setStyle("-fx-background-color:red");
             
             
             // ################ dTotal
             
             // cService_Account_tablefxColumn  = new TableColumn<viewAccountPositons, String>("cPosition");
             dTotal_Account_tablefxColumn.setCellValueFactory(new Callback<CellDataFeatures<viewAccountPositions, String>, ObservableValue<String>>() {
             public ObservableValue<String> call(CellDataFeatures<viewAccountPositions, String> p) {
                 return new ReadOnlyObjectWrapper(p.getValue().getdTotal());
             }       
             });
              
            
             
             
             
             // ################ cPosname
             
             // cService_Account_tablefxColumn  = new TableColumn<viewAccountPositons, String>("cPosition");
             cService_Account_tablefxColumn.setCellValueFactory(new Callback<CellDataFeatures<viewAccountPositions, String>, ObservableValue<String>>() {
             public ObservableValue<String> call(CellDataFeatures<viewAccountPositions, String> p) {
                 return new ReadOnlyObjectWrapper(p.getValue().getcPositionname());
             }       
             });
              
             cService_Account_tablefxColumn.setStyle("-fx-background-color:red");
             
             //Account_tablefx.getColumns().add(date_Account_tablefx);
             
             // #############cAmount
             cAmount_Account_tablefxColumn.setCellValueFactory(new Callback<CellDataFeatures<viewAccountPositions, String>, ObservableValue<String>>() {
             public ObservableValue<String> call(CellDataFeatures<viewAccountPositions, String> p) {
                 return new ReadOnlyObjectWrapper(p.getValue().getcAmountString());
             }       
             });
              
             cAmount_Account_tablefxColumn.setStyle("-fx-background-color:red");
             
              // #############cPrice
             cPrice_Account_tablefxColumn.setCellValueFactory(new Callback<CellDataFeatures<viewAccountPositions, String>, ObservableValue<String>>() {
             public ObservableValue<String> call(CellDataFeatures<viewAccountPositions, String> p) {
                 return new ReadOnlyObjectWrapper(p.getValue().getcPriceString());
             }       
             });
              
             cAmount_Account_tablefxColumn.setStyle("-fx-background-color:red");
             
             //Account_tablefx.getColumns().add(date_Account_tablefx);
             
             // ##############dPosname
              dService_Account_tablefxColumn.setCellValueFactory(new Callback<CellDataFeatures<viewAccountPositions, String>, ObservableValue<String>>() {
             public ObservableValue<String> call(CellDataFeatures<viewAccountPositions, String> p) {
                 return new ReadOnlyObjectWrapper(p.getValue().getdPositionname());
             }       
             });
              
             
             //Account_tablefx.getColumns().add(date_Account_tablefx);
             
             // #################dAmount 
              dAmount_Account_tablefxColumn.setCellValueFactory(new Callback<CellDataFeatures<viewAccountPositions, String>, ObservableValue<String>>() {
             public ObservableValue<String> call(CellDataFeatures<viewAccountPositions, String> p) {
                 return new ReadOnlyObjectWrapper(p.getValue().getdAmountString());
             }       
             });
              
              
                // #############dPrice
             dPrice_Account_tablefxColumn.setCellValueFactory(new Callback<CellDataFeatures<viewAccountPositions, String>, ObservableValue<String>>() {
             public ObservableValue<String> call(CellDataFeatures<viewAccountPositions, String> p) {
                 return new ReadOnlyObjectWrapper(p.getValue().getdPriceString());
             }       
             });
              
             cAmount_Account_tablefxColumn.setStyle("-fx-background-color:red"); 
              
             
             //Account_tablefx.getColumns().add(date_Account_tablefx);
              
              Account_tablefx.setItems(data);
              
//              
//                date_Account_tablefx .setCellFactory(new Callback<TableColumn<viewAccountPositions, String>, TableCell<viewAccountPositions, String>>() {
//  @Override
//  public TableCell call(TableColumn param) {
//      return new TableCell() {
//
//         @Override
//          protected void updateItem(Object item, boolean empty) {
//              super.updateItem(item, empty);
//
//              
//                 // setTextFill(Color.RED);
//                
//                setText(item.toString());
//              
//          }
//      };
//  }
//});

               
     
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
        viewAccountPositions jh= (viewAccountPositions) Account_tablefx.getSelectionModel().getSelectedItem();
        
        
        viewAccountPositions ml=new viewAccountPositions();
        if(jh.isDebit()==false){   
           ml.setDebit(true);
           ml.setdRateid(jh.getcRateid());
           ml.setdAmount(jh.getcAmount());
    
           ml.setdPositionname(jh.getcPositionname());
           ml.setdPrice(jh.getcPrice());
           accview.add(ml);
           data.add(ml);
           data.remove(ml);
           acc.cancelPosition(rates.getDataRecord(ml.getId()),ml.getcAmount(),ml.getId(),rates.getDataRecord(ml.getId()).getPrice(),rates.getDataRecord(ml.getId()).getName());
             balance_fxtextbox.setText(String.valueOf(acc.getBalance()));
        balance_textbox_fxtooltip.setText("Total Credits: " + String.valueOf(acc.getSumofCreditsPos()) + "\n" + "Total Debits: " + 
                 String.valueOf(acc.getSumofDebitsPos()));
        }
    }

    @FXML
    private void editArticle(ActionEvent event) throws IOException {
////////////        
          viewAccountPositions jh= (viewAccountPositions) Account_tablefx.getSelectionModel().getSelectedItem();
          
          
          
         Stage stage = new Stage();
        String fxmlFile = "/fxml/EditPositionFx.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
       

        
        stage.setScene(scene);
        EditPositionFx controller= loader.<EditPositionFx>getController();
       controller.init(eventbus, jh);
       
        
        stage.showAndWait();
        
    }
    
    @Subscribe
    public void listenForPositionEdit(EditPositionEvent event){
        int ik=0;
        
        for(ik=0;accview.get(ik).getId()!=event.getPosition().getId();){
             ik++;
        }
        
        accview.set(ik, event.getPosition());
            
          data.add(event.getPosition());
          data.remove(event.getPosition());
        
          System.out.println(event.getPosition().getcPositionname());
          acc.adjustPosition(event.getPosition().getId(),event.getPosition().getAccountPosition());
          
          balance_fxtextbox.setText(String.valueOf(acc.getBalance()));
        balance_textbox_fxtooltip.setText("Total Credits: " + String.valueOf(acc.getSumofCreditsPos()) + "\n" + "Total Debits: " + 
                 String.valueOf(acc.getSumofDebitsPos()));
          
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
           
           viewAccountPositions ml=new viewAccountPositions();
           
           ml.setDebit(false);
           ml.setcRateid(e.getDbRecordId());
           ml.setcAmount(1);
           ml.setcRateid(e.getDbRecordId());
           
           ml.setcPositionname(rates.getDataRecord(e.getDbRecordId()).getName());
           ml.setcPrice(rates.getPrice());
           accview.add(ml);
           data.add(ml);
           data.remove(ml);
           acc.addPosition(rates.getDataRecord(ml.getId()),1,rates.getDataRecord(ml.getId()).getPrice(),rates.getDataRecord(ml.getId()).getName());
             balance_fxtextbox.setText(String.valueOf(acc.getBalance()));
        balance_textbox_fxtooltip.setText("Total Credits: " + String.valueOf(acc.getSumofCreditsPos()) + "\n" + "Total Debits: " + 
                 String.valueOf(acc.getSumofDebitsPos()));
    }
}
}