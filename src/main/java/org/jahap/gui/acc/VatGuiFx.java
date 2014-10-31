/*
 * The MIT License
 *
 * Copyright 2014 Open Jahap Community.
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
package org.jahap.gui.acc;

import java.net.URL;
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
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import org.apache.log4j.Logger;
import org.jahap.business.base.vattypesbean;
import org.jahap.entities.Vattype;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class VatGuiFx implements Initializable {
    
    static Logger log = Logger.getLogger(VatGuiFx.class.getName());
    
    @FXML
    private TextField vatName;
    @FXML
    private TextField vatPercentage;
    @FXML
    private Button printVat;
    @FXML
    private Button newVat;
    @FXML
    private Button saveVat;
    vattypesbean vatTypeBean;
    @FXML
    private TableView vatTable;
    private long selectedItem;
           
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        initTable();
    }    


     private void initTable(){
          log.debug("Function entry initTable");
        
        vatTypeBean  = new vattypesbean();
      
        
       ObservableList<Vattype> cc=FXCollections.observableList(vatTypeBean.SearchForVatType(null));
   
        
//         -----------------  id
        TableColumn<Vattype,String> IdCol = new TableColumn<Vattype,String>("id");
      IdCol.setCellValueFactory(new Callback<CellDataFeatures<Vattype, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Vattype, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getId());
     }
     
             
      });  
      
//     dataTable.getColumns().add(IdCol);
    
      TableColumn<Vattype,String> vatName = new TableColumn<Vattype,String>("Name");
       vatName.setCellValueFactory(new Callback<CellDataFeatures<Vattype, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Vattype, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getName());
     }
     
             
      });  
      
      vatTable.getColumns().add(vatName);
      
      
      
       TableColumn<Vattype,String> percentage = new TableColumn<Vattype,String>("%");
      percentage.setCellValueFactory(new Callback<CellDataFeatures<Vattype, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Vattype, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getPercentage());
     }
     
             
      });  
      
      vatTable.getColumns().add(percentage);
      
     
      
      
     vatTable.setItems(cc);
      log.debug("Function exit initTabel");
  }  
    
    
    
    @FXML
    private void printRoom(ActionEvent event) {
    }

    @FXML
    private void newRoom(ActionEvent event) {
    }

    @FXML
    private void saveRoom(ActionEvent event) {
    }

    @FXML
    private void TableMouseClicked(MouseEvent event) {
        Vattype j=(Vattype) vatTable.getSelectionModel().getSelectedItem();
        
        if (event.getClickCount()==2){
                   selectedItem=j.getId();
                   vatName.setText(j.getName());
                   vatPercentage.setText(String.valueOf(j.getPercentage()));
            
        }
        
        
    }
    
}
