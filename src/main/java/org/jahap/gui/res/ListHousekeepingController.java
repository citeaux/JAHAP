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

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.log4j.Logger;
import org.jahap.business.base.roomsbean;
import org.jahap.business.res.HousekeepingBean;
import org.jahap.entities.views.Housekeeping;

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
    private  List<Housekeeping> rList;
   
    private HousekeepingBean rbean;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       initTable();
    }
    
    
    void initTable(){
        log.debug("Function entry initTable");
        rbean= new HousekeepingBean();
        rList= rbean.getHousekeepingOverview();
        
         ObservableList<Housekeeping> data= FXCollections.observableList(rList);
        
        
        // -----------------  id
        TableColumn<Housekeeping,String> IdCol = new TableColumn<Housekeeping,String>("Id");
      IdCol.setCellValueFactory(new Callback<CellDataFeatures<Housekeeping, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Housekeeping, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getId());
     }
     
             
      });  
        
     
        
        
       //----------------------------------- roomcode ----------------------- 
    
        TableColumn<Housekeeping,String> roomcode = new TableColumn<Housekeeping,String>("Room");
     roomcode.setCellValueFactory(new Callback<CellDataFeatures<Housekeeping, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Housekeeping, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getCode());
     }
     
             
      });  
        
      //TableColumn<Address, String> col1 = new TableColumn<Address, String>("Name");        
    //col1.setCellValueFactory(new PropertyValueFactory<Address, String>("Name"));  
        
      
      
      dataTable.getColumns().add(roomcode);
       //dataTable.getColumns().add(col1);
      
      //------------------------------------- roomlocation --------------------------------
      
       TableColumn<Housekeeping,String> roomlocation = new TableColumn<Housekeeping,String>("Floor");
     roomlocation.setCellValueFactory(new Callback<CellDataFeatures<Housekeeping, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Housekeeping, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getFloor());
     }
     
             
      });
       dataTable.getColumns().add(roomlocation);
       
       
        //------------------------------------- roomcat --------------------------------
      
       TableColumn<Housekeeping,String> roomcat = new TableColumn<Housekeeping,String>("Category");
      roomcat.setCellValueFactory(new Callback<CellDataFeatures<Housekeeping, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Housekeeping, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getCatName());
     }
     
             
      });
       dataTable.getColumns().add( roomcat);
       
       //------------------------------------- hsk state --------------------------------
      
       TableColumn<Housekeeping,String> cleaningstate = new TableColumn<Housekeeping,String>("Cleaning state");
      cleaningstate.setCellValueFactory(new Callback<CellDataFeatures<Housekeeping, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Housekeeping, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getClean());
     }
     
             
      });
       dataTable.getColumns().add(cleaningstate);
      
      //------------------------------------- hsk blocks --------------------------------
      
       TableColumn<Housekeeping,String> blocks = new TableColumn<Housekeeping,String>("Blocks");
      blocks.setCellValueFactory(new Callback<CellDataFeatures<Housekeeping, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Housekeeping, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getBlocks());
     }
     
             
      });
       dataTable.getColumns().add( blocks);
       
       dataTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
    dataTable.setItems(data);
        log.debug("Function exit initTable");
        
        
    }

    @FXML
    private void PrintReport(ActionEvent event) {
    }

    @FXML
    private void setDirty(ActionEvent event) {
         log.debug("Function entry setDirty");
         ObservableList<Housekeeping> rms=dataTable.getSelectionModel().getSelectedItems();
	  roomsbean jj=new roomsbean();
       jj.setRoomsinListdirtyHSK(rms);
        log.debug("Function exit setDirty");  
        
    }

    @FXML
    private void setClean(ActionEvent event) {
        log.debug("Function entry setClean");
        ObservableList<Housekeeping> rms=dataTable.getSelectionModel().getSelectedItems();
        roomsbean jj=new roomsbean();
	jj.setRoomsinListcleanHSK(rms);
        
        log.debug("Function exit setclean");
    }

    @FXML
    private void blockRoom(ActionEvent event) throws IOException {
	    log.debug("Function entry blockRoom");
	     Housekeeping rms=(Housekeeping) dataTable.getSelectionModel().getSelectedItem();
	     Stage stage = new Stage();
        String fxmlFile = "/fxml/HousekeepingGuiFx.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
       

        
        stage.setScene(scene);
        housekeepinggui controller;
		controller= loader.<housekeepinggui>getController();
       controller.init(rms.getId(),true);
       
        
        stage.showAndWait();
         log.debug("Function exit blockRoom");  
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
