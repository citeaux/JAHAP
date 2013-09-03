/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import org.eclipse.persistence.internal.helper.DatabaseTable;
import org.jahap.business.base.addressbean;
import org.jahap.entities.Address;


/**
 * FXML Controller class
 *
 * @author russ
 */
public class ListDialogAddressController implements Initializable {
    private addressbean addresses;
    private List searchlistAddresses; // All Records of 
    @FXML
    private Button printButton;
     @FXML
     private TableView<Address>dataTable;
    
    
    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
        addresses = new addressbean();
        searchlistAddresses=addresses.SearchForAddress("*");
        ObservableList<Address> data= FXCollections.observableList(searchlistAddresses);
        dataTable = new TableView<Address>(data);
        //
        //dataTable.setItems(data);
      dataTable.setItems(data);
        
       //----------------------------------- Christian Name  ----------------------- 
        
        TableColumn<Address,String> firstNameCol = new TableColumn<Address,String>("First Name");
      firstNameCol.setCellValueFactory(new Callback<CellDataFeatures<Address, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Address, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getChristianname());
     }
     
             
      });
      
      
      dataTable.getColumns().add(firstNameCol);
       
      
      //------------------------------------- Name --------------------------------
      
       TableColumn<Address,String> NameCol = new TableColumn<Address,String>("Name");
      firstNameCol.setCellValueFactory(new Callback<CellDataFeatures<Address, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Address, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getName());
     }
     
             
      });
       dataTable.getColumns().add(NameCol);
       
       
       //---------------------------------- Street --------------------------------
        
         TableColumn<Address,String> StreetCol = new TableColumn<Address,String>("Street");
      firstNameCol.setCellValueFactory(new Callback<CellDataFeatures<Address, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Address, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getStreet());
     }
     
             
      });
       dataTable.getColumns().add(StreetCol);
       
        //---------------------------------- City --------------------------------
        
         TableColumn<Address,String>CityCol = new TableColumn<Address,String>("City");
      firstNameCol.setCellValueFactory(new Callback<CellDataFeatures<Address, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Address, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getCity());
     }
     
             
      });
       dataTable.getColumns().add(CityCol);
       
           //---------------------------------- ZipCode --------------------------------
        
         TableColumn<Address,String>ZipCol = new TableColumn<Address,String>("Zip Code");
      firstNameCol.setCellValueFactory(new Callback<CellDataFeatures<Address, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Address, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getZipcode());
     }
     
             
      });
       dataTable.getColumns().add(ZipCol);
       
       
         //---------------------------------- Phone --------------------------------
        
         TableColumn<Address,String>PhoneCol = new TableColumn<Address,String>("Phone Col");
      firstNameCol.setCellValueFactory(new Callback<CellDataFeatures<Address, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Address, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getPhone());
     }
     
             
      });
       dataTable.getColumns().add(PhoneCol);
        
    
       
       
    }    

    @FXML
    private void printReport(ActionEvent event) {
        
        dataTable.getColumns().get(0).setText("www");
    }
}
