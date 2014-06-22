/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.gui;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.jahap.business.acc.accountsbean;
import org.jahap.business.base.ratesbean;
import org.jahap.entities.AccountPosition;
import org.jahap.entities.Accounts;
import org.jahap.entities.Rates;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class SimpelAccountingController implements Initializable, InterAccSearchResultListener{

    
    
    
    @FXML
    private TableView Account_tablefx;
    
             private TableColumn<viewAccountPositionsProperty, String>id_Account_tablefx;                                                   
        @FXML
        private TableColumn<viewAccountPositionsProperty, String> date_Account_tablefx;
        @FXML
        private TableColumn<viewAccountPositionsProperty, String> cService_Account_tablefxColumn;
        @FXML
        private TableColumn<viewAccountPositionsProperty, String> cAmount_Account_tablefxColumn;
        @FXML
        private TableColumn<viewAccountPositionsProperty, String> dService_Account_tablefxColumn;
        @FXML
        private TableColumn<viewAccountPositionsProperty, String> dAmount_Account_tablefxColumn;
        @FXML
        private TableColumn<viewAccountPositionsProperty, String> cPrice_Account_tablefxColumn;
        @FXML
        private TableColumn<viewAccountPositionsProperty, String> dPrice_Account_tablefxColumn;
        private EventBus eventbus;
        @FXML
        private TableColumn<viewAccountPositionsProperty, String> cTotal_Account_tablefxColumn;
        @FXML
        private TableColumn<viewAccountPositionsProperty, String> dTotal_Account_tablefxColumn;
    
    
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
    private Button printOverview;
    @FXML
    private Button closeAccount;
    @FXML
    private Button AdvancedChargeRates;
    //private List<viewAccountPositions> accview;
    private InterAccSearchResult accsearchresult;
    private  accountsbean acc;
    private ratesbean rates;
    private List<AccountPosition> accpos;
    private long rateid=0;
    //private ObservableList<viewAccountPositions> data;
    final ObservableList<viewAccountPositionsProperty> datam=FXCollections.observableArrayList();
    @FXML
    private Tooltip balance_textbox_fxtooltip;
  
    private ObservableList<String> tempBills=FXCollections.observableArrayList();
    
    @FXML
    private Tab Account;
    
    
    
    private List<AccountViewer> accViewList;
  
    @FXML
    private Button createInvoiceButton;
    @FXML
    private ChoiceBox<String> movePositionToBillChoiceBox;
    @FXML
    private Button removePosfromBillbutton;
    @FXML
    private TabPane AccountTab;
    @FXML
    private Button PrintAndCloseBill_FxButton;
    @FXML
    private TitledPane x1;
    @FXML
    private TitledPane x2;
    
   
    

    @FXML
    private void removePosfromBill(ActionEvent event) {
    }
    
    
    
    private List<BillTabs> billtablist;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    void init(long id){
        //this.eventbus=even;
         //eventbus=new EventBus("Position"); 
          //eventbus.register(SimpelAccountingController.this);
          
                 
        acc=new accountsbean();
        rates= new ratesbean();
        accsearchresult = new InterAccSearchResult();
        accsearchresult.addIDListener(this);
        acc.getDataRecord(id);
        //initTable();
        testinittable();
        double t=acc.getBalance();
        balance_fxtextbox.setText(String.valueOf(acc.getBalance()));
        balance_textbox_fxtooltip.setText("Total Credits: " + String.valueOf(acc.getSumofCreditsPos()) + "\n" + "Total Debits: " + 
                 String.valueOf(acc.getSumofDebitsPos()));
       
              
        
        
    }
    



               
     
//    }
    
    void testinittable(){
            
                    AccountPosition zw=new AccountPosition();
                      viewAccountPositionsProperty bz;
               try {
            for (Iterator<AccountPosition> iAccPos = acc.getAccountPositionCollection().iterator(); iAccPos.hasNext();) {
                
                bz = new viewAccountPositionsProperty();
                zw = iAccPos.next();
                bz.setRatedate(zw.getRatedate());                
                bz.setDebit(zw.getDebit());
                bz.setId(zw.getId());
                bz.setBilled(zw.getBilled());
                bz.setCanceled(zw.isCanceled());
                
                
                try {
                    bz.setBillnamestring(zw.getBill().getBillname());
                } catch (Exception e) {
                    bz.setBillnamestring("");
                }
              
                        try {
                    bz.setBillno(zw.getBill().getBillno());
                } catch (Exception e) {
                     bz.setBillno(0); 
                }
                
           
                // ############### Split Credit Row  #####################      
                if (zw.getDebit() == false) {
                    bz.setcAmount(zw.getAmount());
                    bz.setCpositionname(zw.getPositionname());
                    bz.setcRateid(zw.getRate().getId());
                    bz.setcPrice(zw.getPrice());
                    
                }                
                
                if (zw.getDebit() == true) {
                    bz.setdAmount(zw.getAmount());
                    bz.setDpositionname(zw.getPositionname());
                    bz.setdRateid(zw.getRate().getId());
                    bz.setdPrice(zw.getPrice());
                    
                }

                // accview.add(bz);
                datam.add(bz);
            
            }            
        } catch (Exception e) {
            System.err.print("----<property set >----");
            e.printStackTrace();
        }
               
               TabPane gg=Account.getTabPane();
               billtablist= new ArrayList<BillTabs>();
              boolean gh=false;
               // Search for Billed Position
            for(Iterator<viewAccountPositionsProperty> im=datam.iterator(); im.hasNext();  ){
                 gh=false;
                 viewAccountPositionsProperty ggl=im.next();
                 
                if(ggl.getBillno()!=0){
                       // search for exiting tab with bill no
                       for(Iterator<BillTabs> tbas=billtablist.iterator();tbas.hasNext();){
                           BillTabs koller=tbas.next();   
                               if(koller.getBillno()==ggl.getBillno()) {
                                   
                                   gh=true; 
                                      // add position to Tab ObsList
                                      koller.addPosition(ggl);
                                      
                               } 
                              
                         
                        }
                       // Add new tab
                     try {
                        if (gh == false) {
                            
                            
                            BillTabs jj = new BillTabs(ggl.getBillno());

                            // add position to Tab ObsList
                            jj.addPosition(ggl);
                            
                            billtablist.add(jj);
                        }
                    } catch (Exception e) {
                         System.out.println("----<add to billtablist >-----");
                         e.printStackTrace();
                    }
                     
                     // TEMP BILLS: Bills that are not closed yet and therefore have non billno
                }else{
                    if(ggl.getBillno()==0){
                        if(ggl.getBillnamestring()!=""){
                            
                               for(Iterator<BillTabs> tbas=billtablist.iterator();tbas.hasNext();){
                           BillTabs koller=tbas.next();   
                               if(koller.getBillno()==ggl.getBillno()) {
                                   
                                   gh=true; 
                                      // add position to Tab ObsList
                                      koller.addPosition(ggl);
                                      
                               } 
                              
                         
                        }
                       // Add new tab
                     try {
                        if (gh == false) {
                            
                            
                             BillTabs jj = new BillTabs(ggl.getBillnamestring());
                              jj.addPosition(ggl);
                              billtablist.add(jj);
                              tempBills.add(ggl.getBillnamestring());
                        }
                    } catch (Exception e) {
                         System.out.println("----<add to billtablist TEMP >-----");
                         e.printStackTrace();
                    }
                            
                            
                            
                            
                             
                        }
                    }
                }
                
            } 
               
               
             for(Iterator<BillTabs>kkl=billtablist.iterator();kkl.hasNext();){
                 BillTabs mlk=kkl.next();
                 mlk.createTabel();
                 gg.getTabs().add(mlk.getBilltab());   
                 
             }  
              
             movePositionToBillChoiceBox.setItems(tempBills);
             
             
             
            
             
             
                     
           
                     
                   
        buildTable(Account_tablefx);
        
        
    }
    
    private void buildTable(TableView ko){
              // #################  ID       
            id_Account_tablefx  = new TableColumn<viewAccountPositionsProperty, String>("idx");
             id_Account_tablefx.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("idx"));
                     
           
            
             
             
              // ############### Date
                
                 
             date_Account_tablefx.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("rateDateString"));
             date_Account_tablefx .setCellFactory(new Callback<TableColumn<viewAccountPositionsProperty, String>, TableCell<viewAccountPositionsProperty, String>>() {
                  @Override
                  public TableCell<viewAccountPositionsProperty, String> call(TableColumn<viewAccountPositionsProperty, String> param) {
                      return new TableCell<viewAccountPositionsProperty, String>() {
                                 
                         @Override
                         public void updateItem(String item, boolean empty) {
                             Tooltip tol=new Tooltip("Info");
                             
                              super.updateItem(item, empty);
                              
                               int tl=getIndex();

                               if(tl<=datam.size()-1){
                                 if(datam.get(tl).isDebit()==true){
                                 // DEV: Stylshert implement
                                 // setStyle("-fx-font-style: italic;");
                                 
                                 }
                                 if(datam.get(tl).isCanceled()==true){
                                  setTextFill(Color.RED);
                                   tol.setText("This position is canceled");
                                    Tooltip.install(this, tol);
                                 }
                                 if(datam.get(tl).isBilled()==true){
                                    setTextFill(Color.GREY);
                                    String texttip=new String();
                                    texttip="This position is billed";
                                      if(datam.get(tl).isCanceled()==true){
                                         texttip= texttip + " and canceled";
                                      }
                                    tol.setText(texttip);
                                    Tooltip.install(this, tol);
                                 }
                                 setText(item);
                               }
                               
                                setText(item);
//                               }
                          }
                         
                         
                          
                         
                      };
                  }
                });  
             
                             
              // #############cAmount
             
             cAmount_Account_tablefxColumn.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("camountstring"));
             cAmount_Account_tablefxColumn.setCellFactory(new Callback<TableColumn<viewAccountPositionsProperty, String>, TableCell<viewAccountPositionsProperty, String>>() {
                  @Override
              public TableCell<viewAccountPositionsProperty, String> call(TableColumn<viewAccountPositionsProperty, String> param) {
                      return new TableCell<viewAccountPositionsProperty, String>() {
                                 
                         @Override
                         public void updateItem(String item, boolean empty) {
                             
                             
                              super.updateItem(item, empty);
                              
                               int tl=getIndex();

                                if(tl<=datam.size()-1){
                                 if(datam.get(tl).isBilled()==true){
                                 setTextFill(Color.GREY);
                                    String texttip=new String();
                                    texttip="This position is billed";
                                     
                                 }
                                 setText(item);
                             
                         } 
                          
                         }
                      };
                  }
                });  
             
        
             // ################ cPosname
             
             //cService_Account_tablefxColumn  = new TableColumn<viewAccountPositionsProperty, String>("cPosition");
             cService_Account_tablefxColumn.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("cpositionname"));
           
             
             
              // #############cPrice
            
             cPrice_Account_tablefxColumn.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("cpricestring"));
              // ################ cTotal
             
             cTotal_Account_tablefxColumn.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("ctotal"));
              
             
             
          
             
            
             
             // ##############dPosname
             
              dService_Account_tablefxColumn.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("dpositionname"));
              
             
              dService_Account_tablefxColumn.setStyle("-fx-table-cell-border-color: grey");
              
          
             // #################dAmount
          
              dAmount_Account_tablefxColumn.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("damountstring"));
            
                // #############dPrice
            
             dPrice_Account_tablefxColumn.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("dpricestring"));
             
              // ################ dTotal
                       
            dTotal_Account_tablefxColumn.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("dtotal"));
           
              
             
           
              
             
              
              
  
             
             
           List<viewAccountPositionsProperty> accview=new ArrayList<viewAccountPositionsProperty>();
   
    
   
   
   
             
       ko.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
       ko.setItems(datam);
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
        Calendar cal  = Calendar.getInstance();
        ObservableList<viewAccountPositionsProperty>  gg = FXCollections.observableArrayList();;
        gg=Account_tablefx.getSelectionModel().getSelectedItems();
        if(gg.size()==1){
        Account_tablefx.getSelectionModel().getSelectedItems();
        viewAccountPositionsProperty jh= (viewAccountPositionsProperty) Account_tablefx.getSelectionModel().getSelectedItem();
        
        
        
        viewAccountPositionsProperty ml=new viewAccountPositionsProperty();
        if(jh.isDebit()==false && jh.isCanceled()==false){   
           ml.setDebit(true);
           ml.setdRateid(jh.getcRateid());
           ml.setdAmount(jh.getcAmount());
           ml.setCanceledposition(jh.getId());
           ml.setDpositionname(jh.getCpositionname());
           ml.setdPrice(jh.getcPrice());
           
           ml.setRatedate(cal.getTime());
           
           for(int io=0;io<=datam.size()-1;io++){
            if(datam.get(io).getId()==jh.getId()){
                datam.get(io).setCanceled(true);
            }
        }
           
           datam.add(ml);
           
           Rates rate;
           rate = rates.getDataRecord(ml.getcRateid());
           //datam.remove(ml);
           acc.cancelPosition(rate,ml.getcAmount(),ml.getId(),ml.getcPrice(),ml.getDpositionname());
             balance_fxtextbox.setText(String.valueOf(acc.getBalance()));
        balance_textbox_fxtooltip.setText("Total Credits: " + String.valueOf(acc.getSumofCreditsPos()) + "\n" + "Total Debits: " + 
                 String.valueOf(acc.getSumofDebitsPos()));
        }
        }
        
    }

    @FXML
    private void editArticle(ActionEvent event) throws IOException {
////////////        
          viewAccountPositionsProperty jh= (viewAccountPositionsProperty) Account_tablefx.getSelectionModel().getSelectedItem();
          
          ObservableList<viewAccountPositionsProperty>  gg = FXCollections.observableArrayList();;
        gg=Account_tablefx.getSelectionModel().getSelectedItems();
        if(gg.size()==1){
          if(jh.isCanceled()==false && jh.getCanceledposition()==0){
         Stage stage = new Stage();
        String fxmlFile = "/fxml/EditPositionFx.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
       

        
        stage.setScene(scene);
        EditPositionFx controller= loader.<EditPositionFx>getController();
       controller.init(accsearchresult,this,"edit",jh);
      
        // accsearchresult,this,"rate"
        stage.showAndWait();
          }
          
        }
    }
    
    
    
    //@Subscribe
   
    
    
    
    @FXML
    private void editRates(ActionEvent event) throws IOException {
        
        Stage stage = new Stage();
        String fxmlFile = "/fxml/CscGuiFx.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
       

        
        stage.setScene(scene);
        CscGuiFx controller= loader.<CscGuiFx>getController();
       controller.init(acc.getAccount().getId());
      
        // accsearchresult,this,"rate"
        stage.showAndWait();
    }

    @FXML
    private void printOverview(ActionEvent event) {
    }

    @FXML
    private void createInvoice(ActionEvent event) {
        
                
        BillTabs jj=new BillTabs("Temp");
        jj.createTabel(); // Create Tabel in TAB 
        billtablist.add(jj);
         TabPane gg=Account.getTabPane();
        gg.getTabs().add(jj.getBilltab());
        tempBills.add(jj.getBilltab().getText());
        
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
           
           viewAccountPositionsProperty ml=new viewAccountPositionsProperty();
           
           ml.setDebit(false);
           ml.setcRateid(e.getDbRecordId());
           ml.setcAmount(1);
           ml.setcRateid(e.getDbRecordId());
           Rates rate=rates.getDataRecord(e.getDbRecordId());
           ml.setCpositionname(rate.getName());
           ml.setcPrice(rates.getPrice());
           datam.add(ml);
           datam.add(ml);
           datam.remove(ml);
           acc.addPosition(rate,1,rate.getPrice(),rate.getName());
             balance_fxtextbox.setText(String.valueOf(acc.getBalance()));
        balance_textbox_fxtooltip.setText("Total Credits: " + String.valueOf(acc.getSumofCreditsPos()) + "\n" + "Total Debits: " + 
                 String.valueOf(acc.getSumofDebitsPos()));
       }
       if(e.getTableNameofSource()=="edit"){
                   int ik=0;
                 viewAccountPositionsProperty view=(viewAccountPositionsProperty)e.getEventObject(); 
              
                for(ik=0;datam.get(ik).getId()!=view.getId();){
                     ik++;
                }



                  datam.add(view);
                  datam.remove(view);

                  System.out.println(view.getCpositionname());
                  acc.adjustPosition(view.getId(),view.getAccountPosition());

                  balance_fxtextbox.setText(String.valueOf(acc.getBalance()));
                balance_textbox_fxtooltip.setText("Total Credits: " + String.valueOf(acc.getSumofCreditsPos()) + "\n" + "Total Debits: " + 
                         String.valueOf(acc.getSumofDebitsPos()));
                  System.err.println("After");
       }
}

    @FXML
    private void MovePosition(ActionEvent event) {
        for(Iterator<BillTabs> k= billtablist.listIterator();k.hasNext();){
             BillTabs ko=k.next();
                    if(ko.getBillname()==movePositionToBillChoiceBox.getValue()){
                        ObservableList<viewAccountPositionsProperty> jh=  Account_tablefx.getSelectionModel().getSelectedItems();
                        boolean haki=false;
                        
                        // Is a Position allready billed or marked for Billing?
                        for(Iterator<viewAccountPositionsProperty> olk=jh.listIterator();olk.hasNext();){
                          if(olk.next().getBillno()!=0 || olk.next().getBillnamestring()!="") haki=true;
                        
                        }
                        
                        //Only if the position is not marked (has no Billno / or Billname) move to new bill and mark as be in bill processing
                        if(haki!=true){
                        
                        ko.addPositions(jh); // add all marked Position
                         for(Iterator<viewAccountPositionsProperty> lop=jh.listIterator();lop.hasNext();){
                               viewAccountPositionsProperty fou=lop.next();
                               fou.setBillnamestring(movePositionToBillChoiceBox.getValue());
                               
                         }
                        }
                        
                    }  
                   
        
        
    }
        
       
    }

    @FXML
    private void PrintAndCloseBill(ActionEvent event) {
    }
     
}