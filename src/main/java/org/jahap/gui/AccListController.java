/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jahap.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JRException;
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
    
    private  accountsbean accbean;
      private List accList;
    
       private void initTable(){
       accbean  = new accountsbean();
        accList = accbean.getAccOverview("*");
        ObservableList data= FXCollections.observableList(accList);
        
        
        
//        // -----------------  id
//        TableColumn<accClass,String> IdCol = new TableColumn<accClass,String>("Id");
//      IdCol.setCellValueFactory(new Callback<CellDataFeatures<accClass, String>, ObservableValue<String>>() {
//     public ObservableValue<String> call(CellDataFeatures<accClass, String> p) {
//         return new ReadOnlyObjectWrapper(p.getValue());
//     }
//     
//             
//      });  
//      
//      dataTable.getColumns().add(IdCol);
      
     
       dataTable.setItems(data);
            
    
    
    }
      
      
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
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
    
}
