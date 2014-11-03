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
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import org.apache.log4j.Logger;
import org.jahap.business.acc.revaccountsbean;
import org.jahap.entities.Revaccounts;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class ListDialogRevAccController implements Initializable {
    static Logger log = Logger.getLogger(ListDialogRevAccController.class.getName());
    @FXML
    private Button PrintButton;
    @FXML
    private TableView dataTable;
    private List searchRevAccList;
    private long id=0;
    private revaccountsbean revAccBean;
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
                
        revAccBean = new revaccountsbean();
        searchRevAccList=revAccBean.SearchForRevAccountString();
        ObservableList<Revaccounts> data= FXCollections.observableList(searchRevAccList);
        
        // -----------------  id
        TableColumn<Revaccounts,String> IdCol = new TableColumn<Revaccounts,String>("Id");
      IdCol.setCellValueFactory(new Callback<CellDataFeatures<Revaccounts, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Revaccounts, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getId());
     }
     
             
      });  
        
     
        
        
       //----------------------------------- RevAcc Name  ----------------------- 
    
        TableColumn<Revaccounts,String> revAccName = new TableColumn<Revaccounts,String>("Name");
      revAccName.setCellValueFactory(new Callback<CellDataFeatures<Revaccounts, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Revaccounts, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getName());
     }
     
             
      });  
        
      //TableColumn<Address, String> col1 = new TableColumn<Address, String>("Name");        
    //col1.setCellValueFactory(new PropertyValueFactory<Address, String>("Name"));  
        
      
      
      dataTable.getColumns().add(revAccName);
       //dataTable.getColumns().add(col1);
      
      //------------------------------------- Name --------------------------------
      
       TableColumn<Revaccounts,String> revAccNumber = new TableColumn<Revaccounts,String>("Name");
      revAccNumber.setCellValueFactory(new Callback<CellDataFeatures<Revaccounts, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Revaccounts, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getRevaccnumber());
     }
     
             
      });
       dataTable.getColumns().add(revAccNumber);
       
       
       //---------------------------------- RevAccGroup --------------------------------
        
         TableColumn<Revaccounts,String> revAccGroup = new TableColumn<Revaccounts,String>("Street");
      revAccGroup.setCellValueFactory(new Callback<CellDataFeatures<Revaccounts, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(TableColumn.CellDataFeatures<Revaccounts, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getRev_group());
     }
     
             
      });
       dataTable.getColumns().add(revAccGroup);
       
      
        
    dataTable.setItems(data);
        log.debug("Function exit initTable");
    }

    @FXML
    private void MouseClicked(MouseEvent event) {
        
        
    }
    
    
    
    /**
     * Initializes the controller class. Call from a Address Dialog,
     * Sets selected Address ID in Oberver
     */
   
    
}
