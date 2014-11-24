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
package org.jahap.gui.res;

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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import org.apache.log4j.Logger;
import org.jahap.business.base.roomsbean;
import org.jahap.entities.base.Rooms;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class ListHousekeepingController implements Initializable {
    static Logger log = Logger.getLogger(ListHousekeepingController.class.getName());
    @FXML
    private Button PrintButton;
    @FXML
    private Button setDirty;
    @FXML
    private Button setClean;
    @FXML
    private Button blockRoom;
    @FXML
    private TableView  dataTable;
    @FXML
    private Button Ok;
    @FXML
    private Button Cancel;
    private  List<Rooms> rList;
    private roomsbean rbean;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       initTable();
    }
    
    
    void initTable(){
        log.debug("Function entry initTable");
        rbean= new roomsbean();
        rList=rbean.SearchForRooms(null);
        
         ObservableList<Rooms> data= FXCollections.observableList(rList);
        
        
        // -----------------  id
        TableColumn<Rooms,String> IdCol = new TableColumn<Rooms,String>("Id");
      IdCol.setCellValueFactory(new Callback<CellDataFeatures<Rooms, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Rooms, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getId());
     }
     
             
      });  
        
     
        
        
       //----------------------------------- roomcode ----------------------- 
    
        TableColumn<Rooms,String> roomcode = new TableColumn<Rooms,String>("Room");
     roomcode.setCellValueFactory(new Callback<CellDataFeatures<Rooms, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Rooms, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getCode());
     }
     
             
      });  
        
      //TableColumn<Address, String> col1 = new TableColumn<Address, String>("Name");        
    //col1.setCellValueFactory(new PropertyValueFactory<Address, String>("Name"));  
        
      
      
      dataTable.getColumns().add(roomcode);
       //dataTable.getColumns().add(col1);
      
      //------------------------------------- roomlocation --------------------------------
      
       TableColumn<Rooms,String> roomlocation = new TableColumn<Rooms,String>("Floor");
     roomlocation.setCellValueFactory(new Callback<CellDataFeatures<Rooms, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Rooms, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getLocation().getFloor());
     }
     
             
      });
       dataTable.getColumns().add(roomlocation);
       
       
        //------------------------------------- roomcat --------------------------------
      
       TableColumn<Rooms,String> roomcat = new TableColumn<Rooms,String>("Category");
      roomcat.setCellValueFactory(new Callback<CellDataFeatures<Rooms, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Rooms, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getCategory().getCatName());
     }
     
             
      });
       dataTable.getColumns().add( roomcat);
       
       //------------------------------------- hsk state --------------------------------
      
       TableColumn<Rooms,String> cleaningstate = new TableColumn<Rooms,String>("Cleaning state");
      cleaningstate.setCellValueFactory(new Callback<CellDataFeatures<Rooms, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Rooms, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().isClean());
     }
     
             
      });
       dataTable.getColumns().add( cleaningstate);
       
       dataTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
    dataTable.setItems(data);
        log.debug("Function exit initTable");
        
        
    }

    @FXML
    private void PrintReport(ActionEvent event) {
    }

    @FXML
    private void setDirty(ActionEvent event) {
         log.debug("Function entry blockRoom");
         ObservableList<Rooms> rms=dataTable.getSelectionModel().getSelectedItems();
        rbean.setRoomsinListdirty(rms);
        log.debug("Function exit blockroom");  
        
    }

    @FXML
    private void setClean(ActionEvent event) {
        log.debug("Function entry setClean");
        ObservableList<Rooms> rms=dataTable.getSelectionModel().getSelectedItems();
        rbean.setRoomsinListclean(rms);
        
        log.debug("Function exit setclean");
    }

    @FXML
    private void blockRoom(ActionEvent event) {
         
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
