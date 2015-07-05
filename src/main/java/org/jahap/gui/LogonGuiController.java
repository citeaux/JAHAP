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

package org.jahap.gui;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.jahap.CurrentUser;
import org.jahap.MainEventResult;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.i18n.Resourcen;
import org.jahap.i18n.ResourcenManager;

/**
 * FXML Controller class
 *
 * @author Sebastian Russ <citeaux at https://github.com/citeaux/JAHAP>
 */
public class LogonGuiController implements Initializable {
	
	static Logger log = Logger.getLogger(LogonGuiController.class.getName());
    @FXML
    private Button LoginButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField loginName;
    @FXML
    private PasswordField password;
    
    private MainEventResult mEv;
    ResourcenManager jk;
	@FXML
	private ChoiceBox<Locale> language;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
	    log.debug("Function entry initialize ");
	  Resourcen kl=new Resourcen();
	  jk=kl.getResourcenManager();
	   
	  final ObservableList<Locale> datam=FXCollections.observableArrayList(jk.getAvailableLocales());
         language.setItems(datam);
	    log.debug("Function exit initialize");
    }    

    public void init(MainEventResult mEv){
	    log.debug("Function entry init");
        
        this.mEv=mEv;
	
	    log.debug("Function exit init");
        
    }
    
    @FXML
    private void loginEvent(ActionEvent event) {
        
	    log.debug("Function entry loginEvent");
	 jk.activateLocale(language.getValue());
	    
        JahapDatabaseConnector hhh=JahapDatabaseConnector.getConnector(loginName.getText(), password.getText());
           
         Stage jimbo= (Stage) LoginButton.getScene().getWindow();
         boolean admin=false;
         if(loginName.getText().equals("root")) admin=true;
         CurrentUser hh = CurrentUser.getCurrentUser(loginName.getText(), admin);
         
         this.mEv.setDbRecordId(true, "WW");
        jimbo.close();
        log.debug("Function exit loginEvent ");
    }

    @FXML
    private void cancelAction(ActionEvent event) {
    }
    
}
