/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import org.jahap.business.acc.accountsbean;

import org.jahap.entities.Accounts;


/**
 * FXML Controller class
 *
 * @author russ
 */
public class AccListController implements Initializable {
    @FXML
    private Button PrintButton;
    @FXML
    private TableView  dataTable;
    @FXML
    private Button Ok;
    @FXML
    private Button Cancel;
    private accountsbean accbean;
    private List DialogResSearchResult;

    
    /**
     * Initializes the controller class.
     */
     

      private void initTable(){
        accbean= new accountsbean();
        DialogResSearchResult= accbean.SearchForAcc("*");
        ObservableList<Accounts> data= FXCollections.observableList(DialogResSearchResult);
        
        // -----------------  id
//        TableColumn<Accounts,String> IdCol = new TableColumn<Accounts,String>("Id");
//      IdCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Accounts, String>, ObservableValue<String>>() {
//     public ObservableValue<String> call(TableColumn.CellDataFeatures<Accounts, String> p) {
//         return new ReadOnlyObjectWrapper(p.getValue().getId());
//     }
//     
//             
//      });  
      
//      dataTable.getColumns().add(IdCol);
      
       // -----------------  Arrival
//        TableColumn<Accounts,String> ResNoCol = new TableColumn<Accounts,String>("Res No");
//      ResNoCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Accounts, String>, ObservableValue<String>>() {
//     public ObservableValue<String> call(TableColumn.CellDataFeatures<Accounts, String> p) {
//         return new ReadOnlyObjectWrapper(p.getValue().getReservation().getResno());
//     }
//     
//             
//      });  
      
//      dataTable.getColumns().add(ResNoCol);
//      
//      
//      // -----------------  Check in Date
//        TableColumn<Accounts,String> ArrDateCol = new TableColumn<Accounts,String>("Check in");
//      ArrDateCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Accounts, String>, ObservableValue<String>>() {
//     public ObservableValue<String> call(TableColumn.CellDataFeatures<Accounts, String> p) {
//         return new ReadOnlyObjectWrapper(p.getValue().getCheckindate());
//     }
//     
//             
//      });  
//      
//      dataTable.getColumns().add(ArrDateCol);
//      
       // -----------------  Name
        TableColumn<Accounts,String> NaCol = new TableColumn<Accounts,String>("Name");
      NaCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Accounts, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(TableColumn.CellDataFeatures<Accounts, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getAddress().getName());
     }
     
             
      });  
//      
      dataTable.getColumns().add(NaCol);
//      
//       // -----------------  Christianname
//        TableColumn<Accounts,String> CNameCol = new TableColumn<Accounts,String>("First name");
//      CNameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Accounts, String>, ObservableValue<String>>() {
//     public ObservableValue<String> call(TableColumn.CellDataFeatures<Accounts, String> p) {
//         return new ReadOnlyObjectWrapper(p.getValue().getAddress().getChristianname());
//     }
//     
//      
//             
//      });  
      
//      dataTable.getColumns().add(CNameCol);
      
      
        
       dataTable.setItems(data);
            
    
    
    }
    
    @FXML
    private void PrintReport(ActionEvent event) {
    }

    @FXML
    private void MouseClicked(MouseEvent event) {
    }

    @FXML
    private void OkAction(ActionEvent event) {
    }

    @FXML
    private void CancelAction(ActionEvent event) {
    }
     
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        //searchresult=new ResSearchResult();
    initTable();
    }  
    
}
