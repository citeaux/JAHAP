/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jahap.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import net.sf.jasperreports.engine.JRException;
import org.jahap.business.acc.accountsbean;
import org.jahap.entities.Accounts;
import org.jahap.entities.SimpleList;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class AccListController implements Initializable {
    @FXML
    private Button PrintButton;
    @FXML
    private TableView<SimpleList> dataTable;
    @FXML
    private Button Ok;
    @FXML
    private Button Cancel;
    
    
    public static final String key1="Name";
    
    private  accountsbean accbean;
      private List<SimpleList> accList;
    
       private void initTable(){
       accbean  = new accountsbean();
        accList = accbean.getAccOverview("*");
  ObservableList data= accbean.getAccOverview("*");
        
        
        
//         -----------------  id
//        
//        TableColumn<Map, String> IdCol = new TableColumn<>("Name");
//        
//        IdCol.setCellFactory(new MapValueFactory(key1));
//       
//        dataTable.getColumns().setAll(IdCol);
//        
//       
//            Callback<TableColumn<Map, String>, TableCell<Map, String>>
//            cellFactoryForMap = new Callback<TableColumn<Map, String>,
//                TableCell<Map, String>>() {
//                    @Override
//                    public TableCell call(TableColumn p) {
//                        return new TextFieldTableCell(new StringConverter() {
//                            @Override
//                            public String toString(Object t) {
//                                return t.toString();
//                            }
//                            @Override
//                            public Object fromString(String string) {
//                                return string;
//                            }                                    
//                        });
//                    }
//        };
//        
//            IdCol.setCellFactory(cellFactoryForMap);
        
        TableColumn  IdCol = new TableColumn("Id");
      IdCol.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<ObservableList, String> p) {
         return new SimpleStringProperty(p.getValue().get(j).toString());
     }
     
             
      });  
      
      dataTable.getColumns().add(IdCol);
      
      
      
      
     
      
      
       TableColumn<SimpleList,String> fromDate = new TableColumn<SimpleList,String>("From");
      IdCol.setCellValueFactory(new Callback<CellDataFeatures<SimpleList, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<SimpleList, String> k) {
         return new ReadOnlyObjectWrapper(k.getValue().getCellValue(1));
     }
     
             
      });  
      fromDate.setMinWidth(100);
      dataTable.getColumns().add(fromDate);
//      
//      
//      
//       TableColumn<SimpleList,String> toDate = new TableColumn<SimpleList,String>("To");
//      IdCol.setCellValueFactory(new Callback<CellDataFeatures<SimpleList, String>, ObservableValue<String>>() {
//     public ObservableValue<String> call(CellDataFeatures<SimpleList, String> p) {
//         return new ReadOnlyObjectWrapper(p.getValue().getCellValue(2));
//     }
//     
//             
//      });  
//      toDate.setMinWidth(100);
//      dataTable.getColumns().add(toDate);
//      
//      
//         TableColumn<SimpleList,String> name = new TableColumn<SimpleList,String>("Name");
//      IdCol.setCellValueFactory(new Callback<CellDataFeatures<SimpleList, String>, ObservableValue<String>>() {
//     public ObservableValue<String> call(CellDataFeatures<SimpleList, String> p) {
//         return new ReadOnlyObjectWrapper(p.getValue().getCellValue(3));
//     }
//     
//             
//      });  
//      name.setMinWidth(200);
//      dataTable.getColumns().add(name);
      
    dataTable.setItems(data);
            
    
    
    }
      
     private ObservableList<Map> generateDataInMap(List<SimpleList> gg) {
        int max = 10;
        ObservableList<Map> allData = FXCollections.observableArrayList();
        for (int i = -1; gg.size()<=i;i++ ) {
            Map<String, String> dataRow = new HashMap<>();
 
            String value1 = gg.get(i).getCellValue(3);
            
 
            dataRow.put(key1, value1);
           
 
            allData.add(dataRow);
        }
        return allData;
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
