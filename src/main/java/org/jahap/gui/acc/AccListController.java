/*
 * The MIT License
 *
 * Copyright 2014 Sebastian Russ <citeaux at https://github.com/citeaux/JAHAP>.
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.log4j.Logger;
import org.jahap.business.acc.AccountInfo;
import org.jahap.business.acc.accountsbean;

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
     private  List ll;
    
  static Logger log = Logger.getLogger(AccListController.class.getName());
     
 
    
    private  accountsbean accbean;
	@FXML
	private Button newJob;
	@FXML
	private Button deleteJob;
	@FXML
	private Button editJob;
      
              
    
       private void initTable(){
          log.debug("Function entry initTable");
        
        accbean  = new accountsbean();
      
        ll=accbean.getAccOverview();
       ObservableList<AccountInfo> cc=FXCollections.observableList(ll);
   
        
//         -----------------  id
        TableColumn<AccountInfo,String> IdCol = new TableColumn<AccountInfo,String>("id");
      IdCol.setCellValueFactory(new Callback<CellDataFeatures<AccountInfo, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<AccountInfo, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getId());
     }
     
             
      });  
      
//     dataTable.getColumns().add(IdCol);
    
      TableColumn<AccountInfo,String> arrival = new TableColumn<AccountInfo,String>("Arrivaldate");
      arrival.setCellValueFactory(new Callback<CellDataFeatures<AccountInfo, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<AccountInfo, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getArrivaldate());
     }
     
             
      });  
      
      dataTable.getColumns().add(arrival);
      
       TableColumn<AccountInfo,String> departure = new TableColumn<AccountInfo,String>("departuredate");
      departure.setCellValueFactory(new Callback<CellDataFeatures<AccountInfo, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<AccountInfo, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getDeparturedate());
     }
     
             
      });  
      
      dataTable.getColumns().add(departure);
      
       TableColumn<AccountInfo,String> name = new TableColumn<AccountInfo,String>("name");
      name.setCellValueFactory(new Callback<CellDataFeatures<AccountInfo, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<AccountInfo, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getName());
     }
     
             
      });  
      
      dataTable.getColumns().add(name);
      
      
        TableColumn<AccountInfo,String> balance = new TableColumn<AccountInfo,String>("Balance");
      balance.setCellValueFactory(new Callback<CellDataFeatures<AccountInfo, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<AccountInfo, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getBalance());
     }
     
             
      });  
      
      dataTable.getColumns().add(balance);
      
      
      dataTable.setItems(cc);
      log.debug("Function exit initTabel");
  }  
    
       
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
          log.debug("Function enter initialize");
        
        
        
       
        
        initTable();
        log.debug("Function exit initialize");
    }    

    @FXML
    private void PrintReport(ActionEvent event) {
    }

    @FXML
    private void MouseClicked(MouseEvent event) throws IOException {
        log.debug("Function enter MouseClicked");
        AccountInfo Ai=(AccountInfo) dataTable.getSelectionModel().getSelectedItem();
        int id;
        id= Integer.valueOf(Ai.getId());
        Stage stage = new Stage();
        String fxmlFile = "/fxml/simpelAccounting.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
       

        
        stage.setScene(scene);
        SimpelAccountingController controller= loader.<SimpelAccountingController>getController();
       controller.init(id);
       
      
        stage.showAndWait();
        log.debug("Function exit MouseClicked");
    }

    @FXML
    private void OkAction(ActionEvent event) {
    }

    @FXML
    private void CancelAction(ActionEvent event) {
    }

	@FXML
	private void newJob(ActionEvent event) {
	}

	@FXML
	private void deleteJob(ActionEvent event) {
	}

	@FXML
	private void editJob(ActionEvent event) {
	}
    
}
