/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JRException;
import org.eclipse.persistence.internal.helper.DatabaseTable;
import org.jahap.business.base.addressbean;
import org.jahap.entities.Address;
import org.jahap.sreport.addressreports;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class ListDialogAddressController implements Initializable{
    private addressbean addresses;
    private List searchlistAddresses; // All Records of 
    @FXML
    private Button PrintButton;
     @FXML
     private TableView dataTable;
    @FXML
    private Button Ok;
    @FXML
    private Button Cancel;
    private long id=0;
    private boolean isOverwievDialog=false;
    
    AddressSearchResult searchresult;
    
    
    
    
    
    private void initTable(){
        addresses = new addressbean();
        searchlistAddresses=addresses.SearchForAddress("*");
        ObservableList<Address> data= FXCollections.observableList(searchlistAddresses);
        
        // -----------------  id
        TableColumn<Address,String> IdCol = new TableColumn<Address,String>("Id");
      IdCol.setCellValueFactory(new Callback<CellDataFeatures<Address, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Address, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getId());
     }
     
             
      });  
        
     
        
        
       //----------------------------------- Christian Name  ----------------------- 
    
        TableColumn<Address,String> firstNameCol = new TableColumn<Address,String>("First Name");
      firstNameCol.setCellValueFactory(new Callback<CellDataFeatures<Address, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Address, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getChristianname());
     }
     
             
      });  
        
      TableColumn<Address, String> col1 = new TableColumn<Address, String>("Name");        
    col1.setCellValueFactory(new PropertyValueFactory<Address, String>("Name"));  
        
      
      
      //dataTable.getColumns().add(firstNameCol);
       dataTable.getColumns().add(col1);
      
      //------------------------------------- Name --------------------------------
      
       TableColumn<Address,String> NameCol = new TableColumn<Address,String>("Name");
      NameCol.setCellValueFactory(new Callback<CellDataFeatures<Address, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Address, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getName());
     }
     
             
      });
       dataTable.getColumns().add(NameCol);
       
       
       //---------------------------------- Street --------------------------------
        
         TableColumn<Address,String> StreetCol = new TableColumn<Address,String>("Street");
      StreetCol.setCellValueFactory(new Callback<CellDataFeatures<Address, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(TableColumn.CellDataFeatures<Address, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getStreet());
     }
     
             
      });
       dataTable.getColumns().add(StreetCol);
       
        //---------------------------------- City --------------------------------
        
         TableColumn<Address,String>CityCol = new TableColumn<Address,String>("City");
      CityCol.setCellValueFactory(new Callback<CellDataFeatures<Address, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Address, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getCity());
     }
     
             
      });
       dataTable.getColumns().add(CityCol);
       
           //---------------------------------- ZipCode --------------------------------
        
         TableColumn<Address,String>ZipCol = new TableColumn<Address,String>("Zip Code");
      ZipCol.setCellValueFactory(new Callback<CellDataFeatures<Address, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Address, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getZipcode());
     }
     
             
      });
       dataTable.getColumns().add(ZipCol);
       
       
         //---------------------------------- Phone --------------------------------
        
         TableColumn<Address,String>PhoneCol = new TableColumn<Address,String>("Phone Col");
      PhoneCol.setCellValueFactory(new Callback<CellDataFeatures<Address, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Address, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getPhone());
     }
     
             
      });
       dataTable.getColumns().add(PhoneCol);
        
    dataTable.setItems(data);
    }
    
    
    
    /**
     * Initializes the controller class. Call from a Address Dialog,
     * Sets selected Address ID in Oberver
     */
    public void init(AddressSearchResult searchresults){
         isOverwievDialog=true;
         searchresult=searchresults;
        initTable();
    }
    
    public void initialize(URL url, ResourceBundle rb) {
       
       initTable();
       
    }    

    @FXML
    private void PrintReport(ActionEvent event) {
        
         List<Address> adl= new ArrayList<Address>();
        adl=searchlistAddresses;
        
        addressreports ARP = new addressreports();
        try {
            ARP.multiAdressReport(adl);
        } catch (JRException ex) {
            Logger.getLogger(ListDialogAddressController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void OkAction(ActionEvent event) throws IOException {
        if(isOverwievDialog=false){
        Stage stage = (Stage) Ok.getScene().getWindow();
        stage.close();
        }
        
        if(isOverwievDialog=true){
             Stage stage = new Stage();
        String fxmlFile = "/fxml/AdressGuiFx.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
     

        
        stage.setScene(scene);
        AdressGuiFx controller= loader.<AdressGuiFx>getController();
       controller.init(id);
       
        
        stage.showAndWait();
        }
    }

    @FXML
    private void CancelAction(ActionEvent event) {
    }

    @FXML
    private void MouseClicked(MouseEvent event) {
        
        
    
        
    Address ad=(Address) dataTable.getSelectionModel().getSelectedItem();
    id=ad.getId();
    if (isOverwievDialog=false){ searchresult.setDbRecordId(id, "Address");}
    }

    public void idinfo(AddressSearchResultEvent e) {
        
    }
       
    
    
}